var ajaxUrl = 'ajax/admin/results/';
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
                "mData": "dateTime"
            },
            {
                "mData": "task.name"
            },
            {
                "mData": "client.firstName"
            },
            {
                "mData": "client.phoneNumber"
            },
            {
                "mData": "result"
            },
            {
                "mData": "reason"
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
        "initComplete": function () {
            $('#filter').submit(function () {
                updateTable();
                return false;
            });

            var startDate = $('#startDate');
            var endDate = $('#endDate');
            startDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'ru',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        maxDate: endDate.val() ? endDate.val() : false
                    })
                }
            });
            endDate.datetimepicker({
                timepicker: false,
                format: 'Y-m-d',
                lang: 'en',
                formatDate: 'Y-m-d',
                onShow: function (ct) {
                    this.setOptions({
                        minDate: startDate.val() ? startDate.val() : false
                    })
                }
            });

            $('.time-picker').datetimepicker({
                datepicker: false,
                format: 'H:i',
                lang: 'en'
            });

            $('#dateTime').datetimepicker({
                format: 'Y-m-d\\TH:i',
                lang: 'en'
            });

            makeEditable();
        }
    });
});