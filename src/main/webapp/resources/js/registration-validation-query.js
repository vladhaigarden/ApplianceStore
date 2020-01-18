$(document).ready(function(){
 
  const registrationForm = $('#register-form');
  const login = $('#login');
  const password = $('#password');
  const confirmPassword = $('#confirmPassword');
  const email = $('#email');
  const firstName = $('#firstName');
  const lastName = $('#lastName');


  registrationForm.submit(validateRegistrationForm);

  login.blur(validateLogin);
  firstName.blur(validateFirstName);
  lastName.blur(validateLastName);
  email.blur(validateEmail);
  password.blur(validatePassword);
  confirmPassword.blur(validateConfirmPassword);

    function validateLogin() {
        resetError(login);
        if (!login.val()) {
          showError(login, "Login should not be empty.");
        } else if (isLengthNotValid(login)) {
          showError(login, "Login should be minimum 4 symbols length.");
        } else {
          markValid(login);
        }
      }

    function validateFirstName() {
      resetError(firstName);
      if (!firstName.val()) {
        showError(firstName, "First name should not be empty.");
      } else if (isLengthNotValid(firstName)) {
        showError(firstName, "First name should be minimum 4 symbols length.");
      } else {
        markValid(firstName);
      }
    }

    function validateLastName() {
        resetError(lastName);
        if (!lastName.val()) {
          showError(lastName, "Last name should not be empty.");
        } else if (isLengthNotValid(lastName)) {
          showError(lastName, "Last name should be minimum 4 symbols length.");
        } else {
          markValid(lastName);
        }
      }
  
    function validateEmail() {
      resetError(email);
      if (!email.val()) {
        showError(email,"E-mail should not be empty.");
      } else if (!validateEmailRegExp(email.val())) {
        showError(email, "Please, enter a valid email address.");
      } else {
        markValid(email);
      }
    }
  
    function validatePassword() {
      resetError(password);
      if (!password.val()) {
        showError(password, "Password should not be empty.");
      } else if(!validatePasswordRegExp(password.val())) {
        showError(password, "Password should be 0-9a-zA-Z 8 symbols length minimum.");
      } else {
        markValid(password);
      }
    }
  
    function validateConfirmPassword() {
      resetError(confirmPassword);
      if (confirmPassword.val() && confirmPassword.val() == password.val()) {
        markValid(confirmPassword);
      } else {
        showError(confirmPassword, "Passwords does not match.")
      }
    }
  
    function validateRegistrationForm(event) {

      validateLogin();
      validateFirstName();
      validateLastName();
      validateEmail();
      validatePassword();
      validateConfirmPassword();

      var isFormNotValid = $(registrationForm).find(':input.is-invalid').length > 0;

      if(isFormNotValid){
        event.preventDefault();
      }
    }
  
    function showError(element, message) {
      const invalidFeedback = element.parent().find('.invalid-feedback');

  invalidFeedback.text(message);
  element.addClass('is-invalid');
    }
  
    function markValid(element) {
      element.addClass('is-valid');
    }
    
    function resetError(element) {
      const invalidFeedback = element.parent().find('.invalid-feedback');
    
      element.removeClass('is-invalid');
      invalidFeedback.text("");
    }

    function isLengthNotValid(field){
        return field.val().length<4;
    }
  
    function validateEmailRegExp(email) {
      return /\S+@\S+\.\S+/.test(email);
    }
  
    function validatePasswordRegExp(password) {
      const passwordRegExp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
      return passwordRegExp.test(password);
    }
});