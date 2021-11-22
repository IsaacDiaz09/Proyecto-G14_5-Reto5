package com.isaac.sp_boot.app_reservations.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaac.sp_boot.app_reservations.model.Client;
import com.isaac.sp_boot.app_reservations.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepo;

	/**
	 * Devuelve a todos los clientes
	 * 
	 * @return List Client
	 */
	public List<Client> TraerTodo() {
		return clientRepo.traerClientes();
	}

	/**
	 * trae un cliente por su id
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Client> traerCliente(int id) {
		return clientRepo.traerCliente(id);
	}

	/**
	 * guarda si un cliente si este no existe ya
	 * 
	 * @param Client
	 */
	public void guardarCliente(Client client) {
		if (Objects.isNull(client.getIdClient())) {
			if (client.getAge() > 0) {
				clientRepo.guardarCliente(client);
			}
		} else {
			Optional<Client> catAux = clientRepo.traerCliente(client.getIdClient());
			if (!catAux.isPresent()) {
				clientRepo.guardarCliente(client);
			}
		}

	}

	/**
	 * Actualiza los atributos nombre, edad, contraseña de un cliente si este existe
	 * y no son nulos
	 * 
	 * @param client
	 */
	public void actualizaCliente(Client client) {
		if (!Objects.isNull(client.getIdClient())) {
			Optional<Client> clientAux = clientRepo.traerCliente(client.getIdClient());

			if (clientAux.isPresent()) {
				Client clientToUpdate = clientAux.get();

				if (!Objects.isNull(client.getName())) {
					clientToUpdate.setName(client.getName());
				}
				if (!Objects.isNull(client.getAge())) {
					clientToUpdate.setAge(client.getAge());
				}
				if (!Objects.isNull(client.getName())) {
					clientToUpdate.setPassword(client.getPassword());
				}

				clientRepo.actualizaCliente(clientToUpdate);
			}
		}
	}

	/**
	 * Elimina un cliente si este existe
	 * 
	 * @param id
	 */
	public void eliminarCliente(int id) {
		if (!Objects.isNull(id)) {
			Optional<Client> clienteAux = clientRepo.traerCliente(id);
			if (clienteAux.isPresent()) {
				clientRepo.eliminarCliente(clienteAux.get());
			}
		}
	}

	/**
	 * genera el reporte top de clientes a ser entregado por el controlador
	 * 
	 * @return List
	 */
	public List<Object> getTopClientReport() {
		List<Object> list = new ArrayList<Object>();
		List<Client> clientes = sortClientsByReservations();

		for (Client cliente : clientes) {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("total", clientRepo.nReservationsByClient(cliente.getIdClient()));
			map.put("client", cliente);
			list.add(map);
		}

		return list;
	}

	/**
	 * Ordena los clientes seun quien tenga mas reservaciones a su nombre
	 * 
	 * @return List
	 */
	private List<Client> sortClientsByReservations() {
		int cuentaIntercambios = 0;
		boolean ordenado = false;
		List<Client> clientes = clientRepo.traerClientes();

		while (!ordenado) {
			for (int i = 0; i < clientes.size() - 1; i++) {
				int var2 = clientes.get(i).getReservations().size();
				int var1 = clientes.get(i + 1).getReservations().size();
				// si la cantidad de reservas de un cliente es mayor que la del siguiente...
				if (var1 > var2) {
					// intercambia de posicion a los clientes en la lista
					Client varAux = clientes.get(i);
					clientes.add(i, clientes.get(i + 1));
					clientes.add(i + 1, varAux);
				}
			}
			// si no hay intercambios, ya esta ordenado
			if (cuentaIntercambios == 0) {
				ordenado = true;
			}
			cuentaIntercambios = 0;
		}
		return clientes;

	}
}
