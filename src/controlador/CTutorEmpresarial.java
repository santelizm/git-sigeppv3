
package controlador;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.Empresa;

import modelo.TutorAcademico;
import modelo.TutorEmpresarial;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Default;
import org.zkoss.zk.ui.Component;
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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.STutorEmpresarial;
import servicio.SEmpresa;
import componentes.BotoneraMaestros;
import componentes.BotoneraMaestros;
import componentes.Catalogo;
import configuracion.BeanServicios;

@Controller
public class CTutorEmpresarial extends CGenerico {

	STutorEmpresarial servicioTutorEmpresarial = BeanServicios
			.getSTutorEmpresarial();
	SEmpresa servicioEmpresa = BeanServicios.getSEmpresa();

	@Wire
	private Button btnBuscarTutorEmpresarial;
	@Wire
	private Radiogroup rdbTipoCedulaTutorEmpresarial;
	@Wire
	private Textbox txtCedulaTutorEmpresarial;
	@Wire
	private Textbox txtNombre1TutorEmpresarial;
	@Wire
	private Textbox txtNombre2TutorEmpresarial;
	@Wire
	private Textbox txtApellido1TutorEmpresarial;
	@Wire
	private Textbox txtApellido2TutorEmpresarial;
	@Wire
	private Radiogroup rdbSexoTutorEmpresarial;
	@Wire
	private Textbox txtCorreoTutorEmpresarial;
	@Wire
	private Intbox txtTelefono1TutorEmpresarial;
	@Wire
	private Intbox txtTelefono2TutorEmpresarial;
	@Wire
	private Combobox cmbEmpresa;
	@Wire
	private Div botoneraEstandar;
	@Wire
	private Div catalogoTutorEmpresarial;
	@Wire
	private Listbox listaTutorEmpresarial;
	@Wire
	private Radio rdoFemenino;
	@Wire
	private Radio rdoMasculino;
	@Wire
	private Radio rdoVenezolano;
	@Wire
	private Radio rdoExtranjero;


	public CTutorEmpresarial() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void inicializar() {
		comboEmpresa();
		listadoTutores();
		listaTutorEmpresarial.setVisible(false);
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				
				String cedula = txtCedulaTutorEmpresarial.getValue();
				String nacionalidad =rdbTipoCedulaTutorEmpresarial.getSelectedItem().getLabel();
				String nombre1 = txtNombre1TutorEmpresarial.getValue();
				String nombre2 = txtNombre2TutorEmpresarial.getValue();
				String apellido1 = txtApellido1TutorEmpresarial.getValue();
				String apellido2 = txtApellido2TutorEmpresarial.getValue();

				String correo = txtCorreoTutorEmpresarial.getValue();
				String telefono1 = String.valueOf(txtTelefono1TutorEmpresarial
						.getValue());
				String telefono2 = String.valueOf(txtTelefono2TutorEmpresarial
						.getValue());

				// Hacer lo de la imagen
				// byte[] fotoe=imagen.getContent().getByteData();
				String nombreEmpresa = cmbEmpresa.getValue();
				Empresa empresa = servicioEmpresa
						.buscarPorNombreEmpresa(nombreEmpresa);
				boolean estado = true;
				String sexo="";
				if (rdoFemenino.isChecked())
					sexo = "Femenino";
				else
					sexo = "Masculino";

				String horaAuditoria = String.valueOf(calendario
						.get(Calendar.HOUR_OF_DAY))
						+ ":"
						+ String.valueOf(calendario.get(Calendar.MINUTE))
						+ ":"
						+ String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fecha = new Date();

				TutorEmpresarial nuevoTutor = new TutorEmpresarial(cedula,
						empresa, nacionalidad, nombre1, nombre2, apellido1,
						apellido2, sexo, correo, telefono1, telefono2, nombre1,
						fecha, horaAuditoria, true);

				servicioTutorEmpresarial.guardar(nuevoTutor);
				limpiar();
				Messagebox.show("Se Guardo Exitosamente"); 
		
			}

