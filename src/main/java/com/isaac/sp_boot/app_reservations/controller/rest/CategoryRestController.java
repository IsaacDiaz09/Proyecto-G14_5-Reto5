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

import com.isaac.sp_boot.app_reservations.model.Category;
import com.isaac.sp_boot.app_reservations.service.CategoryService;

@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class CategoryRestController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/all")
	public List<Category> recuperarCategorias() {
		return categoryService.traerTodo();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarCategoria(@RequestBody Category category) {
		categoryService.guardarCategoria(category);
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void actualizarCategoria(@RequestBody Category category) {
		categoryService.actualizarCategoria(category);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarCategoria(@PathVariable("id") int id) {
		categoryService.eliminarCategoria(id);
	}
}
