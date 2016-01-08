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

    $('addTaskForm').submit(function(){
        saveTask();
        return false;
    });

});
//
//function addTask(){
//    $('addTaskForm').submit(function(){
//        saveTask();
//        return false;
//    });
//};

function saveTask(){
    var form = $('#addTaskForm');
    $.ajax({
        type: "POST",
        url: "ajax/admin/tasks/create",
        data: form.serialize(),
        success: function () {
            updateTable();
            successNoty('Task added');
        }
    });
}