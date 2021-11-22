package com.isaac.sp_boot.app_reservations.controller.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isaac.sp_boot.app_reservations.model.Admin;
import com.isaac.sp_boot.app_reservations.service.AdminService;

@RestController
@RequestMapping("/api/Admin")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class AdminRestController {

	@Autowired
	AdminService adminService;

	@GetMapping("/all")
	public List<Admin> traerAdmins() {
		return adminService.traerTodo();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarAdmin(@RequestBody Admin admin) {
		adminService.guardarAdmin(admin);
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void actualizarAdmin(@RequestBody Admin admin) {
		adminService.actualizarAdmin(admin);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarAdmin(Admin admin) {
		adminService.eliminarAdmin(admin);
	}

}
