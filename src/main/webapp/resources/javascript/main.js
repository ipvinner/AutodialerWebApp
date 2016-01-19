"use strict";
if(document.querySelector("[data-component='uploadClientList']")) {
    let inst = new uploadClientList({
        "url": "ajax/admin/clients/addClientsList",
        "componentRoot": document.querySelector("[data-component='uploadClientList']"),
        "btnFile": document.getElementById("uploadFile"),
        "btnSubmit": document.getElementById("submitData"),
        "typeFields": [
            {
                "id": "firstName",
                "title": "Имя"
            },
            {
                "id": "lastName",
                "title": "Фамилия"
            },
            {
                "id": "email",
                "title": "Почтовый адресс"
            },
            {
                "id": "phoneNumber",
                "title": "Номер Телефона"
            }
        ]
    });
}

if(~~!window.location.search.substr(1).indexOf("saved")) {
    noty({
        text: 'Список был сохранён!',
        type: 'success',
        timeout: 3000
    });
}

// Modal
if($('#dialog-modal').length) {

    document.body.addEventListener('click', dialogInit);

    function dialogInit(event) {
        if(event.target.dataset.component !== 'dialog') return;

        let dialog = new Dialog(
            {
                "event": event,
                "component": "dialog",
                "templateId": "dialog-modal-tpl"
            }
        );
    };

    $('#dialog-modal').on('hidden.bs.modal', function (e) {
        $(this).find(".modal-content").empty();
    });
}

if( $('[data-component="btn-file-upload"]').length ) {

    let inst = new btnUpload({
        component: 'btn-file-upload'
    });
}