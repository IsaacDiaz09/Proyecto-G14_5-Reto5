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

import com.isaac.sp_boot.app_reservations.model.CabinRating;
import com.isaac.sp_boot.app_reservations.service.CabinRatingService;

@RestController
@RequestMapping("/api/Score")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class CabinRatingRestController {

	@Autowired
	CabinRatingService cabinRatingService;

	@GetMapping("/all")
	public List<CabinRating> traerCalificaciones() {
		return cabinRatingService.traerTodo();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarCalificacion(@RequestBody CabinRating cabinRating) {
		cabinRatingService.guardarCalificacion(cabinRating);

	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void actualizarCalificacion(@RequestBody CabinRating cabinRating) {
		cabinRatingService.guardarCalificacion(cabinRating);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminarCalificacion(@PathVariable("id") int id) {
		cabinRatingService.eliminarCalificacion(id);
	}

}
