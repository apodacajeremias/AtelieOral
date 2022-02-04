package atelier.vista.reporte;

import atelier.controlador.reportes.ReportePagoControlador;
import atelier.vista.componentes.genericos.ReporteGenerico;

public class ReportePago extends ReporteGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4726468344180138064L;
	
	private ReportePagoControlador controlador;

	public void setUpControlador() {
		controlador = new ReportePagoControlador(this);

	}

	/**
	 * Create the dialog.
	 */
	public ReportePago() {
		getRb6().setSelected(true);
		getRb6().setText("General");
		getRb5().setText("Individual");
		getRb4().setText("Anulado");
		getRb3().setText("Vigente");
		getRb2().setText("Pago");
		getRb1().setText("Pago");
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReportePagoControlador getControlador() {
		return controlador;
	}
	
	
}
