package pe.edu.upc.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.model.Agenda;
import pe.edu.upc.model.Users;
import pe.edu.upc.repository.IAgendaRepository;
import pe.edu.upc.service.IAgendaService;


@Service
public class AgendaServiceimpl implements IAgendaService {
	
	@Autowired
	private IAgendaRepository aAgenda;

	@Override
	@Transactional
	public boolean insertar(Agenda agenda) {
		Agenda objAgenda = aAgenda.save(agenda);
		if (objAgenda == null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public void eliminar(int idagenda) {
		aAgenda.deleteById(idagenda);
		
	}
	
	@Override
	@Transactional
	public List<Agenda> listar() {
		return aAgenda.findAll();
	}

	@Override
	public Optional<Agenda> listarid(int id) {
	
		return aAgenda.findById(id);
	}

	@Override
	public List<Agenda> listarRecordatoriosXgrupo(int id) {
		
		return aAgenda.listaRecordatoriosXGrupo(id);
	}

	
}
