package dmacc.vehiclerental.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.vehiclerental.beans.Customer;
import dmacc.vehiclerental.repos.CustomerRepository;

/**
 * Rumbi Chinhamhora rchinhamhora
 * CIS 175 - Spring 2021
 * Apr 10, 2021
 */

@Controller
public class CustomerController {
	
	@Autowired
	CustomerRepository repo;
	
	@GetMapping("/")
	String viewAllCustomers(Model model) {
		List<Customer> customers = repo.findAll();
		if (customers.isEmpty()) return "newCust";
		model.addAttribute("customers", customers);
		return "viewCustomers";
	}
	
	@GetMapping("/inputCustomer")
	String addNewCustomer(Model model) {
		Customer newCust = new Customer();
		model.addAttribute("newCust", newCust);
		return "newCust";
	}
	
	@GetMapping("/edit/{id}")
	String showUpdateCustomer(@PathVariable("id") long id, Model model) {
		Customer cust = repo.findById(id).orElse(null);
		model.addAttribute("newCust", cust);
		return "newCust";
	}
	
	@PostMapping("/update/{id}")
	String saveCust(Customer newCust, Model model) {
		repo.save(newCust);
		return "viewCustomers";
	}
}
