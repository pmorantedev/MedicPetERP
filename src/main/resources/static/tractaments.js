function formEditable() {
    var form = document.getElementById("tractamentForm");
    var elements = form.elements;
    for (var i = 0, len = elements.length; i < len; ++i) {
        elements[i].disabled = false;
    }
    document.getElementById('nom').style = "background-color: white;";
    document.getElementById('preu').style = "background-color: white;";
}

function mostrar() {
    div = document.getElementById('btns_edicio');
    div.style.display = '';
    div = document.getElementById('btn_editar');
    div.style.display = 'none';
    formEditable();
}

function validar() {
    var preu = document.getElementById('preu').value;
    var error = document.getElementById('preu-error');
    var error2 = document.getElementById('preu-lletres');

    var nom = document.getElementById('nom').value;
    var errorNom = document.getElementById('error-nom');
    var errorNomBuit = document.getElementById('error-buit');

    var formulariNotOk = 0;

    if (nom.length > 45) {
        errorNom.style.display = 'block';
        errorNomBuit.style.display = 'none';
        document.getElementById('nom').classList.add('border-danger');
        formulariNotOk++;
    } else if (nom.trim() === '' || nom.length === 0) {
        errorNomBuit.style.display = 'block';
        errorNom.style.display = 'none';
        document.getElementById('nom').classList.add('border-danger');
        formulariNotOk++;
    } else {
        errorNom.style.display = 'none';
        errorNomBuit.style.display = 'none';
        document.getElementById('nom').classList.remove('border-danger');

    }

    if (preu <= 0) {
        error.style.display = 'block';
        error2.style.display = 'none';
        document.getElementById('preu').classList.add('border-danger');
        formulariNotOk++;
    } else if (isNaN(preu)) {
        error2.style.display = 'block';
        error.style.display = 'none';
        document.getElementById('preu').classList.add('border-danger');
        formulariNotOk++;
    } else {
        error.style.display = 'none';
        error2.style.display = 'none';
        document.getElementById('preu').classList.remove('border-danger');
    }

    if (formulariNotOk == 0) {
        return true;
    } else {
        return false;
    }
}