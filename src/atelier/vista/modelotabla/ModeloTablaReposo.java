package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Reposo;

@SuppressWarnings("serial")
public class ModeloTablaReposo extends AbstractTableModel {

	private String[] columnas = { "CÓDIGO", "PACIENTE", "FECHA", "TIEMPO", "COMENTARIO" };

	private List<Reposo> lista = new ArrayList<Reposo>();

	public void setLista(List<Reposo> lista) {
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
	public String getColumnName(int i) {
		return columnas[i];
	}

	@Override
	public Object getValueAt(int r, int c) {
		switch (c) {
		case 0:
			return lista.get(r).getId();
		case 1:
			return lista.get(r).getPaciente();
		case 2:
			return EventosUtil.formatoFecha(lista.get(r).getFechaReposo());
		case 3:
			return lista.get(r).getTiempo();
		case 4:
			return lista.get(r).getComentario();
		default:
			return null;
		}

	}
}
