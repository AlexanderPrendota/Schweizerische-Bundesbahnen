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
                    '<a onclick="showChat();" class="btn btn-default buttons-create" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>Show</span></a>' +
                    '<a onclick="deleteChat();" class="btn btn-default buttons-selected buttons-remove" tabindex="0" aria-controls="risestable" href="#">' +
                    '<span>Delete</span></a></div></div>'+
                    "<table id='risestable' class='table table-hover'>");
            $("#risestable")
                .append("<thead>" +
                    "<tr class='tab'>" +
                    "<th class='tab center'>User ID</th>" +
                    "<th class='tab center'>User Mail</th>" +
                    "<th class='tab center'>Chat Number</th>" +
                    "</tr>"
                    + "</thead>"
                    + "<tbody>"
                );

            for (var i = 0; i < response.length; i++) {
                $("#risestable")
                    .append(
                        "<tr class='tab'>" +
                        "<td class='tab'>" + response[i].user.id + "</td>" +
                        "<td class='tab'>" + response[i].user.email + "</td>" +
                        "<td class='tab'>" + response[i].chat.id + "</td>" +
                        + "</tr>");
            }
            $("#risestable")
                .append("</tbody></table></div>");

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
        '<input class="btn btn-info" type="submit" id="addchat" value="Create"/>' +
        '</div>'
    );
    goDialogChat();
    autocompliteUsers();
}

function deleteChat() {
    var shosen = localStorage.getItem('chosen_item');
    var mail = JSON.parse(shosen);
    var chatID = mail[2];
    if (localStorage.length > 0) {
        swal({
                title: "Are you sure?",
                text: "Do u want to delete chat with" + mail[1] ,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#ddae19",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false },
            function(){
                $.ajax({
                    type: "DELETE",
                    url : "chat/delete/" + chatID ,
                    contentType: "application/json; charset=utf-8",
                    success: function (response) {
                        swal("Good job!", "Chat has been already deleted","success");
                        showSupport();
                    },
                    error: function (error) {
                        // console.log('request: ', qXHR);
                        // console.log('status text: ', textStatus);
                        // console.log('thrown error: ', JSON.stringify(errorThrown));
                        swal("Oops...", error.responseText, "error");
                    }
                });
            });

        localStorage.clear();
    } else {
        swal({
            title: "Ops!",
            text: "Please choose the conversation!",
            type: "error"
        });
        localStorage.clear();
    }
}

function showChat() {
    var shosen = localStorage.getItem('chosen_item');
    var mail = JSON.parse(shosen);
    if (localStorage.length > 0) {
        console.log(mail);
        $(".chat_window").css("display","block");
        showChatAdmin();
    } else {
        swal({
            title: "Ops!",
            text: "Please choose the conversation!",
            type: "error"
        });
        localStorage.clear();
    }
}

$( document ).ready(function() {
    setInterval(function(){
        $.ajax({
            type: "GET",
            url : "/chat/count" ,
            contentType: "application/json; charset=utf-8",
            success: function (response) {
                if(response == 0){
                    $("#notify").html("");
                } else {
                    $("#notify").html(response);
                }
            },
            error: function () {
                console.log("Error with getting chat notify data")
            }
        });
    }, 5000);
});

