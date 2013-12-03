package servicio;

import java.util.List;

import interfazdao.IAreaConocimientoDAO;
import interfazdao.ITutorEmpresarialDAO;
import modelo.AreaConocimiento;

import modelo.TutorAcademico;
import modelo.TutorEmpresarial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class STutorEmpresarial {

	@Autowired
	private ITutorEmpresarialDAO tutorEmpresarialDAO;

	public void guardar(TutorEmpresarial tutorEmpresarial){
		tutorEmpresarialDAO.save(tutorEmpresarial);
	}
	public List<TutorEmpresarial> buscarTutoresActivos()
	{
		List<TutorEmpresarial> tutores;
		tutores = tutorEmpresarialDAO.buscarTutoresActivos();
		return tutores;
	}
	
	public TutorEmpresarial consultar(String cedula){
		
		return tutorEmpresarialDAO.findOne(cedula);	
	}
	public TutorEmpresarial buscarTutorEmpresarial(String cedula){
		return tutorEmpresarialDAO.findOne(cedula);
	}
}
