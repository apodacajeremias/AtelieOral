package atelier.vista.componentes.genericos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiToolbar;
import atelier.vista.componentes.TextPrompt;

public class VentanaGenerica extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2162327890190805660L;
	private JTable table;
	private CampoTextoPersonalizado tBuscador;
	private MiToolbar miToolBar;
	private LabelPersonalizado lblAccionEjecutada;
	private JLabel lblNombreFormulario;

	/**
	 * Create the dialog.
	 */
	public VentanaGenerica() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGenerica.class.getResource("/img/LogoReporte.png")));
		setBackground(new Color(51, 51, 51));
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		setType(Type.UTILITY);
		setBounds(100, 100, 900, 600);
		// centrad
		setLocationRelativeTo(this);
		// Evita que la ventana pierda el foco hasta que se cierre
		setModal(true);
		getContentPane().setLayout(null);

		miToolBar = new MiToolbar();
		miToolBar.setBounds(484, 10, 400, 35);
		getContentPane().add(miToolBar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBackground(new Color(204, 204, 204));
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, new Color(240, 248, 255)));
		scrollPane.setBounds(484, 93, 400, 467);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setBackground(new Color(204, 204, 204));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setInheritsPopupMenu(true);
		scrollPane.setViewportView(table);

		tBuscador = new CampoTextoPersonalizado();
		tBuscador.mayusculas();
		tBuscador.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tBuscador.setToolTipText("Buscar Registro");
		tBuscador.setBorder(new MatteBorder(0, 0, 1, 0, new Color(255, 228, 225)));
		tBuscador.setSelectionColor(Color.WHITE);
		tBuscador.setBounds(484, 57, 400, 25);
		getContentPane().add(tBuscador);
		tBuscador.setColumns(10);

		TextPrompt busqueda = new TextPrompt("Busqueda", tBuscador);

		lblNombreFormulario = new JLabel("Formulario");
		lblNombreFormulario.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreFormulario.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNombreFormulario.setBounds(20, 57, 454, 25);
		getContentPane().add(lblNombreFormulario);

		lblAccionEjecutada = new LabelPersonalizado(10);
		lblAccionEjecutada.setBounds(20, 545, 454, 15);
		getContentPane().add(lblAccionEjecutada);
		lblAccionEjecutada.setFont(new Font("Tahoma", Font.BOLD, 12));
		busqueda.setLocation(0, 516);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTable getTable() {
		return table;
	}

	public CampoTextoPersonalizado gettBuscador() {
		return tBuscador;
	}

	public MiToolbar getMiToolBar() {
		return miToolBar;
	}

	public LabelPersonalizado getLblAccionEjecutada() {
		return lblAccionEjecutada;
	}

	public JLabel getLblNombreFormulario() {
		return lblNombreFormulario;
	}
}
