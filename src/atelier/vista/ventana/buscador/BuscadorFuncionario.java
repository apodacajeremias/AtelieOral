package atelier.vista.ventana.buscador;

import atelier.controlador.buscador.BuscadorFuncionarioController;
import atelier.vista.componentes.genericos.BuscadorGenerico;

public class BuscadorFuncionario extends BuscadorGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6046263602074126045L;

	private BuscadorFuncionarioController controller;

	public void setUpController() {
		controller = new BuscadorFuncionarioController(this);
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorFuncionario() {
		setTitle("Buscador de Funcionarios");
		getTable().setToolTipText("Doble click para seleccionar");
	}

	public BuscadorFuncionarioController getController() {
		return controller;
	}

}
