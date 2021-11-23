package com.isaac.sp_boot.app_reservations.controller.forms;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.isaac.sp_boot.app_reservations.model.Client;
import com.isaac.sp_boot.app_reservations.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientViewController {
	/**
	 * Inyeccion de dependencia
	 */
	@Autowired
	ClientService clientService;

	/**
	 * Guarda el cliente diligenciado en el formulario
	 * 
	 * @param client
	 * @param result
	 * @param model
	 * @return path
	 */
	@PostMapping(path = "/save")
	public String guardarClienteForm(@Valid Client client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Client> clientes = clientService.TraerTodo();
			// Clientes a listar
			model.addAttribute("clients", clientes);

			// Muestra la tarjeta activa, btn cancelar y textos
			model.addAttribute("title", "Clientes");
			model.addAttribute("action", "Nuevo");
			model.addAttribute("txtCard", "Nuevo Cliente");
			model.addAttribute("isClientActive", true);
			model.addAttribute("formTab", true);

			model.addAttribute("submitBtnTxt", "Agregar");
			model.addAttribute("formAction", "Agregar");

			return "vistas/client-view";
		}
		clientService.guardarCliente(client);

		return "redirect:/clients";
	}

	/**
	 * redirige al form para actualizar la entidad
	 * 
	 * @param client
	 * @param model
	 * @return
	 */
	@GetMapping("/update")
	public String redireccionActualizar(Client client, Model model) {
		client = clientService.traerCliente(client.getIdClient()).get();
		List<Client> clientes = clientService.TraerTodo();
		model.addAttribute("clients", clientes);

		model.addAttribute("client", client);
		model.addAttribute("submitBtnTxt", "Actualizar");
		model.addAttribute("txtCard", "Editar Cliente");
		model.addAttribute("title", "Editar Cliente");
		model.addAttribute("action", "Editar");
		model.addAttribute("isClientActive", true);
		model.addAttribute("formTab", true);
		model.addAttribute("showCancelBtn", true);

		return "vistas/client-view";
	}

	/**
	 * Actualiza un cliente
	 * 
	 * @param client
	 * @param result
	 * @param model
	 * @return String path
	 */
	@PostMapping(path = "/update/save")
	public String actualizarClienteForm(@Valid Client client, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Client> clientes = clientService.TraerTodo();
			// Lista de clientes
			model.addAttribute("clients", clientes);

			// Muestra la tarjeta activa, btn cancelar y textos
			model.addAttribute("isClientActive", true);
			model.addAttribute("formTab", true);

			model.addAttribute("txtCard", "Editar Cliente");
			model.addAttribute("submitBtnTxt", "Actualizar");
			model.addAttribute("title", "Editar Cliente");
			model.addAttribute("action", "Editar");
			model.addAttribute("showCancelBtn", true);

			return "vistas/client-view";
		}
		clientService.actualizaCliente(client);

		return "redirect:/clients";
	}

	/**
	 * elimina el cliente y redirige al form principal
	 * 
	 * @param id
	 * @return path
	 */
	@GetMapping("/delete")
	public String eliminarClienteForm(Client client) {
		client = clientService.traerCliente(client.getIdClient()).get();
		clientService.eliminarCliente(client.getIdClient());

		return "redirect:/clients";
	}

}