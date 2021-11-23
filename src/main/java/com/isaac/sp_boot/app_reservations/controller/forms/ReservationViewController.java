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
import com.isaac.sp_boot.app_reservations.model.Client;
import com.isaac.sp_boot.app_reservations.model.Reservation;
import com.isaac.sp_boot.app_reservations.service.CabinService;
import com.isaac.sp_boot.app_reservations.service.ClientService;
import com.isaac.sp_boot.app_reservations.service.ReservationService;

@Controller
@RequestMapping("/reservations")
public class ReservationViewController {

	/**
	 * Se inyectan las dependencias requeridas
	 */
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private CabinService cabinService;

	@Autowired
	private ClientService clientService;

	/**
	 * Recibe un obj de tipo reserva y lo persiste si es valido
	 * 
	 * @param reservation
	 * @param result
	 * @param model
	 * @return String path
	 */
	@PostMapping(path = "/save", consumes = "application/x-www-form-urlencoded")
	public String guardarReservacionForm(@Valid Reservation reservation, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Reservation> reservaciones = reservationService.traerReservas();
			List<Client> clientes = clientService.TraerTodo();
			List<Cabin> cabanas = cabinService.traerTodo();
			// Objs a listar
			model.addAttribute("clientes", clientes);
			model.addAttribute("reservas", reservaciones);
			model.addAttribute("cabanas", cabanas);
			// Textos a cambiar en la vista
			model.addAttribute("title", "Agregar Reserva");
			model.addAttribute("action", "Nuevo");
			model.addAttribute("submitBtnTxt", "Agregar");
			model.addAttribute("formAction", "Agregar");

			return "vistas/reservation-view";
		}

		reservationService.guardarReservacion(reservation);

		return "redirect:/reservations";
	}

	/**
	 * Dirige al form de actualizar con el obj a modificar
	 * 
	 * @param reservation
	 * @param model
	 * @return String path
	 */
	@GetMapping("/update")
	public String redireccionActualizar(Reservation reservation, Model model) {
		List<Reservation> reservaciones = reservationService.traerReservas();
		List<Client> clientes = clientService.TraerTodo();
		List<Cabin> cabanas = cabinService.traerTodo();
		reservation = reservationService.buscarReserva(reservation);

		model.addAttribute("reservation", reservation);

		// Para que se pueda ver el listado correctamente y se inyecten los objs en los
		// select html
		model.addAttribute("clientes", clientes);
		model.addAttribute("reservas", reservaciones);
		model.addAttribute("cabanas", cabanas);
		model.addAttribute("Status", new String[] { "Programado", "Cancelado", "Realizado" });

		// Actualiza los textos a mostrar, enfoca la tarjeta y muestra el boton de
		// cancelar
		model.addAttribute("submitBtnTxt", "Actualizar");
		model.addAttribute("txtCard", "Editar Reservacion");
		model.addAttribute("title", "Editar Reserva");
		model.addAttribute("action", "Editar");
		model.addAttribute("isReservationActive", true);
		model.addAttribute("formTab", true);
		model.addAttribute("showCancelBtn", true);

		model.addAttribute("Status", new String[] { "Programado", "Cancelado", "Realizado" });

		return "vistas/reservation-view";
	}

	/**
	 * Actualiza la reservacion con nuevos datos
	 * 
	 * @param reservation
	 * @param result
	 * @param model
	 * @return String redirect:path
	 */
	@PostMapping(path = "/update/save")
	public String actualizarReservacionForm(@Valid Reservation reservation, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<Reservation> reservaciones = reservationService.traerReservas();
			List<Client> clientes = clientService.TraerTodo();
			List<Cabin> cabanas = cabinService.traerTodo();
			model.addAttribute("Status", new String[] { "Programado", "Cancelado", "Realizado" });

			// Objs a listar
			model.addAttribute("reservation", reservation);
			model.addAttribute("clientes", clientes);
			model.addAttribute("reservas", reservaciones);
			model.addAttribute("cabanas", cabanas);

			// Textos a cambiar en la vista,mostrar boton cancelar
			model.addAttribute("txtCard", "Editar Reservacion");
			model.addAttribute("submitBtnTxt", "Actualizar");
			model.addAttribute("title", "Editar Reserva");
			model.addAttribute("action", "Editar");
			model.addAttribute("showCancelBtn", true);
			model.addAttribute("isReservationActive", true);

			return "vistas/reservation-view";
		}
		reservationService.actualizaReserva(reservation);

		return "redirect:/reservations";
	}

	/**
	 * Elimina una reserva
	 * 
	 * @param reservation
	 * @return String redirect:path
	 */
	@GetMapping("/delete")
	public String eliminarReservacionForm(Reservation reservation) {
		reservation = reservationService.buscarReserva(reservation);
		reservationService.eliminarReservacion(reservation.getIdReservation());

		return "redirect:/reservations";
	}
}
