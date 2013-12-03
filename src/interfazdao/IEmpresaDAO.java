package interfazdao;

import java.util.List;

import modelo.TutorEmpresarial;
import modelo.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmpresaDAO  extends JpaRepository<Empresa, String> {

	@Query("Select e from Empresa e where e.estadoEliminacion='true'")
	public List<Empresa> buscarEmpresasActivas();

	public Empresa findByNombre(String nombre);

}

