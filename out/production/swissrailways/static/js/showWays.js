/**
 * Created by aleksandrprendota on 29.03.17.
 */

function showWays(event) {

    $("#notice").html("We found a little bit ways for you");
    $("#mainttable").empty();
    $("#lose").empty();
    $("#train").remove();
    $("#maincont").append('<div id="trip"></div>');

    var departure = $("#departure");
    var arrival = $("#arrival");
    var date = $("#datepicker");




   event.preventDefault();

    if (departure.val() === '') {
        swal("Oops...", "Please write correct departure station", "error");
        return '';
    }

    if (arrival.val() === '') {
        swal("Oops...", "Please write arrival station", "error");
        return '';
    }

    if (date.val() === '') {
        swal("Oops...", "Please write correct date ", "error");
        return '';
    }


    var url = '/schedule/departure/' + departure.val() + '/arrival/' + arrival.val() + '/date/' + date.val();

    $.ajax({
    type: "GET",
    url : url,
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: function (response) {
        if (response.length === 0 || response == null){
            $("#mainttable").css("visibility","hidden");
            $("#lose").append("<div class='container'>" +
                "<div class='panel-heading'>" +
                "<div class='panel-title text-center'>" +
                "<h1 class='title'>No ways, Darling! :( </h1>" +
                "</div></div></div>");
            drawTrip();

            $("#fon").append('<img src="images/Landscape.png"/>');
        } else {
            $("#mainttable").css("visibility","visible");
            $("#mainttable")
                .append("<table id='risestable' class='table table-hover'>");
            $("#risestable")
                .append("<thead>" +
                    "<tr class='tab'>" +
                    "<th class='tab'><h4>Train Number</h4></th>" +
                    "<th class='tab'><h4>Station Departure</h4></th>" +
                    "<th class='tab'><h4>Departure</h4></th>" + "<th class='tab'><h4>Station Arrival</h4></th>" +
                    "<th class='tab'><h4>Arrival</h4></th>" + "</tr>" + "</thead>" + "<tbody>");
            for (var i = 0; i < response.length; i++) {
                $("#risestable")
                    .append("<tr id='"+response[i].id+"' class='tab'>" +
                    "<td class='tab'>" + response[i].train.id + "</td>" +
                    "<td class='tab'>" + response[i].stationDeparture.stationName + "</td>" +
                    "<td class='tab'>" +  new Date(response[i].timeDeparture).toLocaleString() + "</td>" +
                    "<td class='tab'>" + response[i].stationArrival.stationName + "</td>"
                        + "<td class='tab'>" + new Date(response[i].timeArrival).toLocaleString() + "</td>" + "</tr>");
            }
            $("#risestable")
                .append("</tbody></table>");
            $("#fon").append('<img src="images/Landscape.png"/>');

            drawTrip();

            toPurchase();
        }
    },
    error: function () {
        swal("Oops...", "Wrong information!", "error");
    }
    });

}
