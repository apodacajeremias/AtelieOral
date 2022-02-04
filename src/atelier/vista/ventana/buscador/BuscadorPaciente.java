package atelier.vista.ventana.buscador;

import atelier.controlador.buscador.BuscadorPacienteControlador;
import atelier.vista.componentes.genericos.BuscadorGenerico;

public class BuscadorPaciente extends BuscadorGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9047956247480719813L;

	private BuscadorPacienteControlador controlador;

	public void setUpControlador() {
		controlador = new BuscadorPacienteControlador(this);
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorPaciente() {
		setTitle("Búsqueda de pacientes");
		getTable().setToolTipText("Doble click para seleccionar");
	}

	public BuscadorPacienteControlador getControlador() {
		return controlador;
	}

}
