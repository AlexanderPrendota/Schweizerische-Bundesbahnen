/**
 * Created by aleksandrprendota on 30.03.17.
 */

function showUser() {
    $("#adminbutton").attr("class","active");
    $("#supportbutton").removeAttr("class");
    $("#statistics").removeAttr("class");
    $("#source").empty();
    $("#logo").empty();
    $("#fon").remove();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Manage Users!</h1>' +
        '</div>' +
        '</div></div>'
    );
    // $("#source").append(
    //     '<div class="col-sm-4 prop rounded">' +
    //     '<div class="input-group way station">' +
    //     '<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>' +
    //     '<input type="text" name="useremail" id="useremail" class="form-control " placeholder="Email">'+
    //     '</div>' + '<div>' +
    //     '<input onclick="deleteUser();" class="btn btn-warning" type="submit" id="deleteuser" value="Delete User"/>' +
    //     '</div>' + '</div>'
    // );
    showAllUsers();

}

function addNotify() {
    $("#adminbutton").attr("class","active");
    $("#supportbutton").removeAttr("class");
    $("#statistics").removeAttr("class");
    $("#source").empty();
    $("#logo").empty();
    $("#fon").remove();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Send information to all users!</h1>' +
        '</div>' +
        '</div></div>'
    );
    $("#source").append(
        '<div class="col-sm-4 prop rounded">' +
            '<div class="input-group way station">' +
                '<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>' +
                '<input type="text" name="subject" id="subject" class="form-control " placeholder="subject">'+
            '</div>' +
            '<div class="input-group way station">' +
                '<span class="input-group-addon"><i class="fa fa-file-text-o" aria-hidden="true"></i></span>' +
                '<input type="text" id="letter" name="letter" class="form-control" placeholder="Message"/>' +
            '</div>' + '<div>' +
        '<input onclick="goToSend();" class="btn btn-info" type="submit" id="send" value="Send"/>' +
        '</div>' + '</div>'
    );
}

function addTrains() {
    $("#dialog").empty();
    $("#logo").empty();
    $("#fon").remove();
    $("#logo").append(
        '<div class="container">' +
            '<div class="panel-heading">' +
                '<div class="panel-title text-center">' +
                    '<h1 class="title">Manage Trains!</h1>' +
                '</div>' +
            '</div></div>'
    );
    $("#dialog").append(
        '<div class="col-sm-12 prop rounded">' +
        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-subway" aria-hidden="true"></i></span>' +
        '<input type="text" id="trainname" name="trainname" class="form-control" placeholder="Name of train">' +
        '</div>' +
        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-suitcase" aria-hidden="true"></i></span>' +
        '<select id="carriage" name="carriage" class="form-control">' +
        '<option>1</option>' +
        '<option>2</option>' +
        '<option>3</option>' +
        '<option>4</option>' +
        '<option>5</option></select>' +
        '</div>' +
        '<input class="btn btn-info" onclick="goAddTrain();" type="submit" id="addtrain" value="Add Train"/>' +
        '</div>'
    );
    showAllTrains();
    goDialogTrain();
}

function addSchedule() {

    $("#fon").remove();
    $("#dialog").empty();
    $("#logo").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Add The Schedule!</h1>' +
        '</div>' +
        '</div></div>'
    );
    $("#dialog").append(
        '<div class="col-sm-4 prop rounded">' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-subway" aria-hidden="true"></i></span>' +
        '<input type="text" id="trainnameinschedule" name="trainnameinschedule" class="form-control autocompliteTrain" placeholder="Name of train">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-arrow-right" aria-hidden="true"></i></span>' +
        '<input type="text" id="station_departure" name="station_departure" class="form-control autocomplite" placeholder="Station departure">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-calendar-o" aria-hidden="true"></i></span>' +
        '<input type="text" id="time_departure" name="time_departure" class="form-control datepicker" placeholder="Date departure">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-arrow-left" aria-hidden="true"></i></span>' +
        '<input type="text"id="station_arrival" name="station_arrival" class="form-control autocomplite" placeholder="Station arrival">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>' +
        '<input type="text" id="time_arrival" name="time_arrival" class="form-control datepicker" placeholder="Date arrival">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-hourglass-start" aria-hidden="true"></i></span>' +
        '<input type="text" id="timeD" name="timeD" class="form-control timepicker" placeholder="Time departure">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-hourglass-end" aria-hidden="true"></i></span>' +
        '<input type="text" id="timeE" name="timeE" class="form-control timepicker" placeholder="Time arrival">' +
        '</div>' +

        '<input class="btn btn-info" onclick="goAddSchedule();" type="submit" id="addschedule" value="Add Schedule"/></div>' +
        '</div>'

    );
    autocompliteTrain();
    autocompliteStation();
    showAllSchedule();
    goesDatePicker();
    goTimePicker();
    goDialogSchedule();

}

function updateSchedule(data){
    $("#fon").remove();
    $("#dialog").empty();
    $("#logo").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Add The Schedule!</h1>' +
        '</div>' +
        '</div></div>'
    );
    $("#dialog").append(
        '<div class="col-sm-4 prop rounded">' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-smile-o" aria-hidden="true"></i></span>' +
        '<input type="text" id="schedule_id" name="schedule_id" class="form-control" readonly="readonly" placeholder="Schedule id">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-subway" aria-hidden="true"></i></span>' +
        '<input type="text" id="trainnameinschedule" name="trainnameinschedule" class="form-control " placeholder="Name of train">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-arrow-right" aria-hidden="true"></i></span>' +
        '<input type="text" id="station_departure" name="station_departure" class="form-control" placeholder="Station departure">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-calendar-o" aria-hidden="true"></i></span>' +
        '<input type="text" id="time_departure" name="time_departure" class="form-control datepicker" placeholder="Date departure">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-arrow-left" aria-hidden="true"></i></span>' +
        '<input type="text"id="station_arrival" name="station_arrival" class="form-control" placeholder="Station arrival">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-calendar-check-o" aria-hidden="true"></i></span>' +
        '<input type="text" id="time_arrival" name="time_arrival" class="form-control datepicker" placeholder="Date arrival">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-hourglass-start" aria-hidden="true"></i></span>' +
        '<input type="text" id="timeD" name="timeD" class="form-control timepicker" placeholder="Time departure">' +
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-hourglass-end" aria-hidden="true"></i></span>' +
        '<input type="text" id="timeE" name="timeE" class="form-control timepicker" placeholder="Time arrival">' +
        '</div>' +

        '<input class="btn btn-info" onclick="confirmUpdateSchedule();" type="submit" id="addschedule" value="Update"/></div>' +
        '</div>'

    );
    $("#schedule_id").attr("value", data[0]);
    $("#trainnameinschedule").attr("value", data[3]);
    $("#station_departure").attr("value", data[4]);
    $("#station_arrival").attr("value", data[5]);
    showAllSchedule();
    goesDatePicker();
    goTimePicker();
    goDialogSchedule();

}

function addStations() {
    $("#dialog").empty();
    $("#logo").empty();
    $("#fon").remove();
    $("#logo").append(
        '<div class="container">' +
            '<div class="panel-heading">' +
                '<div class="panel-title text-center">' +
                    '<h1 class="title">Manage Stations!</h1>' +
                '</div>' +
        '</div></div>'
    );
    $("#dialog").append(
        '<div class="col-sm-4 prop rounded">' +
        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-home" aria-hidden="true"></i></span>' +
        '<input type="text" name="departure" id="stationname" class="form-control " placeholder="Station">'+
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-bar-chart" aria-hidden="true"></i></span>' +
        '<input type="text" name="x_cor" id="x_cor" class="form-control " placeholder="x">'+
        '</div>' +

        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-bar-chart" aria-hidden="true"></i></span>' +
        '<input type="text" name="y_cor" id="y_cor" class="form-control " placeholder="y">'+
        '</div>' +

        '<input onclick="goAddStation();" class="btn btn-info" type="submit" id="addstation" value="Add Station"/>' +
        '</div>'
    );
    showAllStation();
    goDialogStation();

}

function goToSend() {
    var subject = $("#subject");
    var text = $("#letter");

    var mail = {
        to: null,
        subject: subject.val(),
        text: text.val()
    };

    $.ajax({
        type: "POST",
        url : "/notify/message" ,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(mail),
        success: function (response) {
            swal({
                title: "Good job!",
                text: "Message has been already sent",
                type: "success",
                confirmButtonColor: "#77dd55",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){
                window.location = "/admin";
            });

        },
        error: function () {
            swal("Oops...", "Sorry! Problems :( Please try later", "error");
        }
    });



}

