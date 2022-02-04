package atelier.vista.ventana.principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.dao.ConfiguracionDao;
import atelier.modelo.entidades.dao.PacienteDao;
import atelier.modelo.entidades.dao.UsuarioDao;
import atelier.modelo.sesion.Metodos;
import atelier.vista.componentes.CampoTextoPersonalizado;
import atelier.vista.componentes.LabelPersonalizado;
import atelier.vista.componentes.MiBoton;
import atelier.vista.componentes.PanelFondo;
import atelier.vista.reporte.ReporteAgenda;
import atelier.vista.reporte.ReportePago;
import atelier.vista.reporte.ReportePresupuesto;
import atelier.vista.reporte.ReporteReceta;
import atelier.vista.reporte.ReporteReposo;
import atelier.vista.ventana.VentanaConfiguracion;
import atelier.vista.ventana.VentanaFuncionario;
import atelier.vista.ventana.VentanaMedicamento;
import atelier.vista.ventana.VentanaPacientePerfil;
import atelier.vista.ventana.VentanaProcedimiento;
import atelier.vista.ventana.buscador.BuscadorPaciente;
import atelier.vista.ventana.transacciones.TransaccionAgenda;
import atelier.vista.ventana.transacciones.TransaccionPago;
import atelier.vista.ventana.transacciones.TransaccionPresupuesto;
import atelier.vista.ventana.transacciones.TransaccionRecetaReposo;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4166190286945252641L;
	private LabelPersonalizado lblPODAC;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Atelier Oral - PODAC Sistemas Informaticos");
		// centramos la ventana
		setLocationRelativeTo(this);
		// maximizamos la ventana
		setExtendedState(Frame.MAXIMIZED_BOTH);

		setIconImage(new ImageIcon(getClass().getResource("/img/LOGO PODAC.png")).getImage());

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		/*
		 * JMenu para los procesos
		 */

		/*
		 * Jmenu para las tablas
		 */
		JMenu mnRegistros = new JMenu("REGISTROS");
		mnRegistros.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnRegistros);

		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo funcionario");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaFuncionario ventana = new VentanaFuncionario();
				ventana.setUpController();
				ventana.getController().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
		mnRegistros.add(mntmNewMenuItem);

		JMenuItem mntmNuevoMedicamento = new JMenuItem("Nuevo medicamento");
		mntmNuevoMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMedicamento ventana = new VentanaMedicamento();
				ventana.setUpController();
				ventana.getController().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNuevoMedicamento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK));
		mnRegistros.add(mntmNuevoMedicamento);

		JMenuItem mntmNuevoPaciente = new JMenuItem("Nuevo paciente");
		mntmNuevoPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPacientePerfil ventana = new VentanaPacientePerfil();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNuevoPaciente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mnRegistros.add(mntmNuevoPaciente);

		JMenuItem mntmNuevoProcedimiento = new JMenuItem("Nuevo procedimiento");
		mntmNuevoProcedimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProcedimiento ventana = new VentanaProcedimiento();
				ventana.setUpController();
				ventana.getController().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNuevoProcedimiento.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		mnRegistros.add(mntmNuevoProcedimiento);
		JMenu mnProceso = new JMenu("PROCESOS");
		mnProceso.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnProceso);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Agenda: Registrar");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransaccionAgenda ventana = new TransaccionAgenda();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnProceso.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Pago: Registrar");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransaccionAgenda ventana = new TransaccionAgenda();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_1_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnProceso.add(mntmNewMenuItem_1_1);

		JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("Presupuesto: Registrar");
		mntmNewMenuItem_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransaccionPresupuesto ventana = new TransaccionPresupuesto();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_1_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnProceso.add(mntmNewMenuItem_1_2);

		JMenuItem mntmNewMenuItem_1_3 = new JMenuItem("Receta: Registrar");
		mntmNewMenuItem_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransaccionRecetaReposo ventana = new TransaccionRecetaReposo();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_1_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnProceso.add(mntmNewMenuItem_1_3);

		JMenuItem mntmNewMenuItem_1_4 = new JMenuItem("Reposo: Registrar");
		mntmNewMenuItem_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransaccionRecetaReposo ventana = new TransaccionRecetaReposo();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_1_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnProceso.add(mntmNewMenuItem_1_4);

		JMenu mnNewMenu = new JMenu("REPORTES");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Agendas");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteAgenda ventana = new ReporteAgenda();
				ventana.setUpControlador();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_2
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Pagos");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePago ventana = new ReportePago();
				ventana.setUpControlador();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_2_1
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem_2_1);

		JMenuItem mntmNewMenuItem_2_2 = new JMenuItem("Presupuestos");
		mntmNewMenuItem_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePresupuesto ventana = new ReportePresupuesto();
				ventana.setUpControlador();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_2_2
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem_2_2);

		JMenuItem mntmNewMenuItem_2_3 = new JMenuItem("Recetas");
		mntmNewMenuItem_2_3.setEnabled(false);
		mntmNewMenuItem_2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteReceta ventana = new ReporteReceta();
				ventana.setUpController();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_2_3
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem_2_3);

		JMenuItem mntmNewMenuItem_2_4 = new JMenuItem("Reposos");
		mntmNewMenuItem_2_4.setEnabled(false);
		mntmNewMenuItem_2_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteReposo ventana = new ReporteReposo();
				ventana.setUpController();
				ventana.setVisible(true);
			}
		});
		mntmNewMenuItem_2_4
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem_2_4);
		
		JMenuItem mntmNewMenuItem_2_4_1 = new JMenuItem("Paciente con Deuda (Podría demorar)");
		mntmNewMenuItem_2_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PacienteDao dao = new PacienteDao();
				List<Paciente> pacientes = new ArrayList<Paciente>();
				setCursor(new Cursor(WAIT_CURSOR));
				try {
					pacientes = dao.recuperarTodoOrdenadoPorNombre();
					pacientes = pacientes.stream().filter(p -> p.getDiferencia() > 0).collect(Collectors.toList());
					Metodos.getInstance().imprimirPacienteReporte(pacientes);
					setCursor(new Cursor(DEFAULT_CURSOR));
				} catch (Exception e) {
					e.printStackTrace();
					setCursor(new Cursor(DEFAULT_CURSOR));
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2_4_1);

		JMenu mnUtilidades = new JMenu("UTILIDADES");
		mnUtilidades.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnUtilidades);

		JMenuItem mntmInicializacinDeDatos = new JMenuItem("VACIAR TODO");
		mntmInicializacinDeDatos.setEnabled(false);
		mntmInicializacinDeDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro que deseas inicializar el sistema? ",
						"ATENCION", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						inicializarSistema();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		mnUtilidades.add(mntmInicializacinDeDatos);

		JMenuItem mntmConfiguraciones = new JMenuItem("CONFIGURAR");
		mntmConfiguraciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaConfiguracion ventana = new VentanaConfiguracion();
				ventana.setUpController();
				ventana.setVisible(true);
			}
		});
		mnUtilidades.add(mntmConfiguraciones);

		/*
		 * Se obtiene nustro toolbar para los botones de paciente renta vehiculo y salir
		 */
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);

		// boton para mostrar ventana paciente paciente
		MiBoton mbtnPaciente = new MiBoton("Paciente");
		mbtnPaciente.setText("Registrar paciente");
		mbtnPaciente.setPreferredSize(new Dimension(200, 30));
		mbtnPaciente.setFont(new Font("Tahoma", Font.BOLD, 15));
		mbtnPaciente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaPacientePerfil ventana = new VentanaPacientePerfil();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		toolBar.add(mbtnPaciente);

		// boton que permite salir del sitema
		MiBoton mbtnSalir = new MiBoton("Salir");
		mbtnSalir.setPreferredSize(new Dimension(150, 30));
		mbtnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		mbtnSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		MiBoton mbtnPresupuestar = new MiBoton("Presupuestar");
		mbtnPresupuestar.setPreferredSize(new Dimension(150, 30));
		mbtnPresupuestar.setFont(new Font("Tahoma", Font.BOLD, 15));
		mbtnPresupuestar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TransaccionPresupuesto ventana = new TransaccionPresupuesto();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});

		MiBoton mbtnPaciente_1 = new MiBoton("Paciente");
		mbtnPaciente_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscadorPaciente ventana = new BuscadorPaciente();
				ventana.setUpControlador();
				ventana.setVisible(true);
			}
		});
		mbtnPaciente_1.setText("Ver perfil de paciente");
		mbtnPaciente_1.setPreferredSize(new Dimension(200, 30));
		mbtnPaciente_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		toolBar.add(mbtnPaciente_1);
		toolBar.add(mbtnPresupuestar);

		MiBoton mbtnRecetar = new MiBoton("Recetar");
		mbtnRecetar.setPreferredSize(new Dimension(150, 30));
		mbtnRecetar.setFont(new Font("Tahoma", Font.BOLD, 15));
		mbtnRecetar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TransaccionRecetaReposo ventana = new TransaccionRecetaReposo();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		toolBar.add(mbtnRecetar);

		MiBoton mbtnAgendar = new MiBoton("Agendar");
		mbtnAgendar.setPreferredSize(new Dimension(150, 30));
		mbtnAgendar.setFont(new Font("Tahoma", Font.BOLD, 15));
		mbtnAgendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TransaccionAgenda ventana = new TransaccionAgenda();
				ventana.setUpControlador();
				ventana.getControlador().nuevo();
				ventana.setVisible(true);
			}
		});
		mbtnAgendar.setText("Agendar");
		toolBar.add(mbtnAgendar);

		MiBoton mbtnPagar = new MiBoton("Pagar");
		mbtnPagar.setPreferredSize(new Dimension(150, 30));
		mbtnPagar.setFont(new Font("Tahoma", Font.BOLD, 15));
		mbtnPagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TransaccionPago ventana = new TransaccionPago();
				ventana.setUpController();
				ventana.getController().nuevo();
				ventana.setVisible(true);
			}
		});
		toolBar.add(mbtnPagar);
		toolBar.add(mbtnSalir);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 255));
		toolBar.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		LabelPersonalizado lblProgramadores = new LabelPersonalizado(15);
		lblProgramadores.setText("Sesion de ");
		lblProgramadores.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProgramadores.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblProgramadores, BorderLayout.NORTH);

		lblPODAC = new LabelPersonalizado(15);
		lblPODAC.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblPODAC, BorderLayout.SOUTH);

		PanelFondo panelFondo = new PanelFondo("Fondo.jpg");
		contentPane.add(panelFondo, BorderLayout.CENTER);
		panelFondo.setLayout(null);

		CampoTextoPersonalizado lblVersion = new CampoTextoPersonalizado();
		lblVersion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblVersion.setText("Version " + serialVersionUID);
		lblVersion.setForeground(new Color(0, 0, 0));
		lblVersion.setBounds(10, 563, 200, 20);
		panelFondo.add(lblVersion);
	}

	private void inicializarSistema() {
		PacienteDao pacienteDao = new PacienteDao();
		pacienteDao.vaciarTabla("paciente");
		UsuarioDao funcionarioDao = new UsuarioDao();
		funcionarioDao.vaciarTabla("funcionario");
		ConfiguracionDao configuracionDao = new ConfiguracionDao();
		configuracionDao.vaciarTabla("configuracion");
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public LabelPersonalizado getLblPODAC() {
		return lblPODAC;
	}
	
}
