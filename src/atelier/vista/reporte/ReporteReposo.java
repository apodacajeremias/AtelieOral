package atelier.vista.reporte;

import atelier.controlador.reportes.ReporteReposoControlador;
import atelier.vista.componentes.genericos.ReporteGenerico;

public class ReporteReposo extends ReporteGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8168617719511694061L;
	private ReporteReposoControlador controlador;

	/**
	 * Create the dialog.
	 */

	public void setUpController() {
		controlador = new ReporteReposoControlador(this);
	}

	public ReporteReposo() {
		getRb6().setSelected(true);
		getRb6().setText("General");
		getRb5().setText("Individual");
		getRb4().setText("Anulados");
		getRb3().setText("Vigentes");
		getRb1().setText("Reposos");

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReporteReposoControlador getControlador() {
		return controlador;
	}

}
