package com.isaac.sp_boot.app_reservations.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isaac.sp_boot.app_reservations.model.Client;
import com.isaac.sp_boot.app_reservations.service.ClientService;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })

public class ClientRestController {

	@Autowired
	ClientService clientService;

	@GetMapping("/all")
	public List<Client> recuperarClientes() {
		return clientService.TraerTodo();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarCliente(@RequestBody Client client) {
		clientService.guardarCliente(client);
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void actualizaCliente(@RequestBody Client client) {
		clientService.actualizaCliente(client);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarCliente(@PathVariable("id") int id) {
		clientService.eliminarCliente(id);
	}

}
