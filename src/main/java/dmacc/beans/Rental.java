package dmacc.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Rumbi
 * Date: 4/24/21
 * Time: 4:01 PM
 */
@Data
@NoArgsConstructor
@Entity
@Table()
public class Rental {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long rentalId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", nullable = false)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;
    private boolean returned;

}
