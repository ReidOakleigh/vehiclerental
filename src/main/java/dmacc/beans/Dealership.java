package dmacc.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unused")
@Data
@NoArgsConstructor
@Entity
@Table(name="dealership")
public class Dealership {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long dealerId;
	private String name;
}
