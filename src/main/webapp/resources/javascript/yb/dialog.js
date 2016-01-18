"use strict";

/**
 * Component Dialog
 *
 */


class Dialog {
    constructor(options) {
        this.options = options;
        this.elements = {};

        this._render();
    }

    _render() {
        let target = this.options.event.target;
        let tpl = document.getElementById(this.options.templateId).innerHTML.trim();
        let dialogContent = Handlebars.compile(tpl);
        let data = customTbl.getDataRow(target);
        let addRow = (target.dataset.tableCustom === "add-row") ? true : false;
        let index = addRow ? target.dataset.tableCustom : target.closest("tr").dataset.index;
        let title = addRow ? "Добавить новую запись" : "Редактировать запись";

        let dialogContentTpl =  dialogContent({data: data, index: index, title: title, addRow: addRow});

        document.querySelector("#dialog-modal .modal-content")
            .insertAdjacentHTML("beforeEnd", dialogContentTpl);

        $("#dialog-modal").modal();

    }
}
