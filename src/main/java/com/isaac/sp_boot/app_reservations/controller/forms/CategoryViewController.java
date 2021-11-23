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

import com.isaac.sp_boot.app_reservations.model.Category;
import com.isaac.sp_boot.app_reservations.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

	/**
	 * Inyeccion de dependencia
	 */
	@Autowired
	CategoryService categoryService;

	/**
	 * Controlador formulario principal, agrega una entidad
	 * 
	 * @param category
	 * @param result
	 * @return path
	 */
	@PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
	public String guardarCategoriaForm(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Category> categorias = categoryService.traerTodo();

			// Categorias
			model.addAttribute("cats", categorias);

			// Activa la pestaña form
			model.addAttribute("isCategoryActive", true);
			model.addAttribute("formTab", true);

			model.addAttribute("title", "Categorias");

			return "vistas/category-view";
		}
		categoryService.guardarCategoria(category);

		return "redirect:/categories";
	}

	/**
	 * dirige al formulario para actualizar con el obj cargado
	 * 
	 * @param category
	 * @param model
	 * @return String path
	 */
	@GetMapping("/update")
	public String redireccionActualizar(Category category, Model model) {
		category = categoryService.buscarCategoria(category);
		List<Category> categorias = categoryService.traerTodo();

		model.addAttribute("cats", categorias);
		model.addAttribute("category", category);
		model.addAttribute("editMode", true);
		model.addAttribute("title", "Editar Categoria");
		model.addAttribute("formTab", true);
		model.addAttribute("isCategoryActive", true);

		return "vistas/category-view";
	}

	/**
	 * Actualiza una categoria
	 * 
	 * @param category
	 * @param result
	 * @param model
	 * @return String path
	 */
	@PostMapping(path = "/update/save")
	public String actualizarCategoriaForm(@Valid Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Category> categorias = categoryService.traerTodo();

			// Lista de categorias
			model.addAttribute("cats", categorias);
			// Activa la pestaña
			model.addAttribute("isCategoryActive", true);
			model.addAttribute("formTab", true);
			model.addAttribute("title", "Editar Categoria");
			// Cambia textos y muestra boton cancelar
			model.addAttribute("editMode", true);

			return "vistas/category-view";
		}
		categoryService.actualizarCategoria(category);

		return "redirect:/categories";
	}

	/**
	 * Elimina la la categoria si existe
	 * 
	 * @param category
	 * @return String redirect:path
	 */
	@GetMapping("/delete")
	public String eliminarCategoriaForm(Category category) {
		category = categoryService.buscarCategoria(category);
		categoryService.eliminarCategoria(category.getId());
		return "redirect:/categories";
	}

}
