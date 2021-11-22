package com.isaac.sp_boot.app_reservations.repository;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.sp_boot.app_reservations.model.Reservation;
import com.isaac.sp_boot.app_reservations.repository.crud.ReservationCrudRepository;

@Repository
public class ReservationRepository {

	@Autowired
	ReservationCrudRepository reservationCrudRepo;

	@Transactional(readOnly = true)
	public List<Reservation> traerReservas() {
		return (List<Reservation>) reservationCrudRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Reservation> traerReservacion(int id) {
		return reservationCrudRepo.findById(id);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> getReportCanceledVsCompleted() {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("completed", reservationCrudRepo.reportStatusCompletedReservations());
		map.put("cancelled", reservationCrudRepo.reportStatusCancelledReservations());
		return map;
	}

	@Transactional(readOnly = true)
	public List<Reservation> getReservationsBetweenDates(Date start, Date devolution) {
		return reservationCrudRepo.findAllByStartDateAfterAndDevolutionDateBefore(start, devolution);
	}

	@Transactional
	public void guardarReservacion(Reservation reservation) {
		reservationCrudRepo.save(reservation);
	}

	@Transactional
	public void actualizarReservacion(Reservation reservation) {
		reservationCrudRepo.save(reservation);
	}

	@Transactional
	public void eliminarReservacion(Reservation reservation) {
		reservationCrudRepo.delete(reservation);
	}

}
