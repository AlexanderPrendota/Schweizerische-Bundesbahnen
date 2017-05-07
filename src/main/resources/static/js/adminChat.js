/**
 * Created by aleksandrprendota on 07.05.17.
 */
var flagVisible = false;
function adminChat() {

    var shosen = localStorage.getItem('chosen_item');
    var mail = JSON.parse(shosen);
    $('#titlechat').html('Chat with ' + mail[1]);
    (function () {
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
        $(function () {
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
                return $messages.animate({ scrollTop: $messages.prop('scrollHeight') }, 300);
            };
            $('.send_message').click(function (e) {
                sending(getMessageText(),sendMessage);
            });

            $('.close').click(function (e) {
                $(".chat_window").css("visibility","hidden");
                flagVisible = false;
            });

            $( function() {
                $( ".moveAble" ).draggable();
            } );

            $('.message_input').keyup(function (e) {
                if (e.which === 13) {
                    sending(getMessageText(),sendMessage);
                }
            });
            posting(sendMessage);
        });
    }.call(this));
}


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