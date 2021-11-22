package com.isaac.sp_boot.app_reservations.controller.forms_controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isaac.sp_boot.app_reservations.model.Cabin;
import com.isaac.sp_boot.app_reservations.model.Client;
import com.isaac.sp_boot.app_reservations.model.Message;
import com.isaac.sp_boot.app_reservations.service.CabinService;
import com.isaac.sp_boot.app_reservations.service.ClientService;
import com.isaac.sp_boot.app_reservations.service.MessageService;

@Controller
@RequestMapping("/messages")
public class MessageViewController {

	@Autowired
	MessageService msgService;
	@Autowired
	CabinService cabinService;
	@Autowired
	ClientService clientService;

	/**
	 * Recibe un obj mensaje y lo persiste
	 * 
	 * @param message
	 * @param result
	 * @param model
	 * @return String path
	 */
	@PostMapping(path = "/save")
	public String guardarMensajeForm(@Valid Message message, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Client> clients = clientService.TraerTodo();
			List<Cabin> cabins = cabinService.traerTodo();
			List<Message> messages = msgService.traerTodo();

			model.addAttribute("clients", clients);
			model.addAttribute("cabins", cabins);
			model.addAttribute("messages", messages);

			model.addAttribute("title", "Mensajes");
			model.addAttribute("action", "Nuevo");
			model.addAttribute("txtCard", "Nuevo Mensaje");
			model.addAttribute("submitBtnTxt", "Agregar");
			model.addAttribute("isMsgActive", true);
			model.addAttribute("formTab", true);
			model.addAttribute("formAction", "Agregar");

			return "vistas/message-view";
		}
		msgService.guardarMensaje(message);

		return "redirect:/messages";

	}

	/**
	 * Redirige a la vista actualizar e inyecta el obj a modificar
	 * 
	 * @param message
	 * @param model
	 * @return String path
	 */
	@GetMapping("/update")
	public String redireccionActualizar(Message message, Model model) {
		message = msgService.traerMensaje(message.getIdMessage()).get();

		List<Client> clients = clientService.TraerTodo();
		List<Cabin> cabins = cabinService.traerTodo();
		List<Message> messages = msgService.traerTodo();

		model.addAttribute("clients", clients);
		model.addAttribute("cabins", cabins);
		model.addAttribute("messages", messages);

		model.addAttribute("message", message);

		model.addAttribute("submitBtnTxt", "Actualizar");
		model.addAttribute("txtCard", "Editar Mensaje");
		model.addAttribute("title", "Editar Mensaje");
		model.addAttribute("action", "Editar");
		model.addAttribute("isMsgActive", true);
		model.addAttribute("formTab", true);
		model.addAttribute("showCancelBtn", true);

		return "vistas/message-view";
	}

	/**
	 * Actualiza un mensaje
	 * 
	 * @param message
	 * @param result
	 * @param model
	 * @return String path
	 */
	@PostMapping(path = "/update/save")
	public String actualizaMensajeForm(@Valid Message message, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Client> clients = clientService.TraerTodo();
			List<Cabin> cabins = cabinService.traerTodo();
			List<Message> messages = msgService.traerTodo();

			model.addAttribute("clients", clients);
			model.addAttribute("cabins", cabins);
			model.addAttribute("messages", messages);

			model.addAttribute("isMsgActive", true);
			model.addAttribute("formTab", true);

			model.addAttribute("txtCard", "Editar Mensaje");
			model.addAttribute("submitBtnTxt", "Actualizar");
			model.addAttribute("title", "Editar Mensaje");
			model.addAttribute("action", "Editar");
			model.addAttribute("showCancelBtn", true);

			return "vistas/message-view";
		}
		msgService.actualizaMensaje(message);

		return "redirect:/messages";

	}

	/**
	 * elimina un obj de tipo mensaje
	 * 
	 * @param message
	 * @return String redirect:path
	 */
	@GetMapping("/delete")
	public String eliminarMensajeForm(Message message) {
		message = msgService.traerMensaje(message.getIdMessage()).get();
		msgService.eliminarMensaje(message.getIdMessage());

		return "redirect:/messages";
	}

}
