/**
 * Component tableCustom create table from data
 * Set handlers event to table cells and component buttons
 * Update data
 * Send data to define url
 */

"use strict";
class TableCustom {

    constructor(options, title, data) {
        this.options = options || {};
        this.options.data = [];
        this.options.fields = [];

        this._renderTable(title, data);
        this._defineElements();
        this._setEvents();
    }

    /**
     * Render table with data from csv file
     */
    _renderTable(title, data){
        var tpl = document.getElementById("table-custom-template").innerHTML.trim();
        var tplCompile = Handlebars.compile(tpl);
        var viewData = [];

        if(document.body.querySelector("#customUserTableFromCSV")) {

            var table = document.body.querySelector("#customUserTableFromCSV");
            table.parentNode.removeChild(table);
        }

        // prepare data to view
        for(let i = 0; i < data.length - 1; i++) {
            let item = [];
            for(let j = 0; j < title.length; j++) {
                if(data[i][title[j].id] === "index") continue;
                item.push(data[ i ][ title[j].id ]);
            }
            viewData.push(item);
        }

        document.body.querySelector("#upload-file-group")
            .insertAdjacentHTML("afterEnd", tplCompile({title: title, data: viewData}));
    }

    /**
     * Define component dom elements
     * @private
     */
    _defineElements() {
        this.elements = {};
        this.elements.root = document.querySelector('[data-component="table-custom"]');
        this.elements.tbody = this.elements.root.querySelector('tbody');
        this.elements.thead = this.elements.root.querySelector('thead');
        this.elements.btnEdit = document.querySelectorAll('[data-table-custom="edit-row"]');
        this.elements.btnDelete = document.querySelectorAll('[data-table-custom="delete-row"]');
        this.elements.btnUpdate = document.querySelectorAll('[data-table-custom="update-row"]');

    }

    _setEvents() {
        this.elements.root.addEventListener("blur", this._disableEditableCell.bind(this), true);

        document.body.addEventListener("click", this.clickHandler.bind(this));
    }

    /**
     * Handler click
     * @param event
     */
    clickHandler(event) {

        if(event.target.dataset.tableCustom === "delete-row") {

            var row = event.target.closest("tr");
            row.parentNode.removeChild(row);

            // update data-index attribute for row
            this._updateRowIndex();
        }

        if(event.target.dataset.tableCustom === "update-row") {
            var inputs = document.body.querySelectorAll("#row-content input");
            var rowData = [];
            var rowIndex = event.target.dataset.index;
            var td;

            if(event.target.dataset.index === "add-row") {
                var data = [];
                for(var i = 0; i < inputs.length; i++) {
                    data.push(inputs[i].value);
                }

                this.addRow(data);

            } else {
                td = this.elements.tbody.querySelectorAll("[data-index='" + rowIndex + "'] td");

                for(var i = 0; i < inputs.length; i++) {
                    rowData.push(inputs[i].value);
                    td[i].innerHTML = rowData[i];
                }
            }

            $('#dialog-modal').modal('hide');
        }

        if(event.target.tagName.toUpperCase() === "TD")  {
            this._toggleEditableCell(event);
        }
    }

    /**
     * Toggle available edit for cell
     * @private
     */
    _toggleEditableCell(event) {
        if(!event.target.nextElementSibling) return;
        if(event.target.nextElementSibling.nodeName.toLowerCase() !== "td") return;

        if(event.target.hasAttribute("contenteditable")) {
            event.target.removeAttribute("contenteditable");
            event.target.classList.remove("editable");
        } else {
            event.target.setAttribute("contenteditable", "true");
            event.target.classList.add("editable");
            event.target.focus();
        }
    }

    /**
     * Disable editable for all cell inside table
     * @param event
     * @private
     */
    _disableEditableCell(event) {
        var cells = this.elements.root.querySelectorAll(".editable");

        for(var i = 0; i < cells.length; i++) {
            cells[i].removeAttribute("contenteditable");
            cells[i].classList.remove("editable");
        }
    }

    /**
     * add row to table
     * @param data array objects
     * data[item] - it's table row
     * example of data - ['Yuriy', 'Berezovskiy']
     */
    addRow(data) {
        // validate data

        var template = document.getElementById("table-row-template").innerHTML.trim();
        var templateCompile = Handlebars.compile(template);

        this.elements.tbody
            .insertAdjacentHTML("beforeEnd", templateCompile({data: data}));

        this._updateRowIndex();
    }

    getDataRow(el) {
        var rowData = [];
        var theadElements = this.elements.root.querySelectorAll("thead th");
        var rowElements = false;

        if(el.closest("tr")) {
            rowElements = el.closest("tr").querySelectorAll("td");
        }

        for(var i = 0; i < theadElements.length - 1; i++) {
            var item = {};
            if(rowElements) {
                item[theadElements[i].innerHTML] = rowElements[i].innerHTML;
            } else {
                item[theadElements[i].innerHTML] = "";
            }
            rowData.push(item);
        }

        return rowData;
    }

    /**
     * Update row index for custom table
     * @private
     */
    _updateRowIndex() {
        for(var i = 0, len = this.elements.tbody.querySelectorAll("tr").length; i < len; i++ ) {
            this.elements.tbody.querySelectorAll("tr")[i].dataset.index = i;
        }
    }

}
