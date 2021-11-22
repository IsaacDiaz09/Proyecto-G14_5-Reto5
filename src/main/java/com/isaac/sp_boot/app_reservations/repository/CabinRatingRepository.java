package com.isaac.sp_boot.app_reservations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.sp_boot.app_reservations.model.CabinRating;
import com.isaac.sp_boot.app_reservations.repository.crud.CabinRatingCrudRepository;

@Repository
public class CabinRatingRepository {
	@Autowired
	CabinRatingCrudRepository cabinRatingCrudRepo;

	@Transactional(readOnly = true)
	public List<CabinRating> traerTodas() {
		return (List<CabinRating>) cabinRatingCrudRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<CabinRating> traerCalificacion(int id) {
		return cabinRatingCrudRepo.findById(id);
	}

	@Transactional
	public void guardarCalificacion(CabinRating cabinRating) {
		cabinRatingCrudRepo.save(cabinRating);
	}

	@Transactional
	public void actualizarCalificacion(CabinRating cabinRating) {
		cabinRatingCrudRepo.save(cabinRating);
	}

	@Transactional
	public void eliminarCalificacion(CabinRating cabinRating) {
		cabinRatingCrudRepo.delete(cabinRating);

	}

}
