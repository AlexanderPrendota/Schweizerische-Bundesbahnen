/**
 * Created by aleksandrprendota on 28.03.17.
 */
function viewParamsInPurchasePage() {
    $.ajax({
        type: "GET",
        url : '/api/account/user',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#firstname").attr("value", response.firstname);
            $("#lastname").attr("value", response.lastname);
            $("#email").attr("value", response.email);
            $("#birthday").attr("value", response.birthday);
        },
        error: function () {
            swal("Oops...", "Wrong information!", "error");
        }
    });
    getTotalCarriage();
    drawTicket();
    getPrice();
}
function getPrice() {

    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);

    var dep_st = trip.station_departure;
    var arr_st = trip.station_arrival;
    $.ajax({
        type: "GET",
        url : '/price/departure/' + dep_st + '/arrival/' + arr_st,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#pricelabel").attr("value", response);
        },
        error: function () {
            swal("Oops...", "Problems with getting price!", "error");
        }
    });
}
function buyRide() {
    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);
    var carriage = $('#carriage');
    var number = $('#seat');

    $.ajax({
        type: "POST",
        url : '/purchase/ride/' + trip.id + '/carriage/' + carriage.val() + '/seat/' + number.val(),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            swal({
                title: "You bought the ride!",
                text: "We've already sent a ticket to your email!",
                type: "success",
                confirmButtonColor: "#77dd55",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){
                window.location = "/account";
            });
        },
        error: function (error) {
            swal("Oops...", error.responseText, "error");
            // swal({
            //     title: "Oops...",
            //     text: error.responseText,
            //     type: "error",
            //     confirmButtonColor: "#dd5d3e",
            //     confirmButtonText: "OK!",
            //     closeOnConfirm: false }, function(){
            //    // history.go(-1);
            //
            // });

        }
    });
}
