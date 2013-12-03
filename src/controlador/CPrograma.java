package controlador;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;


import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import servicio.SDepartamento;
import servicio.SPrograma;
import modelo.Departamento;
import modelo.Estudiante;
import modelo.Programa;
import modelo.TipoPasantia;
import componentes.BotoneraMaestros;
import componentes.Catalogo;

import configuracion.BeanServicios;

@Controller
public class CPrograma extends CGenerico {
	
	SPrograma servicioPrograma =BeanServicios.getSPrograma();
	SDepartamento servicioDepartamento = BeanServicios.getSDepartamento();
	@Wire
	private Textbox txtNombrePrograma;
	@Wire
	private Textbox txtDescripcionPrograma;
	@Wire
	private Intbox txtTelefonoPrograma;
	@Wire
	private Div botoneraEstandar;
	@Wire
	private Div catalogoPrograma;
	@Wire
	private Listbox listaPrograma;
	@Wire
	private Radio rdbVariasPasantiasSi;
	@Wire
	private Radio rdbVariasPasantiasNo;
	@Wire
	private Textbox txtTutoriadosPrograma;
	@Wire
	private Radiogroup rdbPasantiasPrograma;
	@Wire
	private Listbox listaDepartamento;
	@Wire
	private Listbox listaDepartamentosAgregados;
	@Wire
	private Button pasar1;
	@Wire
	private Button pasar2;
	private long id; 

	
	public CPrograma() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	void inicializar() {
		listaPrograma.setVisible(false);
		listaDepartamentosDisponibles();
		id=0;
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				//Metodo de guardar
				
			   
			    boolean estado=true; 
			    boolean variasPasantias= false;
			    String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();
				String nombre = txtNombrePrograma.getValue();	   
				String descripcion = txtDescripcionPrograma.getValue();
			    String telefono = String.valueOf(txtTelefonoPrograma.getValue());
			    int cantidadTutoriados=Integer.parseInt(txtTutoriadosPrograma.getValue());
			    

			    if(rdbVariasPasantiasSi.isChecked())
			    	variasPasantias= true;
			   
			    				    
               Programa nuevoPrograma = new Programa(id,nombre,descripcion,telefono,variasPasantias,cantidadTutoriados,descripcion,fecha,horaAuditoria,estado);
               servicioPrograma.guardar(nuevoPrograma);
               limpiar();
               Messagebox.show("Programa Guardado Satisfactoriamente");
			   listaPrograma.setVisible(false);	
			   id=0;
			    
			
			    
			}

			@Override
			public void limpiar() {
				// Metodo de limíar
				txtNombrePrograma.setValue("");
				txtDescripcionPrograma.setValue("");
				txtTelefonoPrograma.setValue(null);
				txtTutoriadosPrograma.setValue("");
				rdbPasantiasPrograma.setSelectedItem(null);
				
				
			}

			@Override
			public void salir() {
				// 
				
			}

			@Override
			public void eliminar() {
				Programa nuevoPrograma= servicioPrograma.buscarPrograma(id);
				nuevoPrograma.setEstadoEliminacion(false);
				servicioPrograma.guardar(nuevoPrograma);
				limpiar();
				Messagebox.show("Programa Eliminado Satisfactoriamente");
				id=0;
			}

		};
		botoneraEstandar.appendChild(botonera);

	}
	@Listen("onClick = #btnBuscarPrograma")
	 public void mostrarCatalogo(){
		
		listaPrograma.setVisible(true);
		listadoPrograma();
		
	 }
	
	public void listadoPrograma() {
				
		List<Programa> programas = servicioPrograma.buscarProgramasActivos();
		listaPrograma.setModel(new ListModelList<Programa>(programas));

	}
	
	@Listen("onDoubleClick = #listaPrograma")
	public void seleccion() {
							
		Programa selected= listaPrograma.getSelectedItem().getValue();
		
		txtNombrePrograma.setValue(selected.getNombre());
		txtDescripcionPrograma.setValue(selected.getDescripcion());
		id=selected.getId();
		txtTelefonoPrograma.setValue(Integer.valueOf(selected.getTelefono()));
		txtTutoriadosPrograma.setValue(String.valueOf(selected.getCantidadSugTutorar()));
		if(selected.isPermiteVariasPasantias()==true)
			rdbVariasPasantiasSi.setValue(true);
		else
			rdbVariasPasantiasNo.setValue(true);
		
		listaPrograma.setVisible(false);
		
	}
	
	
	//--------------Cosas que tienen que ver con el doble grid (No es necesario para ustedes (por ahora jeje xD))----------
		//Funcion de boton de izquierda a derecha del doble grid
		@Listen("onClick = #pasar1")
		public void moverDerecha() {
			Listitem list1 = listaDepartamento.getSelectedItem();
			if (list1 == null)
				Messagebox.show("Seleccione un Item");
			else
				list1.setParent(listaDepartamentosAgregados);
		}
		//Funcion de boton de derecha a izquierda del doble grid
		@Listen("onClick = #pasar2")
		public void moverIzquierda() {
			Listitem list2 = listaDepartamentosAgregados.getSelectedItem();
			System.out.println(list2.getValue().toString());
			if (list2 == null)
				Messagebox.show("Seleccione un Item");
			else
				list2.setParent(listaDepartamento);
		}
		
		//Listas para llenar el doble grid (aun se esta trabajando en ello)
		public void listaDepartamentosDisponibles() {

			List<Departamento> departamentos = servicioDepartamento.buscarDepartamentosActivos();
			listaDepartamento.setModel(new ListModelList<Departamento>(departamentos));
		}

	
	
}