function goAddStation(){
    var stationname = $("#stationname");
    var x_cor = $("#x_cor");
    var y_cor = $("#y_cor");

    if (stationname.val() === '') {
        swal("Oops...", "Please write correct name of station", "error");
        return '';
    }

    if (x_cor.val() === '') {
        swal("Oops...", "Please write correct X coordinate", "error");
        return '';
    }

    if (y_cor.val() === '') {
        swal("Oops...", "Please write correct Y coordinate", "error");
        return '';
    }

    $.ajax({
        type: "POST",
        url : '/station/add/' + stationname.val() + '/x_cor/' + x_cor.val() + '/y_cor/' + y_cor.val(),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#dialog").dialog('close');
            swal("Good job!", "Station "+ response.stationName + " has been already added","success");
            showAllStation();
        },
        error: function (error) {
            swal("Oops...", error.responseText, "error");
        }
    });
}

function goAddTrain(){

    var trainname = $("#trainname");
    var carriage = $("#carriage");

    if (trainname.val() === '') {
        swal("Oops...", "Please write correct name of train", "error");
        return '';
    }

    if (parseInt(carriage.val()) > 20) {
        swal("Oops...", "Please write correct count of carriage ", "error");
        return '';
    }
    var url = "train/new/" + trainname.val() + "/carriage/" + carriage.val();
    $.ajax({
        type: "POST",
        url : url ,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            swal("Good job!", "Train has been already added","success");
            $("#dialog").dialog('close');
            showAllTrains();
        },
        error: function (error) {
            swal("Oops...", error.responseText, "error");
        }
    });

}

function goAddSchedule() {

    var train = $("#trainnameinschedule");
    var stationDeparture = $("#station_departure");
    var dateD = $("#time_departure");
    var stationArrival = $("#station_arrival");
    var dateA = $("#time_arrival");
    var timeE = $("#timeE");
    var timeD = $("#timeD");


    var schedule = {
        train: {id: train.val()},
        stationDeparture: {stationName: stationDeparture.val()},
        stationArrival: {stationName: stationArrival.val()},
        timeDeparture: new Date(dateD.val() + ' ' + timeD.val()),
        timeArrival: new Date(dateA.val() + ' ' + timeE.val())
    };

    if (train.val() === '') {
        swal("Oops...", "Please write correct name of train", "error");
        return '';
    }

    // if (!isFirstDateLessThanSecond(deleteSchedule(dateD,dateA))){
    //     swal("Oops...", "Please write correct dates!", "error");
    //     return '';
    // }

    if (stationDeparture.val() === '') {
        swal("Oops...", "Please write correct the departure station", "error");
        return '';
    }
    if (dateD.val() === '') {
        swal("Oops...", "Please write correct departure date", "error");
        return '';
    }

    if (stationArrival.val() === '') {
        swal("Oops...", "Please write correct the arrival station", "error");
        return '';
    }

    if (dateA.val() === '') {
        swal("Oops...", "Please write correct arrival date", "error");
        return '';
    }


    if (timeD.val() === '') {
        swal("Oops...", "Please write correct departure time", "error");
        return '';
    }


    if (stationArrival.val() === stationDeparture.val()) {
        swal("Oops...", "Stations must not be the same", "error");
        return '';
    }

    if (timeE.val() === '') {
        swal("Oops...", "Please write correct arrival time", "error");
        return '';
    }
    $.ajax({
        type: "POST",
        url : "/schedule/new" ,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(schedule),
        success: function (response) {
            $("#dialog").dialog('close');
            swal("Good job!", "Schedule has been already created","success");
            showAllSchedule();

        },
        error: function () {
            swal("Oops...", "Sorry! Problems :( Please try later", "error");
        }
    });

}

function deleteTrain(train) {
    $.ajax({
        type: "DELETE",
        url : "train/delete/" + train ,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            try {
                $("#dialog").dialog('close');
            } catch(e) {

            }

            swal("Good job!", "Train has been already deleted","success");
            showAllTrains();
        },
        error: function (error) {
            // console.log('request: ', qXHR);
            // console.log('status text: ', textStatus);
            // console.log('thrown error: ', JSON.stringify(errorThrown));
            swal("Oops...", error.responseText, "error");
        }
    });
}

function deleteStation(station) {
    //var station = $("#stationname");

    $.ajax({
        type: "DELETE",
        url : "station/delete/" + station  ,
        contentType: "application/json; charset=utf-8",
        success: function () {
            try {
                $("#dialog").dialog('close');
            } catch(e) {}

            swal("Good job!", "Station has been already deleted","success");
            showAllStation();
        },
        error: function (error) {
            // console.log('request: ', qXHR);
            // console.log('status text: ', textStatus);
            // console.log('thrown error: ', JSON.stringify(errorThrown));
            swal("Oops...", error.responseText, "error");
        }
    });
}

