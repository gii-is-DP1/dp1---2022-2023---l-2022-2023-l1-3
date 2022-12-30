package org.springframework.samples.xtreme.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.game.GameService;
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
@RequestMapping(path = "/admins")
public class AdminController{
    
    private final AdminService adminService;
    private final AdminValidator adminValidator;
    private final GameService gameService;

    private static final String VIEWS_FORM = "admins/createAdminForm";
    private static final String USERS_LIST = "admins/adminList";
    private static final String ALL_GAMES = "admins/listAllGames";

    @Autowired
    public AdminController(AdminService adminService,AdminValidator adminValidator,GameService gameService) {
        this.adminService = adminService;
        this.adminValidator = adminValidator;
        this.gameService=gameService;
    }

    @InitBinder("admin")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(adminValidator);
    }

    @GetMapping
    public ModelAndView showAdminList() {
        ModelAndView mav = new ModelAndView(USERS_LIST);
        mav.addObject("admins", this.adminService.getAllAdmins());
        return mav;
    }

    @GetMapping(path="/create")
    public ModelAndView viewForm(){
        ModelAndView mav = new ModelAndView(VIEWS_FORM);
        mav.addObject("admin", new Admin());
        return mav;
    }

    @PostMapping(path = "/create")
    public ModelAndView createAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult res){
        ModelAndView mav = new ModelAndView("redirect:/home");
        if(res.hasErrors()){
            mav = new ModelAndView(VIEWS_FORM);
            mav.addObject("admin", admin);
        } else{
            adminService.save(admin);
        }
        return mav;
    }

    @GetMapping(path="/listAllGames")
    public ModelAndView viewAllGames(){
        ModelAndView mav = new ModelAndView(ALL_GAMES);
        mav.addObject("games", this.gameService.getAll());
        return mav;
    }
}
