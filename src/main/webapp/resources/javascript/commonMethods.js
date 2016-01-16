function deleteRowById(id, url, notification) {
    $.ajax({
        url: url + id,
        type: 'DELETE',
        success: function () {
            //updateTable();
            successNoty(notification);
        }
    });
}

function updateTableByUrl(url) {
    $.get(url, function (data) {
        updateTableByData(data);
    });
}

function chooseTypeOfObjects(){

}





