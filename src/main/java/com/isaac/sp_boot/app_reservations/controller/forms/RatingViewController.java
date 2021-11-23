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

import com.isaac.sp_boot.app_reservations.model.CabinRating;
import com.isaac.sp_boot.app_reservations.service.CabinRatingService;
import com.isaac.sp_boot.app_reservations.service.ReservationService;

@Controller
@RequestMapping("/ratingReservations")
public class RatingViewController {

	/**
	 * Se inyectan las dependencias necesarias
	 */
	@Autowired
	private CabinRatingService reservationRateService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private CabinRatingService cabinRatingService;

	/**
	 * Guarda un obj de tipo calificacion de reserva
	 * 
	 * @param cabinRating
	 * @param result
	 * @param model
	 * @return String path
	 */
	@PostMapping(path = "/save")
	public String guardarCalificacion(@Valid CabinRating cabinRating, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<CabinRating> calificaciones = reservationRateService.traerTodo();
			// objs a listary calificaciones disponibles par ponee en el select
			model.addAttribute("calificaciones", calificaciones);
			model.addAttribute("reservaciones", reservationService.getUnratedReservations());
			// Se pasa a la vista el mismo obj para que no pierda lo que ingreso
			model.addAttribute("rating", cabinRating);

			// Se pasan textos a la vista y se activa la tarjeta
			model.addAttribute("title", "Clientes");
			model.addAttribute("action", "Nuevo");
			model.addAttribute("txtCard", "Nueva Calficación");
			model.addAttribute("isRatingActive", true);
			model.addAttribute("formTab", true);

			model.addAttribute("submitBtnTxt", "Agregar");
			model.addAttribute("formAction", "Agregar");

			return "vistas/rating-view";
		}
		reservationRateService.guardarCalificacion(cabinRating);
		return "redirect:/ratingReservations";
	}

	/**
	 * Redirige a la vista de actualizar calificacion
	 * 
	 * @param rating
	 * @param model
	 * @return String redirect:path
	 */
	@GetMapping("/update")
	public String formEditRating(CabinRating rating, Model model) {
		rating = reservationRateService.traerCalificacionReserva(rating.getId()).get();
		// Se pasa el obj a editar a la sig vista
		model.addAttribute("rating", rating);
		// Elementos necesarios para molstrar el select y alist de nuevo
		List<CabinRating> calificaciones = cabinRatingService.traerTodo();
		model.addAttribute("calificaciones", calificaciones);
		// Se adapta la vista textos headings y se muestra btn cancelar
		model.addAttribute("submitBtnTxt", "Actualizar");
		model.addAttribute("txtCard", "Editar Calificacion");
		model.addAttribute("title", "Editar Calificacion");
		model.addAttribute("action", "Editar");
		model.addAttribute("isRatingActive", true);
		model.addAttribute("formTab", true);
		model.addAttribute("showCancelBtn", true);
		model.addAttribute("editMode", true);

		return "vistas/rating-view";
	}

	/**
	 * Actualiza una calificacion
	 * 
	 * @param cabinRating
	 * @param model
	 * @param result
	 * @return String path
	 */
	@PostMapping("/update/save")
	public String editarCalificacion(@Valid CabinRating cabinRating, Model model, BindingResult result) {
		if (result.hasErrors()) {
			// Se pasa el mismo obj a editar
			model.addAttribute("rating", cabinRating);
			model.addAttribute("isRatingActive", true);

			// Textos a cambiar en la vista,mostrar boton cancelar
			model.addAttribute("txtCard", "Editar Calificación");
			model.addAttribute("submitBtnTxt", "Actualizar");
			model.addAttribute("title", "Editar Calificacion");
			model.addAttribute("action", "Editar");
			model.addAttribute("showCancelBtn", true);

			return "vistas/rating-view";
		}
		reservationRateService.actualizarCalificacion(cabinRating);

		return "redirect:/ratingReservations";
	}

	/**
	 * Elimina la calificacion para una reserva
	 * 
	 * @param cabinRating
	 * @return String redirect:path
	 */
	@GetMapping("/delete")
	public String eliminarReservacionForm(CabinRating cabinRating) {
		cabinRating = reservationRateService.traerCalificacionReserva(cabinRating.getId()).get();
		System.out.println(cabinRating.getId());
		reservationRateService.eliminarCalificacion(cabinRating.getId());

		return "redirect:/ratingReservations";
	}
}