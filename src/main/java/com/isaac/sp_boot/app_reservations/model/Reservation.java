package com.isaac.sp_boot.app_reservations.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3291318410135834648L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;

	@DateTimeFormat(iso = ISO.DATE)
	private Date startDate;

	@NotNull(message = "La fecha de entrega es requerida")
	@DateTimeFormat(iso = ISO.DATE)
	@FutureOrPresent(message="La fecha de devolución debe ser igual o posterior a la actual")
	private Date devolutionDate;

	private String status;

	@ManyToOne
	@JoinColumn(name = "clientCabins")
	@JsonIgnoreProperties({ "reservations", "client" })
	private Cabin cabin;

	@ManyToOne
	@JoinColumn(name = "clientReservations")
	@JsonIgnoreProperties({ "reservations", "messages" })
	private Client client;

	// Relacion 1 - 1
	@OneToOne(mappedBy = "reservation")
	@JsonIgnoreProperties("reservation")
	private CabinRating score;

	// Constructor

	/**
	 * Constructor
	 * 
	 * @param startDate
	 * @param devolutionDate
	 * @param status
	 * @param cabin
	 * @param client
	 * @param score
	 */
	public Reservation(Date startDate, Date devolutionDate, String status, Cabin cabin, Client client,
			CabinRating score) {
		this.startDate = startDate;
		this.devolutionDate = devolutionDate;
		this.status = status;
		this.cabin = cabin;
		this.client = client;
		this.score = score;
	}

}