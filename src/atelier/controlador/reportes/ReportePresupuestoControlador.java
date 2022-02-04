package atelier.controlador.reportes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Funcionario;
import atelier.modelo.entidades.Presupuesto;
import atelier.modelo.entidades.dao.PresupuestoDao;
import atelier.modelo.sesion.Metodos;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaPresupuesto;
import atelier.vista.reporte.ReportePresupuesto;
import atelier.vista.ventana.transacciones.TransaccionPresupuesto;

public class ReportePresupuestoControlador implements ActionListener, MouseListener {
	private ReportePresupuesto reporte;
	private ModeloTablaPresupuesto modeloTabla;
	private PresupuestoDao dao;

	private Presupuesto presupuesto;
	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();

	public ReportePresupuestoControlador(ReportePresupuesto reporte) {
		this.reporte = reporte;
		reporte.setTitle("REPORTE DE PRESUPUESTO");
		modeloTabla = new ModeloTablaPresupuesto();
		reporte.getTable().setModel(modeloTabla);
		tableMenu(reporte.getTable());
		dao = new PresupuestoDao();
		estadoInicial(true);
		setUpEvents();
		cargarColaboradores();
	}

	private void setUpEvents() {
		reporte.getBtnFiltrar().addActionListener(this);
		reporte.getBtnImprimir().addActionListener(this);
		reporte.getBtnLimpiar().addActionListener(this);
		reporte.getTable().addMouseListener(this);
	}

	@SuppressWarnings("unchecked")
	private void cargarColaboradores() {
		if (EventosUtil.liberarAccesoPorPerfil(Sesion.getInstance().getFuncionario(), "ADMIN")
				|| EventosUtil.liberarAccesoPorPerfil(Sesion.getInstance().getFuncionario(), "SUPER")) {
			List<Funcionario> fs = Sesion.getInstance().getFuncionarios();
			for (int i = 0; i < fs.size(); i++) {
				reporte.getCbColaborador().addItem(fs.get(i));
			}
		} else {
			reporte.getCbColaborador().addItem(Sesion.getInstance().getFuncionario());
		}
	}

	private void estadoInicial(boolean b) {
		EventosUtil.estadosBotones(reporte.getBtnFiltrar(), b);
		EventosUtil.estadosBotones(reporte.getBtnImprimir(), b);
	}

	private void vaciarTabla() {
		presupuestos = new ArrayList<Presupuesto>();
		modeloTabla.setLista(presupuestos);
		modeloTabla.fireTableDataChanged();
	}

	private void filtroPorColaborador(String tipoReporte) {
		vaciarTabla();
		Funcionario f = (Funcionario) reporte.getCbColaborador().getSelectedItem();
		String estado = "APROBADO";
		String comparador = reporte.getRb3().isSelected() ? "=" : "<>";
		Integer mes = reporte.getDateChooser().getCalendar().get(Calendar.MONTH) + 1;
		Integer anho = reporte.getDateChooser().getCalendar().get(Calendar.YEAR);
		switch (tipoReporte) {
		case "INDIVIDUAL":
			presupuestos = dao.reporteFuncionarioPresupuesto(comparador, f.getId(), estado, mes, anho);
			modeloTabla.setLista(presupuestos);
			modeloTabla.fireTableDataChanged();
			break;
		case "GENERAL":
			presupuestos = dao.reporteGeneralPresupuesto(comparador, estado, mes, anho);
			modeloTabla.setLista(presupuestos);
			modeloTabla.fireTableDataChanged();
			break;
		}

	}

	private void seleccionarRegistro(int posicion) {
		if (posicion < 0) {
			return;
		}
		presupuesto = presupuestos.get(posicion);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == reporte.getTable() && e.getClickCount() == 1) {
			seleccionarRegistro(reporte.getTable().getSelectedRow());
		}

		if (e.getSource() == reporte.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(reporte.getTable().getSelectedRow());
			abrirTransaccionPresupuesto();

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Filtrar":
			if (reporte.getRb5().isSelected()) {
				filtroPorColaborador("INDIVIDUAL");
			}
			if (reporte.getRb6().isSelected()) {
				filtroPorColaborador("GENERAL");
			}
			break;
		case "Imprimir":
			Metodos.getInstance().imprimirPresupuestoReporte(presupuestos, reporte);
			break;
		case "Limpiar":
			vaciarTabla();
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
		JMenuItem imprimirItem = new JMenuItem("Imprimir presupuesto seleccionado");
		imprimirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodos.getInstance().imprimirPresupuestoIndividual(presupuesto, reporte);
			}
		});
		JMenuItem aprobarPresupuesto = new JMenuItem("Aprobar presupuesto seleccionado");
		aprobarPresupuesto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirmar la aprobación de este presupuesto?",
						"ATENCION", JOptionPane.YES_NO_OPTION);
				if (aceptaAnular == JOptionPane.YES_OPTION) {
					presupuesto = presupuestos.get(row);
					presupuesto.setEstado("APROBADO");
					try {
						dao.modificar(presupuesto);
						dao.commit();
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
						dao.rollBack();
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
					JOptionPane.showMessageDialog(reporte,
							"Para realizar esta acción debe solicitar al administrador local.");
					return;
				}
				Metodos.getInstance().anularRegistro(presupuestos.get(row));

			}
		});

		switch (presupuesto.getEstado()) {
		case "PENDIENTE":
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

	private void abrirTransaccionPresupuesto() {
		TransaccionPresupuesto ventana = new TransaccionPresupuesto();
		ventana.setUpControlador();
		ventana.getControlador().setPresupuesto(presupuesto);
		reporte.dispose();
		ventana.setVisible(true);
	}
}