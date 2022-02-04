package atelier.controlador.ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.Pago;
import atelier.modelo.entidades.Presupuesto;
import atelier.modelo.entidades.Recetario;
import atelier.modelo.entidades.Seguimiento;
import atelier.modelo.entidades.dao.PacienteDao;
import atelier.modelo.entidades.dao.PresupuestoDao;
import atelier.modelo.entidades.interfaces.PacienteInterface;
import atelier.modelo.sesion.Metodos;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaPago;
import atelier.vista.modelotabla.ModeloTablaPresupuesto;
import atelier.vista.modelotabla.ModeloTablaRecetario;
import atelier.vista.modelotabla.ModeloTablaSeguimiento;
import atelier.vista.ventana.VentanaPacientePerfil;
import atelier.vista.ventana.transacciones.TransaccionPago;
import atelier.vista.ventana.transacciones.TransaccionPresupuesto;
import atelier.vista.ventana.transacciones.TransaccionRecetaReposo;
import atelier.vista.ventana.transacciones.TransaccionSeguimiento;

public class VentanaPacientePerfilControlador implements ActionListener, MouseListener, PacienteInterface {
	private VentanaPacientePerfil ventana;
	private String accion;
	private PacienteDao dao;

	private Paciente paciente;

	private Pago pago;
	private List<Pago> pagos = new ArrayList<Pago>();
	private ModeloTablaPago modeloTablaPago;

	private Presupuesto presupuesto;
	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	private ModeloTablaPresupuesto modeloTablaPresupuesto;

	private Seguimiento seguimiento;
	private List<Seguimiento> seguimientos = new ArrayList<Seguimiento>();
	private ModeloTablaSeguimiento modeloTablaSeguimiento;

	private Recetario receta;
	private List<Recetario> recetas = new ArrayList<Recetario>();
	private ModeloTablaRecetario modeloTablaRecetario;

	public VentanaPacientePerfilControlador(VentanaPacientePerfil ventana) {
		this.ventana = ventana;

		modeloTablaPago = new ModeloTablaPago();
		ventana.getTablePago().setModel(modeloTablaPago);
		tableMenuPago(ventana.getTablePago());

		modeloTablaPresupuesto = new ModeloTablaPresupuesto();
		ventana.getTablePresupuesto().setModel(modeloTablaPresupuesto);
		tableMenuPresupuesto(ventana.getTablePresupuesto());

		modeloTablaSeguimiento = new ModeloTablaSeguimiento();
		ventana.getTableSeguimiento().setModel(modeloTablaSeguimiento);

		modeloTablaRecetario = new ModeloTablaRecetario();
		ventana.getTableRecetas().setModel(modeloTablaRecetario);
		tableMenuReceta(ventana.getTableRecetas());

		ventana.getBtnGuardar().addActionListener(this);
		ventana.getBtnNuevoSeguimiento().addActionListener(this);
		ventana.getBtnNuevoPresupuesto().addActionListener(this);
		ventana.getBtnNuevoPago().addActionListener(this);
		ventana.getBtnNuevaReceta().addActionListener(this);
		ventana.getCbPago().addActionListener(this);
		ventana.getCbPresupuesto().addActionListener(this);

		ventana.getTablePresupuesto().addMouseListener(this);
		ventana.getTablePago().addMouseListener(this);
		ventana.getTableSeguimiento().addMouseListener(this);

		estadoInicial();
		dao = new PacienteDao();
	}

