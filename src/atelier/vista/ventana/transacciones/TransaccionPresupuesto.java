package atelier.vista.ventana.transacciones;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import atelier.controlador.transacciones.TransaccionPresupuestoControlador;
import atelier.vista.componentes.CampoNumeroPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;

public class TransaccionPresupuesto extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1019012073287408226L;
	private LabelPersonalizado lPacienteApellido;
	private MiBoton btnBuscarPaciente;
	private MiBoton btnFinalizar;
	private TransaccionPresupuestoControlador controlador;
	private LabelPersonalizado lFechaPresupuesto;
	private LabelPersonalizado lEstado;
	private MiBoton btnCancelar;
	private MiBoton btnNuevo;
	private MiBoton btnBuscarPresupuesto;
	private CampoNumeroPersonalizado tDescuento;
	private LabelPersonalizado lSumatoria;
	private LabelPersonalizado lblDescuento;
	private LabelPersonalizado lPaciente;
	private LabelPersonalizado lPacienteCedula;
	private LabelPersonalizado lPacienteConvenio;
	private LabelPersonalizado lPacienteContacto;
	private JTable table;
	private LabelPersonalizado lProcedimiento;
	private MiBoton btnBuscarProcedimiento;
	private JTable tableFamiliares;
	private MiBoton btnAgregarFamiliar;
	private LabelPersonalizado lValorPagar;

	public void setUpControlador() {
		controlador = new TransaccionPresupuestoControlador(this);

	}

	/**
	 * Create the dialog.
	 * 
	 * @param funcionario
	 */
	public TransaccionPresupuesto() {
		setResizable(false);
		setType(Type.POPUP);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(this);
		setModal(true);
		setTitle("Emisi\u00F3n de Presupuestos");
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

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 186, 874, 1);
		getContentPane().add(separator);

		btnBuscarPresupuesto = new MiBoton("Busqueda");
		btnBuscarPresupuesto.setActionCommand("BuscarPresupuesto");
		btnBuscarPresupuesto.setBounds(774, 536, 110, 30);
		getContentPane().add(btnBuscarPresupuesto);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Informacion del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 5, 594, 90);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lPaciente = new LabelPersonalizado(20);
		lPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lPaciente.setText("");
		lPaciente.setHorizontalAlignment(SwingConstants.LEFT);
		lPaciente.setBounds(14, 20, 250, 20);
		panel_1.add(lPaciente);

		btnBuscarPaciente = new MiBoton("Buscar");
		btnBuscarPaciente.setText("");
		btnBuscarPaciente.setBounds(554, 20, 30, 30);
		panel_1.add(btnBuscarPaciente);
		btnBuscarPaciente.setActionCommand("BuscarPaciente");

		lPacienteApellido = new LabelPersonalizado(20);
		lPacienteApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lPacienteApellido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lPacienteApellido.setBounds(14, 42, 250, 20);
		panel_1.add(lPacienteApellido);

		lPacienteCedula = new LabelPersonalizado(20);
		lPacienteCedula.setHorizontalAlignment(SwingConstants.LEFT);
		lPacienteCedula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPacienteCedula.setBounds(278, 20, 100, 20);
		panel_1.add(lPacienteCedula);

		lPacienteConvenio = new LabelPersonalizado(20);
		lPacienteConvenio.setHorizontalAlignment(SwingConstants.LEFT);
		lPacienteConvenio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lPacienteConvenio.setBounds(14, 65, 368, 15);
		panel_1.add(lPacienteConvenio);

		lPacienteContacto = new LabelPersonalizado(20);
		lPacienteContacto.setHorizontalAlignment(SwingConstants.LEFT);
		lPacienteContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lPacienteContacto.setBounds(278, 42, 100, 20);
		panel_1.add(lPacienteContacto);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Informacion del presupuesto", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 100, 594, 75);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		lEstado = new LabelPersonalizado(12);
		lEstado.setFont(new Font("Tahoma", Font.BOLD, 14));
		lEstado.setBounds(34, 20, 100, 20);
		panel_2.add(lEstado);
		lEstado.setHorizontalAlignment(SwingConstants.LEFT);
		lEstado.setHorizontalTextPosition(SwingConstants.LEFT);

		lFechaPresupuesto = new LabelPersonalizado(14);
		lFechaPresupuesto.setHorizontalAlignment(SwingConstants.LEFT);
		lFechaPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lFechaPresupuesto.setBounds(34, 39, 100, 20);
		panel_2.add(lFechaPresupuesto);

		LabelPersonalizado lblValorPagar_1 = new LabelPersonalizado(14);
		lblValorPagar_1.setText("SUMATORIA");
		lblValorPagar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorPagar_1.setBounds(185, 20, 79, 20);
		panel_2.add(lblValorPagar_1);

		lblDescuento = new LabelPersonalizado(12);
		lblDescuento.setText("DESCUENTO");
		lblDescuento.setBounds(185, 39, 79, 20);
		panel_2.add(lblDescuento);
		lblDescuento.setFont(new Font("Tahoma", Font.PLAIN, 14));

		tDescuento = new CampoNumeroPersonalizado();
		tDescuento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tDescuento.setBounds(281, 39, 121, 25);
		panel_2.add(tDescuento);

		lSumatoria = new LabelPersonalizado(0);
		lSumatoria.setHorizontalAlignment(SwingConstants.LEFT);
		lSumatoria.setBounds(281, 20, 121, 20);
		panel_2.add(lSumatoria);
		lSumatoria.setFont(new Font("Tahoma", Font.PLAIN, 14));

		LabelPersonalizado lblValorPagar = new LabelPersonalizado(14);
		lblValorPagar.setText("VALOR A PAGAR");
		lblValorPagar.setBounds(436, 20, 116, 20);
		panel_2.add(lblValorPagar);

		lValorPagar = new LabelPersonalizado(0);
		lValorPagar.setHorizontalAlignment(SwingConstants.LEFT);
		lValorPagar.setFont(new Font("Tahoma", Font.BOLD, 14));
		lValorPagar.setBounds(436, 39, 121, 20);
		panel_2.add(lValorPagar);

		lProcedimiento = new LabelPersonalizado(10);
		lProcedimiento.setFont(new Font("Tahoma", Font.BOLD, 18));
		lProcedimiento.setHorizontalAlignment(SwingConstants.LEFT);
		lProcedimiento.setBounds(141, 198, 613, 20);
		getContentPane().add(lProcedimiento);

		LabelPersonalizado lblProcedimiento = new LabelPersonalizado(16);
		lblProcedimiento.setText("Procedimiento");
		lblProcedimiento.setForeground(Color.GRAY);
		lblProcedimiento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblProcedimiento.setBounds(20, 198, 111, 20);
		getContentPane().add(lblProcedimiento);

		btnBuscarProcedimiento = new MiBoton("Buscar");
		btnBuscarProcedimiento.setActionCommand("BuscarProcedimiento");
		btnBuscarProcedimiento.setBounds(784, 193, 100, 30);
		getContentPane().add(btnBuscarProcedimiento);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 225, 874, 300);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(
				new TitledBorder(null, "Familiares del paciente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(614, 5, 270, 170);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 59, 250, 100);
		panel.add(scrollPane_1);

		tableFamiliares = new JTable();
		tableFamiliares.setRowHeight(15);
		tableFamiliares.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tableFamiliares.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(tableFamiliares);

		btnAgregarFamiliar = new MiBoton("Nuevo");
		btnAgregarFamiliar.setText("");
		btnAgregarFamiliar.setActionCommand("AgregarFamiliar");
		btnAgregarFamiliar.setBounds(231, 20, 30, 30);
		panel.add(btnAgregarFamiliar);

		LabelPersonalizado lblValorPagar_1_1 = new LabelPersonalizado(14);
		lblValorPagar_1_1.setText("AGREGAR FAMILIAR");
		lblValorPagar_1_1.setBounds(76, 30, 145, 20);
		panel.add(lblValorPagar_1_1);
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

	public TransaccionPresupuestoControlador getControlador() {
		return controlador;
	}

	public LabelPersonalizado getlFechaPresupuesto() {
		return lFechaPresupuesto;
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

	public CampoNumeroPersonalizado gettDescuento() {
		return tDescuento;
	}

	public LabelPersonalizado getlSumatoria() {
		return lSumatoria;
	}

	public LabelPersonalizado getLblDescuento() {
		return lblDescuento;
	}

	public LabelPersonalizado getlPaciente() {
		return lPaciente;
	}

	public LabelPersonalizado getlPacienteCedula() {
		return lPacienteCedula;
	}

	public LabelPersonalizado getlPacienteConvenio() {
		return lPacienteConvenio;
	}

	public LabelPersonalizado getlPacienteContacto() {
		return lPacienteContacto;
	}

	public JTable getTable() {
		return table;
	}

	public LabelPersonalizado getlProcedimiento() {
		return lProcedimiento;
	}

	public MiBoton getBtnBuscarProcedimiento() {
		return btnBuscarProcedimiento;
	}

	public JTable getTableFamiliares() {
		return tableFamiliares;
	}

	public MiBoton getBtnAgregarFamiliar() {
		return btnAgregarFamiliar;
	}

	public LabelPersonalizado getlValorPagar() {
		return lValorPagar;
	}



}
