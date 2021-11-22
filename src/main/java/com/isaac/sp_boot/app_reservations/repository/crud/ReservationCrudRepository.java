package com.isaac.sp_boot.app_reservations.repository.crud;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isaac.sp_boot.app_reservations.model.Reservation;

@Repository
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
	/**
	 * query que regresa el numero de reservas completadas, 0 si es ninguna
	 * 
	 * @return int
	 */
	@Query(value = "select count(r.id_reservation) from Reservations r where r.status='completed'", nativeQuery = true)
	Object reportStatusCompletedReservations();

	/**
	 * query que regresa el numero de reservas canceladas, 0 si es ninguna
	 * 
	 * @return int
	 */
	@Query(value = "select count(r.id_reservation)from Reservations r where r.status='cancelled'", nativeQuery = true)
	Object reportStatusCancelledReservations();

	/**
	 * query que regresa una lista de reservas en un rango de fechas dado
	 * 
	 * @param start
	 * @param devolution
	 * @return List<Reservation>
	 */
	List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore(Date start, Date devolution);
}
