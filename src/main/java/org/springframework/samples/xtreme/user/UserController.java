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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/users")
public class UserController {
    
    private static final String LOGIN_FORM = "users/loginForm";
    private static final String GAME_HOME = "players/gameHome";

	private final UserService userService;
    private final AdminService adminService;

	@Autowired
	public UserController(UserService userService, AdminService adminService) {
		this.userService = userService;
        this.adminService = adminService;
	}

	@InitBinder
	public void setDisallowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @GetMapping("/login")
    public ModelAndView userLogin() {
        ModelAndView mav = new ModelAndView(LOGIN_FORM);
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView processLoginForm(@Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView(GAME_HOME);
        User u = userService.findByUsername(user.getUsername()).get();
        if (result.hasErrors()) {
            mav = new ModelAndView(LOGIN_FORM);
            mav.addObject("user", user);
        }

        if(u==null || !u.getUsername().equals(user.getUsername())
            || !u.getPassword().equals(user.getPassword())) {

            mav = new ModelAndView(LOGIN_FORM);
            mav.addObject("user", user);
            mav.addObject("message", "El usuario o la contraseña no son correctos");
        }
        
        Boolean esAdmin=false;
        if(!u.getAuthorities().isEmpty()){
         esAdmin= u.getAuthorities().stream().anyMatch(x-> x.getAuthority().equals("admin"));
       //System.out.println(esAdmin);
        }
       /* Authentication a=SecurityContextHolder.getContext().getAuthentication();
        User u2=(User) a.getPrincipal();
        System.out.println(u2.getAuthorities());*/
        mav.addObject("esAdmin", esAdmin);
        return mav;
    }
        
}
