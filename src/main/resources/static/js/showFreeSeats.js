/**
 * Created by aleksandrprendota on 15.04.17.
 */

function showFreeSeats() {
    var carriage = $('#carriage');
    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue); // train_number time_departure
    var url = 'seats/freeseats/train/' + trip.train_number + '/time/' + trip.time_departure + '/carriage/' + carriage.val();


    $.ajax({
        type: "GET",
        url : url,
        contentType: "application/json; charset=utf-8",
        success: function (response) {

            appendTheCabine();

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

function appendTheCabine() {


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

    dialogTrainCarriage();

}

function getTotalCarriage() {

    var tripValue = localStorage.getItem('trip');
    var trip = JSON.parse(tripValue);

    $.ajax({
        type: "GET",
        url : "seats/countofcarriage/train/" + trip.train_number,
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

function dialogTrainCarriage(){
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

function setSeat(button) {
    $('#seat').val(button.id);
    try {
        $("#dialog").dialog('close');
    } catch(e) {

    }

}
