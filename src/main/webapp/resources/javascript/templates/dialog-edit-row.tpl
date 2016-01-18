<!-- Template for dialog-->
<script id="dialog-edit-row" type="text/x-handlebars-template">
    <div class="dialog__header">
        <h4>{{title}}</h4>
    </div>

    <div class="dialog__body">
        <div id="row-content">
            {{#each data}}
            <dl class="edit-group row">
                {{#each this}}
                <dt class="col-md-2 text-right">{{@key}}</dt>
                {{#if ../../newString }}
                <dd class="empty col-md-4" contenteditable="true"></dd>
                {{else}}
                <dd class="col-md-4" contenteditable="true">{{this}}</dd>
                {{/if}}
                {{/each}}
                <br>
            </dl>
            {{/each}}
        </div>
    </div>

    <div class="dialog__control">
        <button class="btn btn-default" type="button" data-dialog="close">Закрыть</button>
        <button data-index="{{index}}" class="btn btn-primary" data-table-custom="update-row" type="button">Update</button>
    </div>
</script>