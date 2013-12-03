package interfazdao;

import java.util.List;


import modelo.Estudiante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEstudianteDAO  extends JpaRepository<Estudiante, String> {

	@Query("Select e from Estudiante e where e.estadoEliminacion='true'")
	public List<Estudiante> buscarEstudiantesActivos();
}
