package net.skhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.skhu.domain.Employees;
import net.skhu.repository.EmployeesRepository;

@RestController
@RequestMapping("api")
public class APIController {
/*
    @Autowired CustomersRepository customersRepository;
    @Autowired OrdersRepogitory ordersRepository;
*/

	@Autowired EmployeesRepository employeesRepository;

    @RequestMapping("employees")
    public List<Employees> employees() {
        return employeesRepository.findEmployees();
    }
   /*
    @RequestMapping("customer/{id}")
    public Customers customer(@PathVariable("id") int id) {
        return customersRepository.findById(id).get();
    }

    @RequestMapping("orders")
    public List<Orders> departments() {
        return ordersRepository.findAll();
    }

    @RequestMapping("customer/{id}/orders")
    public List<Orders> customerOrders(@PathVariable("id") int id) {
        Customers customers = customersRepository.findById(id).get();
        return customers.getOrders();
    }
*/

}

