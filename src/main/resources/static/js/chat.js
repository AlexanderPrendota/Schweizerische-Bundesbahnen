/**
 * Created by aleksandrprendota on 06.05.17.
 */
var flagVisible = false;
var messages = 0;
function chatShow() {
    if (!flagVisible){
        $(".chat_window").css("visibility","visible");
        flagVisible = true;
    } else {
        $(".chat_window").css("visibility","hidden");
        flagVisible = false;
    }
}

function processing() {
    $('.messages').empty();
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
            setInterval(function(){
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
                        console.log("Error with getting servlet data")
                    }
                });
            }, 5000);
        });
    }.call(this));
}


function sending(text, sendMessage) {

    var messageDTO = {
        recipientId: 139,
        text: text,
        timeStamp: new Date()
    };

    $.ajax({
        type: "POST",
        url : "/message/post/user" ,
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
    $.ajax({
        type: "GET",
        url : "/message/user/chat" ,
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            var user = $('#email').val();
            console.log(user);
            for (var i=0; i < response.length;i++){
                var sender = response[i].sender.email;
                if(sender == user){
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

$( document ).ready(function() {
    processing();
});