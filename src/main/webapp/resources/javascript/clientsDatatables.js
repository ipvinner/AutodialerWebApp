var ajaxUrl = 'ajax/admin/clients/';
var ajaxUrlList = 'ajax/admin/clients/clientList/';
var ajaxUrlRemoveList = 'ajax/admin/clients/clientList/';
var datatableApi;
var datatableApiList;

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
                "mData": "firstName"
            },
            {
                "mData": "lastName"
            },
            {
                "mData": "phoneNumber"
            },
            {
                "mData": "email"
            },
            {
                "mData": "clientList.name"
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


    datatableApiList = $('#clientsLists').DataTable({
        "sAjaxSource": ajaxUrlList,
        "sAjaxDataProp": "",
        "bPaginate": false,
        "bInfo": false,
        "aoColumns": [
            {
                "mData": "name"
            },
            {
                "mData": "description"
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderEditBtnClient
            },
            {
                "bSortable": false,
                "sDefaultContent": "",
                "mRender": renderDeleteBtnClient
            }
        ],
        "aaSorting": [
            [
                0,
                "asc"
            ]
        ],
        "initComplete": makeEditable
    });
    renderClientListsOptions();

});

function renderEditBtnClient(data, type, row) {
        return '<a class="btn btn-xs btn-primary" onclick="updateRow(' + row.id + ');">Edit</a>';
}

function renderDeleteBtnClient(data, type, row) {
    var message = 'List removed';
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRowById(\'' + row.id + '\', \'' + ajaxUrlRemoveList + '\', \'' + message + '\'  );">Delete</a>';
    }
    return data;
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

    $listSelect.onclick = function(event){
        var target = event.target;
        if (target.tagName != 'option') return;

        getClientsByList(target.value());
    };
}

function getClientsByList(id){
    $.ajax({
        url: "ajax/admin/clients/clientList/" + id,
        type: 'GET',
        dataType : "json",
        success: function (data) {
            updateTable();
            successNoty('getClientList filtered');
        }
    });
}



