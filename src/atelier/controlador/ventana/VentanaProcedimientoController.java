package atelier.controlador.ventana;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.exception.ConstraintViolationException;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Procedimiento;
import atelier.modelo.entidades.dao.ProcedimientoDao;
import atelier.modelo.entidades.interfaces.AccionesABM;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaProcedimiento;
import atelier.vista.ventana.VentanaProcedimiento;

public class VentanaProcedimientoController implements AccionesABM, MouseListener, KeyListener {

	// ATRIBUTOS
	private VentanaProcedimiento vProcedimiento;
	private String accion;
	private Procedimiento procedimiento;
	private ProcedimientoDao dao;
	private List<Procedimiento> lista;
	private ModeloTablaProcedimiento mtProcedimiento;

	// metodo constructor
	public VentanaProcedimientoController(VentanaProcedimiento vProcedimiento) {
		this.vProcedimiento = vProcedimiento;
		this.vProcedimiento.getMiToolBar().setAcciones(this);
		mtProcedimiento = new ModeloTablaProcedimiento();
		this.vProcedimiento.getTable().setModel(mtProcedimiento);
		estadoInicial(true);
		dao = new ProcedimientoDao();
		recuperarTodo();
		setUpEvents();

		vProcedimiento.getTable().getColumnModel().getColumn(0).setPreferredWidth(250);

	}// FIN CONSTRUCTOR

	// AGREGA ESCUCHADORES DE EVENTOS A OS COMPONENTES
	private void setUpEvents() {
		vProcedimiento.getTable().addMouseListener(this);
		vProcedimiento.gettBuscador().addKeyListener(this);

		vProcedimiento.gettValorCooperativa().addMouseListener(this);
		vProcedimiento.gettValorFamiliar().addMouseListener(this);
		vProcedimiento.gettValorIndividual().addMouseListener(this);

	}

	// METODO PARA RECUPERAR TODO EN LA TABLA
	private void recuperarTodo() {
		lista = dao.recuperarTodoOrdenadoPorNombre();
		mtProcedimiento.setLista(lista);
		mtProcedimiento.fireTableDataChanged();
	}

