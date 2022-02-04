package atelier.controlador.ventana;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.exception.ConstraintViolationException;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Funcionario;
import atelier.modelo.entidades.dao.UsuarioDao;
import atelier.modelo.entidades.interfaces.AccionesABM;
import atelier.vista.modelotabla.ModeloTablaFuncionario;
import atelier.vista.ventana.VentanaFuncionario;

public class VentanaFuncionarioController implements AccionesABM,
		MouseListener, KeyListener {

	private VentanaFuncionario vFuncionario;
	private String accion;
	private Funcionario funcionario;
	private UsuarioDao daoF;
	private List<Funcionario> lista;
	private ModeloTablaFuncionario mtFuncionario;

	// CONSTRUCTOR
	public VentanaFuncionarioController(VentanaFuncionario vFuncionario) {
		this.vFuncionario = vFuncionario;
		this.vFuncionario.getMiToolBar().setAcciones(this);
		mtFuncionario = new ModeloTablaFuncionario();
		this.vFuncionario.getTable().setModel(mtFuncionario);
		estadoInicial(true);
		daoF = new UsuarioDao();
		recuperarTodo();
		setUpEvents();
	}

	// AGREGA ESCUCHADORES DE EVENTOS A OS COMPONENTES
	private void setUpEvents() {
		vFuncionario.getTable().addMouseListener(this);
		vFuncionario.gettBuscador().addKeyListener(this);

	}

	private void recuperarTodo() {
		lista = daoF.recuperarTodo();
		mtFuncionario.setLista(lista);
		mtFuncionario.fireTableDataChanged();

	}

	// METODO RECUPERAR POR FILTRO
	private void recuperarPorFiltro() {
		lista = daoF.recuperarPorFiltro(vFuncionario.gettBuscador().getText());
		mtFuncionario.setLista(lista);
		mtFuncionario.fireTableDataChanged();

	}
	
	private void estadoInicial(boolean b) {
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettNombre(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettApellido(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettDocumento(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettDireccion(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.getfFechaNacimiento(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettSexo(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.getRdbEstado(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettTelefono(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettDireccion(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.getRdbEstado(), !b);
		
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettUsuario(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettContrasena(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.getSpTipoAcceso(), !b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.getSpTipoFuncionario(), !b);

		EventosUtil.estadosCampoPersonalizado(vFuncionario.getTable(), b);
		EventosUtil.estadosCampoPersonalizado(vFuncionario.gettBuscador(), b);
	
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtNuevo(), true);
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtSalir(), true);
		
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtModificar(), false);
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtEliminar(), false);
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtCancelar(), false);
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtGuardar(), false);

	}
	
	// METODO VACIAR FORMULARIO
		private void vaciarFormulario() {
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettNombre());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettApellido());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettDireccion());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettSexo());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettTelefono());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettDireccion());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettUsuario());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettDocumento());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettContrasena());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.getfFechaNacimiento());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.getRdbEstado());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.getLblEdad());
			EventosUtil.limpiarCampoPersonalizado(vFuncionario.getLblAccionEjecutada());

			EventosUtil.limpiarCampoPersonalizado(vFuncionario.gettBuscador());
			
			recuperarTodo();
		}


	// METODO CARGAR FORMULARIO
	private void cargarFormulario(int posicion) {

		if (posicion < 0) {
			return;
		}
		funcionario = lista.get(posicion);
		
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtModificar(), true);
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtEliminar(), true);
		EventosUtil.estadosBotones(vFuncionario.getMiToolBar().getbtGuardar(), false);

		vFuncionario.gettNombre().setText(funcionario.getNombre());
		vFuncionario.gettApellido().setText(funcionario.getApellido());
		vFuncionario.gettDocumento().setText(funcionario.getDocIdentidad());
		vFuncionario.gettSexo().setText(funcionario.getSexo());
		vFuncionario.getLblEdad().setText(EventosUtil.calcularEdad(funcionario.getFechaNac()));
		vFuncionario.gettDireccion().setText(funcionario.getDireccion());
		vFuncionario.gettTelefono().setText(funcionario.getTelefono());
		vFuncionario.getRdbEstado().setSelected(funcionario.isEstado());
		vFuncionario.getfFechaNacimiento().setDate(funcionario.getFechaNac());
		vFuncionario.gettUsuario().setText(funcionario.getUsuario());
		vFuncionario.gettContrasena().setText(funcionario.getContras());
		vFuncionario.getSpTipoAcceso().setSelectedItem(funcionario.getTipoAcceso());
		vFuncionario.getSpTipoFuncionario().setSelectedItem(funcionario.getTipoFuncionario());
	}

	// METODO PARA VALIDAR FORMULARIO
	private boolean validarFormulario() {
		// Validar campo vacio
		if (vFuncionario.gettNombre().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El nombre es obligatorio",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettNombre().requestFocus();
			return false;
		}
		if (vFuncionario.gettApellido().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "El apellido es obligatorio",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettApellido().requestFocus();
			return false;
		}

		// si el campo documento esta vacio
		if (vFuncionario.gettDocumento().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"El documento de identidad es una dato importante",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettDocumento().requestFocus();
			return false;
		}
		if (vFuncionario.gettDocumento().getText().equals("0")
				|| vFuncionario.gettDocumento().getText().equals("00")
				|| vFuncionario.gettDocumento().getText().equals("000")
				|| vFuncionario.gettDocumento().getText().equals("0000")
				|| vFuncionario.gettDocumento().getText().equals("00000")
				|| vFuncionario.gettDocumento().getText().equals("000000")
				|| vFuncionario.gettDocumento().getText().equals("0000000")
				|| vFuncionario.gettDocumento().getText().equals("00000000")
				|| vFuncionario.gettDocumento().getText().equals("000000000")
				|| vFuncionario.gettDocumento().getText().equals("0000000000")) {
			JOptionPane.showMessageDialog(null, "El documento no es valido",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettDocumento().requestFocus();
			return false;
		}

		// si el campo direccion esta vacio
		if (vFuncionario.gettDireccion().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "La dirección es obligatoria",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettDireccion().requestFocus();
			return false;
		}

		// validacion de fecha vacia
		if (vFuncionario.getfFechaNacimiento().getDate() == null) {
			JOptionPane.showMessageDialog(null,
					"La fecha de nacimiento está vacia", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			vFuncionario.getfFechaNacimiento().requestFocus();
			return false;
		}
		if (vFuncionario.gettSexo().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"¿Cuál es el género del colaborador", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettSexo().requestFocus();
			return false;
		}

		// si el campo telefono esta vacio
		if (vFuncionario.gettTelefono().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"El campo telefono es obligatorio", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettTelefono().requestFocus();
			return false;
		}

		if (vFuncionario.gettTelefono().getText().equals("0")
				|| vFuncionario.gettTelefono().getText().equals("00")
				|| vFuncionario.gettTelefono().getText().equals("000")
				|| vFuncionario.gettTelefono().getText().equals("0000")
				|| vFuncionario.gettTelefono().getText().equals("00000")
				|| vFuncionario.gettTelefono().getText().equals("000000")
				|| vFuncionario.gettTelefono().getText().equals("0000000")
				|| vFuncionario.gettTelefono().getText().equals("00000000")
				|| vFuncionario.gettTelefono().getText().equals("000000000")
				|| vFuncionario.gettTelefono().getText().equals("0000000000")) {
			JOptionPane.showMessageDialog(null,
					"El telefono ingresado no es valido", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettTelefono().requestFocus();
			return false;
		}

		
		//Si accesos están completos
		if (vFuncionario.gettUsuario().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"El cargo o puesto del empleado debe ser especificado",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettUsuario().requestFocus();
			return false;
		}
		if (String.valueOf(vFuncionario.gettContrasena().getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Se debe especificar una contraseña al usuarioS", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			vFuncionario.gettContrasena().requestFocus();
			return false;
		}
		return true;
	}

	// BOTON NUEVO
	@Override
	public void nuevo() {
		accion = "NUEVO";
		vaciarFormulario();
		estadoInicial(false);
		vFuncionario.gettNombre().requestFocus();
		vFuncionario.getLblAccionEjecutada().setText("Nuevo registro");
	}

	// BOTON MODIFICAR
	@Override
	public void modificar() {
		estadoInicial(false);
		accion = "MODIFICAR";
		vFuncionario.getLblAccionEjecutada().setText("Modificar registro");
	}

	// BOTON ELIMINAR
	@Override
	public void eliminar() {
		int posicion = vFuncionario.getTable().getSelectedRow();
		if (posicion < 0) {
			return;
		}
		if (funcionario.isEstado() == false) {
			JOptionPane.showMessageDialog(null, "El funcionario esta en uso");
			return;
		}
		int respuesta = JOptionPane.showConfirmDialog(null,
				"¿Estás seguro que deseas eliminar el funcionario "
						+ funcionario.getNombre() + "?", "ATENCION",
				JOptionPane.YES_NO_OPTION);
		// SI LA RESPUESTA ES "SI" SE ELIMINARA EL REGISTRO
		if (respuesta == JOptionPane.YES_OPTION) {

			// SE INTENTA ELIMINAR UN REGISTRO
			try {
				daoF.eliminar(funcionario);
				daoF.commit();
				vaciarFormulario();

				recuperarTodo();
				vFuncionario.getLblAccionEjecutada().setText(
						"Registro eliminado con éxito");
			} catch (Exception e) {
				if (e.getCause().getClass() == ConstraintViolationException.class) {
					JOptionPane
							.showMessageDialog(null,
									"No es posible eliminar el funcionario ya que se encuentra en uso");
				}
				daoF.rollBack();
				e.printStackTrace();
			}
		}

	}

	// BOTON GUARDAR
	@Override
	public void guardar() {
		// SI UNO DE LOS CAMPOS ESTAN VACIO NO SE PODRA GUARDAR REGISTRO
		if (!validarFormulario()) {
			return;
		}

		// SI SE PRESIONO EL BOTON NUEVO SE INSTANCIARA UN NUEVO VENDEDOR
		if (accion.equals("NUEVO")) {
			funcionario = new Funcionario();
		}
		funcionario.setNombre(vFuncionario.gettNombre().getText());
		funcionario.setApellido(vFuncionario.gettApellido().getText());
		funcionario.setDocIdentidad(vFuncionario.gettDocumento().getText());
		funcionario.setDireccion(vFuncionario.gettDireccion().getText());
		funcionario.setTelefono(vFuncionario.gettTelefono().getText());
		funcionario.setSexo(vFuncionario.gettSexo().getText());
		funcionario.setEstado(vFuncionario.getRdbEstado().isSelected());
		funcionario.setFechaNac(vFuncionario.getfFechaNacimiento().getDate());
		funcionario.setEdad(EventosUtil.calcularEdad(vFuncionario.getfFechaNacimiento().getDate()));
		
		funcionario.setUsuario(vFuncionario.gettUsuario().getText());
		funcionario.setContras(String.valueOf(vFuncionario.gettContrasena().getPassword()));
		funcionario.setTipoAcceso(vFuncionario.getSpTipoAcceso().getSelectedItem().toString());
		funcionario.setTipoFuncionario(vFuncionario.getSpTipoFuncionario().getSelectedItem().toString());
		
		try {
			if (accion.equals("NUEVO")) {
				daoF.insertar(funcionario);
				vFuncionario.getLblAccionEjecutada().setText("Registro guardado con éxito");
			} else {
				daoF.modificar(funcionario);
				vFuncionario.getLblAccionEjecutada().setText("Registro actualizado con éxito");
			}
			daoF.commit();
			recuperarTodo();
			estadoInicial(true);
			vaciarFormulario();
		} catch (Exception e) {
			if (e.getCause().getClass() == ConstraintViolationException.class) {
				JOptionPane
						.showMessageDialog(null,
								"Existe información como CÉDULA o USUARIO que se encuentra repetido, verifique");
			}
			daoF.rollBack();
		}
	}

	// BOTON CANCELAR
	@Override
	public void cancelar() {
		estadoInicial(true);
		vaciarFormulario();
	}

	// BOTON SALIR
	@Override
	public void salir() {
		vFuncionario.dispose();
	}

	// EVENTO DEL MOUSE
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == vFuncionario.getTable()) {
			cargarFormulario(vFuncionario.getTable().getSelectedRow());
		}

	}

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

	// EVENTO DEL TECLADO
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == vFuncionario.gettBuscador()
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
