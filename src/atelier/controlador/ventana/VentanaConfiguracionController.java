package atelier.controlador.ventana;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Configuracion;
import atelier.modelo.entidades.dao.ConfiguracionDao;
import atelier.modelo.sesion.Sesion;
import atelier.vista.ventana.VentanaConfiguracion;

public class VentanaConfiguracionController implements ActionListener {

	private VentanaConfiguracion vConfiguracion;
	private String accion;
	private ConfiguracionDao dao;
	private Configuracion configuracion;

	public VentanaConfiguracionController(VentanaConfiguracion vConfiguracion) {
		this.vConfiguracion = vConfiguracion;
		dao = new ConfiguracionDao();
		recuperarConfiguracion();
		vConfiguracion.getBtGuardar().addActionListener(this);
		vConfiguracion.getBtCancelar().addActionListener(this);
		estadoInicial(true);
		setUpEvents();

	}

	private void recuperarConfiguracion() {
		configuracion = Sesion.getInstance().getConfiguracion();
		if (configuracion == null) {
			accion = "NUEVO";
		} else {
			accion = "MODIFICAR";
			vConfiguracion.gettNombreEmpresa().setText(configuracion.getEmpresa());
			vConfiguracion.gettContacto().setText(configuracion.getTelefono());
			vConfiguracion.gettContribuyente().setText(configuracion.getRegistroUnico());
			vConfiguracion.gettTitular().setText(configuracion.getTitular());
			vConfiguracion.gettRegistroP().setText(configuracion.getRegistroProfesional());
			vConfiguracion.gettCedula().setText(configuracion.getCedulaTitular());
			vConfiguracion.gettUbicacion().setText(configuracion.getUbicacion());
		}
	}

	// METODO QUE LEVANTA LOS EVENTOS
	private void setUpEvents() {
		vConfiguracion.getBtCancelar().addActionListener(this);
		vConfiguracion.getBtGuardar().addActionListener(this);

	}

	private boolean validarFormulario() {
		if (vConfiguracion.gettNombreEmpresa().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan datos", "Verificar", JOptionPane.ERROR_MESSAGE);
			vConfiguracion.gettNombreEmpresa().requestFocus();
			return false;
		}

		if (vConfiguracion.gettContacto().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan datos", "Verificar", JOptionPane.ERROR_MESSAGE);
			vConfiguracion.gettContacto().requestFocus();
			return false;
		}

		if (vConfiguracion.gettContribuyente().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan datos", "Verificar", JOptionPane.ERROR_MESSAGE);
			vConfiguracion.gettContribuyente().requestFocus();
			return false;
		}

		if (vConfiguracion.gettTitular().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan datos", "Verificar", JOptionPane.ERROR_MESSAGE);
			vConfiguracion.gettTitular().requestFocus();
			return false;
		}

		if (vConfiguracion.gettRegistroP().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan datos", "Verificar", JOptionPane.ERROR_MESSAGE);
			vConfiguracion.gettRegistroP().requestFocus();
			return false;
		}

		if (vConfiguracion.gettCedula().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan datos", "Verificar", JOptionPane.ERROR_MESSAGE);
			vConfiguracion.gettCedula().requestFocus();
			return false;
		}

		if (vConfiguracion.gettUbicacion().getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Faltan datos", "Verificar", JOptionPane.ERROR_MESSAGE);
			vConfiguracion.gettUbicacion().requestFocus();
			return false;
		}
		return true;
	}

	// METODO VACIAR FORMULARIO
	private void vaciarFormulario() {
		EventosUtil.limpiarCampoPersonalizado(vConfiguracion.gettNombreEmpresa());
		EventosUtil.limpiarCampoPersonalizado(vConfiguracion.gettContacto());
		EventosUtil.limpiarCampoPersonalizado(vConfiguracion.gettContribuyente());
		EventosUtil.limpiarCampoPersonalizado(vConfiguracion.gettTitular());
		EventosUtil.limpiarCampoPersonalizado(vConfiguracion.gettRegistroP());

	}

	private void estadoInicial(boolean b) {
		EventosUtil.estadosCampoPersonalizado(vConfiguracion.gettNombreEmpresa(), b);
		EventosUtil.estadosCampoPersonalizado(vConfiguracion.gettContribuyente(), b);
		EventosUtil.estadosCampoPersonalizado(vConfiguracion.gettContacto(), b);
		EventosUtil.estadosCampoPersonalizado(vConfiguracion.gettTitular(), b);
		EventosUtil.estadosCampoPersonalizado(vConfiguracion.gettRegistroP(), b);
		EventosUtil.estadosCampoPersonalizado(vConfiguracion.gettCedula(), b);
		EventosUtil.estadosCampoPersonalizado(vConfiguracion.gettUbicacion(), b);

		vConfiguracion.getLblAccion().setForeground(Color.GREEN);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Guardar":
			guardar();
			break;
		case "Cancelar":
			cancelar();
			break;
		default:
			break;
		}
	}

	private void guardar() {
		if (!validarFormulario()) {
			return;
		}
		if (accion.equals("NUEVO")) {
			configuracion = new Configuracion();

		}
		configuracion.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		configuracion.setTitular(vConfiguracion.gettTitular().getText());
		configuracion.setRegistroProfesional(vConfiguracion.gettRegistroP().getText());
		configuracion.setEmpresa(vConfiguracion.gettNombreEmpresa().getText());
		configuracion.setTelefono(vConfiguracion.gettContacto().getText());
		configuracion.setRegistroUnico(vConfiguracion.gettContribuyente().getText());
		configuracion.setCedulaTitular(vConfiguracion.gettCedula().getText());
		configuracion.setUbicacion(vConfiguracion.gettUbicacion().getText());

		dao = new ConfiguracionDao();
		try {
			if (accion.equals("NUEVO")) {
				dao.insertar(configuracion);
				vConfiguracion.getLblAccion().setText("Guardado");
			} else {
				dao.modificar(configuracion);
				vConfiguracion.getLblAccion().setText("Actualizado");
			}
			dao.commit();
			vConfiguracion.dispose();
		} catch (Exception e) {
			dao.rollBack();
			JOptionPane.showMessageDialog(null, "ERROR");
			vConfiguracion.getLblAccion().setText("No se pudo ejecutar");
			vConfiguracion.getLblAccion().setForeground(Color.RED);
		}

	}

	private void cancelar() {
		vaciarFormulario();
		vConfiguracion.dispose();
	}

}
