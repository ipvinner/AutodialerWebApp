/**
 * Component tableCustom create table from data
 * Set handlers event to table cells and component buttons
 * Update data
 * Send data to define url
 */

Handlebars.registerHelper("math", function(lvalue, operator, rvalue, options) {
    lvalue = parseFloat(lvalue);
    rvalue = parseFloat(rvalue);

    return {
        "+": lvalue + rvalue,
        "-": lvalue - rvalue,
        "*": lvalue * rvalue,
        "/": lvalue / rvalue,
        "%": lvalue % rvalue
    }[operator];
});

"use strict";
class TableCustom {

    constructor(options, title, data) {
        this.options = options || {};
        this.options.data = [];
        this.options.fields = [];
        this.options.title = title;
        this.options.limit = 10;
        this.options.limits = [
            {value: 10, selected: true},
            {value: 20, selected: false},
            {value: 50, selected: false},
            {value: 100, selected: false},
            {value: 200, selected: false},
        ];
        this.options.offset = 0;
        this.options.pages = [];
        this.options.fullData = [];
        this.options.viewData = [];
        this.options.renderPages = [];

        // prepare data to view
        for(let i = 0; i < data.length; i++) {
            let item = [];
            for(let j = 0; j < title.length; j++) {
                if(data[i][title[j].id] === "index") continue;
                item.push(data[ i ][ title[j].id ]);
            }
            this.options.fullData.push(item);
        }

        this.options.viewData = this.options.fullData.slice(0, this.options.limit);
        this._pagination(this.options.fullData);

        this._renderComponent();
        this._defineElements();
        this._setEvents();
    }

    /**
     * Getter data
     * @returns {fullData}
     */
    getData() {
        return this.options.fullData;
    }

    _updateViewTable() {
        if(this.elements.table) {
            this.elements.table.innerHTML = "";
        }

        this.options.viewData = this.options.fullData.slice(this.options.offset, this.options.offset + this.options.limit);
        this._renderTable();
    }

    /**
     * Render table with data from csv file
     */
    _renderComponent(title, data){
        var opt = this.options;
        var tpl = document.getElementById("table-custom-component-template").innerHTML.trim();
        var tplCompile = Handlebars.compile(tpl);

        document.body.querySelector("#upload-file-group")
            .insertAdjacentHTML("afterEnd", tplCompile({limit: opt.limit, offset: opt.offset}));

        this._renderNavigation();
        this._renderTable();
    }

    /**
     * Render table navigation
     * Select for rows limit
     * Pagination for row offset
     */
    _renderNavigation() {
        var opt = this.options;
        var dotsItem = {active: false, content: '...', link: "#", disabled: true};
        var items = [];
        var tpl = document.getElementById("table-custom-navigation-template").innerHTML.trim();
        var tplCompile = Handlebars.compile(tpl);

        if(opt.pages.length <= 5) {
            opt.renderPages = opt.pages;
        } else {
            if(opt.offset < 4 * opt.limit) {
                opt.renderPages = opt.pages.slice(0, 5);
                opt.renderPages.push(dotsItem);
                opt.renderPages.push(opt.pages[opt.pages.length - 1]);
            } else if (opt.offset > opt.fullData.length - 5 * opt.limit) {
                items = opt.pages.slice(opt.pages.length - 6);

                opt.renderPages = opt.pages.slice(0, 1);
                opt.renderPages.push(dotsItem);

                for(var i = 0; i < items.length; i++) {
                    opt.renderPages.push(items[i]);
                }
            } else {
                items = opt.pages.slice(opt.offset/opt.limit - 2, opt.offset/opt.limit + 3);

                opt.renderPages = opt.pages.slice(0, 1);
                opt.renderPages.push(dotsItem);

                for(var i = 0; i < items.length; i++) {
                    opt.renderPages.push(items[i]);
                }

                opt.renderPages.push();
                opt.renderPages.push(dotsItem);
                opt.renderPages.push(opt.pages[opt.pages.length - 1]);
            }
        }

        for(var i = 0; i < opt.renderPages.length; i++) {
            opt.renderPages[i].active = (opt.offset/opt.limit == opt.renderPages[i].link) ? true : false;
        }

        if(document.querySelector('[data-table-custom="navigation"]').hasChildNodes()) {
            document.querySelector('[data-table-custom="navigation"]').innerHTML = "";
        }

        document.querySelector('[data-table-custom="navigation"]')
            .insertAdjacentHTML("beforeEnd", tplCompile({pages: opt.renderPages, limit: opt.limit, limits: opt.limits}));
    }

    /**
     * Render table
     */

    _renderTable() {
        var opt = this.options;
        var tpl = document.getElementById("customUserTableFromCSV-template").innerHTML.trim();
        var tplCompile = Handlebars.compile(tpl);

        if(document.querySelector('[data-table-custom="table"]').hasChildNodes()) {
            document.querySelector('[data-table-custom="table"]').innerHTML = "";
        }

        document.querySelector('[data-table-custom="table"]')
            .insertAdjacentHTML("beforeEnd", tplCompile({title: opt.title, data: opt.viewData, offset: opt.offset}));
    }

    /**
     * Define component dom elements
     * @private
     */
    _defineElements() {
        this.elements = {};
        this.elements.root = document.querySelector('[data-component="table-custom"]');

        this.elements.navigation = this.elements.root.querySelector('[data-table-custom="navigation"]');

        this.elements.table = this.elements.root.querySelector('[data-table-custom="table"]');

        this.elements.btnEdit = this.elements.table.querySelectorAll('[data-table-custom="edit-row"]');
        this.elements.btnDelete = this.elements.table.querySelectorAll('[data-table-custom="delete-row"]');
        this.elements.btnUpdate = this.elements.table.querySelectorAll('[data-table-custom="update-row"]');
    }

