package servicio;

import java.util.List;


import interfazdao.IEstudianteDAO;

import modelo.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SEstudiante {

	@Autowired
	private IEstudianteDAO estudianteDAO;

	public void guardar(Estudiante estudiante){
		estudianteDAO.save(estudiante);
	}
	public List<Estudiante> buscarEstudiantesActivos()
	{
		List<Estudiante> estudiantes;
		estudiantes = estudianteDAO.buscarEstudiantesActivos();
		return estudiantes;
	}
	
	public Estudiante buscarEstudiante(String cedula){
		
		return estudianteDAO.findOne(cedula);
}
}