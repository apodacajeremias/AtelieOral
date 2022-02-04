package atelier.vista.reporte;

import atelier.controlador.reportes.ReportePresupuestoControlador;
import atelier.vista.componentes.genericos.ReporteGenerico;

public class ReportePresupuesto extends ReporteGenerico {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2114666753230866369L;
	private ReportePresupuestoControlador controlador;

	public void setUpControlador() {
		controlador = new ReportePresupuestoControlador(this);

	}

	public ReportePresupuesto() {
		getRb6().setSelected(true);
		getRb6().setText("General");
		getRb5().setText("Individual");
		getRb4().setText("Rechazados y Pendiente");
		getRb3().setText("Aprobados");
		getRb2().setText("Presupuesto");
		getRb1().setText("Presupuesto");
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReportePresupuestoControlador getControlador() {
		return controlador;
	}
	
	

}
