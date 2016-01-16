var ajaxUrl = 'ajax/admin/users/';
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
        "bInfo": false,
        "aoColumns": [
            {
                "mData": "login"
            },
            {
                "mData": "passwordHash"
            },
            {
                "mData": "role"
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

    addUser();
});

function addUser(){
    var form = $('#addUser');
    $('#id').val(0);
    form.submit(function(){
        $.ajax({
            type: "POST",
            url: "ajax/admin/users",
            data: form.serialize(),

            success: function () {
                updateTable();
                successNoty('User added');
            }


        });
        form.find('input:text').val('');
        return false;
    });
}