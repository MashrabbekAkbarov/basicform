package com.example.demo.Validator;

import com.example.demo.Model.AppUser;
import com.example.demo.Model.UserFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppUserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass== UserFormData.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserFormData userdata = (UserFormData) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.appUserForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotEmpty.appUserForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.appUserForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.appUserForm.confirmPassword");

        if (!errors.hasErrors()) {
            if (!userdata.getConfirmPassword().equals(userdata.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.appUserForm.confirmPassword");
            }
        }
    }

}