	// Todos deshabilitados
	private void estadoInicial() {
		EventosUtil.limpiarCampoPersonalizado(ventana.gettApellido());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettDireccion());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettDocumento());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettEdad());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettNombre());
		EventosUtil.limpiarCampoPersonalizado(ventana.getTxtObservacion());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettTelefono());
		EventosUtil.limpiarCampoPersonalizado(ventana.getCbConvenio());
		EventosUtil.limpiarCampoPersonalizado(ventana.getCbPago());
		EventosUtil.limpiarCampoPersonalizado(ventana.getCbPresupuesto());
		EventosUtil.limpiarCampoPersonalizado(ventana.getCbSexo());
		EventosUtil.limpiarCampoPersonalizado(ventana.getDateChooser());

		EventosUtil.limpiarCampoPersonalizado(ventana.getlRegistro_1());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlRegistro_2());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlRegistro_3());
	}

	private void vaciarTablas() {
		pagos = new ArrayList<>();
		modeloTablaPago.setLista(pagos);
		modeloTablaPago.fireTableDataChanged();

		presupuestos = new ArrayList<>();
		modeloTablaPresupuesto.setLista(presupuestos);
		modeloTablaPresupuesto.fireTableDataChanged();

		seguimientos = new ArrayList<>();
		modeloTablaSeguimiento.setSeguimiento(seguimientos);
		modeloTablaSeguimiento.fireTableDataChanged();
	}

	private void seleccionarPago(int posicion) {
		if (posicion < 0) {
			return;
		}
		pago = pagos.get(posicion);
		System.out.println(pago);
	}

	private void seleccionarPresupuesto(int posicion) {
		if (posicion < 0) {
			return;
		}
		presupuesto = presupuestos.get(posicion);
		System.out.println(presupuesto);
	}

	private void seleccionarSeguimiento(int posicion) {
		if (posicion < 0) {
			return;
		}
		seguimiento = seguimientos.get(posicion);
		System.out.println(seguimiento);
	}

	private void seleccionarReceta(int posicion) {
		if (posicion < 0) {
			return;
		}
		receta = recetas.get(posicion);
		System.out.println(receta);
	}

	private void buscarPresupuestos(Paciente paciente) {
		presupuestos = new ArrayList<Presupuesto>();
		presupuestos.addAll(dao.recuperarPresupuestoPorPaciente(paciente.getId()));
		presupuestos.addAll(dao.recuperarPresupuestoPorFamiliar(paciente.getId()));
		modeloTablaPresupuesto.setLista(presupuestos);
		modeloTablaPresupuesto.fireTableDataChanged();
	}

	private void buscarPagos(Paciente paciente) {
		pagos = new ArrayList<Pago>();
		pagos = dao.recuperarPagoPorPaciente(paciente.getId());
		modeloTablaPago.setLista(pagos);
		modeloTablaPago.fireTableDataChanged();
	}

	private void buscarSeguimiento(Paciente paciente) {
		seguimientos = new ArrayList<>();
		seguimientos = dao.recuperarSeguimientoPorPaciente(paciente.getId());
		modeloTablaSeguimiento.setSeguimiento(seguimientos);
		modeloTablaSeguimiento.fireTableDataChanged();
	}

	private void buscarRecetas(Paciente paciente) {
		recetas = new ArrayList<>();
		recetas = dao.recuperarRecetaPorPaciente(paciente.getId());
		modeloTablaRecetario.setLista(recetas);
		modeloTablaRecetario.fireTableDataChanged();
	}

	private void filtrarPresupuesto() {
		List<Presupuesto> lista = presupuestos;
		switch (ventana.getCbPresupuesto().getSelectedItem().toString().toUpperCase()) {
		case "PENDIENTE":
			lista = lista.stream().filter(x -> x.getEstado().equalsIgnoreCase("PENDIENTE"))
					.collect(Collectors.toList());
			modeloTablaPresupuesto.setLista(lista);
			modeloTablaPresupuesto.fireTableDataChanged();
			break;
		case "APROBADO":
			lista = lista.stream().filter(x -> x.getEstado().equalsIgnoreCase("APROBADO")).collect(Collectors.toList());
			modeloTablaPresupuesto.setLista(lista);
			modeloTablaPresupuesto.fireTableDataChanged();
			break;
		case "RECHAZADO":
			lista = lista.stream().filter(x -> x.getEstado().equalsIgnoreCase("RECHAZADO"))
					.collect(Collectors.toList());
			modeloTablaPresupuesto.setLista(lista);
			modeloTablaPresupuesto.fireTableDataChanged();
			break;
		default:
			buscarPresupuestos(paciente);
			break;
		}
	}

	private void filtrarPagos() {
		List<Pago> lista = pagos;
		switch (ventana.getCbPago().getSelectedItem().toString().toUpperCase()) {
		case "VIGENTE":
			lista = lista.stream().filter(x -> x.isEstadoPago() == true).collect(Collectors.toList());
			modeloTablaPago.setLista(lista);
			modeloTablaPago.fireTableDataChanged();
			break;
		case "ANULADO":
			lista = lista.stream().filter(x -> x.isEstadoPago() == false).collect(Collectors.toList());
			modeloTablaPago.setLista(lista);
			modeloTablaPago.fireTableDataChanged();
			break;
		default:
			buscarPagos(paciente);
			break;
		}
	}

	private boolean validarFormulario() {
		if (ventana.gettNombre().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "El nombre es obligatorio", "ERROR", JOptionPane.ERROR_MESSAGE);
			ventana.gettNombre().requestFocus();
			return false;
		}
		if (ventana.gettApellido().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "El apellido es obligatorio", "ERROR", JOptionPane.ERROR_MESSAGE);
			ventana.gettApellido().requestFocus();
			return false;
		}
		if (ventana.gettDocumento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(ventana, "El documento es obligatorio", "ERROR", JOptionPane.ERROR_MESSAGE);
			ventana.gettDocumento().requestFocus();
			return false;
		}
		if (ventana.getDateChooser().getDate() == null || ventana.getDateChooser().getDate().after(new Date())) {
			JOptionPane.showMessageDialog(ventana, "La fecha de nacimiento es una información importante", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			ventana.getDateChooser().requestFocus();
			return false;
		}

		if (ventana.getCbSexo().getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(ventana, "¿Cuál es el género del paciente", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			ventana.getCbSexo().requestFocus();
			return false;

		}
		if (ventana.gettDireccion().getText().isEmpty() || ventana.gettDireccion().getText().equals("\\s")) {
			JOptionPane.showMessageDialog(ventana, "La direccion es obligatoria", "ERROR", JOptionPane.ERROR_MESSAGE);
			ventana.gettDireccion().requestFocus();
			return false;
		}
		if (ventana.gettTelefono().getText().isEmpty() || ventana.gettTelefono().getText().equals("\\s")) {
			JOptionPane.showMessageDialog(ventana, "El telefono de contacto es obligatorio", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			ventana.gettTelefono().requestFocus();
			return false;
		}
		if (ventana.getCbConvenio().getSelectedIndex() > 0) {
			if (ventana.getCbConvenio().getSelectedIndex() == 1 && ventana.gettNroSocio().getText().equals("\\s")) {
				return false;
			}
		}
		if (ventana.gettDocumento().getValue() <= 0 || ventana.gettDocumento().getText().equals("\\s")) {
			JOptionPane.showMessageDialog(ventana, "El número del documento no parece ser correcto.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			ventana.gettDocumento().requestFocus();
			return false;
		}
		if (ventana.gettTelefono().getText().equals("\\s") || ventana.gettTelefono().getText().equals("\\[^0+]")) {
			JOptionPane.showMessageDialog(null, "El valor ingresado en teléfono no es valido", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			ventana.gettTelefono().requestFocus();
			return false;
		}
		return true;
	}

	private void abrirTransaccionPresupuesto() {
		TransaccionPresupuesto ventana = new TransaccionPresupuesto();
		ventana.setUpControlador();
		ventana.getControlador().nuevo();
		ventana.getControlador().setPaciente(paciente);
		ventana.setVisible(true);
	}

	private void visualizarTransaccionPresupuesto(Presupuesto p) {
		TransaccionPresupuesto ventana = new TransaccionPresupuesto();
		ventana.setUpControlador();
		ventana.getControlador().nuevo();
		ventana.getControlador().setPresupuesto(p);
		ventana.setVisible(true);
	}

	private void abrirTransaccionPago() {
		TransaccionPago ventana = new TransaccionPago();
		ventana.setUpController();
		ventana.getController().setPaciente(paciente);
		ventana.setVisible(true);
	}

	private void abrirTransaccionSeguimiento() {
		TransaccionSeguimiento ventana = new TransaccionSeguimiento();
		ventana.setUpControlador();
		ventana.getControlador().setPaciente(paciente);
		ventana.getControlador().setListaPresupuesto(presupuestos);
		ventana.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				filtrarListas();

			}

			@Override
			public void windowClosed(WindowEvent e) {
				filtrarListas();

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
		ventana.setVisible(true);
	}

	private void abrirTransaccionReceta() {
		TransaccionRecetaReposo ventana = new TransaccionRecetaReposo();
		ventana.setUpControlador();
		ventana.getControlador().nuevo();
		ventana.getControlador().setPaciente(paciente);
		ventana.setVisible(true);
	}

//	private void abrirTransaccionReceta() {
//		TransaccionRecetario ventana = new TransaccionRecetario();
//		ventana.setUpController();
//		ventana.getController().nuevo();
//		ventana.getController().setPaciente(paciente);
//		ventana.setVisible(true);
//	}
//
//	private void abrirVentanaReposo() {
//		TransaccionReposo ventana = new TransaccionReposo();
//		ventana.setUpController();
//		ventana.getController().nuevo();
//		ventana.getController().setPaciente(paciente);
//		ventana.setVisible(true);
//	}

	private void filtrarListas() {
		filtrarPagos();
		filtrarPresupuesto();
		buscarSeguimiento(paciente);
		buscarRecetas(paciente);
	}

	public void nuevo() {
		accion = "NUEVO";
		vaciarTablas();
		estadoInicial();
		ventana.gettNombre().requestFocus();
		ventana.getBtnNuevaReceta().setEnabled(false);
		ventana.getBtnNuevoPago().setEnabled(false);
		ventana.getBtnNuevoPresupuesto().setEnabled(false);
		ventana.getBtnNuevoSeguimiento().setEnabled(false);
	}

	public void modificar() {
		accion = "NUEVO";
		vaciarTablas();
		estadoInicial();
		ventana.gettNombre().requestFocus();
		ventana.getBtnNuevaReceta().setEnabled(true);
		ventana.getBtnNuevoPago().setEnabled(true);
		ventana.getBtnNuevoPresupuesto().setEnabled(true);
		ventana.getBtnNuevoSeguimiento().setEnabled(true);
	}

	public void guardar() {
		// Si la validacion no es confirmada entonces la accion guardar no se ejecuta
		if (!validarFormulario()) {
			return;
		}
		// SE INSTANCIA UN NUEVO CLIENTE
		if (accion.equals("NUEVO")) {
			paciente = new Paciente();
			paciente.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		}

		paciente.setApellido(ventana.gettApellido().getText());
		paciente.setConvenio(ventana.getCbConvenio().getSelectedItem().toString());
		paciente.setDireccion(ventana.gettDireccion().getText());
		paciente.setDocIdentidad(ventana.gettDocumento().getText());
		paciente.setEdad(EventosUtil.calcularEdad(ventana.getDateChooser().getDate()));
		paciente.setEsSocio(ventana.getCbConvenio().getSelectedIndex() <= 0 ? false : true);
		paciente.setFechaNac(ventana.getDateChooser().getDate());
		paciente.setNombre(ventana.gettNombre().getText().toUpperCase());
		paciente.setNumeroSocio(ventana.gettNroSocio().getText());
		paciente.setObservacion(ventana.getTxtObservacion().getText());
		paciente.setSexo(ventana.getCbSexo().getSelectedItem().toString().substring(0, 1));
		paciente.setTelefono(ventana.gettTelefono().getText());

		// SE INTENTA INSERTAR EL REGISTRO
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(paciente);
				dao.commit();
				estadoInicial();
				vaciarTablas();
				ventana.getBtnNuevoPago().setEnabled(false);
				ventana.getBtnNuevoPresupuesto().setEnabled(false);
				ventana.getBtnNuevoSeguimiento().setEnabled(false);
				ventana.getlRegistro_1().setText("Registro guardado con éxito");
			} else {
				dao.modificar(paciente);
				dao.commit();
				ventana.getlRegistro_1().setText("Registro actualizado con éxito");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El documento no pueden ser repetitivo");
			dao.rollBack();
			return;
		}

	}

	public void cancelar() {
		nuevo();
	}

	// EVENTO DEL MOUSE
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == ventana.getTablePago()) {
			seleccionarPago(ventana.getTablePago().getSelectedRow());
		}

		if (e.getSource() == ventana.getTableSeguimiento()) {
			seleccionarSeguimiento(ventana.getTablePago().getSelectedRow());
		}

		if (e.getSource() == ventana.getTablePresupuesto()) {
			seleccionarPresupuesto(ventana.getTablePresupuesto().getSelectedRow());
		}
		if (e.getSource() == ventana.getTableRecetas()) {
			seleccionarReceta(ventana.getTableRecetas().getSelectedRow());
		}
		if (e.getSource() == ventana.getTablePresupuesto() && e.getClickCount() == 2) {
			visualizarTransaccionPresupuesto(presupuesto);
		}
	}

	// EVENTOS DEL RATON O MOUSEE
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == ventana.getTablePago()) {
			seleccionarPago(ventana.getTablePago().getSelectedRow());
		}

		if (e.getSource() == ventana.getTableSeguimiento()) {
			seleccionarSeguimiento(ventana.getTablePago().getSelectedRow());
		}

		if (e.getSource() == ventana.getTablePresupuesto()) {
			seleccionarPresupuesto(ventana.getTablePresupuesto().getSelectedRow());
		}
		if (e.getSource() == ventana.getTableRecetas()) {
			seleccionarReceta(ventana.getTableRecetas().getSelectedRow());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == ventana.getTablePago()) {
			seleccionarPago(ventana.getTablePago().getSelectedRow());
		}

		if (e.getSource() == ventana.getTableSeguimiento()) {
			seleccionarSeguimiento(ventana.getTablePago().getSelectedRow());
		}

		if (e.getSource() == ventana.getTablePresupuesto()) {
			seleccionarPresupuesto(ventana.getTablePresupuesto().getSelectedRow());
		}
		if (e.getSource() == ventana.getTableRecetas()) {
			seleccionarReceta(ventana.getTableRecetas().getSelectedRow());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "comboBoxChanged":
			if (paciente != null) {
				filtrarListas();
			}
			break;
		case "Guardar":
			guardar();
			break;
		case "Cancelar":
			cancelar();
			break;
		case "NuevoSeguimiento":
			abrirTransaccionSeguimiento();
			break;
		case "NuevoPago":
			abrirTransaccionPago();
			break;
		case "NuevoPresupuesto":
			abrirTransaccionPresupuesto();
			break;
		case "NuevaReceta":
			abrirTransaccionReceta();
			break;
		default:
			break;
		}

	}

	@Override
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		if (paciente == null) {
			return;
		}
		ventana.gettApellido().setText(paciente.getApellido());
		ventana.gettDireccion().setText(paciente.getDireccion());
		ventana.gettDocumento().setText(paciente.getDocIdentidad());
		ventana.gettEdad().setValue(Double.parseDouble(paciente.getEdad()));
		ventana.gettNombre().setText(paciente.getNombre());
		ventana.gettNroSocio().setText(paciente.getNumeroSocio());
		ventana.getTxtObservacion().setText(paciente.getObservacion());
		ventana.gettTelefono().setText(paciente.getTelefono());
		ventana.getCbConvenio().setSelectedItem(paciente.getConvenio());
		ventana.getCbSexo().setSelectedItem(paciente.getSexo());
		ventana.getDateChooser().setDate(paciente.getFechaNac());

		ventana.getlRegistro_1().setText(paciente.getFuncionarioQueRegistra() == null ? "Sin información."
				: paciente.getFuncionarioQueRegistra().toString());
		ventana.getlRegistro_2().setText(paciente.getFechaRegistro() == null ? "Sin información"
				: "Fecha Registro: " + EventosUtil.formatoFecha(paciente.getFechaRegistro()));
		ventana.getlRegistro_3().setText(paciente.isEstado() ? "ACTIVO" : "INACTIVO");

