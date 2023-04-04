function mostrar() {
    div = document.getElementById('btns_edicio');
    div.style.display = '';
    div = document.getElementById('btn_editar');
    div.style.display = 'none';
    document.getElementById('username').disabled = true;
    document.getElementById('nom').disabled = false;
    document.getElementById('nom').style = "background-color: white;";
}

function mostrarContrasenya() {
    div = document.getElementById('btns_edicioCon');
    div.style.display = '';
    div = document.getElementById('btn_editarCon');
    div.style.display = 'none';
    document.getElementById('contraActual').disabled = false;
    document.getElementById('contraActual').style = "background-color: white;";
    document.getElementById('contraNova').disabled = false;
    document.getElementById('contraNova').style = "background-color: white;";
    document.getElementById('contraNovaConf').disabled = false;
    document.getElementById('contraNovaConf').style = "background-color: white;";
}

function checkPassword() {
    var contra1 = document.getElementById('contraNova').value;
    var contra2 = document.getElementById('contraNovaConf').value;
    var errorContra1 = document.getElementById('contra1-error');
    var errorContra2 = document.getElementById('contra2-error');

    if (contra1 !== contra2) {
        errorContra1.style.display = 'block';
        errorContra2.style.display = 'block';
        document.getElementById("submitContra").disabled = true;
    } else {
        errorContra1.style.display = 'none';
        errorContra2.style.display = 'none';
        document.getElementById("submitContra").disabled = false;
    }
}

function previewImage(event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function () {
        var image = document.getElementById("temp_image");
        image.src = reader.result;
    };
    reader.readAsDataURL(input.files[0]);

    div = document.getElementById('btns_edicioImg');
    div.style.display = 'block';
}

function validar() {
    var valid = true;

    var name = document.getElementById('nom').value;
    var nameError = document.getElementById('error-nom');

    if (name.length > 45) {
        valid = false;
        nameError.style.display = 'block';
        document.getElementById('nom').classList.add('border-danger');
    } else {
        nameError.style.display = 'none';
        document.getElementById('nom').classList.remove('border-danger');
    }

    return valid;
}

