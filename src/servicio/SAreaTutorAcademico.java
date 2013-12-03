
package servicio;

import java.util.List;

import interfazdao.IAreaConocimientoDAO;
import interfazdao.IAreaTutorAcademicoDAO;
import interfazdao.ITutorAcademicoDAO;

import modelo.AreaConocimiento;
import modelo.AreaTutorAcademico;
import modelo.TutorAcademico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAreaTutorAcademico {

	@Autowired
	private IAreaTutorAcademicoDAO areaTutorAcademicoDAO;


	public void guardar(AreaTutorAcademico areaTutorAcademico){
		areaTutorAcademicoDAO.save(areaTutorAcademico);
	}

	public List<AreaTutorAcademico> buscarAreasTutoresActivos()
	{
		List<AreaTutorAcademico> areaTutorAcademico;
		areaTutorAcademico = areaTutorAcademicoDAO.buscarAreasTutoresActivos();
		return areaTutorAcademico;
	}
	
	
}