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

import com.isaac.sp_boot.app_reservations.model.Cabin;
import com.isaac.sp_boot.app_reservations.model.Category;
import com.isaac.sp_boot.app_reservations.service.CabinService;
import com.isaac.sp_boot.app_reservations.service.CategoryService;

@Controller
@RequestMapping("/cabins")
public class CabinViewController {

	/**
	 * Inyeccion de dependencias necesarias
	 */
	@Autowired
	private CabinService cabinService;
	@Autowired
	private CategoryService categoryService;

	/**
	 * valida los campos y si estan correctos persiste/actualiza la cabaña
	 * 
	 * @param cabin
	 * @param result
	 * @return path
	 */
	@PostMapping(path = "/save")
	public String guardarCabanaForm(@Valid Cabin cabin, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Cabin> cabanas = cabinService.traerTodo();
			List<Category> categorias = categoryService.traerTodo();

			// Inyecta los objs a listar en la tabla
			model.addAttribute("cabin", cabin);
			model.addAttribute("categorias", categorias);
			model.addAttribute("cabanas", cabanas);
			// Pone activa la pestaña del form
			model.addAttribute("isCabinActive", true);
			model.addAttribute("formTab", true);
			// Vuelve a poner los textos de los elementos
			model.addAttribute("submitBtnTxt", "Agregar");
			model.addAttribute("title", "Cabañas");
			model.addAttribute("action", "Nuevo");
			model.addAttribute("txtCard", "Nueva Cabaña");

			return "vistas/cabin-view";
		}
		cabinService.guardarCabana(cabin);

		return "redirect:/cabins";
	}

	/**
	 * actualiza una cabaña
	 * 
	 * @param cabin
	 * @param result
	 * @param model
	 * @return String redirect:path
	 */
	@PostMapping(path = "/update/save")
	public String actualizaCabanaForm(@Valid Cabin cabin, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Cabin> cabanas = cabinService.traerTodo();
			List<Category> categorias = categoryService.traerTodo();

			// Inyecta los objs a listar en la tabla
			model.addAttribute("cabin", cabin);
			model.addAttribute("categorias", categorias);
			model.addAttribute("cabanas", cabanas);
			// Pone activa la pestaña del form
			model.addAttribute("isCabinActive", true);
			model.addAttribute("formTab", true);
			// Vuelve a poner los textos de los elementos
			model.addAttribute("txtCard", "Editar Cabaña");
			model.addAttribute("submitBtnTxt", "Actualizar");
			model.addAttribute("title", "Cabañas");
			// Muestra boton cancelar
			model.addAttribute("showCancelBtn", true);

			return "vistas/cabin-view";
		}
		cabinService.actualizarCabana(cabin);
		return "redirect:/cabins";
	}

	/**
	 * dirige al formulario para actualizar
	 * 
	 * @param cabin
	 * @param model
	 * @return String path
	 */
	@GetMapping("/update")
	public String redireccionActualizar(Cabin cabin, Model model) {
		cabin = cabinService.traerCabanaPorId(cabin.getId()).get();
		List<Cabin> cabanas = cabinService.traerTodo();
		List<Category> categorias = categoryService.traerTodo();

		// Modifica la vista a ser ostrada para editar cabaña
		model.addAttribute("categorias", categorias);
		model.addAttribute("cabanas", cabanas);
		model.addAttribute("cabin", cabin);
		model.addAttribute("action", "Editar");
		model.addAttribute("submitBtnTxt", "Actualizar");
		model.addAttribute("title", "Editar Cabaña");
		model.addAttribute("txtCard", "Editar Cabaña");
		model.addAttribute("showCancelBtn", true);
		model.addAttribute("isCabinActive", true);
		model.addAttribute("formTab", true);

		return "vistas/cabin-view";
	}

	/**
	 * Elimina la cabaña seleccionada si existe
	 * 
	 * @param cabin
	 * @return String redirect:path
	 */
	@GetMapping("/delete")
	public String eliminarCategoriaForm(Cabin cabin) {
		cabin = cabinService.traerCabanaPorId(cabin.getId()).get();
		cabinService.eliminarCabana(cabin.getId());

		return "redirect:/cabins";
	}

}