//		pagos = paciente.getPago();
//		modeloTablaPago.setLista(pagos);
//		modeloTablaPago.fireTableDataChanged();
//		
//		presupuestos = paciente.getPresupuesto();
//		modeloTablaPresupuesto.setLista(presupuestos);
//		modeloTablaPresupuesto.fireTabCleDataChanged();

//		buscarPagos(paciente);
//		buscarPresupuestos(paciente);

		filtrarListas();
		accion = "MODIFICAR";

	}

	@Override
	public void setFamiliar(Paciente paciente) {
		// TODO Auto-generated method stub

	}

	private void tableMenuPresupuesto(final JTable table) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				int rowindex = table.getSelectedRow();
				if (rowindex < 0) {
					return;
				}
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu popup = tablePopupPresupuesto(table, rowindex);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private JPopupMenu tablePopupPresupuesto(final JTable table, final int row) {
		JPopupMenu popup = new JPopupMenu("Popup");
		JMenuItem imprimirItem = new JMenuItem("Imprimir presupuesto seleccionado");
		imprimirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodos.getInstance().imprimirPresupuestoIndividual(presupuesto, ventana);
			}
		});
		JMenuItem aprobarPresupuesto = new JMenuItem("Aprobar presupuesto seleccionado");
		aprobarPresupuesto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirmar la aprobación de este presupuesto?",
						"ATENCION", JOptionPane.YES_NO_OPTION);
				if (aceptaAnular == JOptionPane.YES_OPTION) {
					PresupuestoDao pDao = new PresupuestoDao();
					presupuesto = presupuestos.get(row);
					presupuesto.setEstado("APROBADO");
					try {
						pDao.modificar(presupuesto);
						pDao.commit();
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						pDao.rollBack();
					}
				}
			}
		});
		JMenuItem rechazarPresupuesto = new JMenuItem("Rechazar presupuesto seleccionado");
		rechazarPresupuesto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (EventosUtil.liberarAccesoPorPerfil(Sesion.getInstance().getFuncionario(), "OPERADOR")
						&& presupuesto.getEstado().equalsIgnoreCase("APROBADO")) {
					JOptionPane.showMessageDialog(ventana,
							"Para realizar esta acción debe solicitar al administrador local.");
					return;
				}
				Metodos.getInstance().anularRegistro(presupuestos.get(row));

			}
		});

		switch (presupuesto.getEstado()) {
		case "PENDIENTE":
			popup.add(imprimirItem);
			popup.add(aprobarPresupuesto);
			popup.add(rechazarPresupuesto);
			return popup;

		case "APROBADO":
			popup.add(imprimirItem);
			popup.add(rechazarPresupuesto);
			return popup;

		case "RECHAZADO":
			popup.add(aprobarPresupuesto);
			return popup;
		default:
			return popup;
		}
	}

	private void tableMenuPago(final JTable table) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				int rowindex = table.getSelectedRow();
				if (rowindex < 0) {
					return;
				}
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu popup = tablePopupPago(table, rowindex);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private JPopupMenu tablePopupPago(final JTable table, final int row) {
		JPopupMenu popup = new JPopupMenu("Popup");
		JMenuItem imprimirItem = new JMenuItem("Imprimir");
		imprimirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				Metodos.getInstance().imprimirPagoReporte(pagos, ventana);
			}
		});
		JMenuItem anularPago = new JMenuItem("Anular pago seleccionado");
		anularPago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (EventosUtil.liberarAccesoPorPerfil(Sesion.getInstance().getFuncionario(), "ADMIN")
						|| EventosUtil.liberarAccesoPorPerfil(Sesion.getInstance().getFuncionario(), "SUPER")) {
					Metodos.getInstance().anularRegistro(pagos.get(row));
				}
			}
		});
//		popup.add(imprimirItem);
		popup.add(anularPago);
		return popup;
	}

	private void tableMenuReceta(final JTable table) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				int rowindex = table.getSelectedRow();
				if (rowindex < 0) {
					return;
				}
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu popup = tablePopupReceta(table, rowindex);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private JPopupMenu tablePopupReceta(final JTable table, final int row) {
		JPopupMenu popup = new JPopupMenu("Popup");
		JMenuItem imprimirItem = new JMenuItem("Imprimir registro seleccionado");
		imprimirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodos.getInstance().imprimirRecetaReposo(recetas.get(row));
			}
		});
		popup.add(imprimirItem);
		return popup;
	}
}
