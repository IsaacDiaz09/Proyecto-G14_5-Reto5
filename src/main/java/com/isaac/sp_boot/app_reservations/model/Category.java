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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "categories")
public class Category implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6451698233090560327L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty(message = "· El nombre es requerido")
	@Size(min = 3, max = 45, message = "· El nombre debe tener entre 3 y 45 caracteres")
	@Column(length = 45)
	private String name;

	@NotEmpty(message = "· La descripcion es requerida")
	@Size(max = 250, message = "· La descripción es muy larga")
	@Column(length = 250)
	private String description;

	/**
	 * Relacion uno - muchos -> cabañas
	 */
	@OneToMany(cascade = { CascadeType.PERSIST }, mappedBy = "category")
	@JsonIgnoreProperties("category")
	private List<Cabin> cabins;

	// Constructor

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param description
	 * @param cabins
	 */
	public Category(String name, String description, List<Cabin> cabins) {
		this.name = name;
		this.description = description;
		this.cabins = cabins;
	}

}