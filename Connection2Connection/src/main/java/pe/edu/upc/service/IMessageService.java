package pe.edu.upc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.upc.model.Message;
import pe.edu.upc.model.Role;


public interface IMessageService {
	public boolean insertar(Message message);
	public void eliminar(int idmessage);
	List<Message> listar();
}
