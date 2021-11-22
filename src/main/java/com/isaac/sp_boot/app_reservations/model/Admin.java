package com.isaac.sp_boot.app_reservations.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "admins")
public class Admin implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2247784609374932777L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 250)
	@NotEmpty(message = "El campo nombre es obligatorio")
	@Size(min = 3, max = 250, message = "El nombre debe tener mas de 3 letras y menos de 250")
	private String name;

	@Column(length = 45)
	@NotEmpty(message = "El email es obligatorio")
	@Size(max = 45, message = "El email no puede exceder los 45 caracteres")
	@Email(message="Email inválido")
	private String email;

	@Column(length = 45)
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8, max = 45, message = "La contraseña debe tener entre 8 y 45 caracteres")
	private String password;

	// Constructor
	
	/**
	 * Constructor
	 * @param String name
	 * @param String email
	 * @param String password
	 */
	public Admin(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
