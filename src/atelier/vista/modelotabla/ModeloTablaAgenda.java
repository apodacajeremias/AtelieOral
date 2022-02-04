package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.modelo.entidades.Agenda;

public class ModeloTablaAgenda extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3605837039929605786L;
	
	private String[] columnas = {"*", "PACIENTE", "MEDICO", "ESTADO"};
	private List<Agenda> lista = new ArrayList<>();
	
	public void setLista(List<Agenda> lista) {
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
			return lista.get(r);
		case 1:
			return lista.get(r).getPaciente();
		case 2:
			try {
				return lista.get(r).getMedico().getNombre();
			} catch (Exception e) {
				return null;
			}
		case 3:
			return lista.get(r).getMotivo().toUpperCase();
		}
		return null;
	}

}
