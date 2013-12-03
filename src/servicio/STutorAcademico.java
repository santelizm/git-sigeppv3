


package servicio;

import java.util.List;

import interfazdao.IAreaConocimientoDAO;
import interfazdao.ITutorAcademicoDAO;

import modelo.AreaConocimiento;
import modelo.TutorAcademico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class STutorAcademico {

	@Autowired
	private ITutorAcademicoDAO tutorAcademicoDAO;


	public void guardar(TutorAcademico tutorAcademico){
		tutorAcademicoDAO.save(tutorAcademico);
	}
	public TutorAcademico buscarTutorAcademico(String cedula){
		return tutorAcademicoDAO.findOne(cedula);
	}
	public List<TutorAcademico> buscarTutoresActivos()
	{
		List<TutorAcademico> tutorAcademico;
		tutorAcademico = tutorAcademicoDAO.buscarTutoresActivos();
		return tutorAcademico;
	}
	
	
}