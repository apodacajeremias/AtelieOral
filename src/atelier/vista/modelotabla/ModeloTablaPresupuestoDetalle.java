package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.PresupuestoDetalle;

public class ModeloTablaPresupuestoDetalle extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -545738629065442036L;

	private String[] columnas = { "DIENTES", "DESCRIPCIÓN", "PRECIO U.", "CANT.", "SUBTOTAL" };

	private List<PresupuestoDetalle> detalle = new ArrayList<>();

	public void setDetalle(List<PresupuestoDetalle> detalle) {
		this.detalle = detalle;
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
		return detalle.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return detalle.get(rowIndex).getDientes();
		case 1:
			return detalle.get(rowIndex).getProcedimiento().getDescripcion();
		case 2:
			return EventosUtil.separadorMiles((double) detalle.get(rowIndex).getProcedimiento().getValorIndividual());
		case 3:
			return EventosUtil.separadorMiles((double) detalle.get(rowIndex).getCantidad());
		case 4:
			return EventosUtil.separadorMiles((double) detalle.get(rowIndex).getProcedimiento().getValorIndividual()
					* detalle.get(rowIndex).getCantidad());
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int r, int c) {
		PresupuestoDetalle row = detalle.get(r);
		if (0 == c) {
			try {
				row.setDientes(
						aValue.toString().replace(".", ",").replace("//s", ",").replace("/", ",").replace("-", ","));
				row.setCantidad(calcularCantidad(
						aValue.toString().replace(".", ",").replace("//s", ",").replace("/", ",").replace("-", ",")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (3 == c) {
			try {
				row.setCantidad(Integer.parseInt(aValue.toString().replace(".", "")));
				row.setSubTotal(calcularSubtotal(Integer.parseInt(aValue.toString().replace(".", "")), row.getPrecio()));
			} catch (NumberFormatException e) {
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
		case 0:
			return true;
		case 3:
			return true;
		default:
			return false;
		}
	}

	private int calcularCantidad(String cadena) {
		String palabras[] = cadena.split(",");
		return palabras.length;
	}

	private int calcularSubtotal(int cantidad, int precio) {
		return precio * cantidad;
	}
}
