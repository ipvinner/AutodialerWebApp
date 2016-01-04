"use strict";
let inst = new addCSVList({
    "componentRoot": document.querySelector("[data-component='userUploadCSVFile']"),
    "btnFile": document.getElementById("uploadFile"),
    "btnRenderTable": document.getElementById("renderTable"),
    "btnSubmit": document.getElementById("submitData"),
    "typeFields": [
        {
            "id": "name",
            "title": "Имя"
        },
        {
            "id": "lname",
            "title": "Фамилия"
        },
        {
            "id": "phoneNumber",
            "title": "Номер Телефона"
        },
        {
            "id": "email",
            "title": "Почтовый адресс"
        }
    ]
});

let dialog = new Dialog(
    {
        "component": "dialog",
        "templateId": "dialog-edit-row"
    }
);