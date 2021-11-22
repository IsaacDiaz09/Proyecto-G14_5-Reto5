package com.isaac.sp_boot.app_reservations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.isaac.sp_boot.app_reservations.model.Admin;
import com.isaac.sp_boot.app_reservations.model.Cabin;
import com.isaac.sp_boot.app_reservations.model.CabinRating;
import com.isaac.sp_boot.app_reservations.model.Category;
import com.isaac.sp_boot.app_reservations.model.Client;
import com.isaac.sp_boot.app_reservations.model.Message;
import com.isaac.sp_boot.app_reservations.model.Reservation;
import com.isaac.sp_boot.app_reservations.service.AdminService;
import com.isaac.sp_boot.app_reservations.service.CabinRatingService;
import com.isaac.sp_boot.app_reservations.service.CabinService;
import com.isaac.sp_boot.app_reservations.service.CategoryService;
import com.isaac.sp_boot.app_reservations.service.ClientService;
import com.isaac.sp_boot.app_reservations.service.MessageService;
import com.isaac.sp_boot.app_reservations.service.ReservationService;

@Controller
public class IndexController {

	/**
	 * Inyeccion de la dependencia de los servicios necesarios
	 */
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private MessageService msgService;
	@Autowired
	private CabinService cabinService;
	@Autowired
	private CabinRatingService cabinRatingService;

	@GetMapping({ "/", "/index", "/home" })
	public String inicio(Model model) {
		model.addAttribute("routes", new Urls().getIndexRoutes());
		return "index";
	}

	@GetMapping("/cabins")
	public String cabana(Model model) {

		List<Cabin> cabanas = cabinService.traerTodo();
		List<Category> categorias = categoryService.traerTodo();

		model.addAttribute("categorias", categorias);
		model.addAttribute("cabin", new Cabin());
		model.addAttribute("cabanas", cabanas);
		model.addAttribute("title", "Cabañas");
		model.addAttribute("txtCard", "Nueva Cabaña");
		model.addAttribute("action", "Nuevo");
		model.addAttribute("submitBtnTxt", "Agregar");
		model.addAttribute("formAction", "Agregar");

		model.addAttribute("isCabinActive", true);
		model.addAttribute("listTab", true);

		return "vistas/cabin-view";
	}

	@GetMapping("/categories")
	public String categorias(Model model) {

		List<Category> categorias = categoryService.traerTodo();

		model.addAttribute("category", new Category());
		model.addAttribute("cats", categorias);
		model.addAttribute("title", "Categorias");
		model.addAttribute("isCategoryActive", true);
		model.addAttribute("listTab", true);

		return "vistas/category-view";
	}

	@GetMapping("/clients")
	public String clientes(Model model) {

		List<Client> clientes = clientService.TraerTodo();

		model.addAttribute("clients", clientes);
		model.addAttribute("client", new Client());
		model.addAttribute("title", "Clientes");
		model.addAttribute("isClientActive", true);
		model.addAttribute("action", "Nuevo");
		model.addAttribute("listTab", true);
		model.addAttribute("txtCard", "Nuevo Cliente");
		model.addAttribute("submitBtnTxt", "Agregar");
		model.addAttribute("formAction", "Agregar");

		return "vistas/client-view";
	}

	@GetMapping("/messages")
	public String mensajes(Model model) {

		List<Client> clients = clientService.TraerTodo();
		List<Cabin> cabins = cabinService.traerTodo();
		List<Message> messages = msgService.traerTodo();

		model.addAttribute("message", new Message());
		model.addAttribute("clients", clients);
		model.addAttribute("cabins", cabins);
		model.addAttribute("messages", messages);
		model.addAttribute("title", "Mensajes");
		model.addAttribute("isMsgActive", true);
		model.addAttribute("action", "Nuevo");
		model.addAttribute("listTab", true);
		model.addAttribute("txtCard", "Nuevo Mensaje");
		model.addAttribute("submitBtnTxt", "Agregar");
		model.addAttribute("formAction", "Agregar");

		return "vistas/message-view";
	}

	@GetMapping("/reservations")
	public String reservas(Model model) {

		List<Reservation> reservaciones = reservationService.traerReservas();
		List<Client> clientes = clientService.TraerTodo();
		List<Cabin> cabanas = cabinService.traerTodo();

		model.addAttribute("reservation", new Reservation());
		model.addAttribute("clientes", clientes);
		model.addAttribute("cabanas", cabanas);
		model.addAttribute("reservas", reservaciones);
		model.addAttribute("title", "Reservas");
		model.addAttribute("action", "Nuevo");
		model.addAttribute("txtCard", "Nueva Reserva");
		model.addAttribute("submitBtnTxt", "Agregar");
		model.addAttribute("Status", new String[] {"Programado","Cancelado","Realizado"});		
		model.addAttribute("listTab", true);
		model.addAttribute("isReservationActive", true);
		model.addAttribute("formAction", "Agregar");

		return "vistas/reservation-view";
	}

	@GetMapping("/ratingReservations")
	public String calificaciones(Model model) {

		List<CabinRating> calificaciones = cabinRatingService.traerTodo();

		model.addAttribute("reservaciones", reservationService.getUnratedReservations());
		model.addAttribute("calificaciones", calificaciones);
		model.addAttribute("rating", new CabinRating());
		model.addAttribute("title", "Calificación de reservas");
		model.addAttribute("isRatingActive", true);
		model.addAttribute("formAction", "Agregar");
		model.addAttribute("action", "Nuevo");
		model.addAttribute("listTab", true);
		model.addAttribute("txtCard", "Nueva Calificación");
		model.addAttribute("submitBtnTxt", "Agregar");
		model.addAttribute("formAction", "Agregar");
		model.addAttribute("editMode", false);

		return "vistas/rating-view";
	}

	@GetMapping("/admins")
	public String formularioAdmin(Model model) {

		List<Admin> admins = adminService.traerTodo();

		model.addAttribute("admin", new Admin());
		model.addAttribute("admins", admins);
		model.addAttribute("title", "Administradores");
		model.addAttribute("isAdminActive", true);
		model.addAttribute("action", "Nuevo");
		model.addAttribute("listTab", true);
		model.addAttribute("txtCard", "Nuevo Administrador");
		model.addAttribute("submitBtnTxt", "Agregar admin");
		model.addAttribute("formAction", "Agregar");

		return "vistas/admin-view";
	}

}
