package controlador;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;


import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import servicio.SPrograma;
import servicio.STipoPasantia;
import modelo.Departamento;
import modelo.Estudiante;
import modelo.Programa;
import modelo.TipoPasantia;
import componentes.BotoneraMaestros;
import componentes.Catalogo;

import configuracion.BeanServicios;

@Controller
public class CTipoPasantia extends CGenerico {
	
 STipoPasantia servicioTipoPasantia =BeanServicios.getSTipoPasantia();
 SPrograma servicioPrograma = BeanServicios.getSPrograma();
	
	@Wire
	private Textbox txtDescripcionTipoPasantia;
	@Wire
	private Textbox txtObservacionTipoPasantia;
	@Wire
	private Div botoneraEstandar;
	@Wire
	private Div catalogoTipoPasantia;
	@Wire
	private Listbox listaTipoPasantia;
	@Wire
	private Button btnBuscarTipoPasantia;
	private long id;
	
	
	public CTipoPasantia() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	void inicializar() {
		listaTipoPasantia.setVisible(false);
		id=0;
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				//Metodo de guardar
				
				
			    boolean estado=true; 
			    String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();
			    String descripcion = txtDescripcionTipoPasantia.getValue();
			    String observacion=txtObservacionTipoPasantia.getValue();
			    
			   TipoPasantia nuevoTipoPasantia= new TipoPasantia(id,descripcion,observacion,descripcion,fecha,horaAuditoria,estado);
			   servicioTipoPasantia.guardar(nuevoTipoPasantia);
			   Messagebox.show("Tipo de Pasantia Guardado Satisfactoriamente");
			    limpiar();
			    id=0;
			  
			    
			}

			@Override
			public void limpiar() {
				// Metodo de limíar
				txtDescripcionTipoPasantia.setValue("");
				txtObservacionTipoPasantia.setValue("");
				
			}


			@Override
			public void salir() {
				// 
				
			}

			@Override
			public void eliminar() {
//			
				TipoPasantia nuevoTipoPasantia= servicioTipoPasantia.buscarTipoPasantia(id);
				nuevoTipoPasantia.setEstadoEliminacion(false);
				servicioTipoPasantia.guardar(nuevoTipoPasantia);
				limpiar();
				Messagebox.show("Tipo de Pasantia Eliminado Satisfactoriamente");
				id=0;
			 				
			}
			
		};
		botoneraEstandar.appendChild(botonera);

	}
	@Listen("onClick = #btnBuscarTipoPasantia")
	 public void mostrarCatalogo(){
		
		listaTipoPasantia.setVisible(true);
		listadoTipoPasantia();
		
	 }
	public void listadoTipoPasantia() {
		
		List<TipoPasantia> tipoPasantias= servicioTipoPasantia.buscarTipoPasantiasActivos();
		listaTipoPasantia.setModel(new ListModelList<TipoPasantia>(tipoPasantias));

	}
	@Listen("onDoubleClick = #listaTipoPasantia")
	public void seleccion() {
		
					
		TipoPasantia selected= listaTipoPasantia.getSelectedItem().getValue();
	    id=selected.getId();
		txtDescripcionTipoPasantia.setValue(selected.getDescripcion());
		txtObservacionTipoPasantia.setValue(selected.getObservacion());
		
		
		listaTipoPasantia.setVisible(false);
		
	}
}
