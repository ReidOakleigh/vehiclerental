package dmacc.repository;

import dmacc.beans.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    @Query("SELECT v FROM Vehicle v WHERE v.availability = true")
    List<Vehicle> findAllActiveVehicles();
}
