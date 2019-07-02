package com.example.demo.valid;


import com.example.demo.model.Login;
import com.example.demo.model.Users;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	private EmailValidator emailValidator = EmailValidator.getInstance();

	private LoginRepository loginRepository;
	private UserRepository partRepository;

	public UserValidator(LoginRepository loginRepository, UserRepository partRepository) {
		super();
		this.loginRepository = loginRepository;
		this.partRepository = partRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == AppUserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		AppUserForm appUserForm = (AppUserForm) target;

		// Check the fields of AppUserForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.appUserForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.appUserForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.appUserForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "NotEmpty.appUserForm.age");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "NotEmpty.appUserForm.phone");

		if (!this.emailValidator.isValid(appUserForm.getEmail())) {
			errors.rejectValue("email", "Pattern.appUserForm.email");
		} else {
			Users dbUser = partRepository.findByEmail(appUserForm.getEmail());
			if (dbUser != null) {
				errors.rejectValue("email", "Duplicate.appUserForm.email");
			}
		}

		if (!errors.hasFieldErrors("userName")) {
			Login dbUser = loginRepository.findByUsername(appUserForm.getUserName());
			if (dbUser != null) {

				errors.rejectValue("userName", "Duplicate.appUserForm.userName");
			}
		}

	}

}
