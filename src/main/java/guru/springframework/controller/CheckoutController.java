package guru.springframework.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.comands.CheckoutCommand;

@Controller
public class CheckoutController {

	@RequestMapping("/checkout")
	public String CheckoutForm(Model model) {
		
		model.addAttribute("checkoutCommand", new CheckoutCommand());
		return "checkoutform";
	}
	
	@PostMapping("/docheckout")
	public String doCheckout(@Valid CheckoutCommand checkoutCommand, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "checkoutform";
		}
		
		return "checkoutcomplete";
	}
}
