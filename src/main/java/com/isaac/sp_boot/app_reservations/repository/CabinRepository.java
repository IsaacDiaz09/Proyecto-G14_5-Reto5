package com.isaac.sp_boot.app_reservations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.sp_boot.app_reservations.model.Cabin;
import com.isaac.sp_boot.app_reservations.repository.crud.CabinCrudRepository;

@Repository
public class CabinRepository {

	@Autowired
	private CabinCrudRepository cabinCrudRepo;

	@Transactional(readOnly = true)
	public List<Cabin> traerCabanas() {
		return (List<Cabin>) cabinCrudRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Cabin> traerCabana(int id) {
		return cabinCrudRepo.findById(id);
	}

	@Transactional
	public void guardarCabana(Cabin cabin) {
		cabinCrudRepo.save(cabin);
	}

	@Transactional
	public void actualizaCabana(Cabin cabin) {
		cabinCrudRepo.save(cabin);
	}

	@Transactional
	public void eliminarCabana(Cabin cabin) {
		cabinCrudRepo.delete(cabin);
	}

}
