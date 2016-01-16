$(document).ready(function() {
    var form = $('#editUser');

    form.submit(function(e){

        e.preventDefault();

        $.ajax({
            type: "POST",
            url: "ajax/admin/users/",
            data: form.serialize(),
            success: function () {
                successNoty('Update');
            }
        });

    });
});
