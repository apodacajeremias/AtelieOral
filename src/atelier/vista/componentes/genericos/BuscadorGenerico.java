package atelier.vista.componentes.genericos;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import atelier.vista.componentes.CampoTextoPersonalizado;

public class BuscadorGenerico extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1140798880877093242L;
	private CampoTextoPersonalizado tBuscador;
	private JTable table;


	
	/**
	 * Create the dialog.
	 */
	public BuscadorGenerico() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscadorGenerico.class.getResource("/img/Buscar.png")));
		setBounds(100, 100, 500, 300);
		setLocationRelativeTo(this);
		setModal(true);
		getContentPane().setLayout(null);
		
		tBuscador = new CampoTextoPersonalizado();
		tBuscador.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tBuscador.setBounds(10, 11, 464, 30);
		getContentPane().add(tBuscador);
		tBuscador.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBounds(10, 50, 464, 200);
		scrollPane.setToolTipText("Doble clic para seleccionar");
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}


	
	//metodos getters
	public JTextField gettBuscador() {
		return tBuscador;
	}


	public JTable getTable() {
		return table;
	}

}
