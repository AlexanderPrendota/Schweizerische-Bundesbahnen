/**
 * Created by aleksandrprendota on 02.04.17.
 */

function showPanelToFindPassenger(){
    $("#source").empty();
    $("#logo").empty();
    $("#fon").remove();
    $("#mainttable").empty();
    $("#lose").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Find passanger on Train!</h1>' +
        '</div>' +
        '</div></div>'
    );
    $("#source").append(
        '<div class="col-sm-4 prop rounded">' +
        '<div class="input-group way station">' +
        '<span class="input-group-addon"><i class="fa fa-subway" aria-hidden="true"></i></span>' +
        '<input type="text" id="trainwithpas" name="train" class="form-control autocompliteTrain" placeholder="Name of train">' +
        '</div>' +
        '<div class="input-group way station">' +
        '<span class="input-group-addon"><i class="fa fa-calendar-o" aria-hidden="true"></i></span>' +
        '<input type="text" id="dateDeparture" name="dateDeparture" class="form-control datepicker" placeholder="Date start">' +
        '</div>' +
        '<input class="btn btn-info" onclick="getPassangerOnTrain();" type="submit" id="addtrain" value="Search"/></div>' +
        '</div>'
    );
    autocompliteTrain();
    goesDatePicker();

}

function getPassangerOnTrain() {

    var train = $("#trainwithpas");
    var dateDeparture = $("#dateDeparture");
    $("#lose").empty();
    $("#logo").remove();
    $("#mainttable").empty();


    if (train.val() === '') {
        swal("Oops...", "Please write correct name of train", "error");
        return '';
    }

    if (dateDeparture.val() === '') {
        swal("Oops...", "Please write correct date departure", "error");
        return '';
    }

    var url = 'train/passangers/train/'+train.val()+'/departure/'+dateDeparture.val();
    $.ajax({
        type: "GET",
        url : url  ,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {
            if (response.length === 0 || response == null) {
                $("#lose").append("<div class='container'>" +
                    "<div class='panel-heading'>" +
                    "<div class='panel-title text-center'>" +
                    "<h1 class='title'>No passangers on train, Darling! :( </h1>" +
                    "</div></div></div>");

            } else{
                $("#source")
                    .append('<div class="col-sm-4 prop rounded center">' +
                        "<table id='risestable' class='table table-hover'>");
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
                            "<td class='tab'>" + response[i].email + "</td>" +
                            "<td class='tab'>" + response[i].firstname + "</td>" +
                            "<td class='tab'>" + response[i].lastname + "</td>" +
                            + "</tr>");
                }
                $("#risestable")
                    .append("</tbody></table></div>");
            }
        },
        error: function () {
            swal("Oops...", "Problems with getting users! Please Try later!", "error");
        }
    });

}
