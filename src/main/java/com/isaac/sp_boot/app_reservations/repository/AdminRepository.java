package com.isaac.sp_boot.app_reservations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.sp_boot.app_reservations.model.Admin;
import com.isaac.sp_boot.app_reservations.repository.crud.AdminCrudRepository;

@Repository
public class AdminRepository {

	@Autowired
	private AdminCrudRepository adminCrudRepo;

	@Transactional(readOnly = true)
	public List<Admin> traerAdmins() {
		return (List<Admin>) adminCrudRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Admin> traerAdmin(int id) {
		return adminCrudRepo.findById(id);
	}

	@Transactional
	public void guardarAdmin(Admin admin) {
		adminCrudRepo.save(admin);
	}

	@Transactional
	public void actualizarAdmin(Admin admin) {
		adminCrudRepo.save(admin);
	}

	@Transactional
	public void eliminarAdmin(Admin admin) {
		adminCrudRepo.delete(admin);
	}

}
