/**
 * Upload data to server
 * need data: title, description, csv-file
 */

"use strict";
class uploadClientList {
    constructor(options) {
        this.options = {};
        this.options.tableRender = false;
        /**
         * Dom elements
         */
        this._componentRoot = options.componentRoot,
        this._btnFile = options.btnFile;
        this._btnSubmit = options.btnSubmit;

        /**
         * Input data typeFields
         * @type {inst.typeFields|*}
         * @private
         */
        this._typeFields = options.typeFields;

        this._inputData = [];
        this._correctData = [];
        this._inCorrectData = [];
        this._formatData = null;
        /**
         * Event Listeners
         */
        //this._btnFile.addEventListener('change', this._parseCSV.bind(this));
        this._btnFile.addEventListener('change', this._controlUploadFile.bind(this));
        this._componentRoot.addEventListener('click', this._eventHandler.bind(this));

        /**
         * Config
         *
         * @type {{url: string, delimiterStr: string}}
         * @private
         */
        this._config = {
            url: options.url,
            delimiterStr: "\n",
            delimiterChar: ","

        }
    }

    /**
     * check type file, handler upload file
     * @param event
     * @private
     */
    _controlUploadFile(event) {
        let file = event.target.files[0];
        let fileName = file.name;
        let fileType = fileName.split(".")[fileName.split(".").length - 1];

        if(this.options.tableRender) {
            let table = this._componentRoot.querySelector('[data-component="table-custom"]');

            if(table) {
                this._inputData = [];
                table.parentNode.removeChild(table);
            }

            this.options.tableRender = false;
        }

        let chooseFields = this._componentRoot.querySelector("#chooseFields");

        if (chooseFields) {
            chooseFields.parentNode.removeChild(chooseFields);
        }

        if(fileType !== 'csv') {

            noty({
                text: 'Файл ' + fileName + ' некорректный, выберите корректный файл, формата csv.',
                type: 'error',
                timeout: 3000
            });

            return;

        } else {

            noty({
                text: 'Был выбран файл ' + fileName + '.',
                type: 'success',
                timeout: 3000
            });
        }

        this._parseCSV(event, file);
    }

    /**
     * Parse csv file
     * @param file Object
     * @param event Event Object
     * @private
     */
    _parseCSV(event, file) {
        var fileObject = new FileReader();

        fileObject.readAsText(file);

        fileObject.onload = function(file) {
            var fileContent = file.target.result;

            for(let i = 0; i < fileContent.split(this._config.delimiterStr).length; ++i) {
                let str = fileContent.split(this._config.delimiterStr)[i];
                let item = [];

                for(let j = 0; j < str.split(this._config.delimiterChar).length; ++j) {
                    item.push(str.split(this._config.delimiterChar)[j]);
                }

                this._inputData.push(item);
            }

            this._formatData = this._inputData[0].length;
        }.bind(this);

        fileObject.onloadend = function() {
            this._renderListFields();
        }.bind(this);
    }

    /**
     * Render List with standart fields
     */
    _renderListFields(){
        let theTemplateScript = document.getElementById("title-list-template").innerHTML.trim();
        let theTemplate = Handlebars.compile(theTemplateScript);
        let theCompiledHtml = theTemplate( {data: this._typeFields, options: this._inputData[0]});

        this._componentRoot.querySelector("#upload-file-group")
            .insertAdjacentHTML('afterEnd', theCompiledHtml);

        this._componentRoot.querySelector('#chooseFields')
            .addEventListener('change', this._eventHandlerChoosenFields.bind(this));
    }

    _eventHandlerChoosenFields(event) {
        let selects = this._componentRoot.getElementsByTagName('select');
        let indexArr = [];

        for(let i = 0; i < selects.length; i++) {
            indexArr.push(selects[i].selectedIndex);
        }

        if( !~indexArr.indexOf(0) ) this._createTable(event);
    }

    _eventHandler(event) {
        if(event.target.dataset.tableCustom === "submit") {
            event.preventDefault();
            this._prepareDataBeforeSubmit();
            this._submitData(event);
        }
    }
    /**
     * Create table with data from csv file
     */
    _createTable(){
        let chooseFields = this._componentRoot.querySelector("#chooseFields");

        this._updateData();

        window.customTbl = new TableCustom({}, this._userDataStyle, this._correctData);

        if (chooseFields.parentNode) {
            chooseFields.parentNode.removeChild(chooseFields);
        }

        this._createMessage();

        this.options.tableRender = true;
    }

    /**
     * create message after render table
     *
     * @private
     */
    _createMessage() {
        let messageArr = ['абонент', 'абонента', 'абонентов'];

        function createMessage(number, startWord) {
            let endMessage = null;
            let startMessage = (number === 1) ? startWord : (startWord + "o");

            if(number === 1) {
                endMessage = messageArr[0];
            } else if (number == 12
                || number == 13
                || number == 14) {

                endMessage = messageArr[2];
            } else if( number.toString()[number.toString().length - 1] === '2'
                || number.toString()[number.toString().length - 1] === '3'
                || number.toString()[number.toString().length - 1] === '4') {

                endMessage = messageArr[1];
            } else {
                endMessage = messageArr[2];
            }

            return  startMessage + " " + number + " " + endMessage;
        }

        noty({
            text: createMessage( (this._correctData.length), 'Загружен'),
            type: 'success',
            timeout: '3000'
        });

        if(this._inCorrectData.length) {
            noty({
                text: createMessage( (this._inCorrectData.length - 1), 'Не загружен'),
                type: 'error',
                timeout: '3000'
            });
        }
    }

    _updateData(){
        this._setUserDataStyle();
    }

    /**
     * Create data style, from user selection
     */
    _setUserDataStyle() {
        this._userDataStyle = [];

        let userSelections = this._componentRoot.querySelectorAll(".field-select");

        for(let i = 0; i < userSelections.length; i++ ) {
            var optionSelected = userSelections[i].children[userSelections[i].selectedIndex];

            if(!optionSelected.dataset.index) continue;
            this._userDataStyle.push({
                index: optionSelected.dataset.index,
                id: userSelections[i].dataset.field,
                title: userSelections[i].dataset.title
            });
        }

        if(Object.keys(this._userDataStyle).length) {
            this._prepareData();
        } else {
            this._correctData = [];
        }
    }

    /**
     * Improve data according to user selection, [{}, {}, ..., {}]
     */
    _prepareData() {

        this._correctData = [];

        for(let i = 0; i < this._inputData.length; i++) {
            var item = {};

            if(this._inputData[i].length !== this._formatData) {
                this._inCorrectData.push(this._inputData[i]);
                continue;
            }

            for(let j = 0; j < this._inputData[i].length; j++) {
                for(var k = 0; k < this._userDataStyle.length; k++) {
                    if(j === +this._userDataStyle[k].index) {
                        item.index = j;
                        item[this._userDataStyle[k].id] = this._inputData[i][j];
                    }
                }
            }
            if(Object.keys(item).length) {
                this._correctData.push(item);
            }
        }
    }

    /**
     * Prepare data for submit
     * [{}, {}, ..., {}]
     * @private
     */
    _prepareDataBeforeSubmit() {
        this.options.data = [];
        //rewrite this part code
        this.options.title = this._componentRoot.querySelector("#clientListTitle").value;
        this.options.describe = this._componentRoot.querySelector("#clientListDescribe").value;
        this.options.data = window.customTbl.getData();

        //this.options.fields = [];
        //if(this._componentRoot.querySelector('#customUserTableFromCSV')) {
        //    for(let i = 0, th = this._componentRoot.querySelector('thead').querySelectorAll("th"); i < th.length - 1; i++) {
        //        this.options.fields.push(th[i].dataset.tableCustom);
        //    }

            //for(let i = 0, tr = this._componentRoot.querySelector('tbody').querySelectorAll("tr"); i < tr.length; i++) {
            //    let item = {};
            //    for(let j = 0, td = this._componentRoot.querySelector('tbody').querySelectorAll("tr")[i].querySelectorAll("td"); j < td.length - 1; j++) {
            //        item[this.options.fields[j]] = td[j].innerText;
            //    }
            //
            //    this.options.data.push(item);
            //}
        //}
    }

    /**
     * Control data before send to server
     * @returns {boolean}
     * @private
     */
    _controlDataBeforeSend() {
        var validation = true;

        if( !this.options.title ) {

            noty({
                text: "Заполните Название.",
                type: "error",
                timeout: 3000
            });

            this._componentRoot.querySelector("#clientListTitle")
                .parentNode
                    .classList
                        .add("has-error");

            validation = false;
        }

        if( !this.options.describe ) {

            noty({
                text: "Заполните Описание.",
                type: "error",
                timeout: 3000
            });

            this._componentRoot.querySelector("#clientListDescribe")
                .parentNode
                    .classList
                        .add("has-error");

            validation = false;
        }

        if(!this.options.data.length) {
            noty({
                text: "Сформируйте данные.",
                type: "error",
                timeout: 3000
            });

            validation = false;
        }

        return validation;
    }

    /**
     * Submit data to server
     */
    _submitData(event) {

        // prevent submit form
        event.preventDefault();

        if( !this._controlDataBeforeSend() ) {
            document.body.scrollTop = document.documentElement.scrollTop = 0;
            return;
        }

        //let xhr = new XMLHttpRequest();
        let data = {
            name: this.options.title,
            description: this.options.describe,
            clients: this.options.data
        };

        var xhr = $.ajax({
            type: "POST",
            url: this._config.url,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(data)
        });

        var complete = false;

        xhr
            .done(function() {
                complete = true;

            })
            .fail(function() {
                complete = false;
            })
            .always(function() {
                if(complete) {
                    window.location = "/autodialer/tasks?saved";
                } else {
                    noty({
                        text: 'К сожалению произошла ошибка, попробуйте ещё раз!'
                    });
                }
            });
    }
}
