package com.isaac.sp_boot.app_reservations.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isaac.sp_boot.app_reservations.model.Message;
import com.isaac.sp_boot.app_reservations.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	MessageRepository msgRepo;

	/**
	 * recupera todos los mensajes
	 * 
	 * @return List
	 */
	public List<Message> traerTodo() {
		return msgRepo.traerMensajes();
	}

	/**
	 * trae un mensaje si lo encuentra
	 * 
	 * @param id
	 * @return Optional
	 */
	public Optional<Message> traerMensaje(int id) {
		return msgRepo.traerMensaje(id);
	}

	/**
	 * periste un nuevo msg
	 * 
	 * @param msg
	 */
	public void guardarMensaje(Message msg) {
		if (Objects.isNull(msg.getIdMessage())) {
			msgRepo.guardarMensaje(msg);
		} else {
			Optional<Message> catAux = msgRepo.traerMensaje(msg.getIdMessage());
			if (!catAux.isPresent()) {
				msgRepo.guardarMensaje(msg);

			}
		}
	}

	/**
	 * actualiza los atributos de un mensaje existente
	 * 
	 * @param msg
	 */
	public void actualizaMensaje(Message msg) {
		if (!Objects.isNull(msg.getIdMessage())) {
			Optional<Message> msgAux = msgRepo.traerMensaje(msg.getIdMessage());

			if (msgAux.isPresent()) {
				Message msgToUpdate = msgAux.get();

				if (!Objects.isNull(msg.getMessageText())) {
					msgToUpdate.setMessageText(msg.getMessageText());
				}

				msgRepo.actualizaMensaje(msgToUpdate);

			}
		}
	}

	/**
	 * elimina un mensaje si existe
	 * 
	 * @param id
	 */
	public void eliminarMensaje(int id) {
		if (!Objects.isNull(id)) {
			Optional<Message> msg = msgRepo.traerMensaje(id);

			if (msg.isPresent()) {
				msgRepo.eliminarMensaje(msg.get());
			}
		}
	}
}
