/**
 * Created by aleksandrprendota on 06.05.17.
 */
$( document ).ready(function() {
    $.ajax({
        type: "GET",
        url : '/adminparam',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            if ("true".localeCompare(response) == 0){
                $('#navright').prepend('<li><a href="/admin">Admin</a></li>');
                $('.chat_window').remove();
                $('.chat').remove();
            }
        },
        error: function () {}
    });
});
