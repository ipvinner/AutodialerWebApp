"use strict";
if(document.querySelector("[data-component='userUploadCSVFile']")) {
    let inst = new uploadData({
        "url": "ajax/admin/clients/addClientsList",
        "componentRoot": document.querySelector("[data-component='userUploadCSVFile']"),
        "btnFile": document.getElementById("uploadFile"),
        "btnRenderTable": document.getElementById("renderTable"),
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

if(document.querySelector("[data-component='dialog']")) {
    let dialog = new Dialog(
        {
            "component": "dialog",
            "templateId": "dialog-edit-row"
        }
    );
}

if(~~!window.location.search.substr(1).indexOf("saved")) {
    noty({
        text: 'Список был сохранён!'
    });
}