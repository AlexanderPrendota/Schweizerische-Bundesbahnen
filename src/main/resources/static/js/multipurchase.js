/**
 * Created by aleksandrprendota on 17.05.17.
 */

function loadMultipurchaseParams() {
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

    getPrice();
    getTotalCarriageFirst();
    getTotalCarriageSecond();
}

function getPrice() {

    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);

    var dep1 = trip[0].stationDeparture.stationName;
    var arr1 = trip[0].stationArrival.stationName;
    var dep2 = trip[1].stationDeparture.stationName;
    var arr2 = trip[1].stationArrival.stationName;

    $.ajax({
        type: "GET",
        url : '/price/departure/' + dep1 + '/arrival/' + arr1,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response1) {
            $.ajax({
                type: "GET",
                url : '/price/departure/' + dep2 + '/arrival/' + arr2,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (response) {
                    $("#pricelabel").attr("value", response1 + response);
                }
            });
        },
        error: function () {
            swal("Oops...", "Problems with getting price!", "error");
        }
    });
}

function getTotalCarriageFirst() {

    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);

    $.ajax({
        type: "GET",
        url : "seats/countofcarriage/train/" + trip[0].train.id,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            for (var i = 1; i <= response; i++){
                $("#carriage").append(
                    '<option>' + i + '</option>'
                )
            }
        },
        error: function (error) {
            swal({
                title: "Oops...",
                text: "Some problems with getting total carriage",
                type: "error",
                confirmButtonColor: "#dd5d3e",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){
            });

        }
    });

}

function getTotalCarriageSecond() {

    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);

    $.ajax({
        type: "GET",
        url : "seats/countofcarriage/train/" + trip[1].train.id,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            for (var i = 1; i <= response; i++){
                $("#carriage1").append(
                    '<option>' + i + '</option>'
                )
            }

        },
        error: function (error) {
            swal({
                title: "Oops...",
                text: "Some problems with getting total carriage",
                type: "error",
                confirmButtonColor: "#dd5d3e",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){
            });

        }
    });

}

function showFreeSeatsFirstWay() {
    var carriage = $('#carriage');
    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);

    var url = 'seats/freeseats/train/' + trip[0].train.id + '/time/' + new Date(trip[0].timeDeparture).toLocaleString() + '/carriage/' + carriage.val();


    $.ajax({
        type: "GET",
        url : url,
        contentType: "application/json; charset=utf-8",
        success: function (response) {

            appendCabineFirst();

            for (var i = 0; i < response.length; i++){
                $('#' + response[i]).css("color", "black");
                $('#' + response[i]).css("background-color", "#dd5d3e");
            }

        },
        error: function (error) {
            swal({
                title: "Oops...",
                text: "Some problems with getting seats",
                type: "error",
                confirmButtonColor: "#dd5d3e",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){

            });
        }
    });

}

function showFreeSeatsSecondWay() {
    var carriage = $('#carriage1');
    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue); // train_number time_departure
    var url = 'seats/freeseats/train/' + trip[1].train.id + '/time/'
        + new Date(trip[1].timeDeparture).toLocaleString() + '/carriage/' + carriage.val();


    $.ajax({
        type: "GET",
        url : url,
        contentType: "application/json; charset=utf-8",
        success: function (response) {

            appendCabineSecond();

            for (var i = 0; i < response.length; i++){
                $('#' + response[i]).css("color", "black");
                $('#' + response[i]).css("background-color", "#dd5d3e");
            }

        },
        error: function (error) {
            swal({
                title: "Oops...",
                text: "Some problems with getting seats",
                type: "error",
                confirmButtonColor: "#dd5d3e",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){
                // history.go(-1);

            });
        }
    });

}