    _setEvents() {
        this.elements.root.addEventListener("blur", this._disableEditableCell.bind(this), true);
        this.elements.root.addEventListener("change", this._changeLimit.bind(this), true);

        document.body.addEventListener("click", this.clickHandler.bind(this));
        document.body.addEventListener("dblclick", this._dbclickHandler.bind(this));
    }

    /**
     * Handler click
     * @param event
     */
    clickHandler(event) {

        if(event.target.dataset.tableCustom === "delete-row") {
            this._deleteDataRow(event.target);
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
                var tr = this.elements.root.querySelector("tbody [data-index='" + rowIndex + "']");
                td = tr.querySelectorAll("td");

                for(var i = 0; i < inputs.length; i++) {
                    rowData.push(inputs[i].value);
                    td[i].innerHTML = rowData[i];
                }

                this.options.fullData[rowIndex] = rowData;
                this._updateViewTable();
            }

            $('#dialog-modal').modal('hide');
        }

        if(event.target.closest("[data-table-custom='data-offset']") && !!event.target.href) {
            event.preventDefault();
            this._changeOffset(event.target);
        }
    }

    /**
     * Hendler for dblclick on table cell
     * @param event
     * @private
     */
    _dbclickHandler(event) {
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
        if(event.target.tagName.toLowerCase() != 'td') return;
        var data = [];
        var index = +event.target.closest('tr').dataset.index;
        event.target.removeAttribute("contenteditable");
        event.target.classList.remove("editable");

        for(var i = 0; i < event.target.closest('tr').querySelectorAll('td').length - 1; i++) {
            data.push(event.target.closest('tr').querySelectorAll('td')[i].innerHTML);
        }

        this.options.fullData[index] = data;
    }

    /**
     * add row to table
     * @param data array objects
     * data[item] - it's table row
     * example of data - ['Yuriy', 'Berezovskiy']
     */
    addRow(data) {
        // validate data
        this.options.fullData.push(data);
        this._pagination();
        this._renderNavigation();
        this._updateViewTable();
    }

    _deleteDataRow(target) {
        var row = target.closest("tr");
        var index = parseInt(row.dataset.index);

        this.options.fullData.splice(index, 1);
        row.remove();
        this._pagination();
        this._renderNavigation();
        this._updateViewTable();
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
     * Pagination
     * controller for paging
     */

    _pagination() {
        var opt = this.options;
        var active = false;
        var isActive = false;
        opt.pages = [];

        if(opt.limit >= opt.fullData.length) {
            opt.offset = 0;
            return;
        }

        if(opt.offset >= opt.fullData.length) {
            opt.offset -= opt.limit;
        }

        if(opt.limit > opt.offset) {
            opt.offset = 0;
        }

        if(opt.offset > opt.limit && opt.offset % opt.limit !== 0) {
            opt.offset -= opt.offset % opt.limit;
        }

        for(var i = 0, len = Math.ceil(opt.fullData.length/opt.limit); i < len; ++i) {

            if(opt.limit * (i+1) > opt.offset && !isActive) {
                isActive = true;
                active = true;
                opt.currentPage = i;
            } else {
                active = false;
            }

            opt.pages.push({"content": i+1, "link": i, "active": active});
        }
    }

    _changeLimit(event) {
        var opt = this.options;
        opt.limit = parseInt(document
                        .querySelector('[data-table-custom="data-limit"]')
                            .options[document.querySelector('[data-table-custom="data-limit"]').selectedIndex]
                                .value);

        for(var i = 0; i < opt.limits.length; ++i) {
            opt.limits[i].selected = false;
            if(opt.limits[i].value == opt.limit) opt.limits[i].selected = true;
        }

        this.elements.root.setAttribute('data-table-custom-limit-value', opt.limit);
        this._pagination();
        this._renderNavigation();
        this._updateViewTable();
    }

    _changeOffset(target) {
        var opt = this.options;
        if(target.closest("[data-table-custom='data-offset']").querySelector(".active")) {
            target.closest("[data-table-custom='data-offset']")
                .querySelector(".active")
                .classList.remove("active")
            ;
        }

        switch (target.getAttribute("href")) {
            case ">":
                opt.offset = (opt.offset + opt.limit > opt.fullData.length) ? opt.pages[opt.pages.length - 1].link * opt.limit : opt.offset + opt.limit;

                target.closest("[data-table-custom='data-offset']")
                    .querySelector("[href='" + (opt.offset/opt.limit) + "']")
                    .parentNode
                    .classList.add("active");

                break;
            case ">>":
                opt.offset = opt.pages[opt.pages.length - 1].link * opt.limit;

                target.closest("[data-table-custom='data-offset']")
                    .querySelector("[href='" + (opt.pages.length-1) + "']")
                    .parentNode
                    .classList.add("active");

                break;
            case "<<":
                opt.offset = 0;
                target.closest("[data-table-custom='data-offset']")
                    .querySelector("[href='0']")
                    .parentNode
                    .classList.add("active");
                break;
            case "<":
                opt.offset = (opt.offset - opt.limit <= 0) ? 0 : opt.offset - opt.limit;

                target.closest("[data-table-custom='data-offset']")
                    .querySelector("[href='" + (opt.offset/opt.limit) + "']")
                    .parentNode
                    .classList.add("active");

                break;
            default:
                target.parentNode
                    .classList
                    .add("active")
                ;

                opt.offset = parseInt(target.getAttribute("href")) * opt.limit;
        }

        this.elements.root
            .setAttribute("data-table-custom-offset-value", opt.offset);

        this._updateViewTable();
        this._renderNavigation();
    }

}