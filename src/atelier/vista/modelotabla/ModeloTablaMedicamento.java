package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.modelo.entidades.Medicamento;

public class ModeloTablaMedicamento extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6534529106265887918L;

	private String[] columnas = { "MEDICAMENTO", "COMPOSICION" };

	private List<Medicamento> lista = new ArrayList<>();

	public void setLista(List<Medicamento> lista) {
		this.lista = lista;
		fireTableDataChanged();
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
			return lista.get(r).getNombreComercial();
		case 1:
			return lista.get(r).getInformacion();
		}
		return null;
	}

	// ESTE METODO SOLO SE AGREAGA SI NECESITAMOS FORMATEAR UNA COLUMNA
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == 3) {
			return Date.class;
		}
		return super.getColumnClass(columnIndex);
	}

}