	// METODO PARA RECUPERAR POR FILTRO DESDE EL BUSCADOR
	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(vProcedimiento.gettBuscador().getText());
		mtProcedimiento.setLista(lista);
		mtProcedimiento.fireTableDataChanged();

	}

	/*
	 * METODO PARA CARGAR FORMULARIO
	 */
	private void cargarFormulario(int posicion) {
		if (posicion < 0) {
			return;
		}
		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtModificar(), true);
		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtEliminar(), true);
		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtGuardar(), false);

		procedimiento = lista.get(posicion);

		vProcedimiento.gettDescripcion().setText(procedimiento.getDescripcion());
		vProcedimiento.gettValorCooperativa().setText(procedimiento.getValorCooperativa() + "");
		vProcedimiento.gettValorIndividual().setText(procedimiento.getValorIndividual() + "");
		vProcedimiento.gettValorFamiliar().setText(procedimiento.getValorFamiliar() + "");
		vProcedimiento.getRdbtnEstado().setSelected(procedimiento.isEstado());

	}

	// METODO PARA VALIDAR FORMULARIO
	private boolean validarFormulario() {
		if (vProcedimiento.gettDescripcion().getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "El nombre del procedimiento es necesario");
			vProcedimiento.gettDescripcion().requestFocus();
		}
		if (vProcedimiento.gettValorIndividual().getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "Es necesario un valor");
			vProcedimiento.gettValorIndividual().requestFocus();
		}
		if (vProcedimiento.gettValorFamiliar().getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "Es necesario un valor");
			vProcedimiento.gettValorFamiliar().requestFocus();
		}
		if (vProcedimiento.gettValorCooperativa().getText().isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "Es necesario un valor");
			vProcedimiento.gettValorCooperativa().requestFocus();
		}

		return true;
	}

	// METODO PARA VACIAR FORMULARIO
	// SE LE ASIGNA ALOS CAMPOS UN VALOR VACION
	private void vaciarFormulario() {
		EventosUtil.limpiarCampoPersonalizado(vProcedimiento.gettDescripcion());
		EventosUtil.limpiarCampoPersonalizado(vProcedimiento.gettValorCooperativa());
		EventosUtil.limpiarCampoPersonalizado(vProcedimiento.gettValorFamiliar());
		EventosUtil.limpiarCampoPersonalizado(vProcedimiento.gettValorIndividual());

		recuperarTodo();
	}

	// METODO PARA EL ESTADO INICIAL DEL FORMULARIO
	// TODOS DESABILITADOS
	private void estadoInicial(boolean b) {
		EventosUtil.estadosCampoPersonalizado(vProcedimiento.gettDescripcion(), !b);
		EventosUtil.estadosCampoPersonalizado(vProcedimiento.gettValorCooperativa(), !b);
		EventosUtil.estadosCampoPersonalizado(vProcedimiento.gettValorFamiliar(), !b);
		EventosUtil.estadosCampoPersonalizado(vProcedimiento.gettValorIndividual(), !b);

		EventosUtil.estadosCampoPersonalizado(vProcedimiento.getTable(), b);
		EventosUtil.estadosCampoPersonalizado(vProcedimiento.gettBuscador(), b);

		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtNuevo(), true);
		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtSalir(), true);

		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtModificar(), false);
		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtEliminar(), false);
		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtCancelar(), false);
		EventosUtil.estadosBotones(vProcedimiento.getMiToolBar().getbtGuardar(), false);

	}

	// BOTON NUEVO
	@Override
	public void nuevo() {
		// El estadoInicial pasa a falso
		estadoInicial(false);
		accion = "NUEVO";
		vaciarFormulario();
		vProcedimiento.gettDescripcion().requestFocus();
		vProcedimiento.getLblAccionEjecutada().setText("Nuevo registro");

	}

	// BOTON MODIFICAR
	@Override
	public void modificar() {
		// El estadoInicial pasa a falso
		estadoInicial(false);
		accion = "MODIFICAR";
		vProcedimiento.getLblAccionEjecutada().setText("Modificar registro");
	}

	// BOTON ELIMINAR
	@Override
	public void eliminar() {
		// SE SELECIONA UNA FILA DE LA TABLA PARA ELIMINAR
		int posicion = vProcedimiento.getTable().getSelectedRow();
		// SI NO SE HA SELECIONADO NINGUNA FILA RETORNA
		if (posicion < 0) {
			return;
		}

		// Confirmacion para eliminacion
		vProcedimiento.getLblAccionEjecutada().setText("Eliminar registro");
		int respuesta = JOptionPane
				.showConfirmDialog(null,
						"La eliminación del procedimiento " + procedimiento.getDescripcion()
								+ " conlleva la pérdida permanente del registro",
						"ATENCION", JOptionPane.YES_NO_OPTION);
		// SI LA RESPUESTA ES "SI" SE ELIMINARA EL REGISTRO SELECIONADO
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(procedimiento);
				dao.commit();
				vaciarFormulario();
				recuperarTodo();
				vProcedimiento.getLblAccionEjecutada().setText("Registro eliminado");
			} catch (Exception e) {
				if (e.getCause().getClass() == ConstraintViolationException.class) {
					JOptionPane.showMessageDialog(null, "El Procedimiento se encuentra en uso, no es posible eliminar");
				}
				dao.rollBack();
				e.printStackTrace();
			}
		}
	}

	// BOTON GUARDAR
	@Override
	public void guardar() {
		// Si la validacion no es confirmada entonces la accion guardar no se
		// ejecuta
		if (!validarFormulario()) {
			return;
		}

		// SE INSTANCIA UN NUEVO Procedimiento
		if (accion.equals("NUEVO")) {
			procedimiento = new Procedimiento();
			procedimiento.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
			

		}

		procedimiento.setDescripcion(vProcedimiento.gettDescripcion().getText());
		procedimiento.setValorCooperativa(Integer.parseInt(vProcedimiento.gettValorCooperativa().getText()));
		procedimiento.setValorFamiliar(Integer.parseInt(vProcedimiento.gettValorFamiliar().getText()));
		procedimiento.setValorIndividual(Integer.parseInt(vProcedimiento.gettValorIndividual().getText()));
		procedimiento.setEstado(vProcedimiento.getRdbtnEstado().isSelected());

		// SE INTENTA INSERTAR EL REGISTRO
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(procedimiento);
				vProcedimiento.getLblAccionEjecutada().setText("Registro guardado con éxito");
			} else {
				dao.modificar(procedimiento);
				System.out.println("Procedimiento actualizado");
				vProcedimiento.getLblAccionEjecutada().setText("Registro actualizado con éxito");
			}
			dao.commit();
			// una vez guardado los cambios se recupera los registros
			// actualizados
			recuperarTodo();
			// pasamos el formulario a estado inicial
			estadoInicial(true);
			// llamamos al metodo vaciar formulario
			vaciarFormulario();
			// nuevo();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El documento o el teléfono ya existen y no pueden ser repetitivos");
		}
		dao.rollBack();

	}

	// BOTON PARA CANCELAR
	@Override
	public void cancelar() {
		estadoInicial(true);
		vaciarFormulario();

	}

	// BOTON PARA SALIR
	@Override
	public void salir() {
		vProcedimiento.dispose();

	}

	// EVENTO DEL MOUSE
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vProcedimiento.getTable()) {
			// si se da clic a la tabla se carga el formulario segun la fila
			cargarFormulario(vProcedimiento.getTable().getSelectedRow());
		}
		if (e.getSource() == vProcedimiento.gettValorCooperativa()) {
			vProcedimiento.gettValorCooperativa().selectAll();
		}
		if (e.getSource() == vProcedimiento.gettValorFamiliar()) {
			vProcedimiento.gettValorFamiliar().selectAll();
		}
		if (e.getSource() == vProcedimiento.gettValorIndividual()) {
			vProcedimiento.gettValorIndividual().selectAll();
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
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	// EVENTOS DE TECLADO
	@Override
	public void keyPressed(KeyEvent e) {

		// si presiona ENTER en el buscador
		// SE RECUPERARA LO QUE SE HA ESCRITO EN EL BUSCADOR
		if (e.getSource() == vProcedimiento.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}

		if (e.getSource() == vProcedimiento.gettDescripcion() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			vProcedimiento.gettValorIndividual().requestFocus();
		}
		if (e.getSource() == vProcedimiento.gettValorIndividual() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			vProcedimiento.gettValorFamiliar().requestFocus();
		}
		if (e.getSource() == vProcedimiento.gettValorFamiliar() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			vProcedimiento.gettValorCooperativa().requestFocus();
		}
		if (e.getSource() == vProcedimiento.gettValorCooperativa() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea guardar el registro?", "ATENCION",
					JOptionPane.YES_NO_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardar();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
