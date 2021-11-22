package com.isaac.sp_boot.app_reservations.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaac.sp_boot.app_reservations.model.Admin;
import com.isaac.sp_boot.app_reservations.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepo;

	/**
	 * trae a todos los admins existentes
	 * 
	 * @return List
	 */
	public List<Admin> traerTodo() {
		return adminRepo.traerAdmins();
	}

	/**
	 * recupera un admin por su id
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Admin> traerAdmin(int id) {
		return adminRepo.traerAdmin(id);
	}

	/**
	 * Realiza las validaciones y si cumple guarda el administrador
	 * 
	 * @param admin
	 */
	public void guardarAdmin(Admin admin) {
		if (Objects.isNull(admin.getId())) {
			adminRepo.guardarAdmin(admin);
		} else {
			Optional<Admin> adminAux = adminRepo.traerAdmin(admin.getId());
			if (adminAux.isEmpty()) {
				adminRepo.guardarAdmin(admin);
			}
		}
	}

	/**
	 * Actualiza un administrador recibido en una peticion put, actualiza solo los
	 * campos que se hayan enviado en el JSON, lo demas lo deja intacto
	 * 
	 * @param admin
	 */
	public void actualizarAdmin(Admin admin) {
		if (!Objects.isNull(admin.getId())) {
			Optional<Admin> adminAux = adminRepo.traerAdmin(admin.getId());
			if (adminAux.isPresent()) {
				Admin adminToUpdate = adminAux.get();

				if (!Objects.isNull(admin.getName())) {
					adminToUpdate.setName(admin.getName());
				}
				if (!Objects.isNull(admin.getEmail())) {
					adminToUpdate.setEmail(admin.getEmail());
				}
				if (!Objects.isNull(admin.getPassword())) {
					adminToUpdate.setPassword(admin.getPassword());
				}

				adminRepo.guardarAdmin(adminToUpdate);

			}
		}
	}

	/**
	 * Elimina el objeto solo si no es nulo y si existe
	 * 
	 * @param admin
	 */
	public void eliminarAdmin(Admin admin) {
		if (!Objects.isNull(admin.getId())) {
			Optional<Admin> adminAux = adminRepo.traerAdmin(admin.getId());
			if (adminAux.isPresent()) {
				adminRepo.eliminarAdmin(adminAux.get());
			}
		}
	}
}
