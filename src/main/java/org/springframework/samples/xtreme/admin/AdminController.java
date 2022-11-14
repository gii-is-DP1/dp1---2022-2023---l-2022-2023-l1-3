package org.springframework.samples.xtreme.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.samples.xtreme.user.Authorities;
import org.springframework.samples.xtreme.user.AuthoritiesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private final AuthoritiesService authoritiesService;
    private final AdminValidator adminValidator;

    private static final String VIEWS_FORM = "admins/createAdminForm";
    private static final String USERS_LIST = "admins/adminList";

    @Autowired
    public AdminController(AdminService adminService,AuthoritiesService authoritiesService,AdminValidator adminValidator) {
        this.adminService = adminService;
        this.authoritiesService = authoritiesService;
        this.adminValidator = adminValidator;
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
        ModelAndView mav = new ModelAndView("redirect:/");
        Admin a = adminService.findByUsername(admin.getUser().getUsername());
        if(res.hasErrors()){
            mav = new ModelAndView(VIEWS_FORM);
            mav.addObject("admin", admin);
        } else{
            adminService.save(admin);
        }
        return mav;
    }
}
