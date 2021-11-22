package com.isaac.sp_boot.app_reservations.controller.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.isaac.sp_boot.app_reservations.model.Reservation;
import com.isaac.sp_boot.app_reservations.service.ClientService;
import com.isaac.sp_boot.app_reservations.service.ReservationService;

@RestController
@RequestMapping("/api/Reservation")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class ReservationRestController {

	@Autowired
	ReservationService reservationService;

	@Autowired
	ClientService clientService;

	@GetMapping("/all")
	public List<Reservation> recuperarReservaciones() {
		return reservationService.traerReservas();
	}

	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void guardarReservacion(@RequestBody Reservation reservation) {
		reservationService.guardarReservacion(reservation);

	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void actualizarReserva(@RequestBody Reservation reservation) {
		reservationService.actualizaReserva(reservation);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void eliminarReserva(@PathVariable("id") int id) {
		reservationService.eliminarReservacion(id);
	}

	@GetMapping("report-clients")
	public List<Object> reportTopClients() {
		return clientService.getTopClientReport();
	}

	@GetMapping("/report-status")
	public Object getReportStatus() {
		return reservationService.returnReportStatus();

	}

	@GetMapping("/report-dates/{date1}/{date2}")
	public Object getReportDates(@PathVariable("date1") String date1, @PathVariable("date2") String date2) {
		return reservationService.getReportBetweenDates(date1, date2);
	}

}