			@Override
			public void limpiar() {

				
				rdoVenezolano.setDisabled(false);
				rdoExtranjero.setDisabled(false);
				rdbTipoCedulaTutorEmpresarial.setSelectedItem(null);
				txtCedulaTutorEmpresarial.setValue("");
				rdoVenezolano.setValue(null);
				rdoExtranjero.setValue(null);
				txtNombre1TutorEmpresarial.setValue("");
				txtNombre2TutorEmpresarial.setValue("");
				txtApellido1TutorEmpresarial.setValue("");
				txtApellido2TutorEmpresarial.setValue("");
				rdbSexoTutorEmpresarial.setSelectedItem(null);
				txtCorreoTutorEmpresarial.setValue("");
				txtTelefono1TutorEmpresarial.setValue(null);
				txtTelefono2TutorEmpresarial.setValue(null);
				cmbEmpresa.setValue(null);

			}

			@Override
			public void salir() {
				//

			}

			@Override
			public void eliminar() {
				String cedula = txtCedulaTutorEmpresarial.getValue();
				TutorEmpresarial tutorEmpresarial = servicioTutorEmpresarial
						.buscarTutorEmpresarial(cedula);
				tutorEmpresarial.setEstadoEliminacion(false);
				servicioTutorEmpresarial.guardar(tutorEmpresarial);
				Messagebox.show("Se Elimino Exitosamente");
				limpiar();
				Messagebox.show("Tutor Eliminado");

			}
		};
		botoneraEstandar.appendChild(botonera);

	}

	@Listen("onClick = #btnBuscarTutorEmpresarial")
	public void mostrarCatalogo() throws IOException {
		listadoTutores();
		listaTutorEmpresarial.setVisible(true);
	}

	public void listadoTutores() {

		List<TutorEmpresarial> tutores = servicioTutorEmpresarial
				.buscarTutoresActivos();
		listaTutorEmpresarial.setModel(new ListModelList<TutorEmpresarial>(
				tutores));

	}

	public void comboEmpresa() {
		List<Empresa> empresas = servicioEmpresa.buscarEmpresasActivas();
		cmbEmpresa.setModel(new ListModelList<Empresa>(empresas));
	}

	@Listen("onDoubleClick = #listaTutorEmpresarial")
	public void seleccion() {

		TutorEmpresarial tutorEmpresarial = listaTutorEmpresarial
				.getSelectedItem().getValue();

		if (tutorEmpresarial.getNacionalidad().equals("V"))
			rdoVenezolano.setChecked(true);
		else
			rdoExtranjero.setChecked(true);

		txtCedulaTutorEmpresarial.setValue(tutorEmpresarial.getCedula());
		txtNombre1TutorEmpresarial.setValue(tutorEmpresarial.getNombre1());
		txtNombre2TutorEmpresarial.setValue(tutorEmpresarial.getNombre2());
		txtApellido1TutorEmpresarial.setValue(tutorEmpresarial.getApellido1());
		txtApellido2TutorEmpresarial.setValue(tutorEmpresarial.getApellido2());

		if (tutorEmpresarial.getSexo().equals("Femenino"))
			rdoFemenino.setChecked(true);
		else
			this.rdoMasculino.setChecked(true);

		txtCorreoTutorEmpresarial.setValue(tutorEmpresarial.getCorreo());
		txtTelefono1TutorEmpresarial.setValue(Integer.parseInt(tutorEmpresarial
				.getTelefono1()));
		txtTelefono2TutorEmpresarial.setValue(Integer.parseInt(tutorEmpresarial
				.getTelefono2()));
		cmbEmpresa.setValue(tutorEmpresarial.getEmpresa().getNombre());
		txtCedulaTutorEmpresarial.setDisabled(true);
		rdoVenezolano.setDisabled(true);
		rdoExtranjero.setDisabled(true);
		listaTutorEmpresarial.setVisible(false);
	}
}
