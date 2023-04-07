function validar() {
    var valid = true;
    var username = document.getElementById('username').value;
    var usernameError = document.getElementById('error-username');
    var usernameError2 = document.getElementById('errorBuit-username')

    var name = document.getElementById('name').value;
    var nameError = document.getElementById('error-nom');
    var nameError2 = document.getElementById('errorBuit-nom')

    var password = document.getElementById('password').value;
    var passError = document.getElementById('error-password');

    if (username.length > 45) {
        valid = false;
        usernameError.style.display = 'block';
        document.getElementById('username').classList.add('border-danger');
    } else if (username.trim() === '' || username.length === 0) {
        valid = false;
        usernameError.style.display = 'none';
        usernameError2.style.display = 'block';
        document.getElementById('username').classList.add('border-danger');
    } else {
        usernameError.style.display = 'none';
        document.getElementById('username').classList.remove('border-danger');
    }

    if (name.length > 45) {
        valid = false;
        nameError.style.display = 'block';
        document.getElementById('name').classList.add('border-danger');
    } else if (name.trim() === '' || name.length === 0) {
        valid = false;
        nameError.style.display = 'none';
        nameError2.style.display = 'block';
        document.getElementById('name').classList.add('border-danger');
    } else {
        nameError.style.display = 'none';
        document.getElementById('name').classList.remove('border-danger');
    }

    if (password.trim() === '' || password.length === 0) {
        valid = false;
        passError.style.display = 'block';
        document.getElementById('password').classList.add('border-danger');
    } else {
        passError.style.display = 'none';
        document.getElementById('password').classList.remove('border-danger');
    }

    return valid;
}

window.onload = function () {
    var select = document.getElementById("rol_usuari");
    select.oninvalid = function (event) {
        event.target.setCustomValidity("Seleccionar un rol és obligatori");
        select.classList.add('border-danger');
        validar();
    };
    select.oninput = function (event) {
        select.classList.remove('border-danger');
        event.target.setCustomValidity("");
    };
};
