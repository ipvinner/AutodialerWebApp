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

    _renderListFields(){
        var self = this;
        //var tpl = document.getElementById("title-list").innerHTML.trim();
        var tpl = '<div class="chooseFields"><% for(var i = 0; i < items.length; i++) { %> <div class="list-titles-item"><div class="inline-block"><%-items[i].title%></div><div class="inline-block"><select class="field-select" data-field="<%-items[i].id%>" data-title="<%-items[i].title%>"><option>Select your option</option><%for(var j = 0; j < options.length; j++) { %><option data-index="<%-j%>"><%-options[j]%></option><% } %></select></div></div><% } %><button id="renderTable" class="btn btn-primary" type="button" >Render Table</button></div>';
        var titleList = _.template(tpl);

        this._componentRoot
            .insertAdjacentHTML('beforeEnd', titleList({items: self._typeFields, options: self._inputData[0]}));
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
        //var tpl = document.getElementById("table-list").innerHTML.trim();
        var tpl = '<div data-component="table-custom"><div class="table-custom__navigation"><button class="btn btn-primary" data-table-custom="add-row" data-component="dialog" type="button">Add</button><button class="btn btn-primary" data-table-custom="submit" type="button">Submit</button></div><table id="customUserTableFromCSV" class="table" data-component="table-custom"><thead><tr><%for(var i = 0; i < title.length; i++) { %><th data-table-custom="<%-title[i].id%>"><%-title[i].title%></th><% } %><th>Edit/Remove</th></tr></thead><tbody><%for(var i = 0; i < data.length; i++) { %><tr data-index="<%-i%>"><%for(var value in data[i]) { %><%if(value === "index") continue;%><td><%-data[i][value]%></td><% } %><td><button class="btn btn-primary" data-table-custom="edit-row" data-component="dialog" type="button">Edit</button><button class="btn btn-primary" data-table-custom="delete-row" type="button">Delete</button></td></tr><% } %></tbody></table></div>';
        var tableList = _.template(tpl);


        self._updateData();

        if(self._componentRoot.querySelector("#customUserTableFromCSV")) {

            self._componentRoot.querySelector("#customUserTableFromCSV")
                .parentNode
                    .removeChild(self._componentRoot.querySelector("#customUserTableFromCSV"))
            ;
        }

        self._componentRoot
            .insertAdjacentHTML("beforeEnd", tableList({title: self._userDataStyle, data: self._correctData}));

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
