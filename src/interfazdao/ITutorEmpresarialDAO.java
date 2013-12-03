package interfazdao;

import java.util.List;

import modelo.AreaConocimiento;
import modelo.TutorEmpresarial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITutorEmpresarialDAO  extends JpaRepository<TutorEmpresarial, String> {

	@Query("Select t from TutorEmpresarial t where t.estadoEliminacion='true'")
	public List<TutorEmpresarial> buscarTutoresActivos();
}



