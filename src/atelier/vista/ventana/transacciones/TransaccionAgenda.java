package atelier.vista.ventana.transacciones;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JCalendar;

import atelier.controlador.transacciones.TransaccionAgendaControlador;
import atelier.vista.componentes.ClockLabel;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;

public class TransaccionAgenda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2414657657350406543L;
	private JTable table;
	private LabelPersonalizado lblPaciente;
	private MiBoton btnDesagendar;
	private MiBoton btnSalir;
	private MiBoton btnAgendar;
	private MiBoton btnCancelar;
	private MiBoton btnBuscar;

	private TransaccionAgendaControlador controlador;
	@SuppressWarnings("rawtypes")
	private JComboBox sHorario;
	private JCalendar calendar;
	private LabelPersonalizado lblFechaSeleccionada;
	private MiBoton btnConsultar;
	private JPanel panel;
	private LabelPersonalizado lblPacienteApellido;
	private JPanel panel_1;
	@SuppressWarnings("rawtypes")
	private JComboBox cbMedico;

	public void setUpControlador() {
		controlador = new TransaccionAgendaControlador(this);
	}

	/**
	 * Create the dialog.
	 * 
	 * @param funcionario
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TransaccionAgenda() {
		setResizable(false);
		setType(Type.POPUP);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(this);
		setModal(true);
		setTitle("Gestión de Agenda");
		getContentPane().setLayout(null);

		LabelPersonalizado labelPersonalizado = new LabelPersonalizado(22);
		labelPersonalizado.setText("AGENDA");
		labelPersonalizado.setBounds(10, 22, 100, 20);
		getContentPane().add(labelPersonalizado);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 52, 500, 508);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollPane.setViewportView(table);

		calendar = new JCalendar();
		calendar.setWeekOfYearVisible(false);
		calendar.getDayChooser().getDayPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calendar.setBounds(10, 54, 364, 211);
		getContentPane().add(calendar);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione el paciente",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 277, 364, 152);
		getContentPane().add(panel);

		btnBuscar = new MiBoton("Buscar");
		btnBuscar.setBounds(302, 16, 50, 30);
		btnBuscar.setText("");
		btnBuscar.setActionCommand("Buscar");

		btnCancelar = new MiBoton("Cancelar");
		btnCancelar.setBounds(302, 108, 50, 30);
		btnCancelar.setToolTipText("No confirmar agenda, cancelar");
		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.setText("");

		lblPaciente = new LabelPersonalizado(18);
		lblPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPaciente.setBounds(10, 15, 280, 20);
		lblPaciente.setText("");

		btnAgendar = new MiBoton("Agendar");
		btnAgendar.setBounds(178, 108, 114, 30);
		btnAgendar.setText("Agendar");
		btnAgendar.setActionCommand("Agendar");

		sHorario = new JComboBox();
		sHorario.setModel(new DefaultComboBoxModel(new String[] { "07:00", "07:30", "08:00", "08:30", "09:00", "09:30",
				"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00",
				"15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
		sHorario.setBounds(10, 111, 100, 25);

		LabelPersonalizado lblprsnlzdIndiqueElHorario = new LabelPersonalizado(10);
		lblprsnlzdIndiqueElHorario.setText("En el horario");
		lblprsnlzdIndiqueElHorario.setHorizontalAlignment(SwingConstants.LEFT);
		lblprsnlzdIndiqueElHorario.setBounds(10, 92, 187, 15);
		lblprsnlzdIndiqueElHorario.setText("Indique el horario");
		panel.setLayout(null);
		panel.add(btnBuscar);
		panel.add(btnCancelar);
		panel.add(lblPaciente);
		panel.add(btnAgendar);
		panel.add(sHorario);
		panel.add(lblprsnlzdIndiqueElHorario);

		lblPacienteApellido = new LabelPersonalizado(18);
		lblPacienteApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lblPacienteApellido.setText("");
		lblPacienteApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPacienteApellido.setBounds(10, 38, 280, 15);
		panel.add(lblPacienteApellido);

		cbMedico = new JComboBox();
		cbMedico.setBounds(10, 62, 280, 25);
		panel.add(cbMedico);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(
				new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 440, 364, 120);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		ClockLabel labelDia = new ClockLabel("dia");
		labelDia.setForeground(Color.BLACK);
		labelDia.setHorizontalAlignment(SwingConstants.CENTER);
		labelDia.setBounds(103, 90, 159, 20);
		panel_1.add(labelDia);

		ClockLabel labelHora = new ClockLabel("hora");
		labelHora.setForeground(Color.BLACK);
		labelHora.setBounds(10, 16, 344, 40);
		panel_1.add(labelHora);

		ClockLabel labelFeca = new ClockLabel("fecha");
		labelFeca.setHorizontalAlignment(SwingConstants.CENTER);
		labelFeca.setForeground(Color.BLACK);
		labelFeca.setBounds(10, 72, 344, 20);
		panel_1.add(labelFeca);

		JSeparator separator = new JSeparator();
		separator.setBounds(32, 65, 300, 2);
		panel_1.add(separator);

		lblFechaSeleccionada = new LabelPersonalizado(14);
		lblFechaSeleccionada.setText("Agendar al paciente");
		lblFechaSeleccionada.setBounds(120, 27, 254, 15);
		getContentPane().add(lblFechaSeleccionada);
		lblFechaSeleccionada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaSeleccionada.setText("");

		btnSalir = new MiBoton("Salir");
		btnSalir.setBounds(784, 11, 100, 30);
		getContentPane().add(btnSalir);
		btnSalir.setText("Salir");

		btnConsultar = new MiBoton("Buscar");
		btnConsultar.setBounds(384, 11, 200, 30);
		getContentPane().add(btnConsultar);
		btnConsultar.setText("Buscar paciente en agenda");
		btnConsultar.setActionCommand("Consultar");

		btnDesagendar = new MiBoton("Desagendar");
		btnDesagendar.setVisible(false);
		btnDesagendar.setBounds(594, 11, 100, 30);
		getContentPane().add(btnDesagendar);
		btnDesagendar.setText("Desagendar");
		btnDesagendar.setActionCommand("Desagendar");

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JTable getTable() {
		return table;
	}

	public LabelPersonalizado getLblPaciente() {
		return lblPaciente;
	}

	public MiBoton getBtnDesagendar() {
		return btnDesagendar;
	}

	public MiBoton getBtnSalir() {
		return btnSalir;
	}

	public MiBoton getBtnAgendar() {
		return btnAgendar;
	}

	public MiBoton getBtnCancelar() {
		return btnCancelar;
	}

	public MiBoton getBtnBuscar() {
		return btnBuscar;
	}

	public TransaccionAgendaControlador getControlador() {
		return controlador;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getsHorario() {
		return sHorario;
	}

	public JCalendar getCalendar() {
		return calendar;
	}

	public LabelPersonalizado getLblFechaSeleccionada() {
		return lblFechaSeleccionada;
	}

	public MiBoton getBtnConsultar() {
		return btnConsultar;
	}

	public JPanel getPanel() {
		return panel;
	}

	public LabelPersonalizado getLblPacienteApellido() {
		return lblPacienteApellido;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getCbMedico() {
		return cbMedico;
	}

}
