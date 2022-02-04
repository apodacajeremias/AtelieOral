package atelier.vista.ventana.transacciones;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import atelier.controlador.transacciones.TransaccionPagoController;
import atelier.vista.componentes.CampoNumeroPersonalizado;
import atelier.vista.componentes.ClockLabel;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;

public class TransaccionPago extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2414657657350406543L;
	private JTable table;
	private LabelPersonalizado lblPaciente;
	private MiBoton btnBuscarPorPaciente;
	private MiBoton btnSalir;
	private MiBoton btnConfirmar;
	private MiBoton btnCancelar;
	private MiBoton btnBuscar;

	private TransaccionPagoController controller;
	private LabelPersonalizado lblFechaSeleccionada;
	private CampoNumeroPersonalizado tValorPago;
	private CampoNumeroPersonalizado tValorPendiente;
	private CampoNumeroPersonalizado tSumaTotal;
	private CampoNumeroPersonalizado tIngresarValor;
	private LabelPersonalizado lblPacienteApellido;
	private LabelPersonalizado lSaldoIngresado;
	private LabelPersonalizado lMensualidad;
	private LabelPersonalizado lblMensualidad;
	private JRadioButton rdPagarMensualidad;
	private JPanel panelPago;

	public void setUpController() {
		controller = new TransaccionPagoController(this);
	}

	/**
	 * Create the dialog.
	 * 
	 * @param funcionario
	 */
	public TransaccionPago() {
		setResizable(false);
		setType(Type.POPUP);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(this);
		setModal(true);
		setTitle("Pagos");
		getContentPane().setLayout(null);

		LabelPersonalizado lblprsnlzdCaja = new LabelPersonalizado( 22);
		lblprsnlzdCaja.setText("CAJA");
		lblprsnlzdCaja.setBounds(10, 25, 56, 20);
		getContentPane().add(lblprsnlzdCaja);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(379, 52, 505, 508);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnBuscarPorPaciente = new MiBoton("Buscar");
		btnBuscarPorPaciente.setText("Buscar pagos por paciente");
		btnBuscarPorPaciente.setActionCommand("BuscarPaciente");
		btnBuscarPorPaciente.setBounds(379, 11, 193, 30);
		getContentPane().add(btnBuscarPorPaciente);

		btnSalir = new MiBoton("Salir");
		btnSalir.setText("Salir");
		btnSalir.setBounds(784, 11, 100, 30);
		getContentPane().add(btnSalir);

		lblFechaSeleccionada = new LabelPersonalizado( 14);
		lblFechaSeleccionada.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaSeleccionada.setText("");
		lblFechaSeleccionada.setBounds(76, 30, 293, 15);
		getContentPane().add(lblFechaSeleccionada);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(
				new TitledBorder(null, "Datos del Paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 56, 360, 116);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblPaciente = new LabelPersonalizado(18);
		lblPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPaciente.setBounds(10, 15, 280, 20);
		panel.add(lblPaciente);
		lblPaciente.setText("");

		LabelPersonalizado lblprsnlzdValorPago = new LabelPersonalizado(12);
		lblprsnlzdValorPago.setBounds(10, 63, 69, 20);
		panel.add(lblprsnlzdValorPago);
		lblprsnlzdValorPago.setText("Suma Total");

		LabelPersonalizado lblprsnlzdValorPendiente = new LabelPersonalizado(12);
		lblprsnlzdValorPendiente.setBounds(250, 63, 100, 20);
		panel.add(lblprsnlzdValorPendiente);
		lblprsnlzdValorPendiente.setText("Valor Pendiente");

		LabelPersonalizado lblprsnlzdValorPago_1 = new LabelPersonalizado( 12);
		lblprsnlzdValorPago_1.setText("Valor Ya Pago");
		lblprsnlzdValorPago_1.setBounds(123, 63, 90, 20);
		panel.add(lblprsnlzdValorPago_1);

		tSumaTotal = new CampoNumeroPersonalizado();
		tSumaTotal.setBounds(10, 84, 100, 20);
		panel.add(tSumaTotal);

		tValorPendiente = new CampoNumeroPersonalizado();
		tValorPendiente.setBounds(250, 84, 100, 20);
		panel.add(tValorPendiente);

		tValorPago = new CampoNumeroPersonalizado();
		tValorPago.setBounds(123, 84, 100, 20);
		panel.add(tValorPago);

		lblPacienteApellido = new LabelPersonalizado( 18);
		lblPacienteApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lblPacienteApellido.setText("");
		lblPacienteApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPacienteApellido.setBounds(10, 37, 280, 15);
		panel.add(lblPacienteApellido);

		btnBuscar = new MiBoton("Buscar");
		btnBuscar.setBounds(300, 15, 50, 30);
		panel.add(btnBuscar);
		btnBuscar.setText("");
		btnBuscar.setActionCommand("Buscar");

		panelPago = new JPanel();
		panelPago.setBackground(Color.WHITE);
		panelPago.setBorder(new TitledBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registrar Pago", TitledBorder.LEADING,
						TitledBorder.TOP, null, new Color(0, 0, 0)),
				"Ingresar Pago", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPago.setBounds(9, 183, 360, 167);
		getContentPane().add(panelPago);
		panelPago.setLayout(null);

		LabelPersonalizado lblprsnlzdValor = new LabelPersonalizado( 12);
		lblprsnlzdValor.setBounds(28, 67, 95, 15);
		panelPago.add(lblprsnlzdValor);
		lblprsnlzdValor.setText("Valor ingresado");

		tIngresarValor = new CampoNumeroPersonalizado();
		tIngresarValor.setFont(new Font("Tahoma", Font.BOLD, 15));
		tIngresarValor.setBounds(13, 82, 125, 30);
		panelPago.add(tIngresarValor);

		btnConfirmar = new MiBoton("Guardar");
		btnConfirmar.setBounds(145, 121, 143, 30);
		panelPago.add(btnConfirmar);
		btnConfirmar.setText("Confirmar Pago");
		btnConfirmar.setActionCommand("ConfirmarPago");

		btnCancelar = new MiBoton("Cancelar");
		btnCancelar.setBounds(293, 121, 50, 30);
		panelPago.add(btnCancelar);
		btnCancelar.setToolTipText("No confirmar pago, cancelar");
		btnCancelar.setActionCommand("Cancelar");
		btnCancelar.setText("");

		rdPagarMensualidad = new JRadioButton("Pagar mensualidad");
		rdPagarMensualidad.setHorizontalAlignment(SwingConstants.RIGHT);
		rdPagarMensualidad.setBackground(Color.WHITE);
		rdPagarMensualidad.setBounds(145, 20, 201, 20);
		panelPago.add(rdPagarMensualidad);

		lMensualidad = new LabelPersonalizado( 18);
		lMensualidad.setText("");
		lMensualidad.setHorizontalAlignment(SwingConstants.LEFT);
		lMensualidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lMensualidad.setBounds(13, 36, 333, 20);
		panelPago.add(lMensualidad);

		lblMensualidad = new LabelPersonalizado( 10);
		lblMensualidad.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMensualidad.setText("Mensualidad indicada");
		lblMensualidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblMensualidad.setBounds(13, 20, 117, 15);
		panelPago.add(lblMensualidad);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(
				new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(7, 414, 364, 146);
		getContentPane().add(panel_1_1);

		ClockLabel labelDia = new ClockLabel("dia");
		labelDia.setHorizontalAlignment(SwingConstants.CENTER);
		labelDia.setForeground(Color.BLACK);
		labelDia.setBounds(103, 90, 159, 20);
		panel_1_1.add(labelDia);

		ClockLabel labelHora = new ClockLabel("hora");
		labelHora.setForeground(Color.BLACK);
		labelHora.setBounds(10, 16, 344, 40);
		panel_1_1.add(labelHora);

		ClockLabel labelFeca = new ClockLabel("fecha");
		labelFeca.setHorizontalAlignment(SwingConstants.CENTER);
		labelFeca.setForeground(Color.BLACK);
		labelFeca.setBounds(10, 72, 344, 20);
		panel_1_1.add(labelFeca);

		JSeparator separator = new JSeparator();
		separator.setBounds(32, 65, 300, 2);
		panel_1_1.add(separator);

		lSaldoIngresado = new LabelPersonalizado( 18);
		lSaldoIngresado.setText("");
		lSaldoIngresado.setHorizontalAlignment(SwingConstants.LEFT);
		lSaldoIngresado.setFont(new Font("Tahoma", Font.BOLD, 24));
		lSaldoIngresado.setBounds(10, 361, 359, 42);
		getContentPane().add(lSaldoIngresado);

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

	public MiBoton getBtnBuscarPorPaciente() {
		return btnBuscarPorPaciente;
	}

	public MiBoton getBtnSalir() {
		return btnSalir;
	}

	public MiBoton getBtnConfirmar() {
		return btnConfirmar;
	}

	public MiBoton getBtnCancelar() {
		return btnCancelar;
	}

	public MiBoton getBtnBuscar() {
		return btnBuscar;
	}

	public TransaccionPagoController getController() {
		return controller;
	}

	public LabelPersonalizado getLblFechaSeleccionada() {
		return lblFechaSeleccionada;
	}

	public CampoNumeroPersonalizado gettValorPago() {
		return tValorPago;
	}

	public CampoNumeroPersonalizado gettValorPendiente() {
		return tValorPendiente;
	}

	public CampoNumeroPersonalizado gettSumaTotal() {
		return tSumaTotal;
	}

	public CampoNumeroPersonalizado gettIngresarValor() {
		return tIngresarValor;
	}

	public LabelPersonalizado getLblPacienteApellido() {
		return lblPacienteApellido;
	}

	public LabelPersonalizado getlSaldoIngresado() {
		return lSaldoIngresado;
	}

	public LabelPersonalizado getlMensualidad() {
		return lMensualidad;
	}

	public LabelPersonalizado getLblMensualidad() {
		return lblMensualidad;
	}

	public JRadioButton getRdPagarMensualidad() {
		return rdPagarMensualidad;
	}

	public JPanel getPanelPago() {
		return panelPago;
	}

}
