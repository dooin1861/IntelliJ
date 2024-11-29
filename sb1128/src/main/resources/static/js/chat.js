let stompClient = null;
let username;
let isFirstConnect = true;

document.addEventListener('DOMContentLoaded', function() {
    console.log('페이지 로드됨');
    username = document.getElementById('username').value;
    console.log('username:', username);

    connect();

    // 전송 버튼 클릭 이벤트
    document.getElementById('sendButton').addEventListener('click', function() {
        console.log('전송 버튼 클릭됨');
        sendMessage();
    });

    // Enter 키 이벤트
    document.getElementById('message').addEventListener('keydown', function(e) {
        if (e.key === 'Enter') {
            // Shift + Enter인 경우 줄바꿈
            if (e.shiftKey) {
                return; // 기본 줄바꿈 동작 허용
            } else {
                // Enter만 누른 경우 메시지 전송
                e.preventDefault(); // 기본 줄바꿈 동작 방지
                sendMessage();
            }
        }
    });

    // 페이지 언로드(브라우저 닫기, 새로고침 등) 감지
    window.addEventListener('beforeunload', function(e) {
        if (stompClient && stompClient.connected) {
            let leaveMessage = {
                sender: username,
                type: 'LEAVE',
                content: username + '님이 퇴장하셨습니다.'
            };

            // 동기적으로 메시지 전송
            navigator.sendBeacon("/app/chat.leave/notify", JSON.stringify(leaveMessage));

            // WebSocket 연결 해제
            stompClient.disconnect();
        }
    });
});

function connect() {
    console.log('connect 함수 호출됨');
    let socket = new SockJS('/ws-chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        console.log('WebSocket 연결 성공:', frame);

        // 구독 설정
        stompClient.subscribe('/topic/public', function(message) {
            console.log('메시지 수신:', message.body);
            showMessage(JSON.parse(message.body));
        });

        // 채팅 기록은 첫 연결시에만 불러오기
        if (isFirstConnect) {
            loadChatHistory();

            // 입장 메시지도 첫 연결시에만 전송
            setTimeout(() => {
                let joinMessage = {
                    sender: username,
                    type: 'JOIN',
                    content: username + '님이 입장하셨습니다.'
                };
                console.log('입장 메시지 전송:', joinMessage);
                stompClient.send("/app/chat.join", {}, JSON.stringify(joinMessage));
            }, 500);

            isFirstConnect = false;
        }

    }, function(error) {
        console.error('WebSocket 연결 실패:', error);
        setTimeout(connect, 5000);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    isFirstConnect = true;
    console.log("연결 해제됨");
}

function loadChatHistory() {
    fetch('/api/chat/history')
        .then(response => response.json())
        .then(messages => {
            messages.reverse().forEach(message => {
                showMessage(message);
            });
        })
        .catch(error => console.error('채팅 기록 로드 실패:', error));
}

function sendMessage() {
    let messageInput = document.getElementById('message');
    let messageContent = messageInput.value.trim();

    console.log('메시지 전송 시도:', messageContent);

    if(messageContent && stompClient) {
        let chatMessage = {
            sender: username,
            content: messageContent,
            type: 'CHAT'
        };

        console.log('전송할 메시지:', chatMessage);
        stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
        console.log('메시지 전송 요청 완료');

        messageInput.value = '';
    }
}

function showMessage(message) {
    console.log('showMessage 호출됨:', message);

    let messageArea = document.getElementById('messageArea');
    let messageElement = document.createElement('div');
    messageElement.classList.add('message');

    if(message.type === 'JOIN' || message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        messageElement.innerHTML = `
            <div class="message-content">${message.content}</div>
        `;
    } else {
        const isSentByMe = message.originalSender === username;
        console.log('메시지 비교:', {
            originalSender: message.originalSender,
            currentUsername: username,
            isSentByMe: isSentByMe
        });

        messageElement.classList.add(isSentByMe ? 'sent' : 'received');

        let time = message.sendTime ? formatTime(message.sendTime) : formatTime(new Date());

        messageElement.innerHTML = `
            <div class="message-content">${message.content}</div>
            <div class="message-info">
                <span class="sender">${message.sender}</span>
                <span class="time">${time}</span>
            </div>
        `;
    }

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function formatTime(dateStr) {
    const date = new Date(dateStr);
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const hours = date.getHours();
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const ampm = hours >= 12 ? '오후' : '오전';
    const displayHours = hours > 12 ? hours - 12 : hours;

    return `${month}월 ${day}일 ${ampm} ${displayHours}:${minutes}`;
}