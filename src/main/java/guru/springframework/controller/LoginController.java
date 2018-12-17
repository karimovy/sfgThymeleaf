package guru.springframework.controller;

import guru.springframework.comands.LoginCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


/**
 * Created by jt on 2/2/16.
 */
@Controller
@RequestMapping("/thymeleaf")
public class LoginController {

    @RequestMapping("/login")
    public String showLoginForm(Model model){
    	System.out.println("test Login ============================ AAA");
        model.addAttribute("loginCommand", new LoginCommand());
        System.out.println("test Login ============================ BBB");
        return "loginform";
    }

    @RequestMapping("/logout-success")
    public String yourLoggedOut(){

        return "logout-success";
    }

    //@RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(@Valid LoginCommand loginCommand, BindingResult bindingResult){
System.out.println("test AAAAAAAAAAAAAAAAAAAAAAAA");
        if(bindingResult.hasErrors()){
            return "loginform";
        }

        return "redirect:/thymeleaf/";
    }
}
