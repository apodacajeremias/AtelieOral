package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.modelo.entidades.Seguimiento;

public class ModeloTablaSeguimiento extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -545738629065442036L;

	private String[] columnas = { "INFO", "PROCEDIMIENTO", "DIENTE", "COMENTARIO" };

	private List<Seguimiento> seguimientos = new ArrayList<>();

	public void setSeguimiento(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
		fireTableDataChanged();
	}

	@Override
	public String getColumnName(int c) {

		return columnas[c];
	}

	@Override
	public int getColumnCount() {

		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return seguimientos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return seguimientos.get(rowIndex).getPresupuestoDetalle();
		case 1:
			return seguimientos.get(rowIndex).getProcedimiento();
		case 2:
			return seguimientos.get(rowIndex).getDiente().equals("") ? "No se menciona diente especifico." : seguimientos.get(rowIndex).getDiente();
		case 3:
			return seguimientos.get(rowIndex).getComentario().equals("") ? "Sin comentarios, por favor edite el campo si necesario" : seguimientos.get(rowIndex).getComentario(); 
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int r, int c) {
		Seguimiento row = seguimientos.get(r);
		if (2 == c) {
			try {
				row.setDiente(
						aValue.toString().replace(".", ",").replace("//s", ",").replace("/", ",").replace("-", ","));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (3 == c) {
			try {
				row.setComentario((String) aValue.toString().toUpperCase());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.setValueAt(aValue, r, c);
		fireTableDataChanged();
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
