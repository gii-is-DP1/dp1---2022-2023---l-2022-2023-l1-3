package org.springframework.samples.xtreme.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/admins")
public class AdminController{
    
    private AdminService adminService;
    private static final String VIEWS_FORM = "admins/createAdminForm";
    private static final String USERS_LIST = "admins/adminList";

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ModelAndView showAdminList() {
        ModelAndView mav = new ModelAndView(USERS_LIST);
        mav.addObject("players", this.adminService.getAllAdmins());
        return mav;
    }

    @GetMapping(path="/create")
    public ModelAndView viewForm(){
        ModelAndView mav = new ModelAndView(VIEWS_FORM);
        mav.addObject("admin", new Admin());
        return mav;
    }

    @PostMapping(path = "/create")
    public ModelAndView createAdmin(@Valid Admin admin, BindingResult res){
        ModelAndView mav = new ModelAndView("redirect:/");
        Admin a = adminService.findByUsername(admin.getUser().getUsername());
        if(res.hasErrors()){
            mav = new ModelAndView(VIEWS_FORM);
            mav.addObject("admin", admin);
        } 
        if(a!=null) {
            mav = new ModelAndView(VIEWS_FORM);
            mav.addObject("admin", admin);
            if(a.getUser().getUsername().equals(admin.getUser().getUsername())) {
                mav.addObject("message", "El nombre de usuario ya está registrado.");
            }
            if(a.getEmail().equals(admin.getEmail())) {
                mav.addObject("message", "El email de usuario ya está registrado");
            }
        } else{
            adminService.save(admin);
        }
        return mav;
    }
}
