package servicio;

import java.util.List;


import interfazdao.IProgramaDAO;
import interfazdao.ITipoPasantiaDAO;

import modelo.Programa;
import modelo.TipoPasantia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class STipoPasantia {
	
	@Autowired
	private ITipoPasantiaDAO tipoDAO;

	public void guardar(TipoPasantia tipoPasantia){
		tipoDAO.save(tipoPasantia);
		
	}
	public List<TipoPasantia> buscarTipoPasantiasActivos(){
		List<TipoPasantia> tipoPasantias;
		tipoPasantias = tipoDAO.buscarTipoPasantiasActivos();
		return tipoPasantias;
	}
	public TipoPasantia buscarPorDescripcionTipoPasantia(String descripcion){
		TipoPasantia tipo;
		tipo = tipoDAO.findByDescripcion(descripcion);
		return tipo;
		
	}
	public TipoPasantia buscarTipoPasantia(long id){
		return tipoDAO.findOne(id);
				
	}
//    public void ProgramaFalse(long id){
//		 programaDAO.EliminarPrograma(id);
//			
//	}
}
