var ajaxUrl = 'ajax/admin/tasks/';
var datatableApi;

function updateTable() {
    $.get(ajaxUrl, function (data) {
        updateTableByData(data);
    });
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "sAjaxSource": ajaxUrl,
        "sAjaxDataProp": "",
        "bPaginate": false,
        "bFilter": false,
        "bInfo": false,
        "aoColumns": [
            {
                "mData": "name"
            },
            {
                "mData": "active"
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderPlayBtn
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderEditBtn
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderDeleteBtn
            }
        ],
        "aaSorting": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {

        },
        "initComplete": makeEditable
    });

    addTask();
    renderClientListsOptions();
    renderOriginateParamsOptions();

});

function addTask(){
    $('#addTaskForm').submit(function(){
        $.ajax({
            type: "POST",
            url: "ajax/admin/tasks/create",
            data: $('#addTaskForm').serialize(),

            success: function () {
                updateTable();
                successNoty('Task added');
            }


        });
        $("#addTaskForm").find('input:text, select').val('');
        return false;
    });
}

function renderClientListsOptions(){
    var $listSelect = $('#clients_list_id');
    $listSelect.find('option').remove();

    $.ajax({
        url: "ajax/admin/clients/clientList",
        type: 'GET',
        dataType : "json",
        success: function (data) {
            for(var i = 0, len = data.length; i < len; i++) {
                $listSelect.append('<option value=' + data[i]["id"] + '>' + data[i]["name"] + '</option>');
            }
        }
    });
}

function renderOriginateParamsOptions(){
    var $listSelect = $('#task_originate_param_id');
    $listSelect.find('option').remove();

    $.ajax({
        url: "ajax/admin/asterisk",
        type: 'GET',
        dataType : "json",
        success: function (data) {
            for(var i = 0, len = data.length; i < len; i++) {
                $listSelect.append('<option value=' + data[i]["id"] + '>' + data[i]["name"] + '</option>');
            }
        }
    });
}


