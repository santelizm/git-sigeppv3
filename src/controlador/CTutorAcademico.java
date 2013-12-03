package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.AreaAcademica;
import modelo.AreaConocimiento;
import modelo.AreaTutorAcademico;
import modelo.TutorAcademico;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Default;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.SAreaAcademica;
import servicio.SAreaConocimiento;
import servicio.SAreaTutorAcademico;
import servicio.STutorAcademico;

import componentes.BotoneraMaestros;

import componentes.Catalogo;
import configuracion.BeanServicios;

@Controller
public class CTutorAcademico extends CGenerico {

	STutorAcademico servicioTutorAcademico = BeanServicios.getSTutorAcademico();
	SAreaAcademica servicioAreaAcademica = BeanServicios.getSAreaAcademica();
	SAreaConocimiento servicioAreaConocimiento = BeanServicios
			.getSAreaConocimiento();
	SAreaTutorAcademico servicioAreaTutorAcademico = BeanServicios
			.getSAreaTutorAcademico();

	@Wire
	private Button btnBuscarTutorAcademico;
	@Wire
	private Radiogroup rdbTipoCedulaTutorAcademico;
	@Wire
	private Radio rdoVenezolano;
	@Wire
	private Radio rdoExtranjero;
	@Wire
	private Radiogroup rdbSexoTutorAcademico;
	@Wire
	private Textbox txtCedulaTutorAcademico;
	@Wire
	private Textbox txtNombre1TutorAcademico;
	@Wire
	private Textbox txtNombre2TutorAcademico;
	@Wire
	private Textbox txtApellido1TutorAcademico;
	@Wire
	private Textbox txtApellido2TutorAcademico;
	@Wire
	private Radio rdoSexoMTutorAcademico;
	@Wire
	private Radio rdoSexoFTutorAcademico;
	@Wire
	private Textbox txtResidenciaTutorAcademico;
	@Wire
	private Textbox txtCorreoTutorAcademico;
	@Wire
	private Intbox txtTelefono1TutorAcademico;
	@Wire
	private Intbox txtTelefono2TutorAcademico;
	@Wire
	private Combobox cmbAreaAcademica;
	@Wire
	private Listbox listaTutoresAcademicos;
	@Wire
	private Listbox listaAreasConocimiento;
	@Wire
	private Listbox listaAreasAgregadas;
	@Wire
	private Button pasar1;
	@Wire
	private Button pasar2;
	@Wire
	private Div botoneraEstandar;


