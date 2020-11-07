$( "#myform" ).validate({
    rules: {
        username:{
            required: true,
            minlength:6,
            maxlength:25,
            remote:{
                url: "/checkusername",
                type: "post",
                data: {
                    username: function() {
                        return $("#username").val();
                    }
                }
            }
        },
        yourEmail: {
            required: true,
            email: true,
            remote:{
                url: "/checkEmail",
                type: "post",
                data: {
                    yourEmail: function() {
                        return $("#yourEmail").val();
                    }
                }
            }
        },
        password: {
            required: true,
            minlength: 6,
            maxlength: 25
        },
        confirmPassword: {
            equalTo: "#password"
        }
    },
    messages: {
        username: {
            required: "Please enter an username",
            minlength: "The username should longer than 5",
            maxlength: "The username should shorter than 25",
            remote: "Unavailable Username"
        },
        yourEmail: {
            required: "Please provide an email",
            email: "Invalid Format",
            remote: "This email has already exist."
        },
        password: {
            required: "Please provide a password",
            minlength: "The password should longer than 5",
            maxlength: "The password should shorter than 25"
        },
        confirmPassword: {
            required: "Please provide a password",
            equalTo: "Wrong Password"
        }
    },

});



