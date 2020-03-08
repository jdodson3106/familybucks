function doPasswordsMatch(password1, password2) {
    if(!(password1.trim() === password2.trim())) {
        console.log("false")
        return false;
    }
    else {
        console.log("true")
        return true
    }
}

$(document).ready(
    $("#password2").on("change keyup", function () {
        let pass2 = $("#password2").val();
        let pass1 = $("#password1").val();
        if(pass1.trim() == "" || pass2.trim() == "") {
            $("#passwordMatch").text("");
        } else{
            if(doPasswordsMatch(pass1, pass2)) {
                $("#passwordMatch").text("Passwords Match!").css("color", "green");
            } else {
                $("#passwordMatch").text("Passwords do not match!").css("color", "red");
            }
        }

    })
)
