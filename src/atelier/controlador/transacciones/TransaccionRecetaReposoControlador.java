package atelier.controlador.transacciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Medicamento;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.Recetario;
import atelier.modelo.entidades.RecetarioDetalle;
import atelier.modelo.entidades.Reposo;
import atelier.modelo.entidades.dao.RecetarioDao;
import atelier.modelo.entidades.interfaces.MedicamentoInterface;
import atelier.modelo.entidades.interfaces.PacienteInterface;
import atelier.modelo.entidades.interfaces.RecetarioInterface;
import atelier.modelo.entidades.interfaces.ReposoInterface;
import atelier.modelo.sesion.Metodos;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaRecetarioDetalle;
import atelier.vista.ventana.buscador.BuscadorMedicamento;
import atelier.vista.ventana.buscador.BuscadorPaciente;
import atelier.vista.ventana.transacciones.TransaccionRecetaReposo;

public class TransaccionRecetaReposoControlador implements ActionListener, KeyListener, MedicamentoInterface,
		PacienteInterface, ReposoInterface, RecetarioInterface {
	private TransaccionRecetaReposo ventana;

	private ModeloTablaRecetarioDetalle modeloTablaRecetarioDetalle;

	private RecetarioDao dao;

	private Paciente paciente;

	private Reposo reposo;

	private Recetario receta;
	private RecetarioDetalle recetaDetalle;
	private List<RecetarioDetalle> recetaDetalles;
	private Medicamento medicamento;

	private String accion;

	public TransaccionRecetaReposoControlador(TransaccionRecetaReposo ventana) {
		this.ventana = ventana;

		modeloTablaRecetarioDetalle = new ModeloTablaRecetarioDetalle();
		ventana.getTable().setModel(modeloTablaRecetarioDetalle);
		tableMenu(ventana.getTable());

		dao = new RecetarioDao();
		setUpEvents();
		estadoInicial();
	}

	private void abrirBuscadorMedicamento() {
		BuscadorMedicamento ventana = new BuscadorMedicamento();
		ventana.setUpController();
		ventana.getController().setInterfaz(this);
		ventana.setVisible(true);
	}

	private void abrirBuscadorPaciente() {
		BuscadorPaciente ventana = new BuscadorPaciente();
		ventana.setUpControlador();
		ventana.getControlador().setInterfaz(this);
		ventana.setVisible(true);
	}

	private void agregarMedicamento(Medicamento medicamento) {
		if (medicamento == null) {
			return;
		}
		recetaDetalle = new RecetarioDetalle();
		recetaDetalle.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		recetaDetalle.setDias("7 DIA/S");
		recetaDetalle.setHoras("6 HORA/S");
		recetaDetalle.setMedicamento(medicamento);
		recetaDetalles.add(recetaDetalle);
		modeloTablaRecetarioDetalle.setDetalles(recetaDetalles);
		modeloTablaRecetarioDetalle.fireTableDataChanged();
	}

	private void removerMedicamento(int posicion) {
		if (posicion < 0) {
			return;
		}
		recetaDetalles.remove(posicion);
		modeloTablaRecetarioDetalle.setDetalles(recetaDetalles);
		modeloTablaRecetarioDetalle.fireTableDataChanged();
	}

	private boolean validar() {
		if (paciente == null) {
			return false;
		}
		return true;

	}

	public void nuevo() {
		estadoInicial();
		reposo = null;
		accion = "NUEVO";

	}

	public void modificar() {
		estadoInicial();
		accion = "MODIFICAR";

	}

	private void guardar() {
		if (!validar()) {
			return;
		}
		int opcion = JOptionPane.showConfirmDialog(null, "Guardar e imprimir", "Guardar e imprimir",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (opcion == JOptionPane.OK_OPTION) {
			if (accion.equalsIgnoreCase("NUEVO")) {
				receta = new Recetario();
				reposo = new Reposo();
			}
			receta.setObservacion("N/A");
			receta.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
			receta.setPaciente(paciente);
			receta.setEstado(true);
			if (recetaDetalles.size() <= 0) {
				receta.setEstado(false);
			} else {
				for (int i = 0; i < recetaDetalles.size(); i++) {
					recetaDetalles.get(i).setRecetario(receta);
				}
			}
			receta.setDetalle(recetaDetalles);

			reposo.setPaciente(paciente);
			reposo.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
			reposo.setEstado(true);
			if (!ventana.getRdReposo().isSelected()) {
				reposo.setEstado(false);
			}
			reposo.setTiempo(ventana.gettTiempoReposo().getText());
			reposo.setComentario(ventana.gettMotivoReposo().getText());
			reposo.setReceta(receta);
			receta.setReposo(reposo);

			try {
				if (accion.equalsIgnoreCase("NUEVO")) {
					dao.insertar(receta);
					dao.commit();
				} else {
					dao.modificar(receta);
					dao.commit();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				dao.rollBack();
			}
			Metodos.getInstance().imprimirRecetaReposo(receta);
			modificar();
			setReposo(reposo);
			System.out.println(paciente.isEstado() + " " + paciente.getId());
			System.out.println(reposo.isEstado() + " " + reposo.getId());
			System.out.println(receta.isEstado() + " " + receta.getId());

		}
	}

	private void cancelar() {
		estadoInicial();

	}

	private void setUpEvents() {
		ventana.getBtnAgregar().addActionListener(this);
		ventana.getBtnBuscarPaciente().addActionListener(this);
		ventana.getBtnBuscarPresupuesto().addActionListener(this);
		ventana.getBtnCancelar().addActionListener(this);
		ventana.getBtnFinalizar().addActionListener(this);
		ventana.getBtnNuevo().addActionListener(this);

		ventana.gettMotivoReposo().addKeyListener(this);
		ventana.gettTiempoReposo().addKeyListener(this);

	}

	private void estadoInicial() {
		EventosUtil.limpiarCampoPersonalizado(ventana.getlEstado());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlFechaRegistro());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlInfo1());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlInfo2());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPaciente());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPacienteApellido());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPacienteCedula());
		EventosUtil.limpiarCampoPersonalizado(ventana.getlPacienteContacto());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettMotivoReposo());
		EventosUtil.limpiarCampoPersonalizado(ventana.gettTiempoReposo());
		EventosUtil.limpiarCampoPersonalizado(ventana.getTextArea());

		ventana.getRdReposo().setSelected(false);
		ventana.gettMotivoReposo().setText("CIRURGIA DE CARACTER DELICADO");
		ventana.gettTiempoReposo().setText("7 DIAS");

		accion = null;
		receta = null;
		recetaDetalle = null;
		medicamento = null;
		vaciarListas();
	}

	private void previsualizarTexto() {

		String motivo = ventana.gettMotivoReposo().getText();
		String tiempo = ventana.gettTiempoReposo().getText();

		try {
			ventana.getTextArea().setText("Certifico que el Sr. " + paciente.toString() + " con CI. Nro. "
					+ paciente.getDocIdentidad() + " le fue practicado un/a " + motivo + " requiriendo " + tiempo
					+ " de reposo, medicación y observación con la finalidad de que pueda reintegrarse de manera óptima a sus actividades cotidianas y laborales reduciendo de este modo las complicaciones e inconvenientes que pueda presentar el paciente.");
		} catch (Exception e) {
			ventana.getTextArea().setText("Certifico que el Sr. INDICAR PACIENTE con CI. Nro. INDICAR PACIENTE le fue practicado un/a " + motivo + " requiriendo " + tiempo
					+ " de reposo, medicación y observación con la finalidad de que pueda reintegrarse de manera óptima a sus actividades cotidianas y laborales reduciendo de este modo las complicaciones e inconvenientes que pueda presentar el paciente.");
			e.printStackTrace();
		}
	}

	private void vaciarListas() {
		recetaDetalles = new ArrayList<RecetarioDetalle>();
		modeloTablaRecetarioDetalle.setDetalles(recetaDetalles);
		modeloTablaRecetarioDetalle.fireTableDataChanged();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Agregar":
			abrirBuscadorMedicamento();
			break;
		case "Buscar":

			break;
		case "BuscarPaciente":
			abrirBuscadorPaciente();
			break;
		case "Nuevo":
			nuevo();
			break;
		case "Cancelar":
			cancelar();
			break;
		case "Guardar":
			guardar();
			break;
		default:
			break;
		}

	}

	@Override
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;

		if (medicamento != null) {
			agregarMedicamento(medicamento);
		}

		System.out.println(this.medicamento);

	}

	@Override
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		ventana.getlPaciente().setText(paciente.getNombre());
		ventana.getlPacienteApellido().setText(paciente.getApellido());
		ventana.getlPacienteCedula().setText(paciente.getDocIdentidad());
		ventana.getlPacienteContacto().setText(paciente.getTelefono());
		previsualizarTexto();
	}

	@Override
	public void setFamiliar(Paciente paciente) {
		// TODO Auto-generated method stub

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

		JMenuItem anularPago = new JMenuItem("Eliminar medicamento seleccionado");
		anularPago.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removerMedicamento(row);
			}
		});
		popup.add(anularPago);
		return popup;
	}

	@Override
	public void setRecetario(Recetario recetario) {
		this.receta = recetario;

		if (receta == null) {
			return;
		} else {
			ventana.getlInfo2().setText(!receta.isEstado() ? "SIN RECETA" : "CON RECETA");
			recetaDetalles = receta.getDetalle();
			modeloTablaRecetarioDetalle.setDetalles(recetaDetalles);
			modeloTablaRecetarioDetalle.fireTableDataChanged();
		}

	}

	@Override
	public void setReposo(Reposo r) {
		this.reposo = r;

		if (reposo == null) {
			return;
		} else {
			setPaciente(reposo.getPaciente());
			setRecetario(reposo.getReceta());
			ventana.getRdReposo().setSelected(reposo.isEstado());
			ventana.getlFechaRegistro().setText(EventosUtil.formatoFecha(reposo.getFechaRegistro()));
			ventana.getlInfo1().setText(!reposo.isEstado() ? "SIN REPOSO" : "CON REPOSO");
			ventana.gettMotivoReposo().setText(reposo.getComentario());
			ventana.gettTiempoReposo().setText(reposo.getTiempo());
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getSource() == ventana.gettTiempoReposo()) {
			previsualizarTexto();
		}

		if (arg0.getSource() == ventana.gettMotivoReposo()) {
			previsualizarTexto();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource() == ventana.gettTiempoReposo()) {
			previsualizarTexto();
		}

		if (arg0.getSource() == ventana.gettMotivoReposo()) {
			previsualizarTexto();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == ventana.gettTiempoReposo()) {
			previsualizarTexto();
		}

		if (arg0.getSource() == ventana.gettMotivoReposo()) {
			previsualizarTexto();
		}

	}

}
