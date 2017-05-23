/**
 * Created by aleksandrprendota on 26.03.17.
 */

function viewPageParams() {

    $("#mainttable").empty();
    $("#lose").empty();

    $.ajax({
        type: "GET",
        url : '/api/account/user',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            $("#mainuser").append(response.firstname);
            $("#firstname").attr("value", response.firstname);
            $("#lastname").attr("value", response.lastname);
            $("#email").attr("value", response.email);
            $("#datepicker").attr("value", response.birthday);
            $("#accountimage").attr("src", response.imagepath);

        },
        error: function () {
            swal("Oops...", "Wrong information!", "error");
        }
    });
    showUserRides()
}

function saveUserInformation() {

    var emailInput = $("#email");
    var passwordInput = $("#password");
    var firstNameInput = $("#firstname");
    var lastNameInput = $("#lastname");
    var birthdayInput = $("#datepicker");

    var sendData = {
        email : emailInput.val(),
        password : passwordInput.val(),
        lastname : lastNameInput.val(),
        firstname : firstNameInput.val(),
        birthday: birthdayInput.val()
    };

    event.preventDefault();

    $.ajax({
        type: "PUT",
        url : '/api/account/update',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(sendData),
        success: function (response) {
            //swal("Good job! " + response.firstname, "Your information have been changed", "success");
            swal({
                title: "Good job, " + response.firstname,
                text: "Your information have been changed",
                type: "success",
                confirmButtonColor: "#77dd55",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){
                    window.location = "/account";
            });
        },
        error: function () {
            swal("Oops...", "Problems with saving your information! Please Try later!", "error");
        }
    });
}

function showUserRides() {
    $.ajax({
        type: "GET",
        url : '/api/account/rides',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            if (response.length === 0 || response == null){
                $("#mainttable").css("visibility","hidden");
                $("#lose").append("<div class='container'>" +
                    "<div class='panel-heading'>" +
                    "<div class='panel-title text-center'>" +
                    "<h1 class='title'>You have not got rides right now </h1>" +
                    "</div></div></div>");
            } else {

                $("#mainttable").css("visibility","visible");
                $("#mainttable")
                    .append("<table id='risestable' class='table table-hover'>");
                $("#risestable")
                    .append("<thead>" +
                        "<tr class='tab'>" +
                        "<th class='tab'>Train Number</th>" +
                        "<th class='tab'>Station Departure</th>" +
                        "<th class='tab'>Time</th>" + "<th class='tab'>Station Arrival</th>" +
                        "<th class='tab'>Time</th>" + "</tr>" + "</thead>" + "<tbody>");
                for (var i = 0; i < response.length; i++) {
                    $("#risestable")
                        .append("<tr onclick='showRideDetail(this.id);' id='" + response[i].id + "' class='tab'>" +
                            "<td class='tab'>" + response[i].train.id + "</td>" +
                            "<td class='tab'>" + response[i].stationDeparture.stationName + "</td>" +
                            "<td class='tab'>" +new Date(response[i].timeDeparture).toLocaleString() + "</td>" +
                            "<td class='tab'>" + response[i].stationArrival.stationName + "</td>"
                            + "<td class='tab'>" + new Date(response[i].timeArrival).toLocaleString() + "</td>" + "</tr>");
                }
                $("#risestable")
                    .append("</tbody></table>");

            }
        },
        error: function () {
            swal("Oops...", "Problems with gettion your rides darling!", "error");
        }
    });
}

function getName(str){
    if (str.lastIndexOf('\\')){
        var i = str.lastIndexOf('\\') + 1;
    }
    else{
        var i = str.lastIndexOf('/') + 1;
    }
    var filename = str.slice(i);
    var uploaded = document.getElementById("fileformlabel");
    uploaded.innerHTML = filename;

}
