package dmacc.beans;

import javax.persistence.*;

import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long vehicleId;
	private String model;
	private String make;
	private int age;
	private boolean availability;
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "dealerId", nullable = false)
	private Dealership dealer;
}
