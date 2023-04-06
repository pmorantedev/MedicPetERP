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

    var dni = document.getElementById('dni').value;
    var dniError = document.getElementById('error-dni');

    var telefon = document.getElementById('telefon').value;
    var telefonErrorLlarg = document.getElementById('error-telefonLlarg');
    var telefonErrorCar = document.getElementById('error-telefonCar');

    var email = document.getElementById('email').value;
    var emailError = document.getElementById('error-email');

    var address = document.getElementById('address').value;
    var addressError = document.getElementById('error-address');

    if (nomComplert.length > 45) {
        valid = false;
        nomComplertError.style.display = 'block';
        document.getElementById('nomComplert').classList.add('border-danger');
    } else {
        nomComplertError.style.display = 'none';
        document.getElementById('nomComplert').classList.remove('border-danger');
    }

    if (dni.length > 9) {
        valid = false;
        dniError.style.display = 'block';
        document.getElementById('dni').classList.add('border-danger');
    } else {
        dniError.style.display = 'none';
        document.getElementById('dni').classList.remove('border-danger');
    }

    if (email.length > 45) {
        valid = false;
        emailError.style.display = 'block';
        document.getElementById('email').classList.add('border-danger');
    } else {
        emailError.style.display = 'none';
        document.getElementById('email').classList.remove('border-danger');
    }

    if (address.length > 100) {
        valid = false;
        addressError.style.display = 'block';
        document.getElementById('address').classList.add('border-danger');
    } else {
        addressError.style.display = 'none';
        document.getElementById('address').classList.remove('border-danger');
    }

    if (!/^[+\d]+$/.test(telefon)) {
        valid = false;
        telefonErrorCar.style.display = 'block';
        document.getElementById('telefon').classList.add('border-danger');
    } else if (telefon.length > 15) {
        valid = false;
        telefonErrorLlarg.style.display = 'block';
        telefonErrorCar.style.display = 'none';
        document.getElementById('telefon').classList.add('border-danger');
    } else {
        telefonErrorLlarg.style.display = 'none';
        telefonErrorCar.style.display = 'none';
        document.getElementById('telefon').classList.remove('border-danger');
    }


    return valid;
}