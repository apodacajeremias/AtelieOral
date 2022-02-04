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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.Presupuesto;
import atelier.modelo.entidades.PresupuestoDetalle;
import atelier.modelo.entidades.Seguimiento;
import atelier.modelo.entidades.dao.SeguimientoDao;
import atelier.modelo.entidades.interfaces.PacienteInterface;
import atelier.modelo.sesion.Metodos;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaDiente;
import atelier.vista.modelotabla.ModeloTablaPresupuestoDetalle;
import atelier.vista.modelotabla.ModeloTablaSeguimiento;
import atelier.vista.ventana.transacciones.TransaccionSeguimiento;

public class TransaccionSeguimientoControlador
		implements ActionListener, MouseListener, KeyListener, PacienteInterface, PropertyChangeListener {
	private TransaccionSeguimiento ventana;

	private SeguimientoDao dao;
	private Seguimiento seguimiento;
	private List<Seguimiento> seguimientos;
	private ModeloTablaSeguimiento modeloTablaSeguimiento;

	private PresupuestoDetalle presupuestoDetalle;
	private List<PresupuestoDetalle> presupuestoDetalles;
	private ModeloTablaPresupuestoDetalle modeloTablaPresupuestoDetalle;

	private String diente;
	private List<String> dientes;
	private ModeloTablaDiente modeloTablaDiente;

	private Paciente paciente;

	public TransaccionSeguimientoControlador(TransaccionSeguimiento ventana) {
		this.ventana = ventana;
		dao = new SeguimientoDao();
		

		modeloTablaPresupuestoDetalle = new ModeloTablaPresupuestoDetalle();
		ventana.getTablePresupuestoDetalle().setModel(modeloTablaPresupuestoDetalle);
		tableMenu(ventana.getTablePresupuestoDetalle());
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(0).setMaxWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(0).setMinWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(0).setPreferredWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(0).setResizable(false);

		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(2).setMaxWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(2).setMinWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(2).setPreferredWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(2).setResizable(false);

		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(4).setMaxWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(4).setMinWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(4).setPreferredWidth(0);
		ventana.getTablePresupuestoDetalle().getColumnModel().getColumn(4).setResizable(false);

		modeloTablaSeguimiento = new ModeloTablaSeguimiento();
		ventana.getTableSeguimiento().setModel(modeloTablaSeguimiento);
		tableMenuSeguimiento(ventana.getTableSeguimiento());

		modeloTablaDiente = new ModeloTablaDiente();
		ventana.getTableDientes().setModel(modeloTablaDiente);

		setUpEvents();
		estadoInicial();
		vaciarTablas();
	}

	private void estadoInicial() {
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPaciente());

	}

	private void vaciarTablas() {
		seguimientos = new ArrayList<Seguimiento>();
		modeloTablaSeguimiento.setSeguimiento(seguimientos);

		presupuestoDetalles = new ArrayList<PresupuestoDetalle>();
		modeloTablaPresupuestoDetalle.setDetalle(presupuestoDetalles);

	}

	private void setUpEvents() {
		ventana.getBtnAgregar().addActionListener(this);
		ventana.getBtnCancelar().addActionListener(this);
		ventana.getBtnFinalizar().addActionListener(this);
		ventana.getTableDientes().addMouseListener(this);
		ventana.getTablePresupuestoDetalle().addMouseListener(this);
		ventana.getTableSeguimiento().addMouseListener(this);
	}

	private void agregarSeguimiento() {
		if (paciente == null) {
			System.out.println("Sin paciente.");
			return;
		}
		if (presupuestoDetalle == null) {
			System.out.println("Sin presupuestoDetalle.");
			return;
		}
		if (ventana.getTablePresupuestoDetalle().getSelectedRow() < 0) {
			return;
		}
		if (ventana.getTableDientes().getSelectedRow() < 0) {
			diente = "";
		}

		seguimiento = new Seguimiento();
		seguimiento.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		seguimiento.setComentario("PROCEDIMIENTO REALIZADO");
		seguimiento.setPaciente(paciente);
		seguimiento.setPresupuestoDetalle(presupuestoDetalle);
		seguimiento.setProcedimiento(presupuestoDetalle.getProcedimiento());
		seguimiento.setDiente(diente);

		try {
			seguimientos.add(seguimiento);
			modeloTablaSeguimiento.setSeguimiento(seguimientos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dao.rollBack();
		}
		ventana.getTableDientes().clearSelection();
		ventana.getTableSeguimiento().clearSelection();
	}

	private void quitarSeguimiento(int posicion) {
		if (posicion < 0) {
			return;
		}
		seguimientos.remove(posicion);
		modeloTablaSeguimiento.setSeguimiento(seguimientos);
	}

	private void finalizar() {
		if (seguimientos == null || seguimientos.size() <= 0) {
			return;
		}
		int aceptaAnular = JOptionPane.showConfirmDialog(null,
				"¿Confirmar anexar los seguimientos insertados previamente?", "ATENCION", JOptionPane.YES_NO_OPTION);
		if (aceptaAnular == JOptionPane.YES_OPTION) {
			try {
				for (int i = 0; i < seguimientos.size(); i++) {
					dao.insertar(seguimientos.get(i));
					dao.commit();
				}
				ventana.dispose();
			} catch (Exception e) {
				e.printStackTrace();
				dao.rollBack();
				return;
			}
		} else {
			try {
				dao.rollBack();
				ventana.dispose();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

	}

	private void cancelar() {
		int aceptaAnular = JOptionPane.showConfirmDialog(null, "Si cancela el registro, perderá la información cargada",
				"CONFIRMAR", JOptionPane.YES_NO_OPTION);
		if (aceptaAnular == JOptionPane.YES_OPTION) {
			ventana.dispose();
		}
	}

	private void seleccionarPresupuestoDetalle() {
		int posicion = ventana.getTablePresupuestoDetalle().getSelectedRow();
		if (posicion < 0) {
			return;
		}
		presupuestoDetalle = presupuestoDetalles.get(posicion);
		separarDientes(presupuestoDetalle);
	}

	private List<String> separarDientes(PresupuestoDetalle pd) {
		String[] array = pd.getDientes().split(",");
		dientes = Arrays.asList(array);
		modeloTablaDiente.setDiente(dientes);
		modeloTablaDiente.fireTableDataChanged();
		return dientes;
	}

	private String seleccionarDiente() {
		int posicion = ventana.getTableDientes().getSelectedRow();
		if (posicion < 0) {
			return " ";
		}
		diente = dientes.get(posicion);
		System.out.println(diente);
		return diente;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		if (paciente == null) {
			return;
		}

		ventana.getlPaciente().setText(this.paciente.toString());
	}

	@Override
	public void setFamiliar(Paciente paciente) {
		// TODO Auto-generated method stub

	}

	public void setListaPresupuesto(List<Presupuesto> presupuestos) {
		try {
			presupuestos = presupuestos.stream().filter(x -> x.getEstado().equalsIgnoreCase("APROBADO"))
					.collect(Collectors.toList());
			
			for (int i = 0; i < presupuestos.size(); i++) {
				presupuestoDetalles.addAll(presupuestos.get(i).getPresupuestoDetalle());
			}
			presupuestoDetalles = presupuestoDetalles.stream().filter(x -> x.isEstaFinalizado() == false).collect(Collectors.toList());
			modeloTablaPresupuestoDetalle.setDetalle(presupuestoDetalles);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ventana, "Sin procedimientos aprobados.");
			e.printStackTrace();
			return;
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
		if (e.getSource() == ventana.getTableDientes()) {
			seleccionarDiente();
		}
		if (e.getSource() == ventana.getTablePresupuestoDetalle()) {
			seleccionarPresupuestoDetalle();
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
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getSource() == ventana.getTableDientes()) {
			seleccionarDiente();
		}
		if (arg0.getSource() == ventana.getTablePresupuestoDetalle()) {
			seleccionarPresupuestoDetalle();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getSource() == ventana.getTableDientes()) {
			seleccionarDiente();
		}
		if (arg0.getSource() == ventana.getTablePresupuestoDetalle()) {
			seleccionarPresupuestoDetalle();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Finalizar":
			finalizar();
			break;
		case "Agregar":
			agregarSeguimiento();
			break;
		case "Cancelar":
			cancelar();
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
		JPopupMenu popup = new JPopupMenu("Popup");
		JMenuItem imprimirItem = new JMenuItem("Marcar procedimiento finalizado");
		imprimirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodos.getInstance().anularRegistro(presupuestoDetalle);
			}
		});
		popup.add(imprimirItem);
		return popup;
	}

	private void tableMenuSeguimiento(final JTable table) {
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
					JPopupMenu popup = tablePopupSeguimiento(table, rowindex);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private JPopupMenu tablePopupSeguimiento(final JTable table, final int row) {
		JPopupMenu popup = new JPopupMenu("Popup");

		JMenuItem anularPago = new JMenuItem("Eliminar seguimiento seleccionado");
		anularPago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				quitarSeguimiento(row);
			}
		});
		popup.add(anularPago);
		return popup;
	}
}
