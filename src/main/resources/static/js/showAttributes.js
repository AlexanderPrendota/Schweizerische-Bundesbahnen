/**
 * Created by aleksandrprendota on 31.03.17.
 */

function showAllTrains() {

    $("#fon").remove();
    $("#source").empty();
    $("#logo").empty();
    $("#lose").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Manage Trains</h1>' +
        '</div>' +
        '</div></div>'
    );

    $.ajax({
        type: "GET",
        url : '/train/admin/alltrains',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {

            $("#source")
                .append('<div class="col-sm-12 prop rounded center ">' +
                    '<div class="col-sm-12">' +
                    '<div class="dt-buttons btn-group">' +
                    '<a id="newtrain" onclick="addTrains();" class="btn btn-default buttons-create" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>New</span></a>' +
                    '<a onclick="showrowTrain();" class="btn btn-default buttons-selected buttons-remove" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>Delete</span></a></div></div>'+
                    "<table id='risestable' class='table table-hover'>");
            $("#risestable")
                .append("<thead>" +
                    "<tr class='tab'>" +
                        "<th class='tab center'>Train Number</th>" +
                    "</tr>"
                    + "</thead>"
                    + "<tbody>"
                );

            for (var i = 0; i < response.length; i++) {
                $("#risestable")
                    .append(
                        "<tr class='tab'>" +
                        "<td class='tab'>" + response[i].id + "</td>" +
                        + "</tr>");
            }
            $("#risestable")
                .append("</tbody></table></div>");

            showdeleteButton();
            showConfortTable();
            selectedRow();

        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });
}

function showAllStation() {

    $("#fon").remove();
    $("#source").empty();
    $("#logo").empty();
    $("#lose").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Manage stations</h1>' +
        '</div>' +
        '</div></div>'
    );


    $.ajax({
        type: "GET",
        url : '/station/allstations',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {

            $("#source")
                .append('<div class="col-sm-12 prop rounded center">' +
                    '<div class="col-sm-12">' +
                    '<div class="dt-buttons btn-group">' +
                    '<a id="newstation" onclick="addStations();" class="btn btn-default buttons-create" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>New</span></a>' +
                    '<a onclick="showrowStation();" class="btn btn-default buttons-selected buttons-remove" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>Delete</span></a>'+
                    '<a onclick="showGraph();" class="btn btn-default buttons-selected buttons-create" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>Show Graph</span></a></div></div>'+
                    "<table id='risestable' class='table table-hover'>");
            $("#risestable")
                .append("<thead>" +
                    "<tr class='tab'>" +
                    "<th class='tab center'>Station Name</th>" +
                    "<th class='tab center'>Coordinates</th>" +
                    "</tr>"
                    + "</thead>"
                    + "<tbody>"
                );

            for (var i = 0; i < response.length; i++) {
                $("#risestable")
                    .append(
                        "<tr class='tab'>" +
                        "<td class='tab'>"+ response[i].stationName + "</td>" +
                        "<td class='tab'>("+ response[i].x + ";" + response[i].y + ")</td>" +
                        + "</tr>");
            }
            $("#risestable")
                .append("</tbody></table></div>");

            showdeleteButton();
            showConfortTable();
            selectedRow();

        },
        error: function () {
            swal("Oops...", "Some problems with getting station :( Please try later", "error");
        }
    });


}

function showAllUsers() {

    $.ajax({
        type: "GET",
        url : '/user/allusers',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {

            if (response.length === 0 || response == null) {
                $("#lose").append("<div class='container'>" +
                    "<div class='panel-heading'>" +
                    "<div class='panel-title text-center'>" +
                    "<h1 class='title'>No users in database! :( </h1>" +
                    "</div></div></div>");

            } else{
                $("#source")
                    .append('<div class="col-sm-12 prop rounded center ">' +
                        "<table id='risestable' class='table table-hover '>")
                $("#risestable")
                    .append("<thead>" +
                        "<tr class='tab'>" +
                        "<th class='tab center'>Email</th>" +
                        "<th class='tab center'>First Name</th>" +
                        "<th class='tab center'>Last Name</th>" +
                        "</tr>"
                        + "</thead>"
                        + "<tbody>"
                    );

                for (var i = 0; i < response.length; i++) {
                    $("#risestable")
                        .append(
                            "<tr class='tab'>" +
                            "<td class='tab'><input class='btn btn-danger' onclick='showrowUser();' type='submit' value='X'/>" + response[i].email + "</td>" +
                            "<td class='tab'>" + response[i].firstname + "</td>" +
                            "<td class='tab'>" + response[i].lastname + "</td>" +
                            + "</tr>");
                }
                $("#risestable")
                    .append("</tbody></table></div>");
                showdeleteButton();
                showConfortTable()
            }
        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });

}

function showAllSchedule() {
    $("#fon").remove();
    $("#source").empty();
    $("#logo").empty();
    $("#lose").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Manage Schedule!</h1>' +
        '</div>' +
        '</div></div>'
    );
    $.ajax({
        type: "GET",
        url : '/schedule/all',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {

            if (response.length === 0 || response == null) {
                $("#lose").append("<div class='container'>" +
                    "<div class='panel-heading'>" +
                    "<div class='panel-title text-center'>" +
                    "<h1 class='title'>No schedules in database! :( </h1>" +
                    "</div></div></div>");
                scheduleAppendix();

            } else{

                scheduleAppendix();

                for (var i = 0; i < response.length; i++) {
                    $("#risestable")
                        .append(
                            "<tr class='tab'>" +
                            "<td class='tab'>"+ response[i].id + "</td>" +
                            "<td class='tab'>" + new Date(response[i].timeDeparture).toLocaleString()  + "</td>" +
                            "<td class='tab'>" + new Date(response[i].timeArrival).toLocaleString() + "</td>" +
                            "<td class='tab'>" + response[i].train.id + "</td>" +
                            "<td class='tab'>" + response[i].stationDeparture.stationName + "</td>" +
                            "<td class='tab'>" + response[i].stationArrival.stationName + "</td>" +
                            + "</tr>");
                }
                $("#risestable")
                    .append("</tbody></table></div>");
                showdeleteButton();
                showConfortTable();
                selectedRow();
            }
        },
        error: function () {
            swal("Oops...", "Some problems with getting train :( Please try later", "error");
        }
    });
}

