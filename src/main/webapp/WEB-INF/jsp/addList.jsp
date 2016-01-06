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
    <div class="chooseFields">

        {{#each data}}
        <div class="list-titles-item">
            <div class="inline-block">
                {{title}}
            </div>

            <div class="inline-block">
                <select class="field-select" data-field="{{id}}" data-title="{{title}}">
                    <option>Select your option</option>
                    {{#each ../options}}
                    <option data-index="{{@index}}">{{this}}</option>
                    {{/each}}
                </select>
            </div>

        </div>
        {{/each}}

        <button id="renderTable" class="btn btn-primary" type="button" >Render Table</button>
    </div>
</script>

<!-- Template for custom table -->
<script id="table-custom-template" type="text/x-handlebars-template">
    <div data-component="table-custom">
        <div class="table-custom__navigation">
            <button class="btn btn-primary" data-table-custom="add-row" data-component="dialog" type="button">Add</button>
            <button class="btn btn-primary" data-table-custom="submit" type="button">Submit</button>
        </div>

        <table id="customUserTableFromCSV" class="table" data-component="table-custom">
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
    <h3>{{title}}</h3>

    <dl id="row-content">
        {{#each data}}
        {{#each this}}
        <dt>{{@key}}</dt>
        {{#if ../../newString }}
        <dd class="empty" contenteditable="true"></dd>
        {{else}}
        <dd contenteditable="true">{{this}}</dd>
        {{/if}}
        {{/each}}
        <br>
        {{/each}}
    </dl>

    <button data-index="{{index}}" class="btn btn-primary" data-table-custom="update-row" type="button">Update</button>
</script>

<div class="jumbotron">
    <div class="container">
        <div class="container">

            <div data-component="userUploadCSVFile">
                <label for="uploadFile">Add new list users</label>
                <input type="file" name="uploadFile" id="uploadFile"/>
            </div>
            <!-- end userUploadCSVFile -->
        </div>

        <script src="resources/javascript/parseCSVFile.js"></script>
        <script src="resources/javascript/table-custom.js"></script>
        <script src="resources/javascript/dialog.js"></script>
        <script src="resources/javascript/main.js"></script>

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>

