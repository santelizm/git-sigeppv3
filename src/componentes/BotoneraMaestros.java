package componentes;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Messagebox;
import configuracion.BeanServicios;

public abstract class BotoneraMaestros extends Hbox {


	public BotoneraMaestros() {
		super();
		Button btnGuardar = new Button("Guardar");
		Button btnEliminar = new Button("Eliminar");
		Button btnLimpiar = new Button("Limpiar");
		Button btnSalir = new Button("Salir");
		this.appendChild(btnGuardar);
		this.appendChild(btnEliminar);
		this.appendChild(btnLimpiar);
		this.appendChild(btnSalir);
		this.setStyle("width:100% ; height: 2em; background: #BDBDBD");
		btnGuardar.setStyle("");
		btnGuardar.addEventListener(Events.ON_CLICK,
				new EventListener<Event>() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						guardar();
					}
				});
		btnEliminar.addEventListener(Events.ON_CLICK,
				new EventListener<Event>() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						eliminar();
					}
				});
		btnLimpiar.addEventListener(Events.ON_CLICK,
				new EventListener<Event>() {
					@Override
					public void onEvent(Event arg0) throws Exception {
						limpiar();
					}
				});
		btnSalir.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event arg0) throws Exception {
				salir();
			}
		});
	}

	public abstract void guardar();

	public abstract void limpiar();

	public abstract void salir();

	public abstract void eliminar();
}
