function validar() {
    var valid = true;
    var username = document.getElementById('username').value;
    var usernameError = document.getElementById('error-username');

    var name = document.getElementById('name').value;
    var nameError = document.getElementById('error-nom');

    if (username.length > 45) {
        valid = false;
        usernameError.style.display = 'block';
        document.getElementById('username').classList.add('border-danger');
    } else {
        usernameError.style.display = 'none';
        document.getElementById('username').classList.remove('border-danger');
    }

    if (name.length > 45) {
        valid = false;
        nameError.style.display = 'block';
        document.getElementById('name').classList.add('border-danger');
    } else {
        nameError.style.display = 'none';
        document.getElementById('name').classList.remove('border-danger');
    }

    return valid;
}