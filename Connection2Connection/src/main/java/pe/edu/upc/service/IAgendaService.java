package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Agenda;

public interface IAgendaService {
	public boolean insertar(Agenda agenda);
	public void eliminar(int idagenda);
	List<Agenda> listar();
	Optional<Agenda> listarid(int id);
	List<Agenda> listarRecordatoriosXgrupo(int id);
}
