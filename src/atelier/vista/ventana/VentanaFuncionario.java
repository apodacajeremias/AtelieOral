package atelier.vista.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import atelier.controlador.ventana.VentanaFuncionarioController;
import atelier.vista.componentes.CampoNumeroPersonalizado;
import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.TextPrompt;
import atelier.vista.componentes.genericos.VentanaGenerica;

public class VentanaFuncionario extends VentanaGenerica {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8873459751290418831L;
	private CampoTextoPersonalizado tNombre;
	private CampoTextoPersonalizado tApellido;
	private CampoTextoPersonalizado tDireccion;
	private CampoNumeroPersonalizado tDocumento;
	private CampoTextoPersonalizado tTelefono;
	private JDateChooser fFechaNacimiento;

	private VentanaFuncionarioController controller;

	private CampoTextoPersonalizado tSexo;
	private LabelPersonalizado lblEdad;
	private CampoTextoPersonalizado tUsuario;
	private JPasswordField tContrasena;
	private JRadioButton rdbEstado;
	private JPanel pAccesos;
	@SuppressWarnings("rawtypes")
	private JComboBox spTipoAcceso;
	@SuppressWarnings("rawtypes")
	private JComboBox spTipoFuncionario;

	public void setUpController() {
		controller = new VentanaFuncionarioController(this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public VentanaFuncionario() {
		getLblNombreFormulario().setText("Funcionario");
		setType(Type.NORMAL);
		getTable().setSelectionBackground(SystemColor.textHighlight);
		getTable().setToolTipText("Click para seleccionar");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.WHITE);
		setTitle("Registro de Funcionario");

		pAccesos = new JPanel();
		pAccesos.setBackground(Color.WHITE);
		pAccesos.setBounds(20, 395, 454, 139);
		pAccesos.setBorder(new TitledBorder(null, "Datos para acceder al sistema", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(pAccesos);
		pAccesos.setLayout(null);

		LabelPersonalizado lblUsuario = new LabelPersonalizado(11);
		lblUsuario.setText("Usuario");
		lblUsuario.setBounds(233, 44, 46, 14);
		pAccesos.add(lblUsuario);

		tUsuario = new CampoTextoPersonalizado();
		tUsuario.setBounds(206, 59, 100, 25);
		tUsuario.mayusculas();
		tUsuario.soloLetras();
		pAccesos.add(tUsuario);
		tUsuario.mayusculas();
		tUsuario.soloLetras();
		TextPrompt tpuesto = new TextPrompt("Usuario", tUsuario);
		tpuesto.setHorizontalAlignment(SwingConstants.CENTER);
		tUsuario.setColumns(10);

		LabelPersonalizado lblContraseña = new LabelPersonalizado(11);
		lblContraseña.setText("Contraseña");
		lblContraseña.setBounds(224, 90, 65, 14);
		pAccesos.add(lblContraseña);

		tContrasena = new JPasswordField();
		tContrasena.setBounds(206, 103, 100, 25);
		pAccesos.add(tContrasena);
		tContrasena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tContrasena.setColumns(10);

		spTipoAcceso = new JComboBox();
		spTipoAcceso.setModel(new DefaultComboBoxModel(new String[] { "Operador", "Admin", "Super" }));
		spTipoAcceso.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Perfil",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		spTipoAcceso.setEnabled(false);
		spTipoAcceso.setBounds(329, 44, 100, 40);
		pAccesos.add(spTipoAcceso);
		getContentPane().setLayout(null);
		getContentPane().add(pAccesos);

		rdbEstado = new JRadioButton("Aún en la empresa?");
		rdbEstado.setBounds(23, 103, 160, 25);
		pAccesos.add(rdbEstado);
		rdbEstado.setSelected(true);
		rdbEstado.setFont(new Font("Tahoma", Font.PLAIN, 13));

		spTipoFuncionario = new JComboBox();
		spTipoFuncionario.setModel(new DefaultComboBoxModel(new String[] { "Secretario", "Medico" }));
		spTipoFuncionario.setEnabled(false);
		spTipoFuncionario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cargo",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		spTipoFuncionario.setBounds(23, 44, 160, 40);
		pAccesos.add(spTipoFuncionario);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n personal", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 93, 454, 160);
		getContentPane().add(panel);

		// Para cargar el nombre del funcionario
		LabelPersonalizado lblNombre = new LabelPersonalizado(14);
		lblNombre.setText("Nombres");
		lblNombre.setBounds(20, 20, 63, 14);

		// Apellido del funcionario
		LabelPersonalizado lblApellido = new LabelPersonalizado(14);
		lblApellido.setText("Apellidos");
		lblApellido.setBounds(20, 65, 61, 14);
		tApellido = new CampoTextoPersonalizado();
		tApellido.setBounds(20, 80, 250, 25);
		tApellido.mayusculas();
		tApellido.setToolTipText("Apellido del funcionario");
		tApellido.setColumns(10);
		tNombre = new CampoTextoPersonalizado();
		tNombre.setBounds(20, 34, 250, 25);
		tNombre.soloLetras();
		tNombre.mayusculas();
		tNombre.setToolTipText("Nombre del Funcionario");
		tNombre.setColumns(10);

		// Documento de identidad
		LabelPersonalizado lblDocumento = new LabelPersonalizado(11);
		lblDocumento.setText("Documento Id.");
		lblDocumento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDocumento.setBounds(20, 108, 100, 14);
		tDocumento = new CampoNumeroPersonalizado();
		tDocumento.setBounds(20, 123, 136, 25);
		tDocumento.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tDocumento.setToolTipText("Documento del Funcionario");
		tDocumento.setColumns(10);

		fFechaNacimiento = new JDateChooser();
		fFechaNacimiento.setBounds(305, 80, 136, 25);
		fFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// Fecha de nacimiento
		LabelPersonalizado lblFechaNac = new LabelPersonalizado(11);
		lblFechaNac.setText("Fecha de Nac.");
		lblFechaNac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaNac.setBounds(305, 66, 86, 14);

		LabelPersonalizado lblSexo = new LabelPersonalizado(11);
		lblSexo.setText("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSexo.setBounds(305, 20, 33, 14);

		LabelPersonalizado lEdad = new LabelPersonalizado( 11);
		lEdad.setText("Edad");
		lEdad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lEdad.setBounds(305, 108, 33, 14);

		lblEdad = new LabelPersonalizado(20);
		lblEdad.setText("");
		lblEdad.setHorizontalAlignment(SwingConstants.LEFT);
		lblEdad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEdad.setBounds(305, 123, 46, 25);
		tSexo = new CampoTextoPersonalizado();
		tSexo.setBounds(305, 35, 50, 25);
		tSexo.mayusculas();
		tSexo.soloLetras();
		tSexo.setHorizontalAlignment(SwingConstants.CENTER);
		TextPrompt tsexo = new TextPrompt("H | M", tSexo);
		tsexo.setHorizontalAlignment(SwingConstants.CENTER);

		panel.setLayout(null);
		panel.add(lblNombre);
		panel.add(lblApellido);
		panel.add(tApellido);
		panel.add(tNombre);
		panel.add(lblDocumento);
		panel.add(tDocumento);
		panel.add(fFechaNacimiento);
		panel.add(lblFechaNac);
		panel.add(lblSexo);
		panel.add(lEdad);
		panel.add(lblEdad);
		panel.add(tSexo);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(20, 264, 454, 120);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		// Direccion
		LabelPersonalizado lblDireccion = new LabelPersonalizado( 11);
		lblDireccion.setText("Direccion");
		lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDireccion.setBounds(20, 67, 56, 14);
		panel_1.add(lblDireccion);
		tDireccion = new CampoTextoPersonalizado();
		tDireccion.setBounds(20, 83, 410, 25);
		panel_1.add(tDireccion);
		tDireccion.mayusculas();
		tDireccion.setToolTipText("Dirección del funcionario");
		tDireccion.setColumns(10);

		TextPrompt tdireccion = new TextPrompt("Dirección de domicilio", tDireccion);
		tdireccion.setHorizontalAlignment(SwingConstants.CENTER);

		// Telefono
		LabelPersonalizado lblTelefono = new LabelPersonalizado(11);
		lblTelefono.setText("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(20, 20, 52, 14);
		panel_1.add(lblTelefono);
		tTelefono = new CampoTextoPersonalizado();
		tTelefono.setBounds(20, 36, 200, 25);
		tTelefono.soloNumerosEnteros();
		panel_1.add(tTelefono);
		tTelefono.setToolTipText("Número de telefono");
		tTelefono.setColumns(10);
		TextPrompt ttelefono = new TextPrompt("Teléfono de contacto", tTelefono);
		ttelefono.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CampoTextoPersonalizado gettNombre() {
		return tNombre;
	}

	public CampoTextoPersonalizado gettApellido() {
		return tApellido;
	}

	public CampoTextoPersonalizado gettDireccion() {
		return tDireccion;
	}

	public CampoNumeroPersonalizado gettDocumento() {
		return tDocumento;
	}

	public CampoTextoPersonalizado gettTelefono() {
		return tTelefono;
	}

	public JDateChooser getfFechaNacimiento() {
		return fFechaNacimiento;
	}

	public VentanaFuncionarioController getController() {
		return controller;
	}

	public CampoTextoPersonalizado gettSexo() {
		return tSexo;
	}

	public LabelPersonalizado getLblEdad() {
		return lblEdad;
	}

	public CampoTextoPersonalizado gettUsuario() {
		return tUsuario;
	}

	public JPasswordField gettContrasena() {
		return tContrasena;
	}

	public JRadioButton getRdbEstado() {
		return rdbEstado;
	}

	public JPanel getpAccesos() {
		return pAccesos;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getSpTipoAcceso() {
		return spTipoAcceso;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getSpTipoFuncionario() {
		return spTipoFuncionario;
	}

}
