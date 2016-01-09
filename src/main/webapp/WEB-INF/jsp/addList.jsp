<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.9/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="webjars/datetimepicker/2.3.4/jquery.datetimepicker.css">
<link rel="stylesheet" href="resources/css/custom.css">

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.5/handlebars.js"></script>

<body>

<jsp:include page="fragments/bodyHeader.jsp"/>

<!-- Template for select type item -->
<script id="title-list-template" type="text/x-handlebars-template">
    <div id="chooseFields" class="form-horizontal">

        {{#each data}}
        <div class="row space-m-b-s list-titles-item">
            <div class="col-md-2 control-label">
                <strong>{{title}}:</strong>
            </div>

            <div class="col-md-4">
                <select class="field-select form-control" data-field="{{id}}" data-title="{{title}}">
                    <option>Select your option</option>
                    {{#each ../options}}
                    <option data-index="{{@index}}">{{this}}</option>
                    {{/each}}
                </select>
            </div>

        </div>
        {{/each}}

        <button id="renderTable" class="btn btn-primary space-m-b-s" type="button" >Отобразить данные</button>
    </div>
</script>

<!-- Template for custom table -->
<script id="table-custom-template" type="text/x-handlebars-template">
    <div data-component="table-custom">
        <div class="table-custom__navigation text-right space-m-b-s">
            <button class="btn btn-primary" data-table-custom="add-row" data-component="dialog" type="button">Добавить запись</button>
        </div>

        <table id="customUserTableFromCSV" class="table table-bordered table-hover" data-component="table-custom">
            <thead>
            <tr>
                {{#each title}}
                <th data-table-custom="{{id}}">{{title}}</th>
                {{/each}}
                <th width="140">
                    Edit/Remove
                </th>
            </tr>
            </thead>

            <tbody>
            {{#each data}}
            <tr data-index="{{@index}}">
                {{#each this}}
                <td>{{this}}</td>
                {{/each}}
                <td>
                    <button class="btn btn-primary" data-table-custom="edit-row" data-component="dialog" type="button">Edit</button>
                    <button class="btn btn-primary" data-table-custom="delete-row" type="button">Delete</button>
                </td>
            </tr>
            {{/each}}
            </tbody>
        </table>
    </div>
</script>

<!-- Template for table row-->
<script id="table-row-template" type="text/x-handlebars-template">
    <tr data-index="{{@index}}">
        {{#each data}}
        <td>{{this}}</td>
        {{/each}}
        <td>
            <button class="btn btn-primary" data-table-custom="edit-row" data-component="dialog" type="button">Edit</button>
            <button class="btn btn-primary" data-table-custom="delete-row" type="button">Delete</button>
        </td>
    </tr>
</script>

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

<!-- Template for warning message-->
<script id="warning-template" type="text/x-handlebars-template">
    <div class="alert alert-warning" role="alert">
        {{message}}
    </div>
</script>


<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div data-component="userUploadCSVFile" class="space-m-v-md">
                <form id="addClientList" method="post">
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="tableCustomTitle">List name</label>
                            <input placeholder="Название" class="form-control" type="text" name="name" id="tableCustomTitle">

                        </div>

                        <div class="form-group col-xs-4">
                            <label for="tableCustomDescribe">Description</label>
                            <input placeholder="Описание" class="form-control" type="text" name="description" id="tableCustomDescribe">
                        </div>
                    </div>

                    <div class="form-group" id="upload-file-group">
                        <label for="uploadFile">Add file</label>
                        <input type="file" name="uploadFile" id="uploadFile" title="Добавить файл">
                    </div>

                <button type="submit" data-table-custom="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>

            <!-- end userUploadCSVFile -->
        </div>
    </div>
</div>

<script src="resources/javascript/uploadData.js"></script>
<script src="resources/javascript/table-custom.js"></script>
<script src="resources/javascript/dialog.js"></script>
<script src="resources/javascript/main.js"></script>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>

