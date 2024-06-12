package com.plannerapp.util.validation;


import com.plannerapp.model.bindingModels.UserLoginModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserExistValidator implements ConstraintValidator<UserExist, UserLoginModel> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserExistValidator(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize(UserExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginModel userLoginModel, ConstraintValidatorContext context) {
        Optional<User> optionalUser = userService.findByUsername(userLoginModel.getUsername());
        if (optionalUser.isEmpty()){
            return false;
        }
        String  rawPassword = userLoginModel.getPassword();
        String  encodedPassword  = optionalUser.get().getPassword();

        return passwordEncoder.matches(rawPassword, encodedPassword);

    }
}
