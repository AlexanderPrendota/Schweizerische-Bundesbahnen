/**
 * Created by aleksandrprendota on 16.04.17.
 */


function autocompliteStation() {
    $.ajax({
        type: "GET",
        url : '/station/stations/name',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {

            $( ".autocomplite" ).autocomplete({
                source: response
            });

        },
        error: function () {
            swal("Oops...", "Some problems with getting station :( Please try later", "error");
        }
    });
}

function autocompliteTrain() {
    $.ajax({
        type: "GET",
        url : '/train/trains/name',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $( ".autocompliteTrain" ).autocomplete({
                source: response
            });
        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });
}

function autocompliteUsers() {
    $.ajax({
        type: "GET",
        url : '/user/mails',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $( ".autocompliteUsers" ).autocomplete({
                source: response
            });
        },
        error: function () {
            swal("Oops...", "Some problems with getting users mail :( Please try later", "error");
        }
    });
}

