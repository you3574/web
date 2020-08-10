package net.skhu.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.skhu.domain.Product;
import net.skhu.model.Pagination;
import net.skhu.model.ProductModel;
import net.skhu.repository.ProductRepository;

@Controller
@RequestMapping("product")
public class API2Controller {
	@Autowired ProductRepository productRepository;

	@RequestMapping("list")
	public String list(Pagination pagination, Model model) {
		List<Product> list = productRepository.findAll(pagination);
		model.addAttribute("list", list);
		model.addAttribute("orderBy", ProductRepository.orderBy);
		model.addAttribute("searchBy", ProductRepository.searchBy);
		return "product/list";
	}

	@RequestMapping(value="edit", method=RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) {
		Product product = productRepository.findById(id).get();
		ProductModel productModel = new ProductModel();

		productModel.setProductCode(product.getProductCode());
		productModel.setProductName(product.getProductName());
		productModel.setStandardCost(product.getStandardCost());
		productModel.setListPrice(product.getListPrice());
		productModel.setQuantity(product.getQuantity());
		productModel.setCategory(product.getCategory());

		model.addAttribute("productModel", productModel);

		return "product/edit";
	}

	@Transactional
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public String edit(@Valid ProductModel productModel, @RequestParam("id") int id, Model model, Pagination pagination) {

		Product product = productRepository.findById(id).get();
		product.setProductCode(productModel.getProductCode());
		product.setProductName(productModel.getProductName());
		product.setStandardCost(productModel.getStandardCost());
		product.setListPrice(productModel.getListPrice());
		product.setQuantity(productModel.getQuantity());
		product.setCategory(productModel.getCategory());

		productRepository.updateProduct(product.getId(), product.getProductCode(), product.getProductName(), product.getStandardCost(), product.getListPrice(), product.getQuantity(), product.getCategory());
		return "redirect:list?" + pagination.getQueryString();

	}
}