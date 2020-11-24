package pe.edu.upc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.edu.upc.model.Message;
import pe.edu.upc.model.Role;
import pe.edu.upc.model.Users;


public interface IMessageService {
	public boolean insertar(Message message);
	public void eliminar(int idmessage);
	List<Message> listar();
	List<Message> listarEnviados(String correoEmisor);
	List<Message> listarBandeja(String correoReceptor);
	public Message getMensaje(int id);
}
