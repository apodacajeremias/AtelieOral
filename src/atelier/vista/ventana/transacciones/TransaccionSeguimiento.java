package atelier.vista.ventana.transacciones;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import atelier.controlador.transacciones.TransaccionSeguimientoControlador;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;

public class TransaccionSeguimiento extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1397639669876801914L;
	private JTable tablePresupuestoDetalle;
	private JTable tableDientes;
	private LabelPersonalizado lPaciente;
	private JTable tableSeguimiento;
	private MiBoton btnFinalizar;
	private MiBoton btnAgregar;
	private MiBoton btnCancelar;
	
	private TransaccionSeguimientoControlador controlador;
	
	public void setUpControlador() {
		controlador = new TransaccionSeguimientoControlador(this);
	}

	/**
	 * Create the dialog.
	 */
	public TransaccionSeguimiento() {
		setResizable(false);
		setType(Type.POPUP);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(this);
		setModal(true);
		setTitle("Gestión de seguimiento");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 70, 520, 250);
		getContentPane().add(scrollPane_1);
		
		tablePresupuestoDetalle = new JTable();
		tablePresupuestoDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePresupuestoDetalle.setShowVerticalLines(false);
		scrollPane_1.setViewportView(tablePresupuestoDetalle);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(545, 70, 330, 250);
		getContentPane().add(scrollPane_2);
		
		tableDientes = new JTable();
		scrollPane_2.setViewportView(tableDientes);
		
		lPaciente = new LabelPersonalizado(0);
		lPaciente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lPaciente.setText("ENZO JEREMIAS APODACA GONZALEZ");
		lPaciente.setBounds(20, 20, 600, 20);
		getContentPane().add(lPaciente);
		
		LabelPersonalizado lblprsnlzdListaDeProcedimientos = new LabelPersonalizado(0);
		lblprsnlzdListaDeProcedimientos.setText("Lista de procedimientos aprobados");
		lblprsnlzdListaDeProcedimientos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblprsnlzdListaDeProcedimientos.setBounds(20, 51, 287, 20);
		getContentPane().add(lblprsnlzdListaDeProcedimientos);
		
		LabelPersonalizado lblprsnlzdListaDeDientes = new LabelPersonalizado(0);
		lblprsnlzdListaDeDientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblprsnlzdListaDeDientes.setText("Dientes mencionados para tratamiento");
		lblprsnlzdListaDeDientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblprsnlzdListaDeDientes.setBounds(545, 50, 330, 20);
		getContentPane().add(lblprsnlzdListaDeDientes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 367, 855, 193);
		getContentPane().add(scrollPane);
		
		tableSeguimiento = new JTable();
		scrollPane.setViewportView(tableSeguimiento);
		
		btnFinalizar = new MiBoton("Finalizar");
		btnFinalizar.setBounds(773, 331, 100, 30);
		getContentPane().add(btnFinalizar);
		
		btnAgregar = new MiBoton("Nuevo");
		btnAgregar.setText("Agregar");
		btnAgregar.setBounds(551, 331, 100, 30);
		getContentPane().add(btnAgregar);
		
		btnCancelar = new MiBoton("Cancelar");
		btnCancelar.setBounds(662, 331, 100, 30);
		getContentPane().add(btnCancelar);
		
		LabelPersonalizado lblprsnlzdSeguimientoQueSer = new LabelPersonalizado(0);
		lblprsnlzdSeguimientoQueSer.setText("Seguimiento que ser\u00E1 anexado al perfil del paciente");
		lblprsnlzdSeguimientoQueSer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblprsnlzdSeguimientoQueSer.setBounds(20, 341, 520, 20);
		getContentPane().add(lblprsnlzdSeguimientoQueSer);
		
		
	}

	public JTable getTablePresupuestoDetalle() {
		return tablePresupuestoDetalle;
	}

	public JTable getTableDientes() {
		return tableDientes;
	}

	public LabelPersonalizado getlPaciente() {
		return lPaciente;
	}

	public JTable getTableSeguimiento() {
		return tableSeguimiento;
	}

	public MiBoton getBtnFinalizar() {
		return btnFinalizar;
	}

	public MiBoton getBtnAgregar() {
		return btnAgregar;
	}

	public MiBoton getBtnCancelar() {
		return btnCancelar;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TransaccionSeguimientoControlador getControlador() {
		return controlador;
	}
	
}
