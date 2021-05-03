package dmacc.controllers;

import dmacc.beans.Dealership;
import dmacc.beans.Vehicle;
import dmacc.repository.DealershipRepository;
import dmacc.repository.VehicleRepository;
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
public class VehicleController {

	@Autowired
	VehicleRepository repo;
	@Autowired
	DealershipRepository dealerRepo;

	@GetMapping({ "/viewVehicles" })
	public String viewAllVehicles(Model model) {
		if(repo.findAll().isEmpty()) return addNewVehicle(model);
		List<Vehicle> vehicles = repo.findAll();
		model.addAttribute("vehicles", vehicles);
		return "viewVehicles";
	}

	@GetMapping("/inputVehicle")
	public String addNewVehicle(Model model) {
		Vehicle v = new Vehicle();
		model.addAttribute("newVehicle", v);
		List<Dealership> dealerships = dealerRepo.findAll();
		model.addAttribute("dealerships", dealerships);
		return "newVehicle";
	}

	@GetMapping("/editVehicle/{vehicleId}")
	public String showUpdateVehicle(@PathVariable("vehicleId") long vehicleId, Model model) {
		Vehicle vehicle = repo.findById(vehicleId).orElse(null);
		model.addAttribute("newVehicle", vehicle);
		List<Dealership> dealerships = dealerRepo.findAll();
		model.addAttribute("dealerships", dealerships);
		return "newVehicle";
	}

	@PostMapping("/updateVehicle/{vehicleId}")
	public String reviseVehicle(Vehicle newVehicle, Model model) {
		repo.save(newVehicle);
		return viewAllVehicles(model);
	}
	
	@GetMapping("/deleteVehicle/{vehicleId}")
	public String deleteVehicle(@PathVariable("vehicleId") long vehicleId, Model model) {
		repo.findById(vehicleId).ifPresent(repo::delete);
	    return viewAllVehicles(model);
	}
}