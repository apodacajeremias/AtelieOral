package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Recetario;

@SuppressWarnings("serial")
public class ModeloTablaRecetario extends AbstractTableModel {

	private String[] columnas = { "CÓDIGO", "FECHA", "TIPO" };

	private List<Recetario> lista = new ArrayList<Recetario>();

	public void setLista(List<Recetario> lista) {
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
			String rep = "";
			String rec = "";
			try {
				rep = lista.get(r).getReposo().getId()+"";
			} catch (Exception e) {
				rep = "0";
			}
			try {
				rec = lista.get(r).getId()+"";
			} catch (Exception e) {
				rec = "0";
			}
			
			return rec+"."+rep;
		case 1:
			return EventosUtil.formatoFecha(lista.get(r).getFechaReceta());
		case 2:
			String reposo = "";
			String receta = "";
			try {
				if (lista.get(r).getReposo().isEstado()) {
					reposo = "REPOSO";
				}
			} catch (Exception e) {
				
			}
			try {
				if (lista.get(r).isEstado()) {
					receta = "RECETA";
				}
			} catch (Exception e) {
				
			}
			return reposo+" "+receta;
		default:
			return null;
		}

	}

}
