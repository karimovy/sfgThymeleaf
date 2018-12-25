package guru.springframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.ProductService;

@Controller
@RequestMapping("/thymeleaf")
public class IndexController {

	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		
		model.addAttribute("products", productService.listProducts());
		return "index";
	}

	@RequestMapping("/secured")
	public String secured() {
		
		return "secured";
	}

}
