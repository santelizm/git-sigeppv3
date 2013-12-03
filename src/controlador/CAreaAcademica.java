package controlador;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.AreaAcademica;
import modelo.AreaConocimiento;
import modelo.Departamento;
import modelo.TutorAcademico;

import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.SAreaAcademica;
import servicio.SAreaConocimiento;
import servicio.SDepartamento;
import componentes.BotoneraMaestros;

import configuracion.BeanServicios;

@Controller
public class CAreaAcademica extends CGenerico {

	@Wire
	private Textbox txtNombreAreaAcademica;
	@Wire
	private Textbox txtDescripcionAreaAcademica;
	@Wire
	private Combobox cmbDepartamento;
	@Wire
	private Listbox listaAreaAcademica;
	@Wire
	private Button btnBuscarArea;

	@Wire
	private Div botoneraEstandar;
	
	long id =0;

	SAreaAcademica servicioAreaAcademica = BeanServicios.getSAreaAcademica();
	SDepartamento servicioDepartamento = BeanServicios.getSDepartamento();

	public CAreaAcademica() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void inicializar() {
		comboDepartamento();
		listadoAreaAcademica();
		listaAreaAcademica.setVisible(false);
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				// Metodo de guardar
				
				String nombre = txtNombreAreaAcademica.getValue();
				String descripcion = txtDescripcionAreaAcademica.getValue();
				
				boolean estado = true;
				String nombred = cmbDepartamento.getValue();
				Departamento departamento = servicioDepartamento
						.buscarPorNombreDepartamento(nombred);
				
				String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();
				AreaAcademica nuevaAreaAcademica = new AreaAcademica(id,
						departamento, nombre, descripcion, nombre, fecha,
						horaAuditoria, estado);
				servicioAreaAcademica.guardar(nuevaAreaAcademica);
				Messagebox.show("Se Guardo Exitosamente"); 
				id=0;
				System.out.print("Guardado");
				limpiar();
			}

			@Override
			public void limpiar() {
		
				txtNombreAreaAcademica.setValue("");
				txtDescripcionAreaAcademica.setValue("");
				cmbDepartamento.setValue("");
			}

			@Override
			public void salir() {
				//

			}

			@Override
			public void eliminar() {
				//
				
				AreaAcademica areaAcademica=servicioAreaAcademica.buscarAreaAcademica(id);
				areaAcademica.setEstadoEliminacion(false);
				servicioAreaAcademica.guardar(areaAcademica);
				 Messagebox.show("Se Elimino Exitosamente");
			    id=0;
				System.out.print("Eliminado");
				limpiar();
			}
		};
		botoneraEstandar.appendChild(botonera);

	}

	public void comboDepartamento() {
		List<Departamento> departamento = servicioDepartamento
				.buscarDepartamentosActivos();
		cmbDepartamento.setModel(new ListModelList<Departamento>(departamento));

	}

	public void listadoAreaAcademica() {

		List<AreaAcademica> areaAcademica = servicioAreaAcademica
				.buscarAreasActivas();
		listaAreaAcademica.setModel(new ListModelList<AreaAcademica>(
				areaAcademica));

	}

	@Listen("onClick = #btnBuscarArea")
	public void mostrarCatalogo() {
		listadoAreaAcademica();

		listaAreaAcademica.setVisible(true);


	}

	@Listen("onDoubleClick = #listaAreaAcademica")
	public void seleccionarListado() {
		// get the selected object
		AreaAcademica areaAcademica = listaAreaAcademica.getSelectedItem()
				.getValue();

		id=areaAcademica.getId();
		txtNombreAreaAcademica.setValue(areaAcademica.getNombre());
		txtDescripcionAreaAcademica.setValue(areaAcademica.getDescripcion());
		cmbDepartamento.setValue(areaAcademica.getDepartamento().getNombre());

		listaAreaAcademica.setVisible(false);

	}
}
