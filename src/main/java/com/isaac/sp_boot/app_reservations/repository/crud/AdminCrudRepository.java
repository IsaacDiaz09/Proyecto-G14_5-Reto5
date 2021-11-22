package com.isaac.sp_boot.app_reservations.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isaac.sp_boot.app_reservations.model.Admin;

@Repository
public interface AdminCrudRepository extends JpaRepository<Admin, Integer> {

}
