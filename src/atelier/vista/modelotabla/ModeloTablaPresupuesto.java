package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Presupuesto;

@SuppressWarnings("serial")
public class ModeloTablaPresupuesto extends AbstractTableModel {

	private String[] columnas = { "CÓDIGO", "FECHA", "ESTADO", "CLIENTE", "TOTAL" };
	private List<Presupuesto> lista = new ArrayList<Presupuesto>();

	public void setLista(List<Presupuesto> lista) {
		this.lista = lista;
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {// cantidad de filas
		return lista.size();

	}

	@Override
	public int getColumnCount() {// cantidad de columnas
		return columnas.length;
	}

	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {// valor de la celda
		switch (c) {
		case 0:
			return lista.get(r).getId();
		case 1:
			return EventosUtil.formatoFecha(lista.get(r).getFechaPresupuesto());
		case 2:
			return lista.get(r).getEstado().toUpperCase();
		case 3:
			return lista.get(r).getPaciente().getNombre() + " " + lista.get(r).getPaciente().getApellido();
		case 4:
			return EventosUtil.separadorMiles((double) lista.get(r).getValorPagar());

		}
		return null;
	}

}
