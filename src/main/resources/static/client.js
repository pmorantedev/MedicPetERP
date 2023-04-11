function formEditable() {
    var form = document.getElementById("clientForm");
    var elements = form.elements;
    for (var i = 0, len = elements.length; i < len; ++i) {
        elements[i].disabled = false;
    }
    document.getElementById('nomComplert').style = "background-color: white;";
    document.getElementById('dni').style = "background-color: white;";
    document.getElementById('email').style = "background-color: white;";
    document.getElementById('telefon').style = "background-color: white;";
    document.getElementById('adreça').style = "background-color: white;";
}

function mostrar() {
    div = document.getElementById('btns_edicio');
    div.style.display = '';
    div = document.getElementById('btn_editar');
    div.style.display = 'none';

    formEditable();
}

function validar() {
    var valid = true;
    var nomComplert = document.getElementById('nomComplert').value;
    var nomComplertError = document.getElementById('error-nom');
    var nomBuitError = document.getElementById('errorBuit-nom');

    var dni = document.getElementById('dni').value;
    var dniError = document.getElementById('error-dni');
    var dniBuitError = document.getElementById('errorBuit-dni');

    var telefon = document.getElementById('telefon').value;
    var telefonErrorLlarg = document.getElementById('error-telefonLlarg');
    var telefonErrorCar = document.getElementById('error-telefonCar');
    var telefonBuitError = document.getElementById('errorBuit-telefon');

    var email = document.getElementById('email').value;
    var emailError = document.getElementById('error-email');
    var emailBuitError = document.getElementById('errorBuit-email');

    var address = document.getElementById('address').value;
    var addressError = document.getElementById('error-address');
    var addressBuitError = document.getElementById('errorBuit-address');

    if (nomComplert.length > 45) {
        valid = false;
        nomComplertError.style.display = 'block';
        nomBuitError.style.display = 'none';
        document.getElementById('nomComplert').classList.add('border-danger');
    } else if (nomComplert.trim() === '' || nomComplert.length === 0) {
        valid = false;
        nomComplertError.style.display = 'none';
        nomBuitError.style.display = 'block';
        document.getElementById('nomComplert').classList.add('border-danger');
    } else {
        nomComplertError.style.display = 'none';
        nomBuitError.style.display = 'none';
        document.getElementById('nomComplert').classList.remove('border-danger');
    }

    if (dni.length > 9) {
        valid = false;
        dniError.style.display = 'block';
        dniBuitError.style.display = 'none';
        document.getElementById('dni').classList.add('border-danger');
    } else if (dni.trim() === '' || dni.length === 0) {
        valid = false;
        dniError.style.display = 'none';
        dniBuitError.style.display = 'block';
        document.getElementById('dni').classList.add('border-danger');
    } else {
        dniError.style.display = 'none';
        dniBuitError.style.display = 'none';
        document.getElementById('dni').classList.remove('border-danger');
    }

    if (email.length > 45) {
        valid = false;
        emailError.style.display = 'block';
        emailBuitError.style.display = 'none';
        document.getElementById('email').classList.add('border-danger');
    } else if (email.trim() === '' || email.length === 0) {
        valid = false;
        emailError.style.display = 'none';
        emailBuitError.style.display = 'block';
        document.getElementById('email').classList.add('border-danger');
    } else {
        emailError.style.display = 'none';
        emailBuitError.style.display = 'none';
        document.getElementById('email').classList.remove('border-danger');
    }

    if (address.length > 100) {
        valid = false;
        addressError.style.display = 'block';
        addressBuitError.style.display = 'none';
        document.getElementById('address').classList.add('border-danger');
    } else if (address.trim() === '' || address.length === 0) {
        valid = false;
        addressError.style.display = 'none';
        addressBuitError.style.display = 'block';
        document.getElementById('address').classList.add('border-danger');
    } else {
        addressError.style.display = 'none';
        addressBuitError.style.display = 'none';
        document.getElementById('address').classList.remove('border-danger');
    }

    if (telefon.trim() === '' || telefon.length === 0) {
        valid = false;
        telefonErrorLlarg.style.display = 'none';
        telefonErrorCar.style.display = 'none';
        telefonBuitError.style.display = 'block';
        document.getElementById('telefon').classList.add('border-danger');
    } else if (!/^[+\d]+$/.test(telefon)) {
        valid = false;
        telefonErrorCar.style.display = 'block';
        telefonBuitError.style.display = 'none';
        telefonErrorLlarg.style.display = 'none';
        document.getElementById('telefon').classList.add('border-danger');
    } else if (telefon.length > 15) {
        valid = false;
        telefonErrorLlarg.style.display = 'block';
        telefonErrorCar.style.display = 'none';
        telefonBuitError.style.display = 'none';
        document.getElementById('telefon').classList.add('border-danger');
    } else {
        telefonErrorLlarg.style.display = 'none';
        telefonErrorCar.style.display = 'none';
        telefonBuitError.style.display = 'none';
        document.getElementById('telefon').classList.remove('border-danger');
    }


    return valid;
}