package interfazdao;

import java.util.List;


import modelo.Estudiante;
import modelo.Programa;
import modelo.TipoPasantia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ITipoPasantiaDAO  extends JpaRepository<TipoPasantia, String> {
	
	@Query("select tp from TipoPasantia tp where tp.estadoEliminacion='true'")
	public List<TipoPasantia> buscarTipoPasantiasActivos();


	public TipoPasantia findByDescripcion(String nombre);

	@Query("select tp from TipoPasantia tp where tp.id=?1")
	public TipoPasantia findOne(long id);
	

//    @Query("update  Programa set estadoEliminacion='false' where programa_id=?1")
//	public void EliminarPrograma(long id);

    }
