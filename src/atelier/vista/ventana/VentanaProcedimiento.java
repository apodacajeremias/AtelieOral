package atelier.vista.ventana;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import atelier.controlador.ventana.VentanaProcedimientoController;
import atelier.vista.componentes.CampoNumeroPersonalizado;
import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.genericos.VentanaGenerica;

public class VentanaProcedimiento extends VentanaGenerica {
	/**
	 * 
	 */

	private static final long serialVersionUID = 2024175632067518345L;
	private CampoTextoPersonalizado tDescripcion;
	private CampoNumeroPersonalizado tValorIndividual;
	private JRadioButton rdbtnEstado;
	private VentanaProcedimientoController controller;
	private CampoNumeroPersonalizado tValorConvenio;
	private CampoNumeroPersonalizado tValorFamiliar;
	private JPanel panel;
	private JPanel panel_1;

	public void setUpController() {

		controller = new VentanaProcedimientoController(this);
	}

	/**
	 * Create the dialog.
	 * 
	 * @param funcionario
	 */
	public VentanaProcedimiento() {
		getLblNombreFormulario().setText("Procedimiento");
		setType(Type.NORMAL);
		setTitle("Registro de Procedimiento");

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n del procedimiento", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 87, 450, 94);
		getContentPane().add(panel);
		panel.setLayout(null);

		// Descripcion del procedimiento
		LabelPersonalizado lblDescripcion = new LabelPersonalizado(15);
		lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcion.setText("Nombre del procedimiento");
		lblDescripcion.setBounds(20, 20, 183, 14);
		panel.add(lblDescripcion);
		lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);

		tDescripcion = new CampoTextoPersonalizado();
		tDescripcion.setBounds(20, 36, 420, 25);
		panel.add(tDescripcion);
		tDescripcion.mayusculas();
		tDescripcion.setToolTipText("Procedimiento");
		tDescripcion.setColumns(10);

		rdbtnEstado = new JRadioButton("\u00BFProcedimiento a\u00FAn vigente?");
		rdbtnEstado.setBounds(20, 63, 170, 20);
		panel.add(rdbtnEstado);
		rdbtnEstado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 10));
		rdbtnEstado.setSelected(true);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Valores del procedimiento", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_1.setBounds(20, 187, 450, 163);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		LabelPersonalizado lblNewLabel = new LabelPersonalizado( 12);
		lblNewLabel.setText("Pacientes individuales");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(147, 20, 129, 15);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		tValorIndividual = new CampoNumeroPersonalizado();
		tValorIndividual.setBounds(147, 37, 150, 25);
		panel_1.add(tValorIndividual);
		tValorIndividual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tValorIndividual.setColumns(10);

		LabelPersonalizado lblPacientesFamiliares = new LabelPersonalizado(12);
		lblPacientesFamiliares.setText("Pacientes Familiares");
		lblPacientesFamiliares.setHorizontalAlignment(SwingConstants.LEFT);
		lblPacientesFamiliares.setBounds(147, 68, 150, 15);
		panel_1.add(lblPacientesFamiliares);
		lblPacientesFamiliares.setFont(new Font("Tahoma", Font.PLAIN, 14));

		tValorFamiliar = new CampoNumeroPersonalizado();
		tValorFamiliar.setBounds(147, 84, 150, 25);
		panel_1.add(tValorFamiliar);
		tValorFamiliar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tValorFamiliar.setColumns(10);

		LabelPersonalizado lblPacientesConvenio = new LabelPersonalizado(12);
		lblPacientesConvenio.setText("Pacientes cooperativa");
		lblPacientesConvenio.setHorizontalAlignment(SwingConstants.LEFT);
		lblPacientesConvenio.setBounds(147, 115, 150, 15);
		panel_1.add(lblPacientesConvenio);
		lblPacientesConvenio.setText("Pacientes convenio");
		lblPacientesConvenio.setFont(new Font("Tahoma", Font.PLAIN, 14));

		tValorConvenio = new CampoNumeroPersonalizado();
		tValorConvenio.setBounds(147, 130, 150, 25);
		panel_1.add(tValorConvenio);
		tValorConvenio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tValorConvenio.setColumns(10);
		RestrictedTextField tVC = new RestrictedTextField(tValorConvenio);
		tVC.setLimit(8);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CampoTextoPersonalizado gettDescripcion() {
		return tDescripcion;
	}

	public CampoNumeroPersonalizado gettValorIndividual() {
		return tValorIndividual;
	}

	public JRadioButton getRdbtnEstado() {
		return rdbtnEstado;
	}

	public VentanaProcedimientoController getController() {
		return controller;
	}

	public CampoNumeroPersonalizado gettValorCooperativa() {
		return tValorConvenio;
	}

	public CampoNumeroPersonalizado gettValorFamiliar() {
		return tValorFamiliar;
	}

	public CampoNumeroPersonalizado gettValorConvenio() {
		return tValorConvenio;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JPanel getPanel_1() {
		return panel_1;
	}

}
