package com.isaac.sp_boot.app_reservations.repository.crud;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isaac.sp_boot.app_reservations.model.Category;

@Repository
public interface CategoryCrudRepository extends CrudRepository<Category, Integer> {

}
