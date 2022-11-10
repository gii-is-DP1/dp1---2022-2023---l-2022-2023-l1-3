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

@Controller
@RequestMapping(path = "/admins")
public class AdminResource{
    @Autowired
    private AdminService as;
    private static final String VIEWS_FORM = "administrators/createAdminForm";


    @GetMapping(path="/create")
    public String viewForm(ModelMap map){
        String view = VIEWS_FORM;
        map.addAttribute("administrator", new Admin());
        return view;
    }

    @PostMapping(path = "/create")
    public String createProduct(@Valid Admin admin, BindingResult res, ModelMap map){
        String view = "welcome";
        if(res.hasErrors()){
            map.addAttribute("administrator", admin);
            return VIEWS_FORM;
        }else{
           as.save(admin);
            map.addAttribute("message", "Administrator succesfully save");
        }
        return view;
    }
}