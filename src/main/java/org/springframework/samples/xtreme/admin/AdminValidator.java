package org.springframework.samples.xtreme.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AdminValidator implements Validator {

    private static final String REQUIRED = "required";

    @Autowired
    private AdminService adminService;

    @Override
	public boolean supports(Class<?> clazz) {
		return Admin.class.isAssignableFrom(clazz);
	}

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;
        String username = admin.getUser().getUsername();
        Admin a = adminService.findByUsername(admin.getUser().getUsername());

        // Username validation
        if (!StringUtils.hasLength(username) || username.length()>10 || username.length()<4) {
			errors.rejectValue("user.username", REQUIRED+" and between 4 and 10 characters", REQUIRED+" and between 4 and 10 character");
		}

        if(a!=null && a.getUser().getUsername().equals(admin.getUser().getUsername())) {
            errors.rejectValue("user.username", "Username already registered", "Username already registered");
        }

        // Password validation
        if (admin.getUser().getPassword() == null) {
			errors.rejectValue("user.password", REQUIRED, REQUIRED);
		}

        // Email validation
        if (admin.getEmail() == null) {
			errors.rejectValue("email", REQUIRED, REQUIRED);
		}

        if(a!=null && a.getEmail().equals(admin.getEmail())) {
            errors.rejectValue("email", "Email already registered", "Email already register");
        }

        // First name validation
        if (admin.getFirstName() == null) {
			errors.rejectValue("firstName", REQUIRED, REQUIRED);
		}

        // Last name validation
        if (admin.getFirstName() == null) {
			errors.rejectValue("LastName", REQUIRED, REQUIRED);
		}
    
    }
    
}
