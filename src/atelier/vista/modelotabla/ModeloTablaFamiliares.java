package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.modelo.entidades.Familiares;

public class ModeloTablaFamiliares extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5810519927083575676L;

	private String[] columnas = { "FAMILIAR" };

	private List<Familiares> lista = new ArrayList<>();

	public void setLista(List<Familiares> lista) {
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
			return lista.get(r).getPaciente();

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
