package atelier.controlador.transacciones;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.hibernate.exception.ConstraintViolationException;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Agenda;
import atelier.modelo.entidades.Funcionario;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.dao.AgendaDao;
import atelier.modelo.entidades.dao.UsuarioDao;
import atelier.modelo.entidades.interfaces.AccionesABM;
import atelier.modelo.entidades.interfaces.AgendaInterface;
import atelier.modelo.entidades.interfaces.PacienteInterface;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaAgenda;
import atelier.vista.ventana.buscador.BuscadorPaciente;
import atelier.vista.ventana.transacciones.TransaccionAgenda;

public class TransaccionAgendaControlador implements AccionesABM, PropertyChangeListener, ActionListener, MouseListener,
		KeyListener, PacienteInterface, AgendaInterface {
	private TransaccionAgenda ventanaAgenda;
	private ModeloTablaAgenda modeloTablaAgenda;
	private Paciente paciente;
	private List<Agenda> lista;
	private Agenda agenda;
	private AgendaDao dao;
	private String accion;
	private Date fecha;
	private String hora;
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM");
	SimpleDateFormat formatoDia = new SimpleDateFormat("EEEE");

	public TransaccionAgendaControlador(TransaccionAgenda ventanaAgenda) {
		this.ventanaAgenda = ventanaAgenda;
		this.modeloTablaAgenda = new ModeloTablaAgenda();
		this.ventanaAgenda.getTable().setModel(modeloTablaAgenda);
		ventanaAgenda.getTable().getColumnModel().getColumn(0).setPreferredWidth(100);
		ventanaAgenda.getTable().getColumnModel().getColumn(1).setPreferredWidth(200);
		ventanaAgenda.getTable().getColumnModel().getColumn(2).setPreferredWidth(100);
		DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
		derecha.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(SwingConstants.CENTER);

		ventanaAgenda.getTable().getColumnModel().getColumn(0).setCellRenderer(centro);
		ventanaAgenda.getTable().getColumnModel().getColumn(1).setCellRenderer(centro);

		dao = new AgendaDao();
		estadoInicial(true);
		setUpEvents();

		tableMenu(ventanaAgenda.getTable());
		cargarMedicos();
	}

	private void setUpEvents() {
		this.ventanaAgenda.getBtnAgendar().addActionListener(this);
		this.ventanaAgenda.getBtnBuscar().addActionListener(this);
		this.ventanaAgenda.getBtnCancelar().addActionListener(this);
		this.ventanaAgenda.getBtnConsultar().addActionListener(this);
		this.ventanaAgenda.getBtnDesagendar().addActionListener(this);
		this.ventanaAgenda.getBtnSalir().addActionListener(this);
		this.ventanaAgenda.getTable().addMouseListener(this);
		this.ventanaAgenda.getCalendar().addPropertyChangeListener(this);

	}

	private void estadoInicial(boolean b) {
		EventosUtil.estadosBotones(ventanaAgenda.getBtnAgendar(), !b);
		EventosUtil.estadosBotones(ventanaAgenda.getBtnConsultar(), !b);
		EventosUtil.estadosBotones(ventanaAgenda.getBtnDesagendar(), !b);
		EventosUtil.estadosBotones(ventanaAgenda.getBtnCancelar(), !b);

		EventosUtil.limpiarCampoPersonalizado(ventanaAgenda.getLblPaciente());
		EventosUtil.limpiarCampoPersonalizado(ventanaAgenda.getLblPacienteApellido());
	}

	private void recuperarPorFecha() {
		Date date = ventanaAgenda.getCalendar().getDate(); // ic es la interfaz, jDate el JDatechooser
		long d = date.getTime(); // guardamos en un long el tiempo
		java.sql.Date fecha = new java.sql.Date(d);// parseamos al formato del sql

		ventanaAgenda.getLblFechaSeleccionada().setText(EventosUtil.formatoFecha(fecha));
		lista = dao.recuperarTodoOrdenadoHora(fecha);
		modeloTablaAgenda.setLista(lista);
		modeloTablaAgenda.fireTableDataChanged();
	}

	private void recuperarPorPaciente() {
		lista = paciente.getAgenda();
		modeloTablaAgenda.setLista(lista);
		modeloTablaAgenda.fireTableDataChanged();
	}

	private boolean validarReserva(Funcionario medico, Date fechaAgenda, String horaAgenda) {
		dao = new AgendaDao();

		Agenda entidadDeValidacion = dao.verificarAgenda(medico.getId(), fechaAgenda, horaAgenda, "AGENDADO");

		if (entidadDeValidacion == null) {
			return true;
		} else {
			String message = "No es posible reservar.";
			JOptionPane.showMessageDialog(ventanaAgenda, message);
			return false;
		}

	}

	private void seleccionarReserva(int posicion) {
		if (posicion < 0) {
			return;
		}
		agenda = lista.get(posicion);
	}

	public String enviarMensaje() {
		if (agenda == null) {
			return null;
		}
		String mensaje = "Hola, " + EventosUtil.convertToTitleCaseIteratingChars(agenda.getPaciente().getNombre())
				+ " desde Atelie Oral te recordamos" + " que tu consulta está marcada" + " para el *"
				+ formatoDia.format(agenda.getFechaAgenda()) + "* " + formatoFecha.format(agenda.getFechaAgenda())
				+ ", a las *" + agenda.getHoraAgenda() + "* horas.";
		String nro = (agenda.getPaciente().getTelefono());
		String enlace = "https://api.whatsapp.com/send?phone=" + 595 + nro + "&text=" + mensaje;

		String nv = enlace.toString().replace(" ", "%20");
		return nv;
	}

	private void abrirNavegador() {
		Desktop enlace = Desktop.getDesktop();
		try {
			enlace.browse(new URI(enviarMensaje()));
		} catch (IOException | URISyntaxException e) {
			return;
		}
	}

	private void agendar() {
		accion = "NUEVO";
		fecha = ventanaAgenda.getCalendar().getDate();
		hora = ventanaAgenda.getsHorario().getSelectedItem().toString();
		System.out.println("En fecha " + fecha + ", a las " + hora);
		guardar();
	}

	private void desagendar() {
		accion = "MODIFICAR";
		eliminar();

	}

	// private void crearBotonTabla() {
	// TableColumn agregarColumn;
	// agregarColumn = ventanaAgenda.getTable().getColumnModel().getColumn(3);
	// agregarColumn.setCellEditor(new CeldaEditor(ventanaAgenda.getTable()));
	// agregarColumn.setCellRenderer(new CeldaRenderer(true, 3));
	// }

	@Override
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		estadoInicial(false);
		gestionarPaciente();
	}

	private void gestionarPaciente() {
		if (paciente == null) {
			return;
		}

		ventanaAgenda.getLblPaciente().setText(paciente.getNombre());
		ventanaAgenda.getLblPacienteApellido().setText(paciente.getApellido());
		EventosUtil.estadosBotones(ventanaAgenda.getBtnAgendar(), true);
		EventosUtil.estadosBotones(ventanaAgenda.getBtnConsultar(), true);
	}

	private void abrirBuscadorPaciente() {
		BuscadorPaciente buscador = new BuscadorPaciente();
		buscador.setUpControlador();
		buscador.getControlador().setInterfaz(this);
		buscador.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	private void cargarMedicos() {
		UsuarioDao daoU = new UsuarioDao();
		List<Funcionario> medicos = daoU.buscarMedicos();
		ventanaAgenda.getCbMedico().removeAllItems();

		try {
			for (int i = 0; i < medicos.size(); i++) {
				ventanaAgenda.getCbMedico().addItem(medicos.get(i));
			}
		} catch (Exception e) {
			ventanaAgenda.getCbMedico().addItem(null);
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == ventanaAgenda.getTable()) {
			seleccionarReserva(ventanaAgenda.getTable().getSelectedRow());
			EventosUtil.estadosBotones(ventanaAgenda.getBtnDesagendar(), true);
		}
		if (e.getSource() == ventanaAgenda.getTable() && e.getClickCount() == 2) {
			desagendar();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if (e.getSource() == ventanaAgenda.getCalendar()) {
			recuperarPorFecha();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agendar":
			agendar();
			break;
		case "Avisar":
			System.out.println("action");
			break;
		case "Desagendar":
			desagendar();
			break;
		case "Buscar":
			abrirBuscadorPaciente();
			break;
		case "Consultar":
			recuperarPorPaciente();
			break;
		case "Cancelar":
			cancelar();
			break;
		case "Salir":
			salir();
			break;

		default:
			break;
		}
	}

	private void tableMenu(final JTable table) {
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
					JPopupMenu popup = tablePopup(table, rowindex);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private JPopupMenu tablePopup(final JTable table, final int row) {
		agenda = lista.get(row);
		JPopupMenu popup = new JPopupMenu("Popup");
		JMenuItem refreshItem = new JMenuItem("Avisar al " + agenda.getPaciente().getTelefono());
		refreshItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (agenda.getMotivo().equalsIgnoreCase("AGENDADO")) {
					abrirNavegador();
				} else {
					JOptionPane.showMessageDialog(ventanaAgenda, "Agenda cancelada.");
				}

			}
		});

		JMenuItem anularItem = new JMenuItem("Anular");
		anularItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				desagendar();
			}
		});

		popup.add(refreshItem);
		popup.add(anularItem);

		return popup;
	}

	@Override
	public void nuevo() {
		accion = "NUEVO";
		estadoInicial(false);
	}

	@Override
	public void modificar() {
		estadoInicial(false);
		accion = "MODIFICAR";

	}

	@Override
	public void eliminar() {
		int posicion = ventanaAgenda.getTable().getSelectedRow();
		if (posicion < 0) {
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(ventanaAgenda,
				"¿Deseas desagendar esta consulta? \n" + "Paciente: " + agenda.getPaciente().getNombre() + " "
						+ agenda.getPaciente().getApellido() + " \n" + "Fecha: " + agenda.getFechaAgenda() + " \n"
						+ "Hora: " + agenda.getHoraAgenda(),
				"ATENCION", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {

			agenda.setEstado(false);
			agenda.setMotivo("CANCELADO");

			try {
				dao.modificar(agenda);
				dao.commit();
				estadoInicial(true);
			} catch (Exception e) {
				if (e.getCause().getClass() == ConstraintViolationException.class) {
					JOptionPane.showMessageDialog(null, "No es posible desagendar");
				}
				dao.rollBack();
				e.printStackTrace();
			}
		}
		modeloTablaAgenda.fireTableDataChanged();
	}

	@Override
	public void guardar() {
		if (!validarReserva((Funcionario) ventanaAgenda.getCbMedico().getSelectedItem(),
				ventanaAgenda.getCalendar().getDate(), ventanaAgenda.getsHorario().getSelectedItem().toString())) {
			return;
		}
		if (accion.equals("NUEVO")) {
			agenda = new Agenda();
			agenda.setMotivo("AGENDADO");
			agenda.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		}
		agenda.setFechaAgenda(ventanaAgenda.getCalendar().getDate());
		agenda.setHoraAgenda(ventanaAgenda.getsHorario().getSelectedItem().toString());
		agenda.setMedico((Funcionario) ventanaAgenda.getCbMedico().getSelectedItem());
		agenda.setPaciente(paciente);

		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(agenda);
			} else {
				dao.modificar(agenda);
			}
			dao.commit();
			estadoInicial(true);
		} catch (Exception e) {
			dao.rollBack();
			EventosUtil.formatException(e);
		}
		recuperarPorFecha();
	}

	@Override
	public void cancelar() {
		estadoInicial(true);
	}

	@Override
	public void salir() {
		ventanaAgenda.dispose();
	}

	@Override
	public void setFamiliar(Paciente paciente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;

		if (agenda == null) {
			return;
		}

	}
}