	public CTutorAcademico() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void inicializar() {
		comboAreaAcademica();
		listadoTutoresAcademicos();
		listaAreasDisponibles();
		listaTutoresAcademicos.setVisible(false);
		final Calendar calendario = Calendar.getInstance();

		// Aca se encuentran los metodos CRUD (Guardar, Modificar y Eliminar)
		// para los Tutores
		// Academicos, ademas la opcion salir. Se ejecutan con el click de los
		// botonos de la interfaz.
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {

				String cedula = txtCedulaTutorAcademico.getValue();
				String nacionalidad = rdbSexoTutorAcademico.getSelectedItem().getLabel();
				
				String nombre1 = txtNombre1TutorAcademico.getValue();
				String nombre2 = txtNombre2TutorAcademico.getValue();
				String apellido1 = txtApellido1TutorAcademico.getValue();
				String apellido2 = txtApellido2TutorAcademico.getValue();
				String residencia = txtResidenciaTutorAcademico.getValue();
				String correo = txtCorreoTutorAcademico.getValue();
				String telefono1 = String.valueOf(txtTelefono1TutorAcademico
						.getValue());
				String telefono2 = String.valueOf(txtTelefono2TutorAcademico
						.getValue());
				String nombreAreaAcademica = cmbAreaAcademica.getValue();
				AreaAcademica areaAcademica = servicioAreaAcademica
						.buscarPorNombreAreaAcademica(nombreAreaAcademica);
				String sexo="";
				if (rdoSexoFTutorAcademico.isChecked())
					sexo ="Femenino";
				else
					sexo ="Masculino";

				String horaAuditoria = String.valueOf(calendario
						.get(Calendar.HOUR_OF_DAY))
						+ ":"
						+ String.valueOf(calendario.get(Calendar.MINUTE))
						+ ":"
						+ String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();

				// El usuario no se hara aun ya que necesitamos de la seguridad
				// funcional (pasarle cualquier string)
				TutorAcademico nuevoTutorAcademico = new TutorAcademico(cedula,
						areaAcademica, nacionalidad, nombre1, nombre2,
						apellido1, apellido2, sexo, residencia, correo,
						telefono1, telefono2, true, nombre1, fecha,
						horaAuditoria);

				servicioTutorAcademico.guardar(nuevoTutorAcademico);
				Messagebox.show("Se Guardo Exitosamente"); 
				limpiar();
				// Aca va el metodo para guardar las areas de conocimiento del
				// tutor academico
				// for (int i = 0; i < listaAreasAgregadas.getMaxlength(); i++)
				// {
				//
				// }
				System.out.print("guardado");
			}

			@Override
			public void limpiar() {
				
				
				
				rdoExtranjero.setDisabled(false);
				rdoVenezolano.setDisabled(false);
				rdbTipoCedulaTutorAcademico.setSelectedItem(null);
				txtCedulaTutorAcademico.setValue("");
				txtCedulaTutorAcademico.setDisabled(false);
				txtNombre1TutorAcademico.setValue("");
				txtNombre2TutorAcademico.setValue("");
				txtApellido1TutorAcademico.setValue("");
				txtApellido2TutorAcademico.setValue("");
				txtResidenciaTutorAcademico.setValue("");
				txtCorreoTutorAcademico.setValue("");
				rdbSexoTutorAcademico.setSelectedItem(null);
				txtTelefono1TutorAcademico.setValue(null);
				txtTelefono2TutorAcademico.setValue(null);
				cmbAreaAcademica.setValue("");
			}

			@Override
			public void salir() {

			}

			@Override
			public void eliminar() {
				String cedula = txtCedulaTutorAcademico.getValue();
				TutorAcademico tutorAcademico = servicioTutorAcademico
						.buscarTutorAcademico(cedula);
				tutorAcademico.setEstadoEliminacion(false);
				servicioTutorAcademico.guardar(tutorAcademico);
				Messagebox.show("Se Elimino Exitosamente");
				limpiar();
			}
		};
		botoneraEstandar.appendChild(botonera);
	}

	// Ocurre al darle click al boton del catalogo, hace un llamada
	// al metodo que llena el catalogo y lo muestra.
	@Listen("onClick = #btnBuscarTutorAcademico")
	public void mostrarCatalogo() throws IOException {
		listadoTutoresAcademicos();
		listaTutoresAcademicos.setVisible(true);
	}

	// Llena la lista que se mostrara en el catalogo
	public void listadoTutoresAcademicos() {

		List<TutorAcademico> tutorAcademico = servicioTutorAcademico
				.buscarTutoresActivos();
		listaTutoresAcademicos.setModel(new ListModelList<TutorAcademico>(
				tutorAcademico));
	}

	// Se ejecuta al darle doble click a algun item del catalogo para asi
	// seleccionar lo que se desee modificar O eliminar
	@Listen("onDoubleClick = #listaTutoresAcademicos")
	public void seleccion() {
 
		TutorAcademico tutorAcademico = listaTutoresAcademicos
				.getSelectedItem().getValue();	
		
		if (tutorAcademico.getNacionalidad().equals("E"))
			rdoExtranjero.setChecked(true);
		else
			rdoVenezolano.setChecked(true);
			
		txtCedulaTutorAcademico.setValue(tutorAcademico.getCedula());
		this.txtCedulaTutorAcademico.setDisabled(true);
		txtNombre1TutorAcademico.setValue(tutorAcademico.getNombre1());
		txtNombre2TutorAcademico.setValue(tutorAcademico.getNombre2());
		txtApellido1TutorAcademico.setValue(tutorAcademico.getApellido1());
		txtApellido2TutorAcademico.setValue(tutorAcademico.getApellido2());		
		txtResidenciaTutorAcademico.setValue(tutorAcademico.getResidencia());
		txtCorreoTutorAcademico.setValue(tutorAcademico.getCorreo());
		txtTelefono1TutorAcademico.setValue(Integer.parseInt(tutorAcademico.getTelefono1()));
		txtTelefono2TutorAcademico.setValue(Integer.parseInt(tutorAcademico.getTelefono2()));
		cmbAreaAcademica
				.setValue(tutorAcademico.getAreaAcademica().getNombre());

		if (tutorAcademico.getSexo().equals("Masculino"))
			rdoSexoMTutorAcademico.setChecked(true);
			
		else
			rdoSexoFTutorAcademico.setChecked(true);

		rdoExtranjero.setDisabled(true);
		rdoVenezolano.setDisabled(true);
		listaTutoresAcademicos.setVisible(false);
	}

	// Llena la lista que se mostrara en el combo de areas academicas del tutor
	public void comboAreaAcademica() {
		List<AreaAcademica> areaAcademica = servicioAreaAcademica
				.buscarAreasActivas();
		cmbAreaAcademica.setModel(new ListModelList<AreaAcademica>(
				areaAcademica));

	}

	// --------------Cosas que tienen que ver con el doble grid (No es necesario
	// para ustedes (por ahora jeje xD))----------
	// Funcion de boton de izquierda a derecha del doble grid
	@Listen("onClick = #pasar1")
	public void moverDerecha() {
		Listitem list1 = listaAreasConocimiento.getSelectedItem();
		if (list1 == null)
			Messagebox.show("Seleccione un Item");
		else
			list1.setParent(listaAreasAgregadas);
	}

	// Funcion de boton de derecha a izquierda del doble grid
	@Listen("onClick = #pasar2")
	public void moverIzquierda() {
		Listitem list2 = listaAreasAgregadas.getSelectedItem();
		System.out.println(list2.getValue().toString());
		if (list2 == null)
			Messagebox.show("Seleccione un Item");
		else
			list2.setParent(listaAreasConocimiento);
	}

	// Listas para llenar el doble grid (aun se esta trabajando en ello)
	public void listaAreasDisponibles() {

		List<AreaConocimiento> areas = servicioAreaConocimiento
				.buscarAreasActivas();
		listaAreasConocimiento.setModel(new ListModelList<AreaConocimiento>(
				areas));

		List<AreaTutorAcademico> areasTutor = servicioAreaTutorAcademico
				.buscarAreasTutoresActivos();
		listaAreasAgregadas.setModel(new ListModelList<AreaTutorAcademico>(
				areasTutor));
	}
}