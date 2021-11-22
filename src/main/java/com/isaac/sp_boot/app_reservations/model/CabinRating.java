package com.isaac.sp_boot.app_reservations.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reservations_rating")
public class CabinRating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Range(min=1,max=5,message="La calificación de la reserva debe ser entre 1 y 5")
	private int rate;

	@Column(length = 250)
	@Size(max=250,message="El mensaje es demasiado grande")
	private String message;

	@OneToOne
	@JoinColumn(name = "reservation_id")
	@JsonIgnoreProperties("score")
	private Reservation reservation;

	/**
	 * Constructor
	 * @param rate
	 * @param message
	 * @param reservation
	 */
	public CabinRating(int rate, String message, Reservation reservation) {
		this.rate = rate;
		this.message = message;
		this.reservation = reservation;
	}

}