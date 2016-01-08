/**
 * Upload data to server
 * need data: title, description, csv-file
 */

"use strict";
class uploadData {
    constructor(options) {
        this.options = {};
        this.options.url = options.url;
        /**
         * Dom elements
         */
        this._componentRoot = options.componentRoot,
            this._btnFile = options.btnFile;
        this._btnSubmit = options.btnSubmit;
        this._btnRenderTable = options.btnRenderTable;

        /**
         * Input data typeFields
         * @type {inst.typeFields|*}
         * @private
         */
        this._typeFields = options.typeFields;

        this._inputData = [];
        this._correctData = [];

        /**
         * Event Listeners
         */
        this._btnFile.addEventListener('change', this._parseCSV.bind(this));
        this._componentRoot.addEventListener('click', this._eventHandler.bind(this));

        /**
         * Config
         *
         * @type {{url: string, delimiterStr: string}}
         * @private
         */
        this._config = {
            url: "server.jpx",
            delimiterStr: "\n",
            delimiterChar: ","

        }
    }

    _parseCSV(event) {
        /**
         * This method must validate type file
         * work with exeption
         */
        var csv;
        var files = event.target.files;
        var fileObject = new FileReader();

        // check alert for wrong file format
        if(this._componentRoot.querySelector(".alert-warning")) {
            let alert = this._componentRoot.querySelector(".alert-warning");
            this._componentRoot.removeChild(alert);
        }

        if(event.target.files[0].name.split(".")[event.target.files[0].name.split(".").length - 1] !== "csv") {
            let warningTpl = document.getElementById("warning-template").innerHTML.trim();
            let warningCompile = Handlebars.compile(warningTpl);
            let warningHtml = warningCompile( {message: "Выберите корректный файл. (csv)"} );

            this._componentRoot
                .insertAdjacentHTML('beforeEnd', warningHtml);
            return;
        }

        fileObject.readAsText(files[0]);

        fileObject.onload = function(file, filename) {
            csv = file.target.result;

            for(var i = 0; i < csv.split(this._config.delimiterStr).length; ++i) {
                let str = csv.split(this._config.delimiterStr)[i];
                let item = [];

                for(var j = 0; j < str.split(this._config.delimiterChar).length; ++j) {
                    item.push(str.split(this._config.delimiterChar)[j]);
                }

                this._inputData.push(item);
            }
        }.bind(this);

        fileObject.onloadend = function() {
            this._renderListFields();
        }.bind(this);
    }

    /**
     * Render List with standart fields
     */

    _renderListFields(){
        let self = this;
        let theTemplateScript = document.getElementById("title-list-template").innerHTML.trim();
        let theTemplate = Handlebars.compile(theTemplateScript);
        let theCompiledHtml = theTemplate( {data: self._typeFields, options: self._inputData[0]} );

        this._componentRoot.querySelector("#upload-file-group")
            .insertAdjacentHTML('afterEnd', theCompiledHtml);
    }
    _eventHandler(event) {
        if(event.target.id === "renderTable") {
            this._renderTable(event);
        }

        if(event.target.dataset.tableCustom === "submit") {
            this._prepareDataBeforeSubmit();
            this._submitData();
        }
    }
    /**
     * Render table with data from csv file
     */
    _renderTable(){
        var self = this;
        this._updateData();

        window.customTbl = new TableCustom({}, self._userDataStyle, self._correctData);

        this._componentRoot.removeChild(this._componentRoot.querySelector("#chooseFields"));
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
        this.options.title = document.body.querySelector("#tableCustomTitle").value;
        this.options.describe = document.body.querySelector("#tableCustomDescribe").value;

        this.options.fields = [];

        for(let i = 0, th = document.body.querySelector('thead').querySelectorAll("th"); i < th.length - 1; i++) {
            this.options.fields.push(th[i].dataset.tableCustom);
        }

        for(let i = 0, tr = document.body.querySelector('tbody').querySelectorAll("tr"); i < tr.length; i++) {
            let item = {};
            for(let j = 0, td = document.body.querySelector('tbody').querySelectorAll("tr")[i].querySelectorAll("td"); j < td.length - 1; j++) {
                item[this.options.fields[j]] = td[j].innerText;
            }

            this.options.data.push(item);
        }
    }

    /**
     * Control data before send to server
     * @returns {boolean}
     * @private
     */
    _controlDataBeforeSend() {
        var validation = true;

        if( !this.options.title ) {
            let warningTpl = document.getElementById("warning-template").innerHTML.trim();
            let warningCompile = Handlebars.compile(warningTpl);
            let warningHtml = warningCompile( {message: "Заполните Название."} );

            document.body.querySelector("[data-component='userUploadCSVFile']")
                .insertAdjacentHTML('afterBegin', warningHtml);
            validation = false;
        }

        if( !this.options.describe ) {
            let warningTpl = document.getElementById("warning-template").innerHTML.trim();
            let warningCompile = Handlebars.compile(warningTpl);
            let warningHtml = warningCompile( {message: "Заполните Описание."} );

            document.body.querySelector("[data-component='userUploadCSVFile']")
                .insertAdjacentHTML('afterBegin', warningHtml);
            validation = false;
        }

        return validation;
    }

    /**
     * Submit data to server
     */
    _submitData() {

        if( !this._controlDataBeforeSend() ) {
            document.body.scrollTop = document.documentElement.scrollTop = 0;
            return;
        }

        let xhr = new XMLHttpRequest();
        let data = {
            name: this.options.title,
            description: this.options.describe,
            data: this.options.data
        };

        xhr.open("POST", this.options.url, true);

        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

        // ajax handler
        xhr.onreadystatechange = function() {
            if (this.readyState != 4) return;

            alert( this.responseText );


        };

        xhr.send(JSON.stringify(data));

    }
}
