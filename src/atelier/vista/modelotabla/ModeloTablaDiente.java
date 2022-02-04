package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaDiente extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3605837039929605786L;

	private String[] columnas = { "DIENTE"};
	private List<String> dientes = new ArrayList<>();

	public void setDiente(List<String> dientes) {
		this.dientes = dientes;
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return dientes.size();
	}

	@Override
	public String getColumnName(int c) {
		return columnas[c];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return dientes.get(r);
		}
		return null;
	}
}
