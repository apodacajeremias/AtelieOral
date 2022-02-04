package atelier.controlador.ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.exception.ConstraintViolationException;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Medicamento;
import atelier.modelo.entidades.dao.MedicamentoDao;
import atelier.modelo.entidades.interfaces.AccionesABM;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaMedicamento;
import atelier.vista.ventana.VentanaMedicamento;

public class VentanaMedicamentoController implements AccionesABM,
		ActionListener, MouseListener, KeyListener {

	// ATRIBUTOS
	private VentanaMedicamento vMedicamento;
	private String accion;
	private Medicamento medicamento;
	private MedicamentoDao dao;
	private List<Medicamento> lista;
	private ModeloTablaMedicamento mtMedicamento;

	// metodo constructor
	public VentanaMedicamentoController(VentanaMedicamento vMedicamento) {
		this.vMedicamento = vMedicamento;
		this.vMedicamento.getMiToolBar().setAcciones(this);
		mtMedicamento = new ModeloTablaMedicamento();
		this.vMedicamento.getTable().setModel(mtMedicamento);
		estadoInicial(true);
		dao = new MedicamentoDao();
		recuperarTodo();
		setUpEvents();

	}// FIN CONSTRUCTOR

	// AGREGA ESCUCHADORES DE EVENTOS A OS COMPONENTES
	private void setUpEvents() {
		vMedicamento.getTable().addMouseListener(this);
		vMedicamento.gettBuscador().addKeyListener(this);

	}

	// METODO PARA RECUPERAR EN LA TABLA
	private void recuperarTodo() {
		lista = dao.recuperarTodoOrdenadoPorNombre();
		mtMedicamento.setLista(lista);
		mtMedicamento.fireTableDataChanged();
	}

	// METODO PARA RECUPERAR POR FILTRO DESDE EL BUSCADOR
	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(vMedicamento.gettBuscador().getText());
		mtMedicamento.setLista(lista);
		mtMedicamento.fireTableDataChanged();

	}
	/*
	 * METODO PARA CARGAR FORMULARIO
	 */
	private void cargarFormulario(int posicion) {
		if (posicion < 0) {
			return;
		}
		medicamento = lista.get(posicion);

		EventosUtil.estadosBotones(
				vMedicamento.getMiToolBar().getbtModificar(), true);
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtEliminar(),
				true);
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtGuardar(),
				false);

		vMedicamento.gettNombreComercial().setText(
				medicamento.getNombreComercial());
		vMedicamento.gettTipoMedicamento().setText(medicamento.getTipoMedicamento());
		vMedicamento.getTxtObservacion().setText(medicamento.getInformacion());

	}

	// METODO PARA VALIDAR FORMULARIO
	private boolean validarFormulario() {
		if (vMedicamento.gettNombreComercial().getText().isEmpty()) {
			vMedicamento.gettNombreComercial().requestFocus();
			return false;
		}
		if (vMedicamento.gettTipoMedicamento().getText().isEmpty()) {
			vMedicamento.gettTipoMedicamento().requestFocus();
			return false;
		}
		if (vMedicamento.getTxtObservacion().getText().isEmpty()) {
			vMedicamento.getTxtObservacion().requestFocus();
			return false;
		}

		return true;
	}

	// METODO PARA VACIAR FORMULARIO
	// SE LE ASIGNA ALOS CAMPOS UN VALOR VACION
	private void vaciarFormulario() {
		EventosUtil.limpiarCampoPersonalizado(vMedicamento.getLblAccionEjecutada());
		
		EventosUtil.limpiarCampoPersonalizado(vMedicamento.gettNombreComercial());
		EventosUtil.limpiarCampoPersonalizado(vMedicamento.gettTipoMedicamento());
		EventosUtil.limpiarCampoPersonalizado(vMedicamento.getTxtObservacion());
		recuperarTodo();
	}

	// METODO PARA EL ESTADO INICIAL DEL FORMULARIO
	// TODOS DESABILITADOS
	private void estadoInicial(boolean b) {
		EventosUtil.estadosCampoPersonalizado(vMedicamento.gettNombreComercial(), !b);

		EventosUtil.estadosCampoPersonalizado(vMedicamento.gettTipoMedicamento(),
				!b);

		EventosUtil.estadosCampoPersonalizado(vMedicamento.getTxtObservacion(),!b);
		
		
		EventosUtil.estadosCampoPersonalizado(vMedicamento.getTable(), b);
		EventosUtil.estadosCampoPersonalizado(vMedicamento.gettBuscador(), b);
	
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtNuevo(), true);
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtSalir(), true);
		
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtModificar(), false);
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtEliminar(), false);
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtCancelar(), false);
		EventosUtil.estadosBotones(vMedicamento.getMiToolBar().getbtGuardar(), false);


	}

	// BOTON NUEVO
	@Override
	public void nuevo() {
		accion = "NUEVO";
		estadoInicial(false);
		vaciarFormulario();
		vMedicamento.gettNombreComercial().requestFocus();
		vMedicamento.getLblAccionEjecutada().setText("Nuevo registro");

	}

	// BOTON MODIFICAR
	@Override
	public void modificar() {
		// El estadoInicial pasa a falso
		estadoInicial(false);
		accion = "MODIFICAR";
		vMedicamento.getLblAccionEjecutada().setText("Modificar registro");
	}

	// BOTON ELIMINAR
	@Override
	public void eliminar() {
		// SE SELECIONA UNA FILA DE LA TABLA PARA ELIMINAR
		int posicion = vMedicamento.getTable().getSelectedRow();
		// SI NO SE HA SELECIONADO NINGUNA FILA RETORNA
		if (posicion < 0) {
			return;
		}

		// Confirmacion para eliminacion
		vMedicamento.getLblAccionEjecutada().setText("Eliminar registro");
		int respuesta = JOptionPane.showConfirmDialog(
				null,
				"La eliminación del medicamento "
						+ medicamento.getNombreComercial() + " - "
						+ medicamento.getTipoMedicamento()
						+ " conlleva la pérdida permanente del registro",
				"ATENCION", JOptionPane.YES_NO_OPTION);
		// SI LA RESPUESTA ES "SI" SE ELIMINARA EL REGISTRO SELECIONADO
		if (respuesta == JOptionPane.YES_OPTION) {
			try {
				dao.eliminar(medicamento);
				dao.commit();
				vaciarFormulario();
				recuperarTodo();
				vMedicamento.getLblAccionEjecutada().setText(
						"Registro eliminado");
			} catch (Exception e) {
				if (e.getCause().getClass() == ConstraintViolationException.class) {
					JOptionPane
							.showMessageDialog(null,
									"El medicamento se encuentra en uso, no es posible eliminar");
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

		// SE INSTANCIA UN NUEVO CLIENTE
		if (accion.equals("NUEVO")) {
			medicamento = new Medicamento();
			medicamento.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		}

		medicamento.setNombreComercial(vMedicamento.gettNombreComercial().getText().toUpperCase());
		medicamento.setTipoMedicamento(vMedicamento.gettTipoMedicamento().getText().toUpperCase());
		medicamento.setInformacion(vMedicamento.getTxtObservacion().getText().toUpperCase());

		// SE INTENTA INSERTAR EL REGISTRO
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(medicamento);
				vMedicamento.getLblAccionEjecutada().setText(
						"Registro guardado con éxito");
			} else {
				dao.modificar(medicamento);
				System.out.println("Medicamento actualizado");
				vMedicamento.getLblAccionEjecutada().setText(
						"Registro actualizado con éxito");
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
			JOptionPane
					.showMessageDialog(null,
							"El documento o el teléfono ya existen y no pueden ser repetitivos");
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
		vMedicamento.dispose();

	}

	// EVENTO DEL MOUSE
	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == vMedicamento.getTable()) {
			// si se da clic a la tabla se carga el formulario segun la fila
			cargarFormulario(vMedicamento.getTable().getSelectedRow());
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
		if (e.getSource() == vMedicamento.gettBuscador()
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}

		if (e.getSource() == vMedicamento.gettNombreComercial()
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			vMedicamento.gettTipoMedicamento().requestFocus();
		}
		if (e.getSource() == vMedicamento.gettTipoMedicamento()
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			vMedicamento.getTxtObservacion().requestFocus();
		}
		if (e.getSource() == vMedicamento.getTxtObservacion()
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			guardar();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}