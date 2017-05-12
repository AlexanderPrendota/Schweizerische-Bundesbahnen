/**
 * Created by aleksandrprendota on 01.04.17.
 */

// Just only ways. No admin function.

function showSchedule() {
    $("#notice").html("Choose your favorite station!");
    $("#homeact").removeAttr("class");
    $("#find").removeAttr("onclick");
    $("#smartsearch").removeAttr("class");
    $("#sheduleact").attr("class","active");
    $("#train").remove();
    $("#trip").remove();
    $("#mainttable").css("visibility","hidden");

    $("#fon").remove();
    $("#lose").empty();
    $("#divpic").remove();
    $("#divarr").remove();
    $("#find").attr("onclick","goSchedule(event);");
    $("#maincont").append('<div id="bag"></div>');
    drawBag();

}

function goSchedule(event) {

    var stationDepartire = $("#departure");
    $("#mainttable").empty();
    $("#lose").empty();
    $("#bag").remove();

    if (stationDepartire.val() === '') {
        swal("Oops...", "Please write correct station ", "error");
        return '';
    }

    event.preventDefault();

    var url = '/schedule/station/' + stationDepartire.val() ;

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
                            "<td class='tab'>" + new Date(response[i].timeDeparture).toLocaleString() + "</td>" +
                            "<td class='tab'>" + response[i].stationArrival.stationName + "</td>"
                            + "<td class='tab'>" + new Date(response[i].timeArrival).toLocaleString() + "</td>" + "</tr>");
                }
                $("#risestable")
                    .append("</tbody></table>");
                $("#lose").append('<div id="fon"><img src="images/Landscape.png"/></div>');

                toPurchase();
            }
        },
        error: function () {
            swal("Oops...", "Wrong information!", "error");
        }
    });

}

function showSmartSearch() {
    $("#notice").html("Welcome to smart search!");
    $("#smartsearch").attr("class","active");
    $("#homeact").removeAttr("class");
    $("#sheduleact").removeAttr("class");
    $("#find").removeAttr("onclick");
    $("#fon").remove();
    $("#train").remove();
    $("#divpic").remove();
    $("#lose").empty();
    $("#divarr").remove();
    $("#bag").remove();
    $("#showsmsh").empty();
    $("#mainttable").css("visibility","hidden");
    $("#trip").remove();
    $("#find").attr("onclick","goSmartSearch(event);");
    $("#lose").append('<div id="fon"><img src="images/Landscape.png"/></div>');

    $("#showsmsh").append(
        '<div id="divarr" class="input-group way">' +
        '<span class="input-group-addon"><i class="fa fa-arrow-left" aria-hidden="true"></i></span>' +
        '<input type="text" name="arrival" id="arrival" class="form-control autocomplite" placeholder="Arrival Station"/>'+
        '</div><div id="divpic" class="input-group way">' +
        '<span class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i></span>'+
        '<input type="text" name="date" id="datepicker" class="form-control" placeholder="YYYY-MM-DD"/></div>'
    );
    goDatePicker();
    autocompliteStation();
}

function goSmartSearch(event) {
    $("#mainttable").empty();
    $("#lose").empty();
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

    var url = 'schedule/transfer/departure/' + departure.val() + '/arrival/' + arrival.val() + '/date/' + date.val();

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
                    "<h1 class='title'>No ways, Darling! Even Smart! :( </h1>" +
                    "</div></div></div>");
            } else {
                var countOfWay = 1;
                var st_arrival = $("#arrival").val();
                var theFirstsWay = [];
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
                    if (response[i].stationArrival.stationName != st_arrival){
                        theFirstsWay = response[i];
                        console.log(theFirstsWay);
                    } else {
                        if (theFirstsWay.length != 0){

                            $("#risestable")
                                .append('<tr class="blank_row"><td><h5>'+ countOfWay+'.</h5></td><td></td><td></td><td></td><td></td></tr>');
                            countOfWay++;
                            $("#risestable")
                                .append("<tr id='"+theFirstsWay.id+"' class='tab'>" +
                                    "<td class='tab'>" + theFirstsWay.train.id + "</td>" +
                                    "<td class='tab'>" + theFirstsWay.stationDeparture.stationName + "</td>" +
                                    "<td class='tab'>" +  new Date(theFirstsWay.timeDeparture).toLocaleString() + "</td>" +
                                    "<td class='tab'>" + theFirstsWay.stationArrival.stationName + "</td>"
                                    + "<td class='tab'>" + new Date(theFirstsWay.timeArrival).toLocaleString() + "</td>" + "</tr><hr/>");
                        }
                        $("#risestable")
                            .append("<tr id='"+response[i].id+"' class='tab'>" +
                                "<td class='tab'>" + response[i].train.id + "</td>" +
                                "<td class='tab'>" + response[i].stationDeparture.stationName + "</td>" +
                                "<td class='tab'>" +  new Date(response[i].timeDeparture).toLocaleString() + "</td>" +
                                "<td class='tab'>" + response[i].stationArrival.stationName + "</td>"
                                + "<td class='tab'>" + new Date(response[i].timeArrival).toLocaleString() + "</td>" + "</tr><hr/>");
                    }

                }
                $("#risestable")
                    .append("</tbody></table>");
                toPurchase();
            }
        },
        error: function () {
            swal("Oops...", "Wrong information!", "error");
        }
    });
}