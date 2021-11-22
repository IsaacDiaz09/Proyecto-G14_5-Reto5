package com.isaac.sp_boot.app_reservations.repository.crud;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isaac.sp_boot.app_reservations.model.Client;

@Repository
public interface ClientCrudRepository extends CrudRepository<Client, Integer> {
	/**
	 * regresa el numero de reservas por x cliente
	 * 
	 * @param idClient
	 * @return int
	 */
	@Query(value = "select count(id_client) from reservations inner join clients on client_reservations = id_client where client_reservations=?1", nativeQuery = true)

	/**
	 * regresa el numero de reservas que tiene cada cliente, 0 si es ninguna
	 * 
	 * @param idClient
	 * @return int
	 */
	Object returnNumberOfReservationsByClient(int idClient);
}