function showrowTrain() {
    // $("tr.tab").click(function() {
    //
    //     var tableData = $(this).children("td").map(function() {
    //         return $(this).text();
    //     }).get();
        //console.log(tableData);
    var shosen = localStorage.getItem('chosen_item');
    var train = JSON.parse(shosen);
    if (localStorage.length > 0) {
        swal({
                title: "Are you sure?",
                text: "Do u want to delete " + train,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#ddae19",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false },
            function(){
                deleteTrain(train[0]);
                localStorage.clear();
            });
    } else {
        swal({
            title: "Ops!",
            text: "Please choose the train !",
            type: "error"
        });
    }
    // });
}

function showrowStation() {
    // $("tr.tab").click(function() {
    //
    //     var tableData = $(this).children("td").map(function() {
    //         return $(this).text();
    //     }).get();
        //console.log(tableData);
    var shosen = localStorage.getItem('chosen_item');
    var station = JSON.parse(shosen);
    if (localStorage.length > 0) {
        swal({
                title: "Are you sure?",
                text: "Do u want to delete " + station,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#ddae19",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false },
            function(){
                deleteStation(station[0]);
                localStorage.clear();
            });
    } else {
        swal({
            title: "Ops!",
            text: "Please choose the station !",
            type: "error"
        });
    }
    // });
}

function showrowUser() {
    $("tr.tab").click(function() {

        var tableData = $(this).children("td").map(function() {
            return $(this).text();
        }).get();
        console.log(tableData);
        swal({
                title: "Are you sure?",
                text: "Do u want to delete " + tableData,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false },
            function(){
                deleteUser(tableData);
            });
    });
}

function showrowSchedule() {
    // $("tr.tab").click(function() {
    //
    //     var tableData = $(this).children("td").map(function() {
    //         return $(this).text();
    //     }).get();
    //     console.log(tableData);
        var shosen = localStorage.getItem('chosen_item');
        var schedule = JSON.parse(shosen);
        if (localStorage.length > 0) {
            swal({
                    title: "Are you sure?",
                    text: "Do u want to delete " + schedule,
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#ddae19",
                    confirmButtonText: "Yes, delete it!",
                    closeOnConfirm: false },
                function(){
                    deleteSchedule(schedule);
                    localStorage.clear();
                });
        } else {
            swal({
                title: "Ops!",
                text: "Please choose the schedule !",
                type: "error"
            });
        }

 //   });
}

function showEditSchedule() {
    var shosen = localStorage.getItem('chosen_item');
    var schedule = JSON.parse(shosen);
    if (localStorage.length > 0) {
            updateSchedule(schedule);
            localStorage.clear();
    } else {
        swal({
            title: "Ops!",
            text: "Please choose the schedule !",
            type: "error"
        });
    }
}

function showdeleteButton() {
    $(document).ready(function () {
        $(document).on('mouseenter', '.tab', function () {
            $(this).find(":input").show();
        }).on('mouseleave', '.tab', function () {
            $(this).find(":input").hide();
        });
    });

}

function showConfortTable() {
    $("#risestable").dataTable({
        "iDisplayLength": 100
    });
}

function selectedRow() {
    localStorage.clear();
    $("#risestable tr").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
        //var value=$(this).find('td:first').html();

  //      $("tr.tab").click(function() {
            var tableData = $(this).children("td").map(function() {
                return $(this).text();
            }).get();
            localStorage.setItem('chosen_item', JSON.stringify(tableData));
   //     });
    });
}

function scheduleAppendix() {
    $("#source")
        .append('<div class="col-sm-12 prop rounded center" style="width: 1000px;">' +
            '<div class="col-sm-12">' +
            '<div class="dt-buttons btn-group">' +
            '<a id="newschedule" onclick="addSchedule();" class="btn btn-default buttons-create" tabindex="0" aria-controls="risestable" href="#">' +
            '<span>New</span></a>' +
            '<a onclick="showEditSchedule();" class="btn btn-default buttons-selected buttons-edit" tabindex="0" aria-controls="risestable" href="#">' +
            '<span>Edit</span></a>' +
            '<a onclick="showrowSchedule();" class="btn btn-default buttons-selected buttons-remove" tabindex="0" aria-controls="risestable" href="#">' +
            '<span>Delete</span></a></div></div>'+
            "<table id='risestable' style='width: 800px;' class='table table-hover '>");
    $("#risestable")
        .append("<thead>" +
            "<tr class='tab'>" +
            "<th class='tab center'>ID</th>" +
            "<th class='tab center'>Time Departure</th>" +
            "<th class='tab center'>Time Arrival</th>" +
            "<th class='tab center'>Train</th>" +
            "<th class='tab center'>Station Departure</th>" +
            "<th class='tab center'>Station Arrival</th>" +
            "</tr>"
            + "</thead>"
            + "<tbody>"
        );
}
