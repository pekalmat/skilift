package skilift.webserver.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
	uniqueConstraints={
		@UniqueConstraint(columnNames={"seatType"})
	}
)
public class SeatType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	private String seatType;
	
	public SeatType() {
	}
	
	public SeatType(String seatType) {
		this.seatType = seatType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	
}