/**
 * Created by aleksandrprendota on 19.04.17.
 */

function showRideDetail(id) {
    $("#dialog").empty();
    $("#dialog").append(
        '<div class="row main">' +
            '<div class="panel-heading">' +
            '<div class="panel-title text-center">' +
            '<h1 id="mainuser" class="title">Ride Detail №' + id + '</h1>' +
            '<hr/></div></div></div>'
    );

    
    $('dialog').append(
        '<div id="fon"><img src="images/'+ id +'.png"/></div>'
    );

    $.ajax({
        type: "GET",
        url : "/api/account/ride/" + id,
        contentType: "application/json; charset=utf-8",
        success: function (response) {

            var dateDeparture = response.timeDeparture;
            var dateArrival = response.timeArrival;
            var timeInTrinp = parseInt(dateArrival) - parseInt(dateDeparture);
            var hoursDif = timeInTrinp / (3600 * 1000);
            var minutesDif = timeInTrinp % 60;
            console.log(timeInTrinp);
            console.log(dateDeparture);
            console.log(dateArrival);
            $("#dialog").append(
            '<h2>Dear <u>' + $('#firstname').val() + '</u></h2>' +
            '<h2>Your train is <u>' + response.train.id + '</u></h2>' +
            '<h2>Be ready on <u>' + new Date(dateDeparture).toLocaleString() + '</u></h2>' +
            '<h3>Also don\'t forget your books or computer because You will be in pleasure about <u>' +
            + hoursDif + ' hours</u> and <u>' + minutesDif + ' minutes</u></h3>' +
                '<h2>Remember, your seat number:  <u>'+ response.seat.number + '</u>' +
            ' and train carriage: <u>'+ response.seat.cabine + '</u></h2>' +
            '<h2>Your train arrives <u>' +  new Date(dateArrival).toLocaleString() +'</u></h2>'
            );
            $('#dialog').append(
                '<h3>We\'ve prepared <u>QR-CODE</u>. Show one in Train. Good luck, '+ $('#firstname').val() + '</h3>' +
                '<div id="fon"><img src="images/'+ id +'.jpg"/></div>'
            );
        },
        error: function (error) {

            swal("Oops...",  error.responseText, "error");
        }
    });
    
    dialogRideDetail();
}

function dialogRideDetail(){
    $( function() {
        $( "#dialog" ).dialog({
            modal: true,
            closeOnEscape: true,
            maxWidth:600,
            maxHeight: 800,
            width: 600,
            height: 800,
            resizable: false,
            title: "Your ticket detail"
        });
    } );
}
