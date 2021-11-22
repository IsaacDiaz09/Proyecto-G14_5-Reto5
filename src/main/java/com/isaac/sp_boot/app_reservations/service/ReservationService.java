package com.isaac.sp_boot.app_reservations.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaac.sp_boot.app_reservations.model.Reservation;
import com.isaac.sp_boot.app_reservations.repository.ReservationRepository;

@Service
public class ReservationService {
	@Autowired
	ReservationRepository reservationRepo;

	/**
	 * regresa todas las reservas
	 * 
	 * @return List Reservation
	 */
	public List<Reservation> traerReservas() {
		return reservationRepo.traerReservas();
	}

	/**
	 * trae una reserva dentro de un obj Optional
	 * 
	 * @param id
	 * @return Optional<Reservation>
	 */
	public Optional<Reservation> traerReserva(int id) {
		return reservationRepo.traerReservacion(id);
	}

	public Reservation buscarReserva(Reservation reservation) {
		return reservationRepo.traerReservacion(reservation.getIdReservation()).orElse(null);
	}

	/**
	 * persiste un objeto de tipo reserva si este ya no existe
	 * 
	 * @param reservation
	 */
	public void guardarReservacion(Reservation reservation) {
		if (Objects.isNull(reservation.getIdReservation())) {
			if (reservation.getStartDate() == null) {
				reservation.setStartDate(new Date());
			}
			reservation.setStatus("created");
			reservationRepo.guardarReservacion(reservation);
		} else {
			Optional<Reservation> reservationAux = reservationRepo.traerReservacion(reservation.getIdReservation());
			if (!reservationAux.isPresent()) {
				if (reservation.getStartDate() == null) {
					reservation.setStartDate(new Date());
				}
				// Si no se envia un estado para la reserva se establece automaticamente como
				// "creada"
				if (!Objects.isNull(reservation.getStatus())) {
					reservation.setStatus(reservation.getStatus());
				} else {
					reservation.setStatus("created");
				}
				reservationRepo.guardarReservacion(reservation);

			}
		}
	}

	/**
	 * actualiza un objeto de tipo reserva con nuevos atributos
	 * 
	 * @param reservation
	 */
	public void actualizaReserva(Reservation reservation) {
		if (!Objects.isNull(reservation.getIdReservation())) {
			Optional<Reservation> reservationAux = reservationRepo.traerReservacion(reservation.getIdReservation());
			if (reservationAux.isPresent()) {
				Reservation reservationToUpdate = reservationAux.get();

				if (!Objects.isNull(reservation.getStartDate())) {
					reservationToUpdate.setStartDate(reservation.getStartDate());
				}

				if (!Objects.isNull(reservation.getDevolutionDate())) {
					reservationToUpdate.setDevolutionDate(reservation.getDevolutionDate());
				}

				if (!Objects.isNull(reservation.getStatus())) {
					reservationToUpdate.setStatus(reservation.getStatus());
				}

				reservationRepo.actualizarReservacion(reservationToUpdate);

			}
		}
	}

	/**
	 * elimina una reserva si, y solo si esta existe
	 * 
	 * @param id
	 */
	public void eliminarReservacion(int id) {
		if (!Objects.isNull(id)) {
			Optional<Reservation> reservationAux = reservationRepo.traerReservacion(id);
			if (reservationAux.isPresent()) {
				reservationRepo.eliminarReservacion(reservationAux.get());
			}
		}
	}

	public Object returnReportStatus() {
		return reservationRepo.getReportCanceledVsCompleted();
	}

	/**
	 * resultado de una query que regresa reservas en un periodo de tiempo
	 * 
	 * @param date1
	 * @param date2
	 * @return List
	 */
	public List<Reservation> getReportBetweenDates(String date1, String date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateStart = new Date();
		Date dateDevolution = new Date();

		try {
			dateStart = format.parse(date1);
			dateDevolution = format.parse(date2);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		if (dateStart.before(dateDevolution)) {
			return reservationRepo.getReservationsBetweenDates(dateStart, dateDevolution);
		} else {
			return new ArrayList<>();
		}
	}

	/**
	 * Genera una lista con las reservas sin calificar
	 * 
	 * @return List<Reservation>
	 */
	public List<Reservation> getUnratedReservations() {
		List<Reservation> reservacionesNoCalificadas = new ArrayList<>();

		for (Reservation reservacion : reservationRepo.traerReservas()) {
			if (Objects.isNull(reservacion.getScore())) {
				reservacionesNoCalificadas.add(reservacion);
			}
		}
		return reservacionesNoCalificadas;
	}

}
