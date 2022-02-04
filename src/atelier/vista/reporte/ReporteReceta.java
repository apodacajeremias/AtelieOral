package atelier.vista.reporte;

import atelier.controlador.reportes.ReporteRecetaControlador;
import atelier.vista.componentes.genericos.ReporteGenerico;

public class ReporteReceta extends ReporteGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8168617719511694061L;
	private ReporteRecetaControlador controlador;

	/**
	 * Create the dialog.
	 */

	public void setUpController() {
		controlador = new ReporteRecetaControlador(this);
	}

	public ReporteReceta() {
		getRb6().setSelected(true);
		getRb6().setText("General");
		getRb5().setText("Individual");
		getRb4().setText("Anulados");
		getRb3().setText("Vigentes");
		getRb1().setText("Recetas");

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReporteRecetaControlador getControlador() {
		return controlador;
	}

}
