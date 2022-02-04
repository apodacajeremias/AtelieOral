package atelier.vista.ventana.buscador;

import atelier.controlador.buscador.BuscadorMedicamentoController;
import atelier.vista.componentes.genericos.BuscadorGenerico;


public class BuscadorMedicamento extends BuscadorGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6930903962610199066L;

	private BuscadorMedicamentoController controller;

	public void setUpController() {
		controller = new BuscadorMedicamentoController(this);
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorMedicamento() {
		setTitle("Búsqueda de pacientes");
		getTable().setToolTipText("Doble click para seleccionar");
	}

	public BuscadorMedicamentoController getController() {
		return controller;
	}

}
