package atelier.vista.reporte;

import atelier.controlador.reportes.ReporteAgendaControlador;
import atelier.vista.componentes.genericos.ReporteGenerico;

public class ReporteAgenda extends ReporteGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1203406937927609263L;
	private ReporteAgendaControlador controlador;

	public void setUpControlador() {
		controlador = new ReporteAgendaControlador(this);

	}
	
	public ReporteAgenda() {
		getRb2().setText("Agenda");
		getRb1().setText("Agenda");
		getRb6().setText("General");
		getRb5().setText("Individual");
		getRb4().setText("Cancelado");
		getRb3().setText("Agendado");

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ReporteAgendaControlador getControlador() {
		return controlador;
	}

}
