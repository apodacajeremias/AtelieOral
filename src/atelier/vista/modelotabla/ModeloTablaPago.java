package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Pago;

public class ModeloTablaPago extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3605837039929605786L;
	
	private String[] columnas = {"FECHA", "PACIENTE", "VALOR", "ESTADO"};
	private List<Pago> lista = new ArrayList<>();
	
	public void setLista(List<Pago> lista) {
		this.lista = lista;
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}
	
	@Override
	public String getColumnName(int c) {
		return columnas[c];
	}
	
	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return EventosUtil.formatoFecha(lista.get(r).getFechaPago());
		case 1:
			return lista.get(r).getPaciente();
		case 2:
			return EventosUtil.separadorMiles((double) lista.get(r).getValorPago());
		case 3:
			return lista.get(r).isEstadoPago() ? "VIGENTE" : "ANULADO";
		}
		return null;
	}

}
