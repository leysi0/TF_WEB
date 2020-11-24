package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Message;
import pe.edu.upc.model.Role;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IMessageRepository;
import pe.edu.upc.repository.IRoleRepository;
import pe.edu.upc.service.IMessageService;
import pe.edu.upc.service.IRoleService;

@Service
public class MessageServiceimpl implements IMessageService {
	
	@Autowired
	private IMessageRepository dMessage;

	@Override
	@Transactional
	public boolean insertar(Message message) {
		Message objRole = dMessage.save(message);
		if (objRole == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public void eliminar(int idmessage) {
		dMessage.deleteById(idmessage);
		
	}
	
	@Override
	@Transactional
	public List<Message> listar() {
		return dMessage.findAll();
	}

	@Override
	public List<Message> listarEnviados(String correoEmisor) {
		return dMessage.listaEnviados(correoEmisor);
	}

	@Override
	public List<Message> listarBandeja(String correoReceptor) {
		return dMessage.listaBandeja(correoReceptor);
	}

	@Override
	public Message getMensaje(int id) {
		// TODO Auto-generated method stub
		return dMessage.findByMessage(id);
	}


	
}
