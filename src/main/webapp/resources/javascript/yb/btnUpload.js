"use strict";

class btnUpload {
    constructor(options) {
        this.options = options | {};

        this.root = document.querySelector('[data-component="btn-file-upload"]');
        this.elements = {
            btn: this.root.querySelector('[data-button]'),
            file: this.root.querySelector('[data-file]'),
            fileName: this.root.querySelector('[data-file-name]'),
            fileSize: this.root.querySelector('[data-file-size]')
        }

        this._eventHandler();
    };

    _eventHandler() {
        this.root.addEventListener("change", this._change.bind(this));
        this.elements.btn.addEventListener("keypress", this._eventKeyBoard.bind(this));
    };

    _change(event) {
        let file = event.target.files[0];
        let fileName = file.name;
        let fileSize = file.size;
        let fileWeight = ['b', "kb", "mb", "gb"];
        let fileUnit = null;

        if(fileSize < 1000) {
            fileUnit = fileWeight[0];
        } else if(fileSize > 1000 && fileSize < 1000 * 1000){
            fileUnit = fileWeight[1];
            fileSize = fileSize/1000;
        } else if(fileSize > 1000 * 1000 && fileSize < 1000 * 1000 * 1000){
            fileUnit = fileWeight[2];
            fileSize = fileSize/(1000*1000);
        } else {
            fileUnit = fileWeight[3];
            fileSize = fileSize/(1000*1000*1000);
        }

        this.elements.fileName.innerHTML = fileName;
        this.elements.fileSize.innerHTML = "(" + (fileSize + fileUnit) + ")";

        // reset choosen file
        event.target.value = '';
    };

    _eventKeyBoard(event) {
        if(event.keyCode === 32 || event.keyCode === 13) {
            this.elements.file.click();
        }
    }
}