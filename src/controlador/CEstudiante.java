package controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;


import modelo.Estudiante;
import modelo.Programa;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Default;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
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
import servicio.SEstudiante;
import servicio.SPrograma;
import componentes.BotoneraMaestros;

import componentes.Catalogo;
import configuracion.BeanServicios;


@Controller
public class CEstudiante extends CGenerico {
	
	SEstudiante servicioEstudiante = BeanServicios.getSEstudiante();
	SPrograma servicioPrograma =BeanServicios.getSPrograma();
	
	@Wire
	private Radiogroup rdgTipoCedulaEstudiante;
	@Wire
	private Radio rdoExtranjero;
	@Wire
	private Radio rdoVenezolano;
	@Wire
	private Textbox txtCedulaEstudiante;
	@Wire
	private Textbox txtNombre1Estudiante;
	@Wire
	private Textbox txtNombre2Estudiante;
	@Wire
	private Textbox txtApellido1Estudiante;
	@Wire
	private Textbox txtApellido2Estudiante;
	@Wire
	private Radiogroup rdgSexoEstudiante;
	@Wire
	private Radio rdoSexoFemeninoEstudiante;
	@Wire
	private Radio rdoAprobado;
	@Wire
	private Radio rdoNoAprobado;
	@Wire
	private Radio rdoSexoMasculinoEstudiante;
	@Wire
	private Combobox cmbEstadoCivilEstudiante;
	@Wire
	private Datebox dtbFechaNacimientoEstudiante;
	@Wire
	private Textbox txtResidenciaEstudiante;
	@Wire
	private Textbox txtCorreo;
	@Wire
	private Intbox txtTelefonoMovilEstudiante;
	@Wire
	private Intbox txtTelefonoLocalEstudiante;
//	@Wire
//	private Image imagen;
//	@Wire
//	private Fileupload fotoEstudiante;
//	@Wire
//	private Media media;
	@Wire
	private Combobox cmbPrograma;
	@Wire
	private Textbox txtPromedio;
	@Wire
	private Combobox cmbSemestre;
	@Wire
	private Intbox txtCreditosAprobados;
	@Wire
	private Radiogroup rdgServicioComunitario;
	@Wire
	private Div botoneraEstandar;
	@Wire
	private Div catalogoEstudiante;
	@Wire
	private Listbox listaEstudiante;
	
	
	
	public CEstudiante() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	void inicializar() {
		comboPrograma();
		listaEstudiante.setVisible(false);
		rdoVenezolano.setChecked(true);
		final Calendar calendario = Calendar.getInstance();
//		listadoEstudiantes();
		
		BotoneraMaestros botonera = new BotoneraMaestros() {

			@Override
			public void guardar() {
				
				String cedula = txtCedulaEstudiante.getValue();
				String nacionalidad = rdgTipoCedulaEstudiante.getSelectedItem().getLabel();
				String nombre1 = txtNombre1Estudiante.getValue();
				String nombre2 = txtNombre2Estudiante.getValue();
				String apellido1 = txtApellido1Estudiante.getValue();
				String apellido2 = txtApellido2Estudiante.getValue();
				//String sexoe = rdgSexoEstudiante.getSelectedItem().getLabel();
				String estadoCivil= cmbEstadoCivilEstudiante.getValue();
				Date fechaNacimiento = dtbFechaNacimientoEstudiante.getValue();
				String residencia = txtResidenciaEstudiante.getValue();
				String correo = txtCorreo.getValue();
				String telefono1 = String.valueOf(txtTelefonoMovilEstudiante.getValue());
				String telefono2 = String.valueOf(txtTelefonoLocalEstudiante.getValue());
				//Hacer lo de la imagen
				//byte[] fotoe=imagen.getContent().getByteData();
				String nombrePrograma = cmbPrograma.getValue();
				Programa programa = servicioPrograma.buscarPorNombrePrograma(nombrePrograma);
				float promedio = Float.parseFloat(txtPromedio.getValue());
				int semestre = Integer.parseInt(cmbSemestre.getValue());
				int creditos = txtCreditosAprobados.getValue();
				boolean estadoEliminacion=true;
				boolean servicioComunitario=false;
				String sexo = "";
				
				String horaAuditoria = String.valueOf(calendario.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(calendario.get(Calendar.MINUTE))+":"+String.valueOf(calendario.get(Calendar.SECOND));
				java.util.Date fechaAuditoria = new Date();
						
				
				if(rdoSexoFemeninoEstudiante.isChecked())
					sexo= "Femenino";
				
				if(rdoSexoMasculinoEstudiante.isChecked())
				    sexo= "Masculino";
				
				if(rdgServicioComunitario.getSelectedItem().getValue()=="Aprobado")
				    servicioComunitario=true;
						
				
		
            Estudiante nuevoEstudiante = new Estudiante(cedula,programa,nacionalidad,nombre1,nombre2,apellido1,apellido2,sexo,estadoCivil,fechaNacimiento,residencia,correo,telefono1,telefono2,promedio,semestre,creditos,servicioComunitario,nombre1,nombre2,fechaAuditoria,horaAuditoria,estadoEliminacion);			
            servicioEstudiante.guardar(nuevoEstudiante);
            Messagebox.show("Estudiante Guardado Satisfactoriamente");
		    limpiar();
			}


			@Override
			public void limpiar() {
				// Metodo de limíar
				
				txtCedulaEstudiante.setValue("");
				rdgTipoCedulaEstudiante.setSelectedItem(null);
				txtNombre1Estudiante.setValue("");
				txtNombre2Estudiante.setValue("");
				txtApellido1Estudiante.setValue("");
				txtApellido2Estudiante.setValue("");
				cmbEstadoCivilEstudiante.setValue("");
				dtbFechaNacimientoEstudiante.setValue(null);
				txtResidenciaEstudiante.setValue("");
				txtCorreo.setValue("");
				txtTelefonoMovilEstudiante.setValue(null);
				txtTelefonoLocalEstudiante.setValue(null);
			    cmbPrograma.setValue("");
				txtPromedio.setValue("");
				cmbSemestre.setValue("");
				txtCreditosAprobados.setValue(null);
			    rdgServicioComunitario.setSelectedItem(null);
			    rdgSexoEstudiante.setSelectedItem(null);

			}

			@Override
			public void salir() {
				//

			}

			@Override
			public void eliminar() {

				Estudiante nuevoEstudiante= servicioEstudiante.buscarEstudiante(txtCedulaEstudiante.getValue());
				nuevoEstudiante.setEstadoEliminacion(false);
				servicioEstudiante.guardar(nuevoEstudiante);
				  Messagebox.show("Estudiante Eliminado Satisfactoriamente");
				limpiar();

			}
		};
		botoneraEstandar.appendChild(botonera);

	}
	@Listen("onClick = #btnBuscar")
	 public void mostrarCatalogo() throws IOException{
      	listadoEstudiantes();
		listaEstudiante.setVisible(true);
	 }
	
	
	public void comboPrograma() {
		List<Programa> programas = servicioPrograma.buscarProgramasActivos();
		cmbPrograma.setModel(new ListModelList<Programa>(programas));

	}
	public void listadoEstudiantes() {
				
		List<Estudiante> estudiantes = servicioEstudiante.buscarEstudiantesActivos();
		listaEstudiante.setModel(new ListModelList<Estudiante>(estudiantes));

	}
	
//	@Listen("onUpload = #fotoEstudiante")
//	public void processMedia(UploadEvent event) {
//			media=event.getMedia();
//			imagen.setContent((org.zkoss.image.Image) media);
//			
//	}
	
	@Listen("onDoubleClick = #listaEstudiante")
	public void seleccion() throws IOException{
		
//		BufferedImage imag=ImageIO.read(new ByteArrayInputStream(estudiantes.getFoto()));
//		imagen.setContent(imag);
		
		Estudiante estudiante = listaEstudiante.getSelectedItem().getValue();
		txtCedulaEstudiante.setValue(estudiante.getCedula());
		txtNombre1Estudiante.setValue(estudiante.getNombre1());
		txtNombre2Estudiante.setValue(estudiante.getNombre2());
		txtApellido1Estudiante.setValue(estudiante.getApellido1());
		txtApellido2Estudiante.setValue(estudiante.getApellido2());
		cmbPrograma.setValue(estudiante.getPrograma().getNombre());
		cmbEstadoCivilEstudiante.setValue(estudiante.getEdoCivil());
		dtbFechaNacimientoEstudiante.setValue(estudiante.getFechaNacimiento());
		txtResidenciaEstudiante.setValue(estudiante.getResidencia());
		txtCorreo.setValue(estudiante.getCorreo());
		txtTelefonoMovilEstudiante.setValue(Integer.valueOf(estudiante.getTelefono1()));
		txtTelefonoLocalEstudiante.setValue(Integer.valueOf(estudiante.getTelefono2()));
	    txtPromedio.setValue(String.valueOf(estudiante.getPromedio()));
		cmbSemestre.setValue(String.valueOf(estudiante.getSemestre()));
		txtCreditosAprobados.setValue(estudiante.getCreditos());
		
		if(estudiante.getNacionalidad().equals("V"))
		   rdoVenezolano.setChecked(true);
		else
			 rdoExtranjero.setChecked(true);
		
		
		if(estudiante.getSexo().equals("Femenino"))
			   rdoSexoFemeninoEstudiante.setChecked(true);
			else
				  rdoSexoMasculinoEstudiante.setChecked(true);

		
		if(estudiante.getServicioComunitario()==true)
			   rdoAprobado.setSelected(true);
			else
			   rdoNoAprobado.setSelected(true);
		txtCedulaEstudiante.setDisabled(true);
		rdoVenezolano.setDisabled(true);
			 rdoExtranjero.setDisabled(true);
		listaEstudiante.setVisible(false);
//		

			
		
	}
	
}


