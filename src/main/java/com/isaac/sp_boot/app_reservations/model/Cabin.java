package com.isaac.sp_boot.app_reservations.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Historia de usuario 3.1 -Creación de Cabañas.
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "cabins")
public class Cabin implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7302950957278953901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 45)
	@NotEmpty(message = "El nombre no puede estar vacio")
	@Size(max = 45, message = "El nombre puede ser de 45 caracteres como máximo")
	private String name;

	@Column(length = 45)
	@NotEmpty(message = "La marca no puede estar vacia")
	@Size(max = 45, message = "La marca puede tener 45 caracteres como máximo")
	private String brand;

	@NotNull(message = "El número de habitaciones es requerido")
	@Positive(message = "El número de habitaciones no puede ser negativo")
	private int rooms;

	@Column(length = 250)
	@NotEmpty(message = "La descripcion es obligatoria")
	@Size(max = 250, message = "La descripción es muy larga")
	private String description;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	@JsonIgnoreProperties("cabins")
	private Category category;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "cabin")
	@JsonIgnoreProperties({ "cabin", "client" })
	private List<Message> messages;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "cabin")
	@JsonIgnoreProperties("cabin")
	private List<Reservation> reservations;

	// Constructor
	
	/**
	 * Contructor
	 * @param name
	 * @param brand
	 * @param rooms
	 * @param description
	 * @param category
	 * @param messages
	 * @param reservations
	 */
	public Cabin(String name, String brand, int rooms, String description, Category category, List<Message> messages,
			List<Reservation> reservations) {
		super();
		this.name = name;
		this.brand = brand;
		this.rooms = rooms;
		this.description = description;
		this.category = category;
		this.messages = messages;
		this.reservations = reservations;
	}


}