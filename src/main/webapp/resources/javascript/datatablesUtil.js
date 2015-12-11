var form;

function makeEditable() {
    form = $('#detailsForm');

    $('#add').click(function () {
        form.find(":input").val("");
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.edit').click(function () {
        updateRow($(this).closest('tr').attr("id"));
    });
    form.submit(function () {
        save();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function updateRow(id) {
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function play(id) {
    $.ajax({
        url: ajaxUrl + 'play/' + id,
        type: 'GET',
        success: function () {
            successNoty('Task started');
        }
    });
}


function enable(chkbox) {
    var enabled = chkbox.is(":checked");
    var row = chkbox.closest('tr');
    row.css("text-decoration", enabled ? "none" : "line-through");
    $.ajax({
        url: ajaxUrl + row.attr('id'),
        type: 'POST',
        data: 'enabled=' + enabled,
        success: function () {
            successNoty(enabled ? 'Enabled' : 'Disabled');
        }
    });
}

//function updateTableByData(data) {
//    datatableApi.clear();
//    $.each(data, function (key, item) {
//        datatableApi.row.add(item);
//    });
//    datatableApi.draw();
//    init();
//}

function updateTableByData(data) {
    datatableApi.clear().rows.add(data).draw();
}

function save() {
    var form = $('#detailsForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

var failedNote;

function closeNote() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNote();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNote();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}

function renderPlayBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-success" onclick="play(' + row.id + ');">Play</a>';
    }
    return data;
}


function renderEditBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-primary" onclick="updateRow(' + row.id + ');">Edit</a>';
    }
    return data;
}

function renderDeleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-xs btn-danger" onclick="deleteRow(' + row.id + ');">Delete</a>';
    }
    return data;
}