function deleteUser(data) {
    var email = $("#useremail");
    $.ajax({
        type: "DELETE",
        url : "/user/delete/" + data[0]  ,
        contentType: "application/json; charset=utf-8",
        success: function () {
            swal("Good job!", "User has been already deleted","success");
            showUser();
        },
        error: function (error) {
            // console.log('request: ', qXHR);
            // console.log('status text: ', textStatus);
            // console.log('thrown error: ', JSON.stringify(errorThrown));
            swal("Oops...",  error.responseText, "error");
        }
    });

}

function deleteSchedule(data) {

        $.ajax({
            type: "DELETE",
            url : "schedule/delete/" + data[0]  ,
            contentType: "application/json; charset=utf-8",
            success: function () {
                swal("Good job!", "Schedule has been already deleted","success");
                showAllSchedule();
            },
            error: function () {
                console.log('request: ', qXHR);
                console.log('status text: ', textStatus);
                console.log('thrown error: ', JSON.stringify(errorThrown));
                swal("Oops...", "Sorry! We can't delete this user :( Please try later", "error");
            }
        });

}

function confirmUpdateSchedule() {
    var train = $("#trainnameinschedule").val();
    var id = $("#schedule_id").val();
    var stationDeparture = $("#station_departure").val();
    var dateD = $("#time_departure").val();
    var stationArrival = $("#station_arrival").val();
    var dateA = $("#time_arrival").val();
    var timeE = $("#timeE").val();
    var timeD = $("#timeD").val();

    if (train === '') {
        swal("Oops...", "Please write correct name of train", "error");
        return '';
    }

    if (stationDeparture === '') {
        swal("Oops...", "Please write correct the departure station", "error");
        return '';
    }
    if (dateD === '') {
        swal("Oops...", "Please write correct departure date", "error");
        return '';
    }

    if (stationArrival === '') {
        swal("Oops...", "Please write correct the arrival station", "error");
        return '';
    }

    if (dateA === '') {
        swal("Oops...", "Please write correct arrival date", "error");
        return '';
    }


    if (timeD === '') {
        swal("Oops...", "Please write correct departure time", "error");
        return '';
    }


    if (stationArrival === stationDeparture) {
        swal("Oops...", "Stations must not be the same", "error");
        return '';
    }

    if (timeE === '') {
        swal("Oops...", "Please write correct arrival time", "error");
        return '';
    }

    var schedule = {
        id: id,
        train: {id: train},
        stationDeparture: {stationName: stationDeparture},
        stationArrival: {stationName: stationArrival},
        timeDeparture: new Date(dateD + ' ' + timeD),
        timeArrival: new Date(dateA + ' ' + timeE)
    };

    $.ajax({
        type: "PUT",
        url : "/schedule/update" ,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(schedule),
        success: function (response) {
            try {
                $("#dialog").dialog('close');
            } catch(e) {

            }

            swal("Good job!", "User has been already updated","success");
            showAllSchedule();

        },
        error: function () {
            swal("Oops...", "Sorry! Problems :( Please try later", "error");
        }
    });
}

function goDialogSchedule(){
    $( function() {
        $( "#dialog" ).dialog({
            modal: true,
            closeOnEscape: true,
            maxWidth:400,
            maxHeight: 550,
            width: 400,
            height: 550,
            resizable: false,
            title: "Add the schedule!",
        });
    } );
}

function goDialogTrain(){
    $( function() {
        $( "#dialog" ).dialog({
            modal: true,
            closeOnEscape: true,
            maxWidth:400,
            maxHeight: 300,
            width: 400,
            height: 300,
            resizable: false,
            title: "Add the train!"
        });
    } );
}

function goDialogStation(){
    $( function() {
        $( "#dialog" ).dialog({
            modal: true,
            closeOnEscape: true,
            maxWidth:400,
            maxHeight: 300,
            width: 400,
            height: 300,
            resizable: false,
            title: "Add the station!"
        });
    } );
}

function goDialogChat(){
    $( function() {
        $( "#dialog" ).dialog({
            modal: true,
            closeOnEscape: true,
            maxWidth:400,
            maxHeight: 300,
            width: 400,
            height: 300,
            resizable: false,
            title: "Add new chat!"
        });
    } );
}

function isFirstDateLessThanSecond(stringDate1, stringDate2) {
    var date1 = new Date(stringDate1);
    var date2 = new Date(stringDate2);
    return date1 <= date2;
}
