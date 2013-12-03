package interfazdao;

import java.util.List;


import modelo.AreaConocimiento;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAreaConocimientoDAO  extends JpaRepository<AreaConocimiento, Long> {

	@Query("select ac from AreaConocimiento ac where ac.estadoEliminacion='true'")
	public List<AreaConocimiento> buscarAreasActivas();
	
	public AreaConocimiento findByNombre(String nombre);
	
	
}
