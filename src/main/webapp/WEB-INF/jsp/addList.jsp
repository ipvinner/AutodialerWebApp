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
    </div>
</script>

<!-- Template for custom table -->
<script id="table-custom-template" type="text/x-handlebars-template">
    <div data-component="table-custom" class="space-m-b-s">
        <div class="table-custom__navigation text-right space-m-b-s">
            <button class="btn btn-primary btn-sm" data-table-custom="add-row" data-component="dialog" type="button"><fmt:message key="clients.add_client"/></button>
        </div>

        <table id="customUserTableFromCSV" class="table table-striped display dataTable no-footer" data-component="table-custom">
            <thead>
            <tr>
                {{#each title}}
                <th data-table-custom="{{id}}">{{title}}</th>
                {{/each}}
                <th width="70">
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
                <td class="text-center">
                    <button class="btn btn-primary btn-xs" data-table-custom="edit-row" data-component="dialog" type="button">Edit</button>
                    <button class="btn btn-danger btn-xs" data-table-custom="delete-row" type="button">Delete</button>
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
        <td class="text-center">
            <button class="btn btn-primary btn-xs" data-table-custom="edit-row" data-component="dialog" type="button">Edit</button>
            <button class="btn btn-danger btn-xs" data-table-custom="delete-row" type="button">Delete</button>
        </td>
    </tr>
</script>

<!-- Template for dialog-modal-tpl-->
<script id="dialog-modal-tpl" type="text/x-handlebars-template">

    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title">{{title}}</h4>
    </div>

    <div class="modal-body">
        <div id="row-content" class="form-horizontal">
            {{#each data}}
            <div class="edit-group form-group">
                {{#each this}}
                <label class="col-sm-4 control-label">{{@key}}</label>
                {{#if ../../addRow }}
                <div class="col-sm-8">
                    <input type="email" class="form-control" placeholder="{{@key}}">
                </div>
                {{else}}
                <div class="col-sm-8">
                    <input type="text" value="{{@this}}" class="form-control">
                </div>
                {{/if}}
                {{/each}}
            </div>
            {{/each}}
        </div>
    </div>

    <div class="modal-footer">
        <button class="btn btn-default" type="button" data-dismiss="modal" aria-label="Close">Закрыть</button>
        <button data-index="{{index}}" class="btn btn-primary" data-table-custom="update-row" type="button">Сохранить</button>
    </div>
</script>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <div data-component="uploadClientList" class="space-m-v-md">
                <form id="addClientList" method="post">
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="clientListTitle"><fmt:message key="clients.list.name"/></label>
                            <input placeholder="Название" class="form-control" type="text" name="name" id="clientListTitle">

                        </div>

                        <div class="form-group col-xs-4">
                            <label for="clientListDescribe"><fmt:message key="clients.list.description"/></label>
                            <input placeholder="Описание" class="form-control" type="text" name="description" id="clientListDescribe">
                        </div>
                    </div>

                    <div class="form-group custom-btnUpload" id="upload-file-group" data-component="btn-file-upload">
                        <label for="uploadFile"><fmt:message key="clients.choose_list_file"/></label>

                        <div class="clearfix">
                            <div class="btn-upload pull-left">
                                <button type="button" class="btn btn-default btn-sm" data-button><fmt:message key="clients.upload_file"/></button>
                                <input type="file" name="uploadFile" id="uploadFile" data-file>
                            </div>

                            <div class="notice text-info">
                                <span data-file-name><fmt:message key="clients.upload_filename_message"/></span>
                                <span data-file-size>(15mb)</span>
                            </div>
                        </div>

                    </div>

                <button type="submit" id="submitData" data-table-custom="submit" class="btn btn-primary btn-sm"><fmt:message key="app.send"/></button>
                </form>
            </div>

            <!-- end userUploadCSVFile -->
        </div>
    </div>
</div>

<div id="dialog-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="resources/javascript/yb/uploadClientList.js"></script>
<script src="resources/javascript/yb/table-custom.js"></script>
<script src="resources/javascript/yb/dialog.js"></script>
<script src="resources/javascript/yb/btnUpload.js"></script>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>

