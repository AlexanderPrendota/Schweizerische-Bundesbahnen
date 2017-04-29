/**
 * Created by aleksandrprendota on 25.03.17.
 */


function user(lastname, firstname, email, password, birthday) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.birthday = birthday

}

function parseUser() {
    var firstname = document.getElementById('firstname');
    var lastname = document.getElementById('lastname');
    var email = document.getElementById('email');
    var birthday = document.getElementById('datepicker');
    var password = document.getElementById('password');
    var confirmPassword = document.getElementById('confirm');
    var gender = '';


    if (email.value === '') {
        swal("Oops...", "Please write correct email", "error");
        return '';
    }

    if (lastname.value === '') {
        swal("Oops...", "Please write correct last name", "error");
        return '';
    }

    if (firstname.value === '') {
        swal("Oops...", "Please write correct first name", "error");
        return '';
    }
    console.log(birthday.value);
    if (birthday.value === '') {
        swal("Oops...", "Please write correct birthday", "error");
        return '';
    }

    // if (!validateEmail(email.value)) {
    //     swal("Oops...", "Please write correct email", "error");
    //     return '';
    // }


    if (password.value != confirmPassword.value) {
        swal("Ooops!", "Passwords not equals!", "error");
        password.value = '';
        confirmPassword.value = '';
        return '';
    }
    var userObj = new user(
        lastname.value,
        firstname.value,
        email.value,
        password.value,
        birthday.value,
        gender);
    return JSON.stringify(userObj);
}

function checkAnswer(data) {

    var status = data["status"];
    var statusText = data["statusText"];
    if (status !== null) {
        swal(status + " error. " + statusText);
    }

    var errortype = data["errortype"];
    if (data !== null && errortype !== null && errortype.length !== 0) {
        var message = data["message"];
        if (message.length !== 0) {
            swal(errortype, message);
        } else {
            swal(errortype);
        }
    }
}

function validateEmail(email) {

    // Ohohoh!!! Wunderbar!!!

    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);

}


function sendAjax(data, url, callback) {
    $.ajax({
        dataType: "json",
        url: url,
        data: data,
        type: "POST",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
           // swal("Good job! " + response.email, "You've been registered", "success");
            swal({
                title: "Good job!",
                text: "You've been registered",
                type: "success",
                confirmButtonColor: "#77dd55",
                confirmButtonText: "OK!",
                closeOnConfirm: false }, function(){
                window.location = "/login";
            });
        },
        error: function () {
            swal("Oops...", "Wrong information!", "error");
        }
    });

}

function form_registration_submit() {
    stringUser = parseUser();
    if (stringUser != '') {
        var data = sendAjax(stringUser, "/registration", checkAnswer);
        document.getElementById('form_registration').reset();
    }
    return false;
}
