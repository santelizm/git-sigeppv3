package interfazdao;

import java.util.List;


import modelo.Estudiante;
import modelo.Programa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProgramaDAO  extends JpaRepository<Programa, String> {
	
	@Query("select p from Programa p where p.estadoEliminacion='true'")
	public List<Programa> buscarProgramasActivos();


	public Programa findByNombre(String nombre);

	
	@Query("select p from Programa p where p.id=?1")
	public Programa findOne(long id);
	

//    @Query("update  Programa set estadoEliminacion='false' where programa_id=?1")
//	public void EliminarPrograma(long id);

    }
