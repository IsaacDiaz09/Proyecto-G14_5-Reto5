package com.isaac.sp_boot.app_reservations.controller;

import java.util.LinkedHashMap;
import java.util.Map;

public class Urls {

	private Map<String, String> routes = new LinkedHashMap<String, String>();

	public Map<String, String> getIndexRoutes() {
		
		routes.put("Categorias", "categories");
		routes.put("Cabañas", "cabins");
		routes.put("Clientes", "clients");
		routes.put("Mensajes", "messages");
		routes.put("Reservaciones", "reservations");
		routes.put("Calificaciones de Reserva", "ratingReservations");
		routes.put("Usuarios administrativos", "admins");
		
		return routes;
	}

}
