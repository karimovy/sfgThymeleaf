package guru.springframework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.ProductService;

@Controller
@RequestMapping("/thymeleaf")
public class ProductController {
	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/product")
	public String getProduct() {
		return "redirect:/thymeleaf/";
	}
	
	@RequestMapping("/product/{id}")
	public String getProductId(@PathVariable Integer id, Model model) {
		System.out.println("Controller call !!!");
		model.addAttribute("product", productService.getProduct(id));
		return "product";
	}

}
