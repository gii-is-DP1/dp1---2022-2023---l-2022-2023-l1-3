package org.springframework.samples.xtreme.user;

import java.util.Optional;
import java.util.function.Predicate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.admin.AdminService;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/users")
public class UserController {
    
    private static final String LOGIN_FORM = "users/loginForm";
    private static final String GAME_HOME = "players/gameHome";
    private static final String LOGOUT = "users/logout";

    private final UserValidator userValidator;

	@Autowired
	public UserController(UserValidator userValidator) {
        this.userValidator = userValidator;
	}

	@InitBinder
	public void setDisallowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/login")
    public ModelAndView userLogin() {
        ModelAndView mav = new ModelAndView(LOGIN_FORM);
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView processLoginForm(@Valid @ModelAttribute("user") User user, BindingResult result) {
        ModelAndView mav = new ModelAndView(GAME_HOME);
        if (result.hasErrors()) {
            mav = new ModelAndView(LOGIN_FORM);
            mav.addObject("user", user);
        }
        return mav;
    }
        
    @GetMapping(path = "/logout-screen")
    public ModelAndView userLogout() {
        ModelAndView mav = new ModelAndView(LOGOUT);
        return mav;
    }

}
