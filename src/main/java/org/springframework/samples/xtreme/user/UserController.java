package org.springframework.samples.xtreme.user;

import java.util.Optional;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.admin.AdminService;
import org.springframework.samples.xtreme.player.LoginForm;
import org.springframework.samples.xtreme.player.Player;
import org.springframework.samples.xtreme.player.PlayerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/users")
public class UserController {
    
    private static final String LOGIN_FORM = "users/loginForm";
    private static final String LOGOUT = "users/logout";
    private static final String HOME = "users/home";

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id");
    }

    @GetMapping("/login")
    public ModelAndView userLogin() {
        ModelAndView mav = new ModelAndView(LOGIN_FORM);
        mav.addObject("user", new User());
        return mav;
    }

    @GetMapping(path = "/logout-screen")
    public ModelAndView userLogout() {
        ModelAndView mav = new ModelAndView(LOGOUT);
        return mav;
    }

    @GetMapping(path="/home")
    public ModelAndView gameHome() {
        ModelAndView mav = new ModelAndView(HOME);

        // obtener el usuario actualmente logueado
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails=null;
        Boolean esAdmin = false;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
           System.out.println("---Nombre actual del usuario logueado: "+userDetails.getUsername());
           System.out.println("su rol es: "+ userDetails.getAuthorities());
            esAdmin=userDetails.getAuthorities().stream().anyMatch(x-> x.getAuthority().equals("admin"));
          }

        mav.addObject("esAdmin", esAdmin);
        return mav;
    }

}
