package atelier.vista.ventana;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import atelier.controlador.ventana.VentanaPacientePerfilControlador;
import atelier.vista.componentes.CampoNumeroPersonalizado;
import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;

public class VentanaPacientePerfil extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8968394196315925950L;
	private CampoNumeroPersonalizado tEdad;
	private CampoTextoPersonalizado tNombre;
	private CampoTextoPersonalizado tApellido;
	private CampoNumeroPersonalizado tDocumento;
	private JComboBox<String> cbSexo;
	private CampoTextoPersonalizado tTelefono;
	private JComboBox<String> cbConvenio;
	private JTable tablePresupuesto;
	private JTable tableSeguimiento;
	private JTable tablePago;
	private JComboBox<String> cbPago;
	private JComboBox<String> cbPresupuesto;
	private MiBoton btnGuardar;
	private MiBoton btnNuevoSeguimiento;
	private VentanaPacientePerfilControlador controlador;
	private JDateChooser dateChooser;
	private MiBoton btnNuevoPago;
	private MiBoton btnNuevoPresupuesto;
	private CampoTextoPersonalizado tDireccion;
	private CampoTextoPersonalizado tNroSocio;
	private MiBoton btnCancelar;
	private JScrollPane scrollPane_3;
	private JTextArea txtObservacion;
	private LabelPersonalizado lRegistro_2;
	private LabelPersonalizado lRegistro_3;
	private LabelPersonalizado lRegistro_1;
	private MiBoton btnNuevaReceta;
	private JTable tableRecetas;

	public void setUpControlador() {
		controlador = new VentanaPacientePerfilControlador(this);
	}

	/**
	 * Create the dialog.
	 */
	public VentanaPacientePerfil() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setBackground(new Color(51, 51, 51));
		setBounds(100, 100, 1280, 640);
		setLocationRelativeTo(this);
		setResizable(false);
		setModal(true);
		setTitle("Perfil del paciente");
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n personal", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 340, 343);
		getContentPane().add(panel);

		LabelPersonalizado lNombre = new LabelPersonalizado(18);
		lNombre.setText("Nombres");
		lNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lNombre.setBounds(21, 20, 92, 15);
		panel.add(lNombre);

		LabelPersonalizado lApellido = new LabelPersonalizado(18);
		lApellido.setText("Apellidos");
		lApellido.setHorizontalAlignment(SwingConstants.LEFT);
		lApellido.setForeground(Color.BLACK);
		lApellido.setBackground(Color.ORANGE);
		lApellido.setBounds(21, 71, 119, 15);
		panel.add(lApellido);

		tApellido = new CampoTextoPersonalizado();
		tApellido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tApellido.setBounds(new Rectangle(0, 0, 200, 30));
		tApellido.setToolTipText("Apellido del cliente");
		tApellido.setColumns(10);
		tApellido.setBounds(20, 87, 300, 25);
		panel.add(tApellido);

		tNombre = new CampoTextoPersonalizado();
		tNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tNombre.setToolTipText("Nombre del Paciente");
		tNombre.setColumns(10);
		tNombre.setBounds(20, 38, 300, 25);
		panel.add(tNombre);

		LabelPersonalizado lDocumento = new LabelPersonalizado(18);
		lDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lDocumento.setText("Documento Id.");
		lDocumento.setHorizontalAlignment(SwingConstants.LEFT);
		lDocumento.setBounds(21, 120, 119, 14);
		panel.add(lDocumento);

		LabelPersonalizado lFechaNac = new LabelPersonalizado(18);
		lFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lFechaNac.setText("Fecha de Nac.");
		lFechaNac.setHorizontalAlignment(SwingConstants.LEFT);
		lFechaNac.setBounds(21, 175, 137, 14);
		panel.add(lFechaNac);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("d/M/yyyy");
		dateChooser.setBounds(21, 197, 140, 25);
		panel.add(dateChooser);

		tDocumento = new CampoNumeroPersonalizado();
		tDocumento.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tDocumento.setBounds(new Rectangle(0, 0, 200, 30));
		tDocumento.setToolTipText("Documento del Paciente");
		tDocumento.setColumns(10);
		tDocumento.setBounds(22, 142, 140, 25);
		panel.add(tDocumento);

		LabelPersonalizado lSexo = new LabelPersonalizado(18);
		lSexo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lSexo.setText("Sexo");
		lSexo.setHorizontalAlignment(SwingConstants.LEFT);
		lSexo.setBounds(170, 120, 50, 14);
		panel.add(lSexo);

		cbSexo = new JComboBox<String>();
		cbSexo.setActionCommand("");
		cbSexo.setModel(new DefaultComboBoxModel<String>(new String[] { " ", "Hombre", "Mujer" }));
		cbSexo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbSexo.setBounds(170, 142, 150, 25);
		panel.add(cbSexo);

		LabelPersonalizado lEdad = new LabelPersonalizado(18);
		lEdad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lEdad.setText("Edad");
		lEdad.setHorizontalAlignment(SwingConstants.LEFT);
		lEdad.setBounds(170, 175, 74, 14);
		panel.add(lEdad);

		tEdad = new CampoNumeroPersonalizado();
		tEdad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tEdad.setToolTipText("Documento del Paciente");
		tEdad.setColumns(10);
		tEdad.setBounds(new Rectangle(0, 0, 200, 30));
		tEdad.setBounds(170, 197, 100, 25);
		panel.add(tEdad);

		LabelPersonalizado lTelefono = new LabelPersonalizado(18);
		lTelefono.setBounds(20, 230, 121, 14);
		panel.add(lTelefono);
		lTelefono.setText("Telefono");
		lTelefono.setHorizontalAlignment(SwingConstants.LEFT);
		lTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));

		tTelefono = new CampoTextoPersonalizado();
		tTelefono.setBounds(20, 252, 200, 25);
		panel.add(tTelefono);
		tTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tTelefono.setToolTipText("N\u00FAmero de telefono");
		tTelefono.setColumns(10);

		LabelPersonalizado lDireccion = new LabelPersonalizado(18);
		lDireccion.setBounds(20, 285, 121, 14);
		panel.add(lDireccion);
		lDireccion.setText("Direccion");
		lDireccion.setHorizontalAlignment(SwingConstants.LEFT);
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 18));

		tDireccion = new CampoTextoPersonalizado();
		tDireccion.setBounds(20, 307, 300, 25);
		panel.add(tDireccion);
		tDireccion.setToolTipText("Direcci\u00F3n del cliente");
		tDireccion.setColumns(10);

		JPanel panelSocio = new JPanel();
		panelSocio.setLayout(null);
		panelSocio.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Convenios y Descuentos",

				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSocio.setBackground(Color.WHITE);
		panelSocio.setBounds(10, 365, 340, 75);
		getContentPane().add(panelSocio);

		tNroSocio = new CampoTextoPersonalizado();
		tNroSocio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tNroSocio.setPreferredSize(new Dimension(10, 25));
		tNroSocio.setColumns(10);
		tNroSocio.setBounds(190, 39, 140, 25);
		panelSocio.add(tNroSocio);

		cbConvenio = new JComboBox<String>();
		cbConvenio.setActionCommand("");
		cbConvenio.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Cooperativa", "Mapogos" }));
		cbConvenio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbConvenio.setBounds(20, 39, 140, 25);
		panelSocio.add(cbConvenio);

		LabelPersonalizado lblprsnlzdNmeroDeSoco = new LabelPersonalizado(18);
		lblprsnlzdNmeroDeSoco.setText("Nro. Socio");
		lblprsnlzdNmeroDeSoco.setHorizontalAlignment(SwingConstants.LEFT);
		lblprsnlzdNmeroDeSoco.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblprsnlzdNmeroDeSoco.setBounds(190, 20, 81, 14);
		panelSocio.add(lblprsnlzdNmeroDeSoco);

		LabelPersonalizado lblprsnlzdIndique = new LabelPersonalizado(18);
		lblprsnlzdIndique.setText("Indique");
		lblprsnlzdIndique.setHorizontalAlignment(SwingConstants.LEFT);
		lblprsnlzdIndique.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblprsnlzdIndique.setBounds(20, 20, 81, 14);
		panelSocio.add(lblprsnlzdIndique);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(814, 54, 450, 245);
		getContentPane().add(scrollPane);

		tablePresupuesto = new JTable();
		tablePresupuesto.setCellSelectionEnabled(true);
		tablePresupuesto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePresupuesto.setShowVerticalLines(false);
		scrollPane.setViewportView(tablePresupuesto);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(360, 54, 440, 245);
		getContentPane().add(scrollPane_1);

		tableSeguimiento = new JTable();
		tableSeguimiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableSeguimiento.setShowVerticalLines(false);
		scrollPane_1.setViewportView(tableSeguimiento);

		LabelPersonalizado lblprsnlzdSeguimientoDelTratamiento = new LabelPersonalizado(18);
		lblprsnlzdSeguimientoDelTratamiento.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblprsnlzdSeguimientoDelTratamiento.setText("Seguimiento del tratamiento");
		lblprsnlzdSeguimientoDelTratamiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblprsnlzdSeguimientoDelTratamiento.setBounds(360, 23, 257, 25);
		getContentPane().add(lblprsnlzdSeguimientoDelTratamiento);

		btnNuevoSeguimiento = new MiBoton("Nuevo");
		btnNuevoSeguimiento.setActionCommand("NuevoSeguimiento");
		btnNuevoSeguimiento.setText("Nuevo registro");
		btnNuevoSeguimiento.setBounds(670, 23, 130, 25);
		getContentPane().add(btnNuevoSeguimiento);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(814, 340, 450, 253);
		getContentPane().add(scrollPane_2);

		tablePago = new JTable();
		tablePago.setShowVerticalLines(false);
		tablePago.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(tablePago);

		LabelPersonalizado lblprsnlzdPresupuestos = new LabelPersonalizado(18);
		lblprsnlzdPresupuestos.setText("Lista de presupuestos");
		lblprsnlzdPresupuestos.setHorizontalAlignment(SwingConstants.LEFT);
		lblprsnlzdPresupuestos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblprsnlzdPresupuestos.setBounds(814, 23, 172, 25);
		getContentPane().add(lblprsnlzdPresupuestos);

		LabelPersonalizado lblprsnlzdListaDePagos = new LabelPersonalizado(18);
		lblprsnlzdListaDePagos.setText("Lista de pagos");
		lblprsnlzdListaDePagos.setHorizontalAlignment(SwingConstants.LEFT);
		lblprsnlzdListaDePagos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblprsnlzdListaDePagos.setBounds(814, 311, 117, 25);
		getContentPane().add(lblprsnlzdListaDePagos);

		cbPresupuesto = new JComboBox<String>();
		cbPresupuesto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbPresupuesto.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Todos", "Aprobado", "Pendiente", "Rechazado" }));
		cbPresupuesto.setBounds(1154, 23, 100, 25);
		getContentPane().add(cbPresupuesto);

		cbPago = new JComboBox<String>();
		cbPago.setModel(new DefaultComboBoxModel<String>(new String[] { "Todos", "Vigente", "Anulado" }));
		cbPago.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbPago.setBounds(1154, 310, 100, 25);
		getContentPane().add(cbPago);

		btnNuevoPresupuesto = new MiBoton("Nuevo");
		btnNuevoPresupuesto.setText("Nuevo registro");
		btnNuevoPresupuesto.setActionCommand("NuevoPresupuesto");
		btnNuevoPresupuesto.setBounds(1014, 23, 130, 25);
		getContentPane().add(btnNuevoPresupuesto);

		btnNuevoPago = new MiBoton("Nuevo");
		btnNuevoPago.setText("Nuevo registro");
		btnNuevoPago.setActionCommand("NuevoPago");
		btnNuevoPago.setBounds(1014, 310, 130, 25);
		getContentPane().add(btnNuevoPago);

		btnGuardar = new MiBoton("Guardar");
		btnGuardar.setBounds(10, 563, 100, 30);
		getContentPane().add(btnGuardar);
		btnGuardar.setText("Guardar");

		btnCancelar = new MiBoton("Cancelar");
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(120, 563, 100, 30);
		getContentPane().add(btnCancelar);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 451, 340, 50);
		getContentPane().add(scrollPane_3);

		txtObservacion = new JTextArea();
		txtObservacion.setLineWrap(true);
		txtObservacion.setWrapStyleWord(true);
		scrollPane_3.setViewportView(txtObservacion);
		txtObservacion.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lRegistro_1 = new LabelPersonalizado(0);
		lRegistro_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lRegistro_1.setBounds(10, 507, 340, 15);
		getContentPane().add(lRegistro_1);

		lRegistro_2 = new LabelPersonalizado(0);
		lRegistro_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lRegistro_2.setBounds(10, 533, 150, 15);
		getContentPane().add(lRegistro_2);

		lRegistro_3 = new LabelPersonalizado(0);
		lRegistro_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lRegistro_3.setBounds(250, 533, 100, 15);
		getContentPane().add(lRegistro_3);
		
		LabelPersonalizado lblprsnlzdRecetasYReposos = new LabelPersonalizado(18);
		lblprsnlzdRecetasYReposos.setText("Recetas y Reposos");
		lblprsnlzdRecetasYReposos.setHorizontalAlignment(SwingConstants.LEFT);
		lblprsnlzdRecetasYReposos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblprsnlzdRecetasYReposos.setBounds(360, 311, 257, 25);
		getContentPane().add(lblprsnlzdRecetasYReposos);
		
		btnNuevaReceta = new MiBoton("Nuevo");
		btnNuevaReceta.setText("Nuevo registro");
		btnNuevaReceta.setActionCommand("NuevaReceta");
		btnNuevaReceta.setBounds(670, 311, 130, 25);
		getContentPane().add(btnNuevaReceta);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(360, 340, 440, 253);
		getContentPane().add(scrollPane_4);
		
		tableRecetas = new JTable();
		scrollPane_4.setViewportView(tableRecetas);
		setType(Type.NORMAL);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CampoNumeroPersonalizado gettEdad() {
		return tEdad;
	}

	public CampoTextoPersonalizado gettNombre() {
		return tNombre;
	}

	public CampoTextoPersonalizado gettApellido() {
		return tApellido;
	}

	public CampoNumeroPersonalizado gettDocumento() {
		return tDocumento;
	}

	public JComboBox<String> getCbSexo() {
		return cbSexo;
	}

	public CampoTextoPersonalizado gettTelefono() {
		return tTelefono;
	}

	public JComboBox<String> getCbConvenio() {
		return cbConvenio;
	}

	public JTable getTablePresupuesto() {
		return tablePresupuesto;
	}

	public JTable getTableSeguimiento() {
		return tableSeguimiento;
	}

	public JTable getTablePago() {
		return tablePago;
	}

	public JComboBox<String> getCbPago() {
		return cbPago;
	}

	public JComboBox<String> getCbPresupuesto() {
		return cbPresupuesto;
	}

	public MiBoton getBtnGuardar() {
		return btnGuardar;
	}

	public MiBoton getBtnNuevoSeguimiento() {
		return btnNuevoSeguimiento;
	}

	public VentanaPacientePerfilControlador getControlador() {
		return controlador;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public MiBoton getBtnNuevoPago() {
		return btnNuevoPago;
	}

	public MiBoton getBtnNuevoPresupuesto() {
		return btnNuevoPresupuesto;
	}

	public CampoTextoPersonalizado gettDireccion() {
		return tDireccion;
	}

	public CampoTextoPersonalizado gettNroSocio() {
		return tNroSocio;
	}

	public MiBoton getBtnCancelar() {
		return btnCancelar;
	}

	public JScrollPane getScrollPane_3() {
		return scrollPane_3;
	}

	public JTextArea getTxtObservacion() {
		return txtObservacion;
	}

	public LabelPersonalizado getlRegistro_2() {
		return lRegistro_2;
	}

	public LabelPersonalizado getlRegistro_3() {
		return lRegistro_3;
	}

	public LabelPersonalizado getlRegistro_1() {
		return lRegistro_1;
	}

	public MiBoton getBtnNuevaReceta() {
		return btnNuevaReceta;
	}

	public JTable getTableRecetas() {
		return tableRecetas;
	}
	
}
