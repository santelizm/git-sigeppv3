package componentes;


import java.util.List;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.hibernate.cfg.annotations.ListBinder;
import org.zkoss.zk.ui.event.*;
import org.zkoss.zul.*;

public abstract class Catalogo<Clase> extends Div{

	Listbox lsbCatalogo;

	
	public Catalogo(List<Clase> lista, String... campos) {
		super();
		crearLista(lista, campos);
	
	}


	public void crearLista(List<Clase> lista, String[] campos){
		Hbox hbxBusqueda = new Hbox();
		final Combobox cmbCampos = new Combobox();
		final Textbox txtBuscar = new Textbox();
		Button btnBuscar = new Button("BUSCAR");
		btnBuscar.addEventListener(Events.ON_CLICK, new EventListener<Event>() {

			@Override
			public void onEvent(Event arg0) throws Exception {
				List<Clase> listaNueva = buscar(cmbCampos.getSelectedItem().getLabel(), txtBuscar.getValue());	
				lsbCatalogo.setModel(new ListModelList<Clase>(listaNueva));
			}
		});
		lsbCatalogo = new Listbox();
		Listhead encabezado = new Listhead();
		for (int i=0; i<campos.length;i++){
			cmbCampos.appendChild(new Comboitem(campos[i]));
			encabezado.appendChild(new Listheader(campos[i]));
		}
		lsbCatalogo.appendChild(encabezado);
		lsbCatalogo.setModel(new ListModelList<Clase>(lista));
		lsbCatalogo.setItemRenderer(new ListitemRenderer<Clase>() {

			@Override
			public void render(Listitem fila, Clase objeto, int arg2)
					throws Exception {
						String[] registros = crearRegistros(objeto);
						for (int i=0; i<registros.length; i++){
							Listcell celda = new Listcell(registros[i]);
							celda.setParent(fila);
						}
			}
		});
		this.appendChild(hbxBusqueda);
		hbxBusqueda.appendChild(cmbCampos);
		hbxBusqueda.appendChild(txtBuscar);
		hbxBusqueda.appendChild(btnBuscar);
		this.appendChild(lsbCatalogo);
	}


	protected abstract List<Clase> buscar(String campo, String valor);

	protected abstract String[] crearRegistros(Clase objeto);

}