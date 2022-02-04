package atelier.vista.modelotabla;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import atelier.modelo.entidades.Funcionario;

public class ModeloTablaFuncionario extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8592826238780596204L;

	private String[] columnas = { "NOMBRE COMPLETO", "DOCUMENTO" };
	private List<Funcionario> lista = new ArrayList<>();

	public void setLista(List<Funcionario> lista) {
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
			return lista.get(r).getNombre() + " " + lista.get(r).getApellido();
		case 1:
			return lista.get(r).getDocIdentidad();
		case 2:
			return lista.get(r).getFechaNac();

		}
		return null;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == 3) {
			return Date.class;
		}
		/*
		 * if (columnIndex==3) { return Boolean.class; }
		 */
		return super.getColumnClass(columnIndex);
	}

}
