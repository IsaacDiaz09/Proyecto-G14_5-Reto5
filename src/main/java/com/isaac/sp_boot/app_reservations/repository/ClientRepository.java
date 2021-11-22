package com.isaac.sp_boot.app_reservations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.sp_boot.app_reservations.model.Client;
import com.isaac.sp_boot.app_reservations.repository.crud.ClientCrudRepository;

@Repository
public class ClientRepository {

	@Autowired
	private ClientCrudRepository clienteCrudRepo;

	@Transactional(readOnly = true)
	public List<Client> traerClientes() {
		return (List<Client>) clienteCrudRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Client> traerCliente(int id) {
		return clienteCrudRepo.findById(id);
	}

	@Transactional
	public void guardarCliente(Client client) {
		clienteCrudRepo.save(client);
	}

	@Transactional
	public void actualizaCliente(Client client) {
		clienteCrudRepo.save(client);
	}

	@Transactional
	public void eliminarCliente(Client client) {
		clienteCrudRepo.delete(client);
	}

	@Transactional(readOnly = true)
	public Object nReservationsByClient(int id) {
		return clienteCrudRepo.returnNumberOfReservationsByClient(id);
	}
}
