package dmacc.repository;

import dmacc.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Rumbi Chinhamhora rchinhamhora
 * CIS 175 - Spring 2021
 * Apr 10, 2021
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	List<Customer> findAll();
}
