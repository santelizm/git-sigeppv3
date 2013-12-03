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

import servicio.SDepartamento;
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
public class CDepartamento extends CGenerico {
	
 SDepartamento servicioDepartamento = BeanServicios.getSDepartamento();
 SPrograma servicioPrograma = BeanServicios.getSPrograma();
	
	@Wire
	private Textbox txtNombreDepartamento;
	@Wire
	private Textbox txtDescripcionDepartamento;
	@Wire
	private Div botoneraEstandar;
	@Wire
	private Div catalogoDepartamento;
	@Wire
	private Listbox listaDepartamento;
	@Wire
	private Button btnBuscarDepartamento;
	private long id;
	
	
	public CDepartamento() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	void inicializar() {
		listaDepartamento.setVisible(false);
		id=0;
		
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				//Metodo de guardar
				
				
			    boolean estado=true; 
			    String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();
			    String nombre= txtNombreDepartamento.getValue();
			    String descripcion= txtDescripcionDepartamento.getValue();
			    Departamento nuevoDepartamento = new Departamento(id,nombre,descripcion,nombre,fecha,horaAuditoria,estado);
			    servicioDepartamento.guardar(nuevoDepartamento);
			    Messagebox.show("Departamento Guardado Satisfactoriamente");
			    limpiar();
			    id=0;
			  
			    
			}

			@Override
			public void limpiar() {
				// Metodo de limíar
				txtNombreDepartamento.setValue("");
				txtDescripcionDepartamento.setValue("");
				
				
			}


			@Override
			public void salir() {
				// 
				
			}

			@Override
			public void eliminar() {
//				
				Departamento nuevoDepartamento= servicioDepartamento.buscarDepartamento(id);
				nuevoDepartamento.setEstadoEliminacion(false);
				servicioDepartamento.guardar(nuevoDepartamento);
				 Messagebox.show("Departamento Eliminado Satisfactoriamente");
				limpiar();
				id=0;
				
				
			}
			
		};
		botoneraEstandar.appendChild(botonera);

	}
	
	@Listen("onClick = #btnBuscarDepartamento")
	 public void mostrarCatalogo(){
		
		listaDepartamento.setVisible(true);
		listadoDepartamento();
		
	 }
	public void listadoDepartamento() {
		
		List<Departamento> departamentos= servicioDepartamento.buscarDepartamentosActivos();
		listaDepartamento.setModel(new ListModelList<Departamento>(departamentos));

	}
	@Listen("onDoubleClick = #listaDepartamento")
	public void seleccion() {
		
					
		Departamento selected= listaDepartamento.getSelectedItem().getValue();
		id=selected.getId();
		txtNombreDepartamento.setValue(selected.getNombre());
		txtDescripcionDepartamento.setValue(selected.getDescripcion());
		
		
		listaDepartamento.setVisible(false);
		
	}
}
