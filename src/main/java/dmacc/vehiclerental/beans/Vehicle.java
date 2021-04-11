package dmacc.vehiclerental.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="vehicle")

public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String model;
	private String make;
	private int age;
	private boolean availability;

}
