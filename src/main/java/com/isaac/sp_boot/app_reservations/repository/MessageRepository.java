package com.isaac.sp_boot.app_reservations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.sp_boot.app_reservations.model.Message;
import com.isaac.sp_boot.app_reservations.repository.crud.MessageCrudRepository;

@Repository
public class MessageRepository {

	@Autowired
	MessageCrudRepository messageCrudRepo;

	@Transactional(readOnly = true)
	public List<Message> traerMensajes() {
		return (List<Message>) messageCrudRepo.findAll();
	}

	@Transactional(readOnly = true)
	public Optional<Message> traerMensaje(int id) {
		return messageCrudRepo.findById(id);
	}

	@Transactional
	public void guardarMensaje(Message msg) {
		messageCrudRepo.save(msg);
	}

	@Transactional
	public void actualizaMensaje(Message msg) {
		messageCrudRepo.save(msg);
	}

	@Transactional
	public void eliminarMensaje(Message msg) {
		messageCrudRepo.delete(msg);
	}
}
