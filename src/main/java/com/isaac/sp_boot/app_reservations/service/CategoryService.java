package com.isaac.sp_boot.app_reservations.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaac.sp_boot.app_reservations.model.Category;
import com.isaac.sp_boot.app_reservations.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepo;

	/**
	 * recupera todas las categorias
	 * 
	 * @return List
	 */
	public List<Category> traerTodo() {
		return categoryRepo.traerCategorias();
	}

	/**
	 * trae una categoria por su id
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Category> traerCategoria(int id) {
		return categoryRepo.traerCategoria(id);
	}

	public Category buscarCategoria(Category category) {
		return categoryRepo.traerCategoria(category.getId()).orElse(null);
	}

	public void guardarCategoria(Category cat) {
		if (Objects.isNull(cat.getId())) {
			categoryRepo.guardarCategoria(cat);
		} else {
			Optional<Category> catAux = categoryRepo.traerCategoria(cat.getId());
			if (!catAux.isPresent()) {
				categoryRepo.guardarCategoria(cat);
			}
		}
	}

	/**
	 * Actualiza los atributos de una categoria que no sean nulos, solo si la
	 * categoria existe
	 * 
	 * @param cat
	 */
	public void actualizarCategoria(Category cat) {
		if (!Objects.isNull(cat.getId())) {
			Optional<Category> catAux = categoryRepo.traerCategoria(cat.getId());

			if (catAux.isPresent()) {
				Category category = catAux.get();

				if (!Objects.isNull(cat.getName())) {
					category.setName(cat.getName());
				}
				if (!Objects.isNull(cat.getDescription())) {
					category.setDescription(cat.getDescription());
				}

				categoryRepo.actualizarCategoria(category);
			}
		}
	}

	/**
	 * elimina na categoria si existe
	 * 
	 * @param id
	 */
	public void eliminarCategoria(int id) {
		if (!Objects.isNull(id)) {
			Optional<Category> catAux = categoryRepo.traerCategoria(id);
			if (catAux.isPresent()) {
				categoryRepo.eliminarCategoria(catAux.get());
			}
		}
	}
}
