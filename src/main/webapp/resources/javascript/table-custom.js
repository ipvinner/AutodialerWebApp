/**
 * Component tableCustom create table from data
 * Set handlers event to table cells and component buttons
 * Update data
 * Send data to define url
 */

"use strict";
class TableCustom {

    constructor(options) {
        this.options = options || {};
        this.options.data = [];
        this.options.fields = [];

        this._defineElements();
        this._setEvents();

        window.tableCustom = this;
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
        this.elements.root.addEventListener("dblclick", this._toggleEditableCell.bind(this));
        this.elements.root.addEventListener("blur", this._disableEditableCell.bind(this), true);

        this.elements.root.addEventListener("click", this.clickHandler.bind(this));
    }

    /**
     * Toggle available edit for cell
     * @private
     */
    _toggleEditableCell(event) {

        if(event.target.tagName.toUpperCase() === "TD")  {

            if(event.target.hasAttribute("contenteditable")) {
                event.target.removeAttribute("contenteditable");
                event.target.classList.remove("editable");
            } else {
                event.target.setAttribute("contenteditable", "true");
                event.target.classList.add("editable");
            }
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

        if(event.target.dataset.tableCustom === "submit") {
            this._prepareData();
            this._submitData();
        }

        if(event.target.dataset.tableCustom === "update-row") {
            var dd = this.elements.root.querySelectorAll("#row-content dd");
            var rowData = [];
            var rowIndex = event.target.dataset.index;
            var td;

            if(event.target.dataset.index === "newString") {
                var obj = {};
                for(var i = 0; i < dd.length; i++) {
                    obj[dd[i].innerText] = dd[i].innerText;
                }

                rowData.push(obj);

                this.addRow(rowData);

            } else {
                td = this.elements.tbody.querySelectorAll("[data-index='" + rowIndex + "'] td");

                for(var i = 0; i < dd.length; i++) {
                    rowData.push(dd[i].innerText);
                    td[i].innerHTML = rowData[i];
                }
            }

            dialog.destruct();
        }
    }

    /**
     * add row to table
     * @param data array objects
     * data[item] - it's table row
     * example of data - [{name: 'Yuriy', surName: "Berezovskiy"}]
     */
    addRow(data) {
        // validate data

        //var tpl = document.getElementById("table-row").innerHTML.trim();
        var tpl = '<%for(var i = 0; i < data.length; i++) { %><tr data-index="<%-i%>"><%for(var value in data[i]) { %><%if(value === "index") continue;%><td><%-data[i][value]%></td><% } %><td><button class="btn btn-primary" data-table-custom="edit-row" data-component="dialog" type="button">Edit</button><button class="btn btn-primary" data-table-custom="delete-row" type="button">Delete</button></td></tr><% } %>';
        var tableRow = _.template(tpl);

        this.elements.tbody
            .insertAdjacentHTML("beforeEnd", tableRow({data: data}));

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

    /**
     * Prepare data for submit
     * [{}, {}, ..., {}]
     * @private
     */
    _prepareData() {
        this.options.data = [];

        this.options.fields = [];

        for(let i = 0, th = this.elements.thead.querySelectorAll("th"); i < th.length - 1; i++) {
            this.options.fields.push(th[i].dataset.tableCustom);
        }

        for(let i = 0, tr = this.elements.tbody.querySelectorAll("tr"); i < tr.length; i++) {
            let item = {};
            for(let j = 0, td = this.elements.tbody.querySelectorAll("tr")[i].querySelectorAll("td"); j < td.length - 1; j++) {
                item[this.options.fields[j]] = td[j].innerText;
            }

            this.options.data.push(item);
        }
    }

    /**
     * Submit data to server
     */
    _submitData() {

        let xhr = new XMLHttpRequest();
        let data = {data: JSON.stringify(this.options.data)};

        xhr.open("POST", this.options.url, true);

        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

        // ajax handler
        xhr.onreadystatechange = function() {
            if (this.readyState != 4) return;

            alert( this.responseText );
        };

        xhr.send(data.data);

    }

}
