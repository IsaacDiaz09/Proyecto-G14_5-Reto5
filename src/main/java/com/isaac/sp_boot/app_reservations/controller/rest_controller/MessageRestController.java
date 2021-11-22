package com.isaac.sp_boot.app_reservations.controller.rest_controller;

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

import com.isaac.sp_boot.app_reservations.model.Message;
import com.isaac.sp_boot.app_reservations.service.MessageService;

@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class MessageRestController {

	@Autowired
	MessageService msgService;

	@GetMapping("/all")
	public List<Message> recuperarMensajes() {
		return msgService.traerTodo();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarMensaje(@RequestBody Message msg) {
		msgService.guardarMensaje(msg);

	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void actualizarMensaje(@RequestBody Message msg) {
		msgService.actualizaMensaje(msg);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarMensaje(@PathVariable("id") int id) {
		msgService.eliminarMensaje(id);
	}
}
