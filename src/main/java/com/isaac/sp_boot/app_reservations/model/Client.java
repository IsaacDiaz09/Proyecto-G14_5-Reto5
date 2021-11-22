package com.isaac.sp_boot.app_reservations.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clients")
public class Client implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4139042031597267795L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idClient;

	@Column(length = 45)
	@NotEmpty(message = "El email es obligatorio")
	@Size(max = 45, message = "El email es muy largo")
	private String email;

	@Column(length = 45)
	@Size(min = 8, max = 45, message = "La contraseña debe tener entre 8 y 45 caracteres")
	@NotEmpty(message = "La contraseña es obligatoria")
	private String password;

	@Column(length = 250)
	@Size(min = 3, max = 250, message = "El nombre debe tener entre 250 y 3 caracteres")
	private String name;

	@Max(value = 100, message = "El valor de la edad es muy elevado")
	private int age;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "client")
	@JsonIgnoreProperties("client")
	private List<Message> messages;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "client")
	@JsonIgnoreProperties("client")
	private List<Reservation> reservations;

	// Constructor

	/**
	 * Constructor
	 * 
	 * @param email
	 * @param password
	 * @param name
	 * @param age
	 * @param messages
	 * @param reservations
	 */
	public Client(String email, String password, String name, int age, List<Message> messages,
			List<Reservation> reservations) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
		this.messages = messages;
		this.reservations = reservations;
	}

}