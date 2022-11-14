package org.springframework.samples.xtreme.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {

    private static final String REQUIRED = "required";

    @Autowired
    private PlayerService playerService;

    @Override
	public boolean supports(Class<?> clazz) {
		return Player.class.isAssignableFrom(clazz);
	}

    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;
        String username = player.getUser().getUsername();
        Player p = playerService.findByUsername(player.getUser().getUsername());

        // Username validation
        if (!StringUtils.hasLength(username) || username.length()>10 || username.length()<4) {
			errors.rejectValue("user.username", REQUIRED+" and between 4 and 10 characters", REQUIRED+" and between 4 and 10 character");
		}

        if(p!=null && p.getUser().getUsername().equals(player.getUser().getUsername())) {
            errors.rejectValue("user.username", "Username already registered", "Username already registered");
        }

        // Password validation
        if (player.getUser().getPassword() == null) {
			errors.rejectValue("user.password", REQUIRED, REQUIRED);
		}

        // Email validation
        if (player.getEmail() == null) {
			errors.rejectValue("email", REQUIRED, REQUIRED);
		}

        if(p!=null && p.getEmail().equals(player.getEmail())) {
            errors.rejectValue("email", "Email already registered", "Email already register");
        }

        // First name validation
        if (player.getFirstName() == null) {
			errors.rejectValue("firstName", REQUIRED, REQUIRED);
		}

        // Last name validation
        if (player.getFirstName() == null) {
			errors.rejectValue("LastName", REQUIRED, REQUIRED);
		}
    
    }
    
}
