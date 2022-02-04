package atelier.vista.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import atelier.controlador.ventana.VentanaMedicamentoController;
import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.TextPrompt;
import atelier.vista.componentes.genericos.VentanaGenerica;

public class VentanaMedicamento extends VentanaGenerica {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1318673527321553149L;
	private CampoTextoPersonalizado tNombreComercial;
	private CampoTextoPersonalizado tTipoMedicamento;
	private JTextArea txtObservacion;
	private VentanaMedicamentoController controller;

	public void setUpController() {

		controller = new VentanaMedicamentoController(this);
	}

	public VentanaMedicamento() {
		getLblNombreFormulario().setText("Medicamento");
		setType(Type.NORMAL);
		getTable().setToolTipText("Click para seleccionar");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.WHITE);
		setTitle("Registro de Medicamentos");

		txtObservacion = new JTextArea();
		txtObservacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dosaje y Drogas",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtObservacion.setLineWrap(true);
		txtObservacion.setWrapStyleWord(true);
		txtObservacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				Character c = evt.getKeyChar();
				if (Character.isLetter(c)) {
					evt.setKeyChar(Character.toUpperCase(c));
					txtObservacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
							"Dosaje y Drogas - " + txtObservacion.getText().length() + "/254", TitledBorder.LEADING,
							TitledBorder.TOP, null, new Color(0, 0, 0)));
				}
				if (txtObservacion.getText().length() > 255) {
					evt.consume();
				}
			}
		});
		txtObservacion.setFont(new Font("Monospaced", Font.PLAIN, 15));
		txtObservacion.setToolTipText("Observaciones relevantes");
		txtObservacion.setBounds(20, 222, 454, 200);
		TextPrompt tOtratos = new TextPrompt("Indique drogas y dosajes", txtObservacion);
		tOtratos.setVerticalAlignment(SwingConstants.TOP);
		tOtratos.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(txtObservacion);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 93, 450, 118);
		getContentPane().add(panel);
		panel.setLayout(null);

		LabelPersonalizado lNombre = new LabelPersonalizado(14);
		lNombre.setText("Nombre Comercial");
		lNombre.setBounds(20, 20, 135, 15);
		panel.add(lNombre);

		tNombreComercial = new CampoTextoPersonalizado();
		tNombreComercial.setBounds(20, 35, 420, 25);
		panel.add(tNombreComercial);

		tNombreComercial.setToolTipText("Nombre del medicamento en las farmacias");
		tNombreComercial.setColumns(10);

		LabelPersonalizado lTipoMedicamento = new LabelPersonalizado(11);
		lTipoMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lTipoMedicamento.setBounds(20, 66, 140, 14);
		panel.add(lTipoMedicamento);
		lTipoMedicamento.setText("Tipo de Medicamento");
		lTipoMedicamento.setHorizontalAlignment(SwingConstants.LEFT);
		tTipoMedicamento = new CampoTextoPersonalizado();
		tTipoMedicamento.setBounds(20, 82, 250, 25);
		panel.add(tTipoMedicamento);
		tTipoMedicamento.setToolTipText("Dosaje del medicamento mencionado");
		tTipoMedicamento.setColumns(10);

	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CampoTextoPersonalizado gettNombreComercial() {
		return tNombreComercial;
	}

	public CampoTextoPersonalizado gettTipoMedicamento() {
		return tTipoMedicamento;
	}

	public JTextArea getTxtObservacion() {
		return txtObservacion;
	}

	public VentanaMedicamentoController getController() {
		return controller;
	}

}
