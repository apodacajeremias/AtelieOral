package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Procedimiento;

public class ModeloTablaProcedimiento extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4234581036007495177L;

	private String[] columnas = { "PROCEDIMIENTO", "VALOR IND" };

	private List<Procedimiento> lista = new ArrayList<>();

	public void setLista(List<Procedimiento> lista) {
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
			return lista.get(r).getDescripcion();
		case 1:
			return EventosUtil.separadorMiles((double)lista.get(r).getValorIndividual());
		}
		return null;
	}

}
