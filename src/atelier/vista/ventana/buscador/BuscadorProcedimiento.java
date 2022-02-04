package atelier.vista.ventana.buscador;

import javax.swing.JPanel;

import atelier.controlador.buscador.BuscadorProcedimientoController;
import atelier.vista.componentes.genericos.BuscadorGenerico;

public class BuscadorProcedimiento extends BuscadorGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2951149324603781804L;
	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	private BuscadorProcedimientoController controller;


	public void setUpController() {
		controller = new BuscadorProcedimientoController(this);

	}

	/**
	 * Create the dialog.
	 */
	public BuscadorProcedimiento() {
		setTitle("Búsqueda de procedimientos");
		getTable().setToolTipText("Doble click para seleccionar");

	}

	public BuscadorProcedimientoController getController() {
		return controller;
	}

}