function appendCabineFirst() {


    $('#dialog1').empty();
    $('#dialog').empty();
    $('#dialog').append(
        '<div class="prop rounded" style="width: 700px;">' +
        '<div class="btn-group btn-group-justified">' +
        '<div class="btn-group">' +
        '<input id="1" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">1</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="3" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">3</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="5" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">5</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="7" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">7</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="9" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">9</input>'+
        '</div>' +
        '</div>' +
        '<div class="btn-group btn-group-justified carriageroad">' +
        '<div class="btn-group">' +
        '<input id="2" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">2</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="4" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">4</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="6" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">6</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="8" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">8</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="10" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">10</input>'+
        '</div>' +
        '</div>' +
        '<div class="btn-group btn-group-justified" >' +
        '<div class="btn-group">' +
        '<input id="12" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">12</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="14" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">14</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="16" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">16</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="18" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">18</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="20" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">20</input>'+
        '</div>' +
        '</div>' +
        '<div class="btn-group btn-group-justified">' +
        '<div class="btn-group">' +
        '<input id="11" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">11</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="13" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">13</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="15" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">15</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="17" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">17</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="19" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeat(this)">19</input>'+
        '</div>' +
        '</div>' +
        '</div>'
    );

    dialogCarriageFirst();

}

function appendCabineSecond() {

    $('#dialog1').empty();
    $('#dialog').empty();
    $('#dialog1').append(
        '<div class="prop rounded" style="width: 700px;">' +
        '<div class="btn-group btn-group-justified">' +
        '<div class="btn-group">' +
        '<input id="1" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">1</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="3" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">3</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="5" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">5</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="7" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">7</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="9" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">9</input>'+
        '</div>' +
        '</div>' +
        '<div class="btn-group btn-group-justified carriageroad">' +
        '<div class="btn-group">' +
        '<input id="2" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">2</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="4" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">4</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="6" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">6</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="8" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">8</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="10" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">10</input>'+
        '</div>' +
        '</div>' +
        '<div class="btn-group btn-group-justified" >' +
        '<div class="btn-group">' +
        '<input id="12" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">12</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="14" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">14</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="16" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">16</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="18" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">18</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="20" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">20</input>'+
        '</div>' +
        '</div>' +
        '<div class="btn-group btn-group-justified">' +
        '<div class="btn-group">' +
        '<input id="11" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">11</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="13" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">13</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="15" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">15</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="17" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">17</input>'+
        '</div>' +
        '<div class="btn-group">' +
        '<input id="19" type="image" style="width: 100px; height: 50px; margin-right: 5px;" src="images/seat.svg" class="btn btn-default" onclick="setSeatSecond(this)">19</input>'+
        '</div>' +
        '</div>' +
        '</div>'
    );

    dialogCarriageSecond();

}

function dialogCarriageFirst(){
    $( function() {
        $( "#dialog" ).dialog({
            modal: true,
            closeOnEscape: true,
            maxWidth:750,
            maxHeight: 400,
            width: 750,
            height: 400,
            resizable: false,
            title: "Choose the free seat"
        });
    } );
}

function dialogCarriageSecond(){
    $( function() {
        $( "#dialog1" ).dialog({
            modal: true,
            closeOnEscape: true,
            maxWidth:750,
            maxHeight: 400,
            width: 750,
            height: 400,
            resizable: false,
            title: "Choose the free seat"
        });
    } );
}

function setSeat(button) {
    $('#seat').val(button.id);
    try {
        $("#dialog").dialog('close');
    } catch(e) {

    }

}

function setSeatSecond(button) {
    $('#seat1').val(button.id);
    try {
        $("#dialog1").dialog('close');
    } catch(e) {

    }

}

function buyTransferRide() {
    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);
    var carriage = $('#carriage').val();
    var number = $('#seat').val();
    var carriage1 = $('#carriage1').val();
    var number1 = $('#seat1').val();
    
    $.ajax({
        type: "POST",
        url : '/purchase/multi/c/' + carriage + '/n/' + number + '/c1/' + carriage1 + '/n1/' + number1,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(trip),
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
        }
    });
}


