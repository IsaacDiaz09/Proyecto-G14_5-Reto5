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

import com.isaac.sp_boot.app_reservations.model.Admin;
import com.isaac.sp_boot.app_reservations.service.AdminService;

@Controller
@RequestMapping("/admins")
public class AdminViewController {

	/**
	 * Inyeccion de dependencia
	 */
	@Autowired
	AdminService adminService;

	/**
	 * Utilizado tanto para persistir un admin como actualizarlo
	 * 
	 * @param admin
	 * @param result
	 * @return String path
	 */
	@PostMapping(path = "/save")
	public String guardarAdminForm(@Valid Admin admin, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Admin> admins = adminService.traerTodo();

			// Inyecta los objs para listar y los hace visibles
			model.addAttribute("admin", admin);
			model.addAttribute("admins", admins);
			model.addAttribute("isAdminActive", true);
			model.addAttribute("formTab", true);

			// Modifica la vista
			model.addAttribute("title", "Administradores");
			model.addAttribute("submitBtnTxt", "Agregar");
			model.addAttribute("action", "Nuevo");
			model.addAttribute("txtCard", "Nuevo Administrador");

			model.addAttribute("formAction", "Agregar");

			return "vistas/admin-view";
		}

		adminService.guardarAdmin(admin);

		return "redirect:/admins";
	}

	/**
	 * metodo que redirige a actualizar admin. re utiliza la misma vista de agregar
	 * pero manda diferentes datos, activa la otra tarjeta para insertar los nuevos
	 * datos
	 * 
	 * @return path
	 */
	@GetMapping("/update")
	public String redireccionActualizar(Admin admin, Model model) {
		admin = adminService.traerAdmin(admin.getId()).get();
		List<Admin> admins = adminService.traerTodo();

		// Inserta el obj a modificar y los de la lista para que se puedan seguir viendo
		model.addAttribute("admin", admin);
		model.addAttribute("admins", admins);
		// Modifica los textos y pone activa la otra pestaña
		model.addAttribute("action", "Editar");
		model.addAttribute("submitBtnTxt", "Actualizar");
		model.addAttribute("txtCard", "Editar Administrador");
		model.addAttribute("title", "Editar Administrador");
		model.addAttribute("isAdminActive", true);
		model.addAttribute("formTab", true);
		// Activa el boton de cancelar en el form actualizar
		model.addAttribute("showCancelBtn", true);

		return "vistas/admin-view";
	}

	/**
	 * Actualiza un administrador
	 * 
	 * @param admin
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/update/save")
	public String actualizarAdminForm(@Valid Admin admin, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Admin> admins = adminService.traerTodo();

			// Inyecta los objs para listar y los hace visibles
			model.addAttribute("admin", admin);
			model.addAttribute("admins", admins);
			model.addAttribute("isAdminActive", true);
			model.addAttribute("formTab", true);

			// Modifica la vista
			model.addAttribute("title", "Editar Admin");
			model.addAttribute("txtCard", "Editar Administrador");
			model.addAttribute("submitBtnTxt", "Actualizar");
			model.addAttribute("showCancelBtn", true);
			model.addAttribute("action", "Editar");

			return "vistas/admin-view";
		}
		adminService.actualizarAdmin(admin);

		return "redirect:/admins";

	}

	/**
	 * elimina un admin
	 * 
	 * @param admin
	 * @return path, redirige al formulario principal para ver el cambio
	 */
	@GetMapping("/delete")
	public String eliminarAdminForm(Admin admin) {
		adminService.eliminarAdmin(admin);
		return "redirect:/admins";
	}
}
