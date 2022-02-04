package atelier.vista.ventana.transacciones;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import atelier.controlador.transacciones.TransaccionRecetaReposoControlador;
import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;

public class TransaccionRecetaReposo extends JDialog {

	private static final long serialVersionUID = -1019012073287408226L;
	private LabelPersonalizado lPacienteApellido;
	private MiBoton btnBuscarPaciente;
	private MiBoton btnFinalizar;
	private TransaccionRecetaReposoControlador controlador;
	private LabelPersonalizado lFechaRegistro;
	private LabelPersonalizado lEstado;
	private MiBoton btnCancelar;
	private MiBoton btnNuevo;
	private MiBoton btnBuscarPresupuesto;
	private LabelPersonalizado lInfo1;
	private LabelPersonalizado lPaciente;
	private LabelPersonalizado lPacienteCedula;
	private LabelPersonalizado lPacienteContacto;
	private JTable table;
	private MiBoton btnAgregar;
	private LabelPersonalizado lInfo2;
	private JPanel panelReceta;
	private JRadioButton rdReposo;
	private JTextArea tMotivoReposo;
	private CampoTextoPersonalizado tTiempoReposo;
	private JPanel panel;
	private JTextArea textArea;

	public void setUpControlador() {
		controlador = new TransaccionRecetaReposoControlador(this);

	}

	/**
	 * Create the dialog.
	 */
	public TransaccionRecetaReposo() {
		setResizable(false);
		setType(Type.POPUP);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(this);
		setModal(true);
		setTitle("Emisi\u00F3n de Receta con Reposo");
		getContentPane().setLayout(null);

		btnFinalizar = new MiBoton("Finalizar");
		btnFinalizar.setActionCommand("Guardar");
		btnFinalizar.setBounds(259, 536, 110, 30);
		getContentPane().add(btnFinalizar);

		btnNuevo = new MiBoton("Nuevo");
		btnNuevo.setActionCommand("Nuevo");
		btnNuevo.setBounds(10, 536, 110, 30);
		getContentPane().add(btnNuevo);

		btnCancelar = new MiBoton("Cancelar");
		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.setBounds(136, 536, 110, 30);
		getContentPane().add(btnCancelar);

		btnBuscarPresupuesto = new MiBoton("Busqueda");
		btnBuscarPresupuesto.setActionCommand("Buscar");
		btnBuscarPresupuesto.setBounds(774, 536, 110, 30);
		getContentPane().add(btnBuscarPresupuesto);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Informacion del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 5, 429, 90);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lPaciente = new LabelPersonalizado(20);
		lPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lPaciente.setText("ENZO JEREMIAS");
		lPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		lPaciente.setBounds(14, 20, 250, 20);
		panel_1.add(lPaciente);

		btnBuscarPaciente = new MiBoton("Buscar");
		btnBuscarPaciente.setText("");
		btnBuscarPaciente.setBounds(388, 20, 30, 30);
		panel_1.add(btnBuscarPaciente);
		btnBuscarPaciente.setActionCommand("BuscarPaciente");

		lPacienteApellido = new LabelPersonalizado(20);
		lPacienteApellido.setText("APODACA GONZALEZ");
		lPacienteApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lPacienteApellido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lPacienteApellido.setBounds(14, 42, 250, 20);
		panel_1.add(lPacienteApellido);

		lPacienteCedula = new LabelPersonalizado(20);
		lPacienteCedula.setText("4.563.438");
		lPacienteCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lPacienteCedula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPacienteCedula.setBounds(278, 20, 100, 20);
		panel_1.add(lPacienteCedula);

		lPacienteContacto = new LabelPersonalizado(20);
		lPacienteContacto.setText("0985300003");
		lPacienteContacto.setHorizontalAlignment(SwingConstants.LEFT);
		lPacienteContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPacienteContacto.setBounds(278, 42, 100, 20);
		panel_1.add(lPacienteContacto);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion general",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(449, 5, 429, 90);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		lEstado = new LabelPersonalizado(0);
		lEstado.setFont(new Font("Tahoma", Font.BOLD, 18));
		lEstado.setBounds(9, 20, 200, 20);
		panel_2.add(lEstado);
		lEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lEstado.setHorizontalTextPosition(SwingConstants.LEFT);

		lFechaRegistro = new LabelPersonalizado(0);
		lFechaRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lFechaRegistro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lFechaRegistro.setBounds(9, 45, 200, 20);
		panel_2.add(lFechaRegistro);

		lInfo1 = new LabelPersonalizado(0);
		lInfo1.setHorizontalAlignment(SwingConstants.LEFT);
		lInfo1.setBounds(218, 20, 200, 20);
		panel_2.add(lInfo1);
		lInfo1.setFont(new Font("Tahoma", Font.PLAIN, 18));

		lInfo2 = new LabelPersonalizado(0);
		lInfo2.setHorizontalAlignment(SwingConstants.LEFT);
		lInfo2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lInfo2.setBounds(218, 45, 200, 20);
		panel_2.add(lInfo2);

		panelReceta = new JPanel();
		panelReceta.setBackground(Color.WHITE);
		panelReceta.setBounds(10, 101, 874, 186);
		getContentPane().add(panelReceta);
		panelReceta.setLayout(null);

		LabelPersonalizado lblProcedimiento = new LabelPersonalizado(16);
		lblProcedimiento.setBounds(10, 5, 419, 20);
		panelReceta.add(lblProcedimiento);
		lblProcedimiento.setText("Lista de medicamentos para receta");
		lblProcedimiento.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnAgregar = new MiBoton("Nuevo");
		btnAgregar.setBounds(774, 0, 100, 30);
		panelReceta.add(btnAgregar);
		btnAgregar.setText("Agregar");
		btnAgregar.setActionCommand("Agregar");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 36, 874, 150);
		panelReceta.add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		rdReposo = new JRadioButton("Incluir reposo");
		rdReposo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(rdReposo.isSelected());
			}
		});
		rdReposo.setBackground(Color.WHITE);
		rdReposo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		rdReposo.setBounds(10, 297, 200, 20);
		getContentPane().add(rdReposo);

		panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(10, 324, 874, 151);
		getContentPane().add(panel);
		panel.setLayout(null);

		LabelPersonalizado lblprsnlzdMotivoDeReposo = new LabelPersonalizado(16);
		lblprsnlzdMotivoDeReposo.setBounds(82, 0, 200, 20);
		panel.add(lblprsnlzdMotivoDeReposo);
		lblprsnlzdMotivoDeReposo.setText("Motivo de reposo");
		lblprsnlzdMotivoDeReposo.setFont(new Font("Tahoma", Font.BOLD, 18));

		tTiempoReposo = new CampoTextoPersonalizado();
		tTiempoReposo.setBounds(0, 120, 364, 30);
		panel.add(tTiempoReposo);

		LabelPersonalizado lblprsnlzdTiempoDeReposo = new LabelPersonalizado(16);
		lblprsnlzdTiempoDeReposo.setBounds(82, 92, 200, 20);
		panel.add(lblprsnlzdTiempoDeReposo);
		lblprsnlzdTiempoDeReposo.setText("Tiempo de reposo");
		lblprsnlzdTiempoDeReposo.setFont(new Font("Tahoma", Font.BOLD, 18));

		tMotivoReposo = new JTextArea();
		tMotivoReposo.setBounds(0, 31, 364, 50);
		panel.add(tMotivoReposo);
		tMotivoReposo.setBorder(new LineBorder(new Color(0, 0, 0)));
		tMotivoReposo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tMotivoReposo.setWrapStyleWord(true);
		tMotivoReposo.setLineWrap(true);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setBorder(new TitledBorder(null, "Previsualizaci\u00F3n de texto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		textArea.setBounds(374, 0, 500, 150);
		panel.add(textArea);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LabelPersonalizado getlPacienteApellido() {
		return lPacienteApellido;
	}

	public MiBoton getBtnBuscarPaciente() {
		return btnBuscarPaciente;
	}

	public MiBoton getBtnFinalizar() {
		return btnFinalizar;
	}

	public TransaccionRecetaReposoControlador getControlador() {
		return controlador;
	}

	public LabelPersonalizado getlFechaRegistro() {
		return lFechaRegistro;
	}

	public LabelPersonalizado getlEstado() {
		return lEstado;
	}

	public MiBoton getBtnCancelar() {
		return btnCancelar;
	}

	public MiBoton getBtnNuevo() {
		return btnNuevo;
	}

	public MiBoton getBtnBuscarPresupuesto() {
		return btnBuscarPresupuesto;
	}

	public LabelPersonalizado getlInfo1() {
		return lInfo1;
	}

	public LabelPersonalizado getlPaciente() {
		return lPaciente;
	}

	public LabelPersonalizado getlPacienteCedula() {
		return lPacienteCedula;
	}

	public LabelPersonalizado getlPacienteContacto() {
		return lPacienteContacto;
	}

	public JTable getTable() {
		return table;
	}

	public MiBoton getBtnAgregar() {
		return btnAgregar;
	}

	public LabelPersonalizado getlInfo2() {
		return lInfo2;
	}

	public JPanel getPanelReceta() {
		return panelReceta;
	}

	public JRadioButton getRdReposo() {
		return rdReposo;
	}

	public JTextArea gettMotivoReposo() {
		return tMotivoReposo;
	}

	public CampoTextoPersonalizado gettTiempoReposo() {
		return tTiempoReposo;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	
}
