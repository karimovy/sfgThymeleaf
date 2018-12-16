package guru.springframework.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.comands.CheckoutCommand;
import guru.springframework.comands.LoginCommand;

@Controller
@RequestMapping("/thymeleaf")
public class LoginController {

	@RequestMapping("/login")
	public String showLoginForm(Model model) {
		
		model.addAttribute("loginCommand", new LoginCommand());
		
		return "loginform";
	}
	
	@RequestMapping("/logout-success")
	private String yourLoggedOut() {
		return "logout-success";

	}
	
	@PostMapping("/dologin")
	public String doLogin(@Valid LoginCommand loginCommand, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			return "loginform";
		}
		
		return "redirect:/thymeleaf/";
	}
}
