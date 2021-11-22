package com.isaac.sp_boot.app_reservations.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaac.sp_boot.app_reservations.model.CabinRating;
import com.isaac.sp_boot.app_reservations.repository.CabinRatingRepository;

@Service
public class CabinRatingService {
	@Autowired
	CabinRatingRepository cabinRatingRepository;

	/**
	 * trae a todas las calificaciones
	 * 
	 * @return List
	 */
	public List<CabinRating> traerTodo() {
		return cabinRatingRepository.traerTodas();
	}

	/**
	 * trae una calificacion por su id
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<CabinRating> traerCalificacionReserva(int id) {
		return cabinRatingRepository.traerCalificacion(id);
	}

	/**
	 * comprueba que el objeto recibido no exista, entonces realiza la persistencia
	 * 
	 * @param rating
	 */
	public void guardarCalificacion(CabinRating rating) {
		if (Objects.isNull(rating.getId())) {
			cabinRatingRepository.guardarCalificacion(rating);

		} else {
			Optional<CabinRating> ratingAux = cabinRatingRepository.traerCalificacion(rating.getId());
			if (!ratingAux.isPresent()) {
				cabinRatingRepository.guardarCalificacion(rating);

			}
		}
	}

	/**
	 * actualiza una calificacion con un nuevo mensaje y puntuacion si son enviados
	 * y dicha calificacion existe
	 * 
	 * @param rating
	 */
	public void actualizarCalificacion(CabinRating rating) {
		if (!Objects.isNull(rating.getId())) {
			Optional<CabinRating> ratingAux = cabinRatingRepository.traerCalificacion(rating.getId());
			if (ratingAux.isPresent()) {
				CabinRating ratingToUpdate = ratingAux.get();

				if (!Objects.isNull(rating.getRate())) {
					ratingToUpdate.setRate(rating.getRate());
				}

				if (!Objects.isNull(rating.getMessage())) {
					ratingToUpdate.setMessage(rating.getMessage());
				}
				cabinRatingRepository.actualizarCalificacion(ratingToUpdate);
			}
		}
	}

	/**
	 * metodo para eliminar una calificacion de cabaña si existe
	 * 
	 * @param id
	 */
	public void eliminarCalificacion(int id) {
		if (!Objects.isNull(id)) {
			Optional<CabinRating> ratingAux = cabinRatingRepository.traerCalificacion(id);
			if (ratingAux.isPresent()) {
				cabinRatingRepository.eliminarCalificacion(ratingAux.get());
			}
		}
	}

}
