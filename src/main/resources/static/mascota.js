function formEditable() {

    var form = document.getElementById("mascotaForm");
    console.log(">> Habilitar formulari: ", form);
    var elements = form.elements;
    for (var i = 0, len = elements.length; i < len; ++i) {
        elements[i].disabled = false;
    }
    document.getElementById('nom').style = "background-color: white;";
    document.getElementById('dataNaixement').style = "background-color: white;";
    document.getElementById('sexeMascota').style = "background-color: white;";
    document.getElementById('especie').style = "background-color: white;";
    document.getElementById('raça').style = "background-color: white;";
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
    var nom = document.getElementById('nom').value;
    var nomError = document.getElementById('error-nom');

    var especie = document.getElementById('especie').value;
    var especieError = document.getElementById('error-especie');

    var raça = document.getElementById('raça').value;
    var raçaError = document.getElementById('error-raça');

    var data = document.getElementById('dataNaixement').value;
    var dataError = document.getElementById('error-data');

    var sexe = document.getElementById('sexeMascota').value;
    var sexeError = document.getElementById('error-sexe');

    if (nom.length > 45) {
        valid = false;
        nomError.style.display = 'block';
        document.getElementById('nom').classList.add('border-danger');
    } else {
        nomError.style.display = 'none';
        document.getElementById('nom').classList.remove('border-danger');
    }

    if (especie.length > 45) {
        valid = false;
        especieError.style.display = 'block';
        document.getElementById('especie').classList.add('border-danger');
    } else {
        especieError.style.display = 'none';
        document.getElementById('especie').classList.remove('border-danger');
    }

    if (raça.length > 45) {
        valid = false;
        raçaError.style.display = 'block';
        document.getElementById('raça').classList.add('border-danger');
    } else {
        raçaError.style.display = 'none';
        document.getElementById('raça').classList.remove('border-danger');
    }

    if (data === '') {
        valid = false;
        dataError.style.display = 'block';
        document.getElementById('dataNaixement').classList.add('border-danger');
    } else {
        dataError.style.display = 'none';
        document.getElementById('dataNaixement').classList.remove('border-danger');
    }

    if (sexe === '') {
        valid = false;
        sexeError.style.display = 'block';
        document.getElementById('sexeMascota').classList.add('border-danger');
    } else {
        sexeError.style.display = 'none';
        document.getElementById('sexeMascota').classList.remove('border-danger');
    }

    return valid;
}