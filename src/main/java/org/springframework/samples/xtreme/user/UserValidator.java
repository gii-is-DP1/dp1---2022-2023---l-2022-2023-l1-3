package org.springframework.samples.xtreme.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final String REQUIRED = "required";

    @Autowired
    private UserService userService;

    @Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String username = user.getUsername();
        User u = userService.findByUsername(user.getUsername()).get();

        // Username validation
        if (!StringUtils.hasLength(username) || username.length()>10 || username.length()<4) {
			errors.rejectValue("username", REQUIRED+" and between 4 and 10 characters", REQUIRED+" and between 4 and 10 character");
		}

        if (u!=null && !u.getUsername().equals(user.getUsername())) {
            errors.rejectValue("username", "Username does not match", "Username does not match");
        }

        // Password validation
        if (user.getPassword() == null) {
			errors.rejectValue("password", REQUIRED, REQUIRED);
		}

        if (u!=null && !u.getPassword().equals(user.getPassword())) {
            errors.rejectValue("password", "Password does not match", "Password does not match");
        }
    }  
}
