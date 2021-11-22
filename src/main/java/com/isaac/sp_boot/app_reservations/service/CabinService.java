package com.isaac.sp_boot.app_reservations.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaac.sp_boot.app_reservations.model.Cabin;
import com.isaac.sp_boot.app_reservations.repository.CabinRepository;

@Service
public class CabinService {
	@Autowired
	CabinRepository cabinRepo;

	/**
	 * recupra todas las bañs
	 * 
	 * @return List
	 */
	public List<Cabin> traerTodo() {
		return cabinRepo.traerCabanas();
	}

	/**
	 * trae una cabaña por su id
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Cabin> traerCabanaPorId(int id) {
		return cabinRepo.traerCabana(id);
	}

	/**
	 * Guarda una cabaña solo si no existe
	 * 
	 * @param cabin
	 */
	public void guardarCabana(Cabin cabin) {
		if (Objects.isNull(cabin.getId())) {
			cabinRepo.guardarCabana(cabin);
		} else {
			Optional<Cabin> cabinAux = cabinRepo.traerCabana(cabin.getId());
			if (cabinAux.isEmpty()) {
				cabinRepo.guardarCabana(cabin);
			}

		}
	}

	/**
	 * Actualiza los atributos de una cabaña
	 * 
	 * @param cabin
	 */
	public void actualizarCabana(Cabin cabin) {
		if (!Objects.isNull(cabin.getId())) {
			Optional<Cabin> cabinAux = cabinRepo.traerCabana(cabin.getId());

			if (cabinAux.isPresent()) {
				Cabin cabinToUpdate = cabinAux.get();

				if (!Objects.isNull(cabin.getBrand())) {
					cabinToUpdate.setBrand(cabin.getBrand());
				}
				if (!Objects.isNull(cabin.getName())) {
					cabinToUpdate.setName(cabin.getName());
				}

				if (!Objects.isNull(cabin.getDescription())) {
					cabinToUpdate.setDescription(cabin.getDescription());
				}

				if (!Objects.isNull(cabin.getRooms())) {
					cabinToUpdate.setRooms(cabin.getRooms());
				}
				cabinRepo.actualizaCabana(cabinToUpdate);
			}
		}
	}

	/**
	 * elimina una cabaña si existe
	 * 
	 * @param id
	 */
	public void eliminarCabana(int id) {
		if (!Objects.isNull(id)) {
			Optional<Cabin> cabinAux = cabinRepo.traerCabana(id);
			if (cabinAux.isPresent()) {
				cabinRepo.eliminarCabana(cabinAux.get());
			}
		}

	}
}
