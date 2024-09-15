function validatePasswordMatch() {
    debugger;
    // Get the password and confirm password fields
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm-password").value;

    // Get the confirm password input element
    var confirmPasswordField = document.getElementById("confirm-password");

    // Check if passwords match
    if (password !== confirmPassword) {
        // Change the background color to red if they don't match
        confirmPasswordField.style.backgroundColor = "lightcoral";
    } else {
        // Reset the background color if they match
        confirmPasswordField.style.backgroundColor = "";
    }
}