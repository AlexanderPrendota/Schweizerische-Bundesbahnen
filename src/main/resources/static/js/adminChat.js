/**
 * Created by aleksandrprendota on 07.05.17.
 */
var messages = 0;

var Message;

Message = function (arg) {
    this.text = arg.text, this.message_side = arg.message_side;
    this.draw = function (_this) {
        return function () {
            var $message;
            $message = $($('.message_template').clone().html());
            $message.addClass(_this.message_side).find('.text').html(_this.text);
            $('.messages').append($message);
            return setTimeout(function () {
                return $message.addClass('appeared');
            }, 0);
        };
    }(this);
    return this;
};

var getMessageText, message_side, sendMessage;

message_side = 'right';

getMessageText = function () {
    var $message_input;
    $message_input = $('.message_input');
    return $message_input.val();
};

sendMessage = function (text, side) {
    var $messages, message;
    if (text.trim() === '') {
        return;
    }
    $('.message_input').val('');
    $messages = $('.messages');
    message_side = side;
    //   message_side = message_side === 'left' ? 'right' : 'left';
    message = new Message({
        text: text,
        message_side: message_side
    });
    message.draw();
    return $messages.animate({ scrollTop: $messages.prop('scrollHeight') }, 0);
};

function sending(text, sendMessage) {

    var shosen = localStorage.getItem('chosen_item');
    var mail = JSON.parse(shosen);

    var messageDTO = {
        recipientId: mail[0],
        text: text,
        timeStamp: new Date(),
        chatId: mail[2]
    };

    $.ajax({
        type: "POST",
        url : "/message/post/admin" ,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(messageDTO),
        success: function (response) {
            sendMessage(response.text,'right');
            messages++;
        },
        error: function () {
            swal("Oops...", "Sorry! Problems with getting data from server:( Please try later", "error");
        }
    });
}

function posting(sendMessage) {
    var shosen = localStorage.getItem('chosen_item');
    var mail = JSON.parse(shosen);
    var chatID = mail[2];
    $('.messages').empty();
    $.ajax({
        type: "GET",
        url : "message/admin/chat/" + chatID ,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            var user = "Prendota@mail.ru";
            for (var i=0; i < response.length;i++){
                var sender = response[i].sender.email;
                if(sender === user){
                    sendMessage(response[i].text, "right");
                } else{
                    sendMessage(response[i].text, "left");
                }
            }
        },
        error: function () {
            swal("Oops...", "Sorry! Problems with getting data from server:( Please try later", "error");
        }
    });
}

function showChatAdmin() {
    var shosen = localStorage.getItem('chosen_item');
    var mail = JSON.parse(shosen);
    $('#titlechat').html('Chat with ' + mail[1]);
    $('.messages').empty();
    $('.send_message').attr("onclick","sending(getMessageText(),sendMessage);");

    $('.close').click(function (e) {
        localStorage.clear();
        $(".chat_window").css("display","none");
        clearInterval(inter);
        messages = 0;
    });

    $( ".moveAble" ).draggable();

    // $('.message_input').keyup(function (e) {
    //     if (e.which === 13) {
    //         sending(getMessageText(),sendMessage);
    //     }
    // });

    var inter = setInterval(function(){
        $.ajax({
            type: "GET",
            url : "/message/messages" ,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                console.log("thinking");
                if (messages != response){
                    posting(sendMessage);
                    messages = response
                }
            },
            error: function () {
                console.log("Error with getting count of message")
            }
        });
    }, 3000);
}


