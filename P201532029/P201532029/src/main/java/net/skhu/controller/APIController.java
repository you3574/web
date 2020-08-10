package net.skhu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.domain.Customer;
import net.skhu.domain.Order;
import net.skhu.domain.OrderDetails;
import net.skhu.domain.Product;
import net.skhu.repository.CustomersRepository;
import net.skhu.repository.OrderRepository;
import net.skhu.repository.ProductRepository;

@RestController
@RequestMapping("api")
public class APIController {

	@Autowired CustomersRepository customerRepository;
	@Autowired OrderRepository orderRepository;
	@Autowired ProductRepository productRepository;

	 @RequestMapping("products")
	    public List<Product> employees(){
	        return productRepository.findAll();
	    }


	@RequestMapping("customer/{id}/orderDate")
	public List<Date> orderDate(@PathVariable("id") int id) { //날짜
		Customer customer = customerRepository.findById(id).get();
		List<Date> list = new ArrayList<Date>();
		for(Order o : customer.getOrders()) {
			list.add(o.getOrderDate());}
		return list;
	}

	@RequestMapping("customer/{id}/products")
	public List<Product> product(@PathVariable("id") int id) {
		Customer customer = customerRepository.findById(id).get();
		List<Product> product = new ArrayList<Product>();
		for(Order o : customer.getOrders()) {
			for(OrderDetails d : o.getOrderDetails()) {
				product.add(d.getProduct());}
		}
		return product;
	}
}