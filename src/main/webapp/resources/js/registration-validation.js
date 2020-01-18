(() => {
    const registrationForm = document.getElementById('register-form');
    const allElements = registrationForm.elements;
    const login = allElements.login;
    const firstName = allElements.firstName;
    const lastName = allElements.lastName;
    const email = allElements.email;
    const password = allElements.password;
    const confirmPassword = allElements.confirmPassword;
  
    registrationForm.addEventListener('submit', validateRegistrationForm);
  
    login.addEventListener('blur', validateLogin);
    firstName.addEventListener('blur', validateFirstName);
    lastName.addEventListener('blur', validateLastName);
    email.addEventListener('blur', validateEmail);
    password.addEventListener('blur', validatePassword);
    confirmPassword.addEventListener('blur', validateConfirmPassword);

    function validateLogin() {
        resetError(login);
        if (!login.value) {
          showError(login, "Login should not be empty.");
        } else if (isLengthNotValid(login)) {
          showError(login, "Login should be minimum 4 symbols length.");
        } else {
          markValid(login);
        }
      }

    function validateFirstName() {
      resetError(firstName);
      if (!firstName.value) {
        showError(firstName, "First name should not be empty.");
      } else if (isLengthNotValid(firstName)) {
        showError(firstName, "First name should be minimum 4 symbols length.");
      } else {
        markValid(firstName);
      }
    }

    function validateLastName() {
        resetError(lastName);
        if (!lastName.value) {
          showError(lastName, "Last name should not be empty.");
        } else if (isLengthNotValid(lastName)) {
          showError(lastName, "Last name should be minimum 4 symbols length.");
        } else {
          markValid(lastName);
        }
      }
  
    function validateEmail() {
      resetError(email);
      if (!email.value) {
        showError(email,"E-mail should not be empty.");
      } else if (!validateEmailRegExp(email.value)) {
        showError(email, "Please, enter a valid email address.");
      } else {
        markValid(email);
      }
    }
  
    function validatePassword() {
      resetError(password);
      if (!password.value) {
        showError(password, "Password should not be empty.");
      } else if(!validatePasswordRegExp(password.value)) {
        showError(password, "Password should be 0-9a-zA-Z 8 symbols length minimum.");
      } else {
        markValid(password);
      }
    }
  
    function validateConfirmPassword() {
      resetError(confirmPassword);
      if (confirmPassword.value && confirmPassword.value == password.value) {
        markValid(confirmPassword);
      } else {
        showError(confirmPassword, "Passwords does not match.")
      }
    }
  
    function validateRegistrationForm(event) {
      const formFields = registrationForm.querySelectorAll(':scope input');
      const formFieldsArray = Array.prototype.slice.call(formFields);
  
      validateLogin();
      validateFirstName();
      validateLastName();
      validateEmail();
      validatePassword();
      validateConfirmPassword();
  
      const isValid = formFieldsArray.every(function(field) {
        return field.classList.contains('is-valid');
      });
  
      if (!isValid) {
        event.preventDefault();
      }
    }
  
    function showError(element, message) {
      const invalidFeedback = element.parentNode.querySelector('.invalid-feedback');
  
      invalidFeedback.textContent = message;
      element.classList.add('is-invalid');
    }
  
    function markValid(element) {
      element.classList.add('is-valid');
    }
  
    function resetError(element) {
      const invalidFeedback = element.parentNode.querySelector('.invalid-feedback');
  
      element.classList.remove('is-invalid');
      invalidFeedback.textContent = "";
    }

    function isLengthNotValid(field){
        return field.value.length<4;
    }
  
    function validateEmailRegExp(email) {
      return /\S+@\S+\.\S+/.test(email);
    }
  
    function validatePasswordRegExp(password) {
      const passwordRegExp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
      return passwordRegExp.test(password);
    }
  })();