package dmacc.controllers;

import dmacc.beans.Customer;
import dmacc.beans.Rental;
import dmacc.beans.Vehicle;
import dmacc.repository.CustomerRepository;
import dmacc.repository.RentalRepository;
import dmacc.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Rumbi
 * Date: 4/24/21
 * Time: 4:09 PM
 */
@Controller
public class RentalController {
    @Autowired
    RentalRepository repo;

    @Autowired
    CustomerRepository custRepo;

    @Autowired
    VehicleRepository vehicleRepo;

    @GetMapping("/inputRental")
    String createRental( Model model) {
        System.out.println("Input rental");
        Rental rental = new Rental();
        List<Customer> customers = custRepo.findAll(Sort.by("custName"));
        List<Vehicle> vehicles = vehicleRepo.findAllActiveVehicles();
        model.addAttribute("customers", customers);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("rental", rental);
        System.out.println("Rendering newRental");
        return "newRental";
    }

    @PostMapping("/saveRental/{rentalId}")
    String saveRental( Rental rental, Model model) {
        if (rental.getRentalId() == 0) {
            repo.save(rental);
            updateAvail(rental);
        } else if ( rental.isReturned() ) {
            Rental r = repo.getOne(rental.getRentalId());
            r.setReturned(true);
            repo.save(r);
            updateAvail(r);
        }
        return viewRentals(model);
    }

    private void updateAvail( Rental rental ) {
        Vehicle vehicle = vehicleRepo.getOne(rental.getVehicle().getVehicleId());
        vehicle.setAvailability(rental.isReturned());
        vehicleRepo.save(vehicle);
    }

    @GetMapping("/viewRentals")
    String viewRentals(Model model) {
        System.out.println("View rentals");
        if (repo.findAllActiveRentals().isEmpty()) return createRental(model);
        List<Rental> rentals = repo.findAllActiveRentals();
        System.out.println(rentals.size());
        model.addAttribute("rentals", rentals);
        return "viewRentals";
    }

    @GetMapping("/viewRental/{rentalId}")
    String viewRental( @PathVariable("rentalId") long rentalId, Model model) {
        Rental rental = repo.getOne(rentalId);
        model.addAttribute("rental", rental);
        return "viewRental";
    }
}
