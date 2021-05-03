package dmacc.controllers;

import dmacc.beans.Dealership;
import dmacc.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Rumbi Chinhamhora rchinhamhora
 * CIS 175 - Spring 2021
 * Apr 10, 2021
 */

@Controller
public class DealershipController {

	@Autowired
	DealershipRepository repo;

	@GetMapping({ "/viewDealers" })
	public String viewAllDealerships(Model model) {
		if(repo.findAll().isEmpty()) return addNewDealership(model);
		List<Dealership> dealerships = repo.findAll();
		model.addAttribute("dealerships", dealerships);
		return "viewDealers";
	}

	@GetMapping("/inputDealership")
	public String addNewDealership(Model model) {
		Dealership newDealership = new Dealership();
		model.addAttribute("newDealership", newDealership);
		return "newDealer";
	}

	@GetMapping("/editDealer/{dealerId}")
	public String showUpdateDealership(@PathVariable("dealerId") long dealerId, Model model) {
		Dealership newDealership = repo.findById(dealerId).orElse(null);
		model.addAttribute("newDealership", newDealership);
		return "newDealer";
	}

	@PostMapping("/updateDealer/{dealerId}")
	public String saveDealership(Dealership newDealership, Model model) {
		repo.save(newDealership);
		return viewAllDealerships(model);
	}
	
	@GetMapping("/deleteDealer/{dealerId}")
	public String deleteDealer(@PathVariable("dealerId") long dealerId, Model model) {
		repo.findById(dealerId).ifPresent(repo :: delete);
	    return viewAllDealerships(model);
	}
}