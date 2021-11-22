package com.isaac.sp_boot.app_reservations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.sp_boot.app_reservations.model.Category;
import com.isaac.sp_boot.app_reservations.repository.crud.CategoryCrudRepository;

@Repository
public class CategoryRepository {

	@Autowired
	CategoryCrudRepository categoryCrudRepo;

	@Transactional(readOnly = true)
	public List<Category> traerCategorias() {
		return (List<Category>) categoryCrudRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Category> traerCategoria(int id) {
		return categoryCrudRepo.findById(id);
	}

	@Transactional
	public void guardarCategoria(Category cat) {
		categoryCrudRepo.save(cat);
	}

	@Transactional
	public void actualizarCategoria(Category cat) {
		categoryCrudRepo.save(cat);
	}

	@Transactional
	public void eliminarCategoria(Category cat) {
		categoryCrudRepo.delete(cat);
	}
}
