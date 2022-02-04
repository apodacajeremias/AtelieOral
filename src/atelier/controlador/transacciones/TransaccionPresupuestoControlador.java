package atelier.controlador.transacciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Familiares;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.Presupuesto;
import atelier.modelo.entidades.PresupuestoDetalle;
import atelier.modelo.entidades.Procedimiento;
import atelier.modelo.entidades.dao.PresupuestoDao;
import atelier.modelo.entidades.interfaces.PacienteInterface;
import atelier.modelo.entidades.interfaces.PresupuestoInterface;
import atelier.modelo.entidades.interfaces.ProcedimientoInterface;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaFamiliares;
import atelier.vista.modelotabla.ModeloTablaPresupuestoDetalle;
import atelier.vista.reporte.ReportePresupuesto;
import atelier.vista.ventana.buscador.BuscadorPaciente;
import atelier.vista.ventana.buscador.BuscadorProcedimiento;
import atelier.vista.ventana.transacciones.TransaccionPresupuesto;

public class TransaccionPresupuestoControlador implements ActionListener, MouseListener, KeyListener, PacienteInterface,
		ProcedimientoInterface, PresupuestoInterface, PropertyChangeListener {

	private TransaccionPresupuesto ventana;
	private ModeloTablaPresupuestoDetalle mtPresupuestoDetalle;
	private ModeloTablaFamiliares mtFamiliares;
	private String accion;
	private Paciente paciente;
	private Procedimiento procedimiento;
	private List<PresupuestoDetalle> items = new ArrayList<PresupuestoDetalle>();
	private PresupuestoDetalle detalle;
	private Presupuesto presupuesto;
	private List<Familiares> familiares = new ArrayList<Familiares>();
	private PresupuestoDao dao;
	private Familiares familiar;

	public TransaccionPresupuestoControlador(TransaccionPresupuesto transaccionPresupuesto) {
		this.ventana = transaccionPresupuesto;
		this.mtPresupuestoDetalle = new ModeloTablaPresupuestoDetalle();
		this.mtFamiliares = new ModeloTablaFamiliares();
		this.ventana.getTable().setModel(mtPresupuestoDetalle);
		this.ventana.getTableFamiliares().setModel(mtFamiliares);
		dao = new PresupuestoDao();
		nuevo();
		estadoInicial(true);
		tableMenu(transaccionPresupuesto.getTable());
		tableMenuFamiliar(transaccionPresupuesto.getTableFamiliares());

		transaccionPresupuesto.getTable().getColumnModel().getColumn(0).setPreferredWidth(50);
		transaccionPresupuesto.getTable().getColumnModel().getColumn(1).setPreferredWidth(150);
		transaccionPresupuesto.getTable().getColumnModel().getColumn(2).setPreferredWidth(30);
		transaccionPresupuesto.getTable().getColumnModel().getColumn(3).setPreferredWidth(10);
		transaccionPresupuesto.getTable().getColumnModel().getColumn(4).setPreferredWidth(30);

		setUpEvents();
	}

	private void setUpEvents() {
		this.ventana.getBtnBuscarPaciente().addActionListener(this);
		this.ventana.getBtnAgregarFamiliar().addActionListener(this);
		this.ventana.getBtnBuscarProcedimiento().addActionListener(this);
		this.ventana.getBtnFinalizar().addActionListener(this);
		this.ventana.gettDescuento().addKeyListener(this);
		this.ventana.gettDescuento().addMouseListener(this);
		this.ventana.getTable().addMouseListener(this);
		this.ventana.getBtnNuevo().addActionListener(this);
		this.ventana.getBtnCancelar().addActionListener(this);
		this.ventana.getBtnBuscarPresupuesto().addActionListener(this);
		this.ventana.getTable().addPropertyChangeListener(this);

	}

	// INHERENTE AL FORMULARIO DEL PRESUPUESTO
	private void estadoInicial(boolean b) {
		paciente = null;
		procedimiento = null;
		familiares = null;
		items = null;
		detalle = null;
		presupuesto = null;
		EventosUtil.estadosBotones(ventana.getBtnBuscarPaciente(), !b);
		EventosUtil.estadosBotones(ventana.getBtnAgregarFamiliar(), !b);
		EventosUtil.estadosBotones(ventana.getBtnBuscarProcedimiento(), !b);
		EventosUtil.estadosBotones(ventana.getBtnCancelar(), !b);
		EventosUtil.estadosBotones(ventana.getBtnFinalizar(), !b);

		EventosUtil.estadosBotones(ventana.getBtnBuscarPresupuesto(), b);
		EventosUtil.estadosBotones(ventana.getBtnNuevo(), b);

		EventosUtil.limpiarCampoPersonalizado(ventana.getlPaciente());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPacienteApellido());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPacienteContacto());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPacienteConvenio());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPacienteCedula());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlProcedimiento());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettDescuento());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlSumatoria());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlValorPagar());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlEstado());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlFechaPresupuesto());
		EventosUtil.limpiarCampoPersonalizado(ventana.getTable());
		EventosUtil.estadosCampoPersonalizado(ventana.gettDescuento(), !b);
		EventosUtil.estadosCampoPersonalizado(ventana.getTable(), !b);

		items = new ArrayList<>();
		mtPresupuestoDetalle.setDetalle(items);
		mtPresupuestoDetalle.fireTableDataChanged();

		familiares = new ArrayList<>();
		mtFamiliares.setLista(familiares);
		mtFamiliares.fireTableDataChanged();
	}

	// VALIDACIONES PARA USO Y GUARDADO
	private boolean validarFormulario() {
		if (paciente == null) {
			JOptionPane.showMessageDialog(null, "Debe asignar un paciente, por favor");
			ventana.getBtnBuscarPaciente().requestFocus();
			return false;
		}
		if (items == null || items.size() <= 0) {
			JOptionPane.showMessageDialog(null, "Asignar procedimientos para este presupuesto");
			ventana.getBtnBuscarProcedimiento().requestFocus();
			return false;
		}
		return true;
	}

	private boolean procedimientoRepetido(Procedimiento p) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProcedimiento().getId() == p.getId()) {
				return true;
			}
		}
		return false;
	}

	// CALCULOS DEL FORMULARIO DE PRESUPUESTO
	private int totalVenta(List<PresupuestoDetalle> items) {
		int total = 0;
		try {
			total += sumatoriaVenta(items) - descuentoVenta(items);
			ventana.getlValorPagar().setText(EventosUtil.separadorMiles((double) total));
			return total;
		} catch (Exception e) {
			ventana.getlValorPagar().setText(EventosUtil.separadorMiles((double) total));
			return total;
		}

	}

	private int sumatoriaVenta(List<PresupuestoDetalle> items) {
		int sumatoria = 0;
		for (int i = 0; i < items.size(); i++) {
			sumatoria += (items.get(i).getProcedimiento().getValorIndividual()) * items.get(i).getCantidad();
		}
		ventana.getlSumatoria().setText(EventosUtil.separadorMiles((double) sumatoria));
		return sumatoria;
	}

	private int descuentoVenta(List<PresupuestoDetalle> items) {
		int descuento = 0;

		if (familiares.size() > 0) {
			for (int i = 0; i < items.size(); i++) {
				descuento += (items.get(i).getProcedimiento().getValorIndividual()
						- items.get(i).getProcedimiento().getValorFamiliar()) * items.get(i).getCantidad();
			}
			ventana.gettDescuento().setValue((double) descuento);
			return descuento;
		}

		if (paciente.isEsSocio()) {
			for (int i = 0; i < items.size(); i++) {
				descuento += (items.get(i).getProcedimiento().getValorIndividual()
						- items.get(i).getProcedimiento().getValorCooperativa()) * items.get(i).getCantidad();
			}
			ventana.gettDescuento().setValue((double) descuento);
			return descuento;
		} else {
			descuento = Integer.parseInt(ventana.gettDescuento().getText());
			return descuento;
		}

	}

	// Mï¿½TODOS PARA USO DE FUNCIONES DE ABM

	public void nuevo() {
		accion = "NUEVO";
		estadoInicial(false);
		ventana.getBtnBuscarPaciente().setEnabled(true);
		ventana.getlEstado().setText("PENDIENTE");
		ventana.getlFechaPresupuesto().setText(EventosUtil.formatoFecha(new Date()));
	}

	private void agregarItem(Procedimiento p) {
		if (procedimiento == null) {
			return;
		}
		detalle = new PresupuestoDetalle();
		detalle.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		detalle.setProcedimiento(procedimiento);
		detalle.setPrecio(procedimiento.getValorIndividual());
		detalle.setDientes("");
		detalle.setCantidad(1);
		items.add(detalle);
		mtPresupuestoDetalle.setDetalle(items);
		mtPresupuestoDetalle.fireTableDataChanged();
		ventana.getBtnBuscarProcedimiento().requestFocus();

		totalVenta(items);
	}

	private void quitarItem() {
		if (ventana.getTable().getSelectedRow() < 0) {
			return;
		}

		int respuesta = JOptionPane.showConfirmDialog(ventana, "¿Deseas eliminar este detalle?",
				"ATENCION", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				items.remove(ventana.getTable().getSelectedRow());
				mtPresupuestoDetalle.setDetalle(items);
				mtPresupuestoDetalle.fireTableDataChanged();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		totalVenta(items);
	}

	private void duplicarItem() {
		if (ventana.getTable().getSelectedRow() < 0) {
			return;
		}
		detalle = items.get(ventana.getTable().getSelectedRow());

		int respuesta = JOptionPane.showConfirmDialog(ventana, "¿Deseas duplicar este item?", "ATENCION",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				agregarItem(detalle.getProcedimiento());
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		totalVenta(items);
	}

	private void quitarFamiliar(int posicion) {
		if (posicion < 0) {
			return;
		}
		familiares.remove(posicion);
		mtFamiliares.setLista(familiares);
		mtFamiliares.fireTableDataChanged();
		totalVenta(items);
	}

	private void guardar() {
		if (!validarFormulario()) {
			return;
		}
		if (accion.equals("NUEVO")) {
			presupuesto = new Presupuesto();
			presupuesto.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
			presupuesto.setEsMensualidad(false);
		}
		presupuesto.setValorPagar(totalVenta(items));
		presupuesto.setSumaTotal(sumatoriaVenta(items));
		presupuesto.setDescuento(descuentoVenta(items));
		presupuesto.setPaciente(paciente);
		presupuesto.setPresupuestoDetalle(items);
		presupuesto.setTieneDescuentoPorConvenio(paciente.isEsSocio());
		presupuesto.setInformacionConvenio(paciente.getConvenio());

		for (int i = 0; i < items.size(); i++) {
			items.get(i).setPresupuesto(presupuesto);
		}
		for (int i = 0; i < familiares.size(); i++) {
			familiares.get(i).setPresupuesto(presupuesto);
		}
		presupuesto.setFamiliares(familiares);
		dao = new PresupuestoDao();
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(presupuesto);
			} else {
				dao.modificar(presupuesto);
			}
			dao.commit();
			estadoInicial(true);
		} catch (Exception e) {
			dao.rollBack();
		}
	}

	private void cancelar() {
		estadoInicial(true);
	}

	private void abrirBuscadorPaciente(boolean familiar) {
		BuscadorPaciente buscador = new BuscadorPaciente();
		buscador.setUpControlador();
		buscador.getControlador().setFamiliar(familiar);
		buscador.getControlador().setInterfaz(this);
		buscador.setVisible(true);
	}

	private void abrirBuscadorProcedimiento() {
		BuscadorProcedimiento buscador = new BuscadorProcedimiento();
		buscador.setUpController();
		buscador.getController().setInterfaz(this);
		buscador.setVisible(true);
	}

	private void abrirReportePresupuesto() {
		ReportePresupuesto reporte = new ReportePresupuesto();
		reporte.setUpControlador();
		ventana.dispose();
		reporte.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == ventana.gettDescuento() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			totalVenta(items);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == ventana.gettDescuento() && e.getClickCount() == 1) {
			ventana.gettDescuento().selectAll();
		}

		if (e.getSource() == ventana.getTable() && e.getClickCount() == 1) {
			setProcedimiento(items.get(ventana.getTable().getSelectedRow()).getProcedimiento());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	// MANEJO DE BUSCADORES
	@Override
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		gestionarPaciente();
	}

	private void gestionarPaciente() {
		if (paciente == null) {
			return;
		}
		ventana.getlPaciente().setText(paciente.getNombre());
		ventana.getlPacienteApellido().setText(paciente.getApellido());
		ventana.getlPacienteContacto().setText(paciente.getTelefono());
		try {
			ventana.getlPacienteCedula()
					.setText(EventosUtil.separadorMiles(Double.parseDouble(paciente.getDocIdentidad())));
		} catch (Exception e) {
			ventana.getlPacienteCedula().setText(paciente.getDocIdentidad());
		}

		if (paciente.isEsSocio()) {
			ventana.getlPacienteConvenio().setText("Convenio de " + paciente.getConvenio());
		} else {
			ventana.getlPacienteConvenio().setText("Sin convenios asignados.");
		}

		EventosUtil.estadosBotones(ventana.getBtnAgregarFamiliar(), true);
		totalVenta(items);
	}

	@Override
	public void setFamiliar(Paciente paciente) {
		if (paciente == null) {
			return;
		}

		if (this.paciente.getId() == paciente.getId()) {
			return;
		}

		familiar = new Familiares();

		familiar.setPaciente(paciente);

		familiares.add(familiar);
		mtFamiliares.setLista(familiares);
		mtFamiliares.fireTableDataChanged();
		totalVenta(items);
	}

	@Override
	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
		gestionarProcedimiento();
	}

	private void gestionarProcedimiento() {
		if (paciente == null) {
			return;
		}
		if (procedimiento == null) {
			return;
		}

		ventana.getlProcedimiento().setText(procedimiento.getDescripcion());

		if (procedimientoRepetido(procedimiento)) {
			return;
		}

		agregarItem(procedimiento);

	}

	@Override
	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
		gestionarPresupuesto();
	}

	private void gestionarPresupuesto() {
		if (presupuesto == null) {
			return;
		}

		setPaciente(presupuesto.getPaciente());

		ventana.getlEstado().setText(presupuesto.getEstado());
		ventana.getlSumatoria().setText(EventosUtil.separadorMiles((double) presupuesto.getSumaTotal()));
		ventana.getlValorPagar()
				.setText(EventosUtil.separadorMiles((double) presupuesto.getValorPagar()));
		ventana.gettDescuento().setValue((double) presupuesto.getDescuento());

		ventana.getlFechaPresupuesto()
				.setText(EventosUtil.formatoFecha(presupuesto.getFechaPresupuesto()));

		items = presupuesto.getPresupuestoDetalle();

		mtPresupuestoDetalle.setDetalle(items);
		mtPresupuestoDetalle.fireTableDataChanged();

		familiares = presupuesto.getFamiliares();
		mtFamiliares.setLista(familiares);
		mtFamiliares.fireTableDataChanged();

		EventosUtil.estadosBotones(ventana.getBtnCancelar(), true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "BuscarPaciente":
			abrirBuscadorPaciente(false);
			break;
		case "AgregarFamiliar":
			abrirBuscadorPaciente(true);
			break;
		case "BuscarProcedimiento":
			abrirBuscadorProcedimiento();
			break;
		case "Quitar":
			quitarItem();
			break;
		case "Guardar":
			guardar();
			break;
		case "Nuevo":
			nuevo();
			break;
		case "Cancelar":
			cancelar();
			break;
		case "BuscarPresupuesto":
			abrirReportePresupuesto();
			
			break;

		default:
			break;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getSource() == ventana.getTable()) {
			try {
				totalVenta(items);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
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
		detalle = items.get(row);
		JPopupMenu popup = new JPopupMenu("Popup");
		JMenuItem quitarItem = new JMenuItem("Quitar");
		JMenuItem duplicarItem = new JMenuItem("Duplicar");
		quitarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarItem();
			}
		});

		duplicarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				duplicarItem();
			}
		});

		popup.add(quitarItem);
		popup.add(duplicarItem);

		return popup;
	}

	private void tableMenuFamiliar(final JTable table) {
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
					JPopupMenu popup = tablePopupFamiliar(table, rowindex);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private JPopupMenu tablePopupFamiliar(final JTable table, final int row) {
		JPopupMenu popup = new JPopupMenu("Popup");
		JMenuItem quitarItem = new JMenuItem("Quitar");
		quitarItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarFamiliar(row);
			}
		});

		popup.add(quitarItem);

		return popup;
	}

}