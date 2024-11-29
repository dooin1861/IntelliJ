function showRegisterForm() {
    document.querySelector('.login-form').parentElement.style.display = 'none';
    document.querySelector('.register-box').style.display = 'block';
}

function showLoginForm() {
    document.querySelector('.login-form').parentElement.style.display = 'block';
    document.querySelector('.register-box').style.display = 'none';
}

// 에러 메시지 표시
document.addEventListener('DOMContentLoaded', function() {
    const error = /*[[${error}]]*/ null;
    const message = /*[[${message}]]*/ null;

    if (error) {
        alert(error);
    }
    if (message) {
        alert(message);
    }
});