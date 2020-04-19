package skilift.webserver.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Reservation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@NotNull
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "gastronomy_id")
	private Gastronomy gastronomy;
	
	@ManyToOne
	@JoinColumn(name = "seat_type_id")
	private SeatType seatType;
	
	@NotNull
	private Date reservationTime;
	
	@NotNull
	private Date reservationTimestamp;
	
	@NotNull
	private Integer seats;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Gastronomy getGastronomy() {
		return gastronomy;
	}

	public void setGastronomy(Gastronomy gastronomy) {
		this.gastronomy = gastronomy;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	public Date getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Date getReservationTimestamp() {
		return reservationTimestamp;
	}

	public void setReservationTimestamp(Date reservationTimestamp) {
		this.reservationTimestamp = reservationTimestamp;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

}