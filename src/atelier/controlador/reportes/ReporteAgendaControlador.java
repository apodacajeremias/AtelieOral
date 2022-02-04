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
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Agenda;
import atelier.modelo.entidades.Funcionario;
import atelier.modelo.entidades.dao.AgendaDao;
import atelier.modelo.sesion.Metodos;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaAgenda;
import atelier.vista.reporte.ReporteAgenda;

public class ReporteAgendaControlador implements ActionListener, MouseListener {
	private ReporteAgenda reporte;
	private ModeloTablaAgenda modeloTabla;
	private AgendaDao dao;

	private Agenda agenda;
	private List<Agenda> agendas = new ArrayList<Agenda>();

	public ReporteAgendaControlador(ReporteAgenda reporte) {
		this.reporte = reporte;
		reporte.setTitle("REPORTE DE AGENDA");
		modeloTabla = new ModeloTablaAgenda();
		reporte.getTable().setModel(modeloTabla);
		tableMenu(reporte.getTable());
		dao = new AgendaDao();
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
		agendas = new ArrayList<Agenda>();
		modeloTabla.setLista(agendas);
		modeloTabla.fireTableDataChanged();
	}

	private void filtroPorColaborador(String tipoReporte) {
		vaciarTabla();
		Funcionario f = (Funcionario) reporte.getCbColaborador().getSelectedItem();
		String motivo = reporte.getRb3().isSelected() ? "AGENDADO" : "CANCELADO";
		Integer mes = reporte.getDateChooser().getCalendar().get(Calendar.MONTH) + 1;
		Integer anho = reporte.getDateChooser().getCalendar().get(Calendar.YEAR);
		switch (tipoReporte) {
		case "INDIVIDUAL":
			agendas = dao.reporteMedicoAgenda(f.getId(), motivo, mes, anho);
			modeloTabla.setLista(agendas);
			modeloTabla.fireTableDataChanged();
			break;
		case "GENERAL":
			agendas = dao.reporteGeneralAgenda(motivo, mes, anho);
			modeloTabla.setLista(agendas);
			modeloTabla.fireTableDataChanged();
			break;
		}
	}

	private void seleccionarRegistro(int posicion) {
		if (posicion < 0) {
			return;
		}
		agenda = agendas.get(posicion);
		System.out.println(agenda);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == reporte.getTable() && e.getClickCount() == 1) {
			seleccionarRegistro(reporte.getTable().getSelectedRow());
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
			Metodos.getInstance().imprimirAgendaReporte(agendas, reporte);
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
		JMenuItem imprimirItem = new JMenuItem("Imprimir");
		imprimirItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodos.getInstance().imprimirAgendaReporte(agendas, reporte);
			}
		});
		JMenuItem anularAgenda = new JMenuItem("Anular reserva seleccionada");
		anularAgenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Metodos.getInstance().anularRegistro(agendas.get(row));
			}
		});
//		popup.add(imprimirItem);
		popup.add(anularAgenda);
		return popup;
	}
}