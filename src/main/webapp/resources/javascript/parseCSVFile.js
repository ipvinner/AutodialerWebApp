"use strict";
class addCSVList {
    constructor(options) {
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

        fileObject.readAsText(files[0]);

        fileObject.onload = function(file) {
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

        this._componentRoot
            .insertAdjacentHTML('beforeEnd', theCompiledHtml);
    }
    _eventHandler(event) {
        if(event.target.id === "renderTable") {
            this._renderTable(event);
        }
    }
    /**
     * Render table with data from csv file
     */
    _renderTable(event){
        var self = this;
        var tpl = document.getElementById("table-custom-template").innerHTML.trim();
        var tableList = Handlebars.compile(tpl);
        var data = [];

        this._updateData();

        if(self._componentRoot.querySelector("#customUserTableFromCSV")) {

            self._componentRoot.querySelector("#customUserTableFromCSV")
                .parentNode
                .removeChild(self._componentRoot.querySelector("#customUserTableFromCSV"))
            ;
        }

        // prepare data to view
        for(var i = 0; i < self._correctData.length - 1; i++) {
            var item = [];
            for(var j = 0; j < self._userDataStyle.length; j++) {
                if(self._correctData[i][self._userDataStyle[j].id] === "index") continue;
                item.push(self._correctData[ i ][ self._userDataStyle[j].id ]);
            }
            data.push(item);
        }

        self._componentRoot
            .insertAdjacentHTML("beforeEnd", tableList({title: self._userDataStyle, data: data}));

        window.customTbl = new TableCustom({
            url: "/server.js"
        });

        this._componentRoot.removeChild(this._componentRoot.querySelector(".chooseFields"));
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
}
