function validarVisita() {

    var valid = true;
    var treballador = document.getElementById('treballador').value;
    var treballadorError = document.getElementById('error-veterinari');
    var data = document.getElementById('dataVisita').value;
    var dataError = document.getElementById('error-data');

    if (treballador.trim() === '' || treballador.length === 0) {
        treballadorError.style.display = 'block';
        document.getElementById('treballador').classList.add('border-danger');
        valid = false;
    } else {
        treballadorError.style.display = 'none';
        document.getElementById('treballador').classList.remove('border-danger');
    }

    if (!/^\d{4}-\d{2}-\d{2}$/.test(data)) {
        valid = false;
        dataError.style.display = 'block';
        document.getElementById('dataVisita').classList.add('border-danger');
    } else {
        dataError.style.display = 'none';
        document.getElementById('dataVisita').classList.remove('border-danger');
    }

    return valid;
}

function formEditable() {

    var form = document.getElementById("visitaForm");
    var elements = form.elements;
    for (var i = 0, len = elements.length; i < len; ++i) {
        elements[i].disabled = false;
    }
    document.getElementById('diagnostic').disabled = true;
    document.getElementById('medicacio').disabled = true;
    document.getElementById('dataVisita').style = "background-color: white;";
    document.getElementById('treballador').style = "background-color: white;";
    document.getElementById('nomMascota').disabled = true;
    document.getElementById('diagnostic').disabled = false;
    document.getElementById('medicacio').disabled = false;
    document.getElementById('diagnostic').style = "background-color: white;";
    document.getElementById('medicacio').style = "background-color: white;";
}

function mostrar() {

    div = document.getElementById('btns_edicio');
    div.style.display = '';
    div = document.getElementById('btn_editar');
    div.style.display = 'none';

    formEditable();
}