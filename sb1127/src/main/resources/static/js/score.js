// 전역 변수
let currentPosition = 0;
const MAX_POSITION = 32;

// 음표 기호 매핑
const NOTE_SYMBOLS = {
    '1n': '𝅝', // 온음표
    '2n': '𝅗𝅥', // 2분음표
    '4n': '♩', // 4분음표
    '8n': '♪', // 8분음표
    '16n': '♬' // 16분음표
};

function addNote() {
    const string = document.getElementById('string').value;
    const fret = document.getElementById('fret').value;
    const duration = document.getElementById('duration').value;

    if (!string || !fret) {
        alert('줄과 프렛 번호를 모두 입력해주세요.');
        return;
    }

    // 해당 줄의 탭 라인 찾기
    const tabLine = document.getElementById(`line-${string}`);
    if (!tabLine) {
        console.error('탭 라인을 찾을 수 없습니다.');
        return;
    }

    // 현재 탭 내용을 배열로 변환
    let content = tabLine.textContent.split('');

    // 프렛 번호와 음표 기호 추가
    const noteSymbol = NOTE_SYMBOLS[duration] || '';

    // 프렛 번호가 한 자리인 경우
    if (fret.length === 1) {
        content[currentPosition] = fret;
        content[currentPosition + 1] = noteSymbol;
    } else {
        // 프렛 번호가 두 자리인 경우
        content[currentPosition] = fret[0];
        content[currentPosition + 1] = fret[1];
        if (currentPosition + 2 < MAX_POSITION) {
            content[currentPosition + 2] = noteSymbol;
        }
    }

    // 업데이트된 내용을 탭 라인에 적용
    tabLine.textContent = content.join('');

    // 다음 위치로 이동 (음표 기호 포함하여 3칸씩)
    currentPosition = (currentPosition + 3) % MAX_POSITION;

    // 음표 정보를 화면에 표시
    updateNoteDisplay(string, fret, duration);
}

function updateNoteDisplay(string, fret, duration) {
    const noteInfo = document.createElement('div');
    noteInfo.className = 'note-info';
    noteInfo.innerHTML = `
        ${string}번 줄, ${fret}프렛 
        <span class="note-symbol">${NOTE_SYMBOLS[duration]}</span>
    `;

    const noteDisplay = document.getElementById('note-display');
    if (noteDisplay) {
        noteDisplay.appendChild(noteInfo);
    }
}

function clearTab() {
    for (let i = 1; i <= 6; i++) {
        const tabLine = document.getElementById(`line-${i}`);
        if (tabLine) {
            tabLine.textContent = '-'.repeat(MAX_POSITION);
        }
    }
    currentPosition = 0;

    // 음표 표시 초기화
    const noteDisplay = document.getElementById('note-display');
    if (noteDisplay) {
        noteDisplay.innerHTML = '';
    }
}

// CSS 스타일 추가
const style = document.createElement('style');
style.textContent = `
    .note-info {
        display: inline-block;
        margin: 5px;
        padding: 5px 10px;
        background-color: #f0f0f0;
        border-radius: 4px;
        font-family: 'Arial', sans-serif;
    }
    .note-symbol {
        font-size: 1.2em;
        margin-left: 5px;
    }
`;
document.head.appendChild(style);