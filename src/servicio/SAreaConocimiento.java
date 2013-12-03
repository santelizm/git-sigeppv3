package servicio;

import java.util.List;

import interfazdao.IAreaConocimientoDAO;

import modelo.AreaConocimiento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAreaConocimiento {

	@Autowired
	private IAreaConocimientoDAO areaConocimientoDAO;

	public void guardar(AreaConocimiento areaConocimiento){
		areaConocimientoDAO.save(areaConocimiento);
	}
	public AreaConocimiento buscarAreaConocimiento(long id){
		return areaConocimientoDAO.findOne(id);
	}
	
	public  List<AreaConocimiento> buscarAreasActivas(){
    	List<AreaConocimiento> areaConocimiento;
    	areaConocimiento =areaConocimientoDAO.buscarAreasActivas();
		return areaConocimiento;
		
	}
	
	 public AreaConocimiento buscarPorNombreAreaConocimiento(String nombre){
		 	AreaConocimiento areaConocimiento;
		 	areaConocimiento = areaConocimientoDAO.findByNombre(nombre);
			return areaConocimiento;
			
		}
	
	}

