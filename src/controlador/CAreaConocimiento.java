package controlador;

import java.awt.Button;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.AreaAcademica;
import modelo.AreaConocimiento;
import modelo.Departamento;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.SAreaConocimiento;
import componentes.BotoneraMaestros;
import componentes.BotoneraMaestros;
import configuracion.BeanServicios;

@Controller
public class CAreaConocimiento extends CGenerico {

	SAreaConocimiento servicioAreaConocimiento = BeanServicios.getSAreaConocimiento();
	public CAreaConocimiento() {
		// TODO Auto-generated constructor stub
	}

	@Wire
	private Div botoneraEstandar;
	@Wire
	private Textbox txtNombreAreaConocimiento;
	@Wire
	private Textbox txtDescripcionAreaConocimiento;
	@Wire
	private Button btnBuscarAreaConocimiento;
	@Wire
	private Listbox listaAreaConocimiento;

	long id=0;
	@Override
	void inicializar() {
		listaAreaConocimiento.setVisible(false);
		listadoAreaConocimiento();
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				// Metodo de guardar
				String nombre = txtNombreAreaConocimiento.getValue();	   
				String descripcion = txtDescripcionAreaConocimiento.getValue();
				
				boolean estado= true;
				
				String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();
				
				AreaConocimiento areaConocimiento = new AreaConocimiento(id,nombre,descripcion,nombre, fecha,horaAuditoria,true);
				servicioAreaConocimiento.guardar(areaConocimiento);
				Messagebox.show("Se Guardo Exitosamente"); 
				id=0;
        System.out.println("guardado");
        limpiar();
			}

			@Override
			public void limpiar() {
				// Metodo de limíar


				txtNombreAreaConocimiento.setValue("");
				txtDescripcionAreaConocimiento.setValue("");
			}

			@Override
			public void salir() {
				//

			}

			@Override
			public void eliminar() {
				

			AreaConocimiento areaConocimiento=servicioAreaConocimiento.buscarAreaConocimiento(id);
			areaConocimiento.setEstadoEliminacion(false);
			servicioAreaConocimiento.guardar(areaConocimiento);
			 Messagebox.show("Se Elimino Exitosamente");
			id=0;
			limpiar();
			}
		};
		botoneraEstandar.appendChild(botonera);

	}
	public void listadoAreaConocimiento() {
		
		List<AreaConocimiento> areaAcademica = servicioAreaConocimiento.buscarAreasActivas();
		listaAreaConocimiento.setModel(new ListModelList<AreaConocimiento>(areaAcademica));

		}


			 @Listen("onClick = #btnBuscarAreaConocimiento")
			 public void mostrarCatalogo(){
				 listadoAreaConocimiento();
				
				 listaAreaConocimiento.setVisible(true);
				
			 }
			
		
			 @Listen("onClick = #listaAreaConocimiento")
				public void seleccion(){
				
				 AreaConocimiento areaConocimiento= listaAreaConocimiento.getSelectedItem().getValue();

				id=areaConocimiento.getId();
				
				 txtNombreAreaConocimiento.setValue(areaConocimiento.getNombre());
				 txtDescripcionAreaConocimiento.setValue(areaConocimiento.getDescripcion());
			 	 listaAreaConocimiento.setVisible(false);
			 
			 }
}
