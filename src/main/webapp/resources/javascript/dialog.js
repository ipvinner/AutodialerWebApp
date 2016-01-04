"use strict"

/**
 * Component Dialog
 *
 */


class Dialog {
    constructor(options) {
        this.options = options;
        this.elements = {};
        this.options.isOpen = false;

        this._eventHandler();
    }

    /**
     * eventHandler
     */

    _eventHandler() {
        document.addEventListener("click", this._dialogHandler.bind(this));
    }

    _dialogHandler(event) {
        if(event.target.getAttribute("data-component") === "dialog") {
            this.render();
            this.options.isOpen = true;
        } else if((!event.target.closest(".dialog__container")
            && this.options.isOpen)
            || event.target.closest("[data-dialog='close']")) {
            this.destruct();
            this.options.isOpen = false;
        }
    }

    render() {
        var self = this;
        var dialog = '<div class="dialog is-open">' +
            '<div class="dialog__container">' +
            '<button data-dialog="close" class="dialog__close" type="button" title="Close">' +
            '<span class="hidden">×</span>' +
            '<i class="fa fa-times"></i>' +
            '</button>' +
            '<div data-dialog="content" class="dialog__content">' +
            '</div>' +
            '</div>' +
            '</div>'
        ;
        //var tpl = document.getElementById(self.options.templateId).innerHTML.trim();
        var tpl = '<h3>Edit Row Data</h3><dl id="row-content"><%for(var i = 0; i < data.length; i++) {%><%for(var value in data[i]) { %><dt><%-value%></dt><%if(index === "newString") {%><dd class="empty" contenteditable="true"></dd><%} else {%><dd contenteditable="true"><%-data[i][value]%></dd><% } %><br><% } %><% } %></dl><button data-index="<%-index%>" class="btn btn-primary" data-table-custom="update-row" type="button">Update</button>';
        var dialogContent = _.template(tpl);
        var data = customTbl.getDataRow(event.target);
        var index = event.target.closest("tr") ? event.target.closest("tr").dataset.index : "newString";

        document.body.classList.add("has-dialog");

        event.target
            .insertAdjacentHTML("afterEnd", dialog);

        document.querySelector(".dialog__content")
            .insertAdjacentHTML("beforeEnd", dialogContent({data: data, index: index}));

        this.elements.root = document.querySelector(".dialog.is-open");
    }

    destruct() {
        if(document.body.classList.contains("has-dialog")) {
            document.body.classList.remove("has-dialog");
            this.elements.root.parentNode.removeChild(this.elements.root);
        }
    }
}
