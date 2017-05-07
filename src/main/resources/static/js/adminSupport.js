/**
 * Created by aleksandrprendota on 07.05.17.
 */

function showSupport(){
    $("#dialog").empty();
    $("#logo").empty();
    $("#fon").remove();
    $("#source").empty();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Support</h1>' +
        '</div>' +
        '</div></div>'
    );

    $.ajax({
        type: "GET",
        url : '/chat/chats',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (response) {

            $("#source")
                .append('<div class="col-sm-12 prop rounded center ">' +
                    '<div class="col-sm-12">' +
                    '<div class="dt-buttons btn-group">' +
                    '<a onclick="newChat();" class="btn btn-default buttons-create" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>New</span></a>' +
                    '<a onclick="showChat();" class="btn btn-default buttons-create" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>Show</span></a>' +
                    '<a onclick="deleteChat();" class="btn btn-default buttons-selected buttons-remove" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>Delete</span></a></div></div>'+
                    "<table id='risestable' class='table table-hover'>");
            $("#risestable")
                .append("<thead>" +
                    "<tr class='tab'>" +
                    "<th class='tab center'>User Mail</th>" +
                    "</tr>"
                    + "</thead>"
                    + "<tbody>"
                );

            for (var i = 0; i < response.length; i++) {
                $("#risestable")
                    .append(
                        "<tr class='tab'>" +
                        "<td class='tab'>" + response[i].user.email + "</td>" +
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

function newChat() {
    $("#dialog").empty();
    $("#logo").empty();
    $("#fon").remove();
    $("#logo").append(
        '<div class="container">' +
        '<div class="panel-heading">' +
        '<div class="panel-title text-center">' +
        '<h1 class="title">Support</h1>' +
        '</div>' +
        '</div></div>'
    );

    $("#dialog").append(
        '<div class="col-sm-4 prop rounded">' +
        '<div class="input-group way station" style="width: 300px;">' +
        '<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>' +
        '<input type="text" name="chatmail" id="chatmail" class="form-control autocompliteUsers" placeholder="User mail">'+
        '</div>' +
        '<input onclick="addChat();" class="btn btn-info" type="submit" id="addchat" value="Create"/>' +
        '</div>'
    );
    goDialogChat();
    autocompliteUsers();
    
}

function deleteChat() {
    alert("delete");
}

function showChat() {
    alert("show");
}

function addChat() {
    alert("add");
}