package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import modelo.AreaAcademica;
import modelo.Empresa;
import modelo.TutorAcademico;

import modelo.TutorEmpresarial;

import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.bind.annotation.Default;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
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

import servicio.SEmpresa;

import componentes.BotoneraMaestros;
import componentes.BotoneraMaestros;
import componentes.Catalogo;
import configuracion.BeanServicios;

@Controller
public class CEmpresa extends CGenerico {
	
	SEmpresa servicioEmpresa =BeanServicios.getSEmpresa();
	
	@Wire
	private Button btnBuscarEmpresa;
	@Wire
	private Textbox txtRifEmpresa;
	@Wire
	private Textbox txtNombreEmpresa;
	@Wire
	private Textbox txtDireccion1Empresa;
	@Wire
	private Textbox txtDireccion2Empresa;
	@Wire
	private Intbox txtTelefonolocalEmpresa;
	@Wire
	private Intbox txtTelefonoMovilEmpresa;
	@Wire
	private Textbox txtCorreo1Empresa;
	@Wire
	private Textbox txtCorreo2Empresa;
	@Wire
	private Textbox txtNombreContacto;
	@Wire
	private Div botoneraEstandar;
	@Wire
	private Div catalogoEmpresa;
	@Wire
	private Listbox listaEmpresa;
	
	
	public CEmpresa() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	void inicializar() {
		listadoEmpresas();
		listaEmpresa.setVisible(false);
		final Calendar calendario = Calendar.getInstance();
		BotoneraMaestros botonera = new BotoneraMaestros() {

				@Override
				public void guardar() {
					String rif = txtRifEmpresa.getValue();
					String nombre = txtNombreEmpresa.getValue();
					String direccion1 = txtDireccion1Empresa.getValue();
					String direccion2 = txtDireccion2Empresa.getValue();
					
					String telefono1 = String.valueOf(txtTelefonolocalEmpresa
							.getValue());
					String telefono2 = String.valueOf(txtTelefonolocalEmpresa
							.getValue());

					String correo1 = txtCorreo1Empresa.getValue();
					String correo2 = txtCorreo2Empresa.getValue();
					String nombreContacto = txtNombreContacto.getValue();
					boolean estado=true;
				

					String horaAuditoria = String.valueOf(calendario
							.get(Calendar.HOUR_OF_DAY))
							+ ":"
							+ String.valueOf(calendario.get(Calendar.MINUTE))
							+ ":"
							+ String.valueOf(calendario.get(Calendar.SECOND));
					java.util.Date fecha = new Date();
					
				
					Empresa nuevaEmpresa = new Empresa(rif, nombre, direccion1, 							
							direccion2,telefono1, telefono2, correo1,
							correo2, nombreContacto, fecha, horaAuditoria,
							nombre,true);
				
					servicioEmpresa.guardar(nuevaEmpresa);
					Messagebox.show("Se Guardo Exitosamente"); 
				
			
					limpiar();
					
				}

				@Listen("onClick = #botonlimpiar")
				public void limpiar() {

					txtRifEmpresa.setDisabled(false);
					txtRifEmpresa.setValue("");
					txtNombreEmpresa.setValue("");
					txtDireccion1Empresa.setValue("");
					txtDireccion2Empresa.setValue("");
					txtTelefonolocalEmpresa.setValue(null);
					txtTelefonoMovilEmpresa.setValue(null);
					txtCorreo1Empresa.setValue("");
					txtCorreo2Empresa.setValue("");
					txtNombreContacto.setValue("");
					
				}

			@Override
			public void salir() {
				//
			}
			
				@Override
				public void eliminar() {
					String rif = txtRifEmpresa.getValue();
					Empresa empresa = servicioEmpresa.buscarEmpresa(rif);
					empresa.setEstadoEliminacion(false);
					servicioEmpresa.guardar(empresa);
					limpiar();
					listadoEmpresas();
					 Messagebox.show("Se Elimino Exitosamente");

				}
			
			
		};
		botoneraEstandar.appendChild(botonera);

	}
	
	@Listen("onClick = #btnBuscarEmpresa")
	public void mostrarCatalogo() throws IOException {
		listadoEmpresas();
		listaEmpresa.setVisible(true);
	}
	
	public void listadoEmpresas() {
		
		List<Empresa> empresas = servicioEmpresa.buscarEmpresasActivas();
		listaEmpresa.setModel(new ListModelList<Empresa>(empresas));

	
	}
	
	@Listen("onDoubleClick = #listaEmpresa")
	public void seleccion(){
	
	
		Empresa empresa = listaEmpresa.getSelectedItem().getValue();
	
		
		txtRifEmpresa.setValue(empresa.getRif());
		txtRifEmpresa.setDisabled(true);
		txtNombreEmpresa.setValue(empresa.getNombre());
		txtDireccion1Empresa.setValue(empresa.getDireccion1());
		txtDireccion2Empresa.setValue(empresa.getDireccion2());
		txtTelefonolocalEmpresa.setValue(Integer.parseInt(empresa.getTelefono1()));
		txtTelefonoMovilEmpresa.setValue(Integer.parseInt(empresa.getTelefono2()));
		txtCorreo1Empresa.setValue(empresa.getCorreoElectronico1());
		txtCorreo2Empresa.setValue(empresa.getCorreoElectronico2());
		txtNombreContacto.setValue(empresa.getPersonaContacto());
		
		listaEmpresa.setVisible(false);
	}
}
