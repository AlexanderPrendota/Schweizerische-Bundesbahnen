/**
 * Created by aleksandrprendota on 06.05.17.
 */
$( document ).ready(function() {
    $.ajax({
        type: "GET",
        url : '/adminparam',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function () {
            $('#navright').prepend('<li><a href="/admin">Admin</a></li>');
            $('.chat_window').remove();
            $('.chat').remove();
        },
        error: function () {}
    });
});
