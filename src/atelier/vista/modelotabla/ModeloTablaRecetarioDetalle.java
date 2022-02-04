package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.modelo.entidades.RecetarioDetalle;

public class ModeloTablaRecetarioDetalle extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1638544966397476648L;
	private String[] columnas = {"NOMBRE COMERCIAL", "COMPOSICION DEL MED.", "CADA/H", "DURANTE/D"};
	private List<RecetarioDetalle> detalles = new ArrayList<>();

	public void setDetalles(List<RecetarioDetalle> detalles) {
		this.detalles = detalles;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return detalles.size();
	}

	@Override
	public String getColumnName(int i) {
		return columnas[i];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return detalles.get(rowIndex).getMedicamento().getNombreComercial();
		case 1:
			return detalles.get(rowIndex).getMedicamento().getInformacion();
		case 2:
			return detalles.get(rowIndex).getHoras();
		case 3:
			return detalles.get(rowIndex).getDias();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		RecetarioDetalle row = detalles.get(rowIndex);
		if (columnIndex == 2) {
			if (aValue == null) {
				return;
			}
			row.setHoras((String) aValue);
		} else if (columnIndex == 3) {
			if (aValue == null) {
				return;
			}
			row.setDias((String) aValue);
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 2:

			return true;
		case 3:

			return true;
		default:
			return false;
			
		}
	}

}
