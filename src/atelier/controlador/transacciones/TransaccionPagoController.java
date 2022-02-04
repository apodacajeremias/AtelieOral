package atelier.controlador.transacciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import org.hibernate.exception.ConstraintViolationException;

import atelier.controlador.util.EventosUtil;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.Pago;
import atelier.modelo.entidades.Presupuesto;
import atelier.modelo.entidades.PresupuestoDetalle;
import atelier.modelo.entidades.Procedimiento;
import atelier.modelo.entidades.dao.PagoDao;
import atelier.modelo.entidades.dao.PresupuestoDao;
import atelier.modelo.entidades.interfaces.AccionesABM;
import atelier.modelo.entidades.interfaces.PacienteInterface;
import atelier.modelo.entidades.interfaces.ProcedimientoInterface;
import atelier.modelo.sesion.Sesion;
import atelier.vista.modelotabla.ModeloTablaPago;
import atelier.vista.ventana.buscador.BuscadorPaciente;
import atelier.vista.ventana.buscador.BuscadorProcedimiento;
import atelier.vista.ventana.transacciones.TransaccionPago;

public class TransaccionPagoController implements AccionesABM, PropertyChangeListener, ActionListener, MouseListener, KeyListener, PacienteInterface, ProcedimientoInterface {
	private TransaccionPago tPago;
	private ModeloTablaPago modeloTablaPago;
	private PagoDao dao;
	private List<Pago> lista;
	private Paciente paciente;

	private PresupuestoDao daoP;
	private List<Presupuesto> listaP;

	private Pago pago;

	private List<Presupuesto> presupuestos = new ArrayList<Presupuesto>();
	private Procedimiento procedimiento;

	public TransaccionPagoController(TransaccionPago transaccionPago) {
		this.tPago = transaccionPago;
		this.modeloTablaPago = new ModeloTablaPago();
		this.tPago.getTable().setModel(modeloTablaPago);

		tPago.getTable().getColumnModel().getColumn(0).setPreferredWidth(70);
		tPago.getTable().getColumnModel().getColumn(1).setPreferredWidth(70);
		tPago.getTable().getColumnModel().getColumn(2).setPreferredWidth(200);

		DefaultTableCellRenderer derecha = new DefaultTableCellRenderer();
		derecha.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centro = new DefaultTableCellRenderer();
		centro.setHorizontalAlignment(SwingConstants.CENTER);

		tPago.getTable().getColumnModel().getColumn(0).setCellRenderer(centro);
		tPago.getTable().getColumnModel().getColumn(1).setCellRenderer(centro);

		dao = new PagoDao();
		daoP = new PresupuestoDao();
		estadoInicial(true);
		setUpEvents();
	}

	private void setUpEvents() {
		this.tPago.getBtnBuscar().addActionListener(this);
		this.tPago.getBtnBuscarPorPaciente().addActionListener(this);
		this.tPago.getBtnCancelar().addActionListener(this);
		this.tPago.getBtnConfirmar().addActionListener(this);
		this.tPago.getBtnSalir().addActionListener(this);
		this.tPago.getRdPagarMensualidad().addMouseListener(this);

	}
	private void estadoInicial(boolean b) {
		EventosUtil.estadosBotones(tPago.getBtnCancelar(), !b);
		EventosUtil.estadosBotones(tPago.getBtnConfirmar(), !b);

		EventosUtil.limpiarCampoPersonalizado(tPago.getLblPaciente());
		EventosUtil.limpiarCampoPersonalizado(tPago.getLblPacienteApellido());
		EventosUtil.limpiarCampoPersonalizado(tPago.gettIngresarValor());
		EventosUtil.limpiarCampoPersonalizado(tPago.gettSumaTotal());
		EventosUtil.limpiarCampoPersonalizado(tPago.gettValorPago());
		EventosUtil.limpiarCampoPersonalizado(tPago.gettValorPendiente());
		EventosUtil.limpiarCampoPersonalizado(tPago.getlMensualidad());
		EventosUtil.limpiarCampoPersonalizado(tPago.getlSaldoIngresado());
		EventosUtil.limpiarCampoPersonalizado(tPago.getRdPagarMensualidad());

		EventosUtil.estadosCampoPersonalizado(tPago.getTable(), false);

		EventosUtil.estadosCampoPersonalizado(tPago.getPanelPago(), !b);

		recuperarPorFecha();

	}

	private void recuperarPorFecha(){
		Date date = new Date();
		long d = date.getTime(); //guardamos en un long el tiempo
		java.sql.Date fecha = new java.sql.Date(d);// parseamos al formato del sql  

		tPago.getLblFechaSeleccionada().setText(EventosUtil.formatoFecha(fecha));
		lista = dao.recuperarPorFecha(fecha);
		modeloTablaPago.setLista(lista);
		modeloTablaPago.fireTableDataChanged();
		sumarPagos();
	}

	private void recuperarPorPaciente(){
		lista = dao.recuperarPorPaciente(paciente.getId());
		modeloTablaPago.setLista(lista);
		modeloTablaPago.fireTableDataChanged();
		sumarPagos();
		System.out.println("recuperarPorPaciente");
	}

	private void calcularDeudas() {
		lista = dao.recuperarEstadoPorPaciente(paciente.getId(), true);
		listaP = daoP.recuperarPorPaciente(paciente.getId()+"", "APROBADO");
		int sumaTotal = 0;
		int valorPendiente = 0;
		int valorPago = 0;

		try {
			for (int i = 0; i < listaP.size(); i++) {
				sumaTotal += listaP.get(i).getValorPagar();
				tPago.gettSumaTotal().setValue((double) sumaTotal);
			}
		} catch (Exception e) {
			tPago.gettSumaTotal().setValue((double) sumaTotal);
		}
		try {
			for (int i = 0; i < lista.size(); i++) {
				valorPago += lista.get(i).getValorPago();
				tPago.gettValorPago().setText(String.valueOf(valorPago));
			}
		} catch (Exception e) {
			tPago.gettValorPago().setText(String.valueOf(valorPago));
		}
		try {
			valorPendiente = sumaTotal - valorPago;
			tPago.gettValorPendiente().setText(String.valueOf(valorPendiente));
		} catch (Exception e) {
			tPago.gettValorPendiente().setText(String.valueOf(valorPendiente));
		}

	}
	private void sumarPagos() {
		int suma = 0;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).isEstadoPago() == true) {
				suma = suma + lista.get(i).getValorPago();
			}
		}
		tPago.getlSaldoIngresado().setText("Ingreso: "+EventosUtil.separadorMiles((double) suma)+" Gs.");

	}

	private void generarMensualidad() {
		if (paciente == null) {
			return;
		}
		if (procedimiento == null) {
			return;
		}
		List<PresupuestoDetalle> detalles = new ArrayList<PresupuestoDetalle>();
		PresupuestoDetalle detalle = new PresupuestoDetalle();
		Presupuesto presupuesto = new Presupuesto();

		presupuesto.setDescuento(0);
		presupuesto.setEmitidoPor("PRESUPUESTO EN CAJA");
		presupuesto.setEstado("APROBADO");
		presupuesto.setPaciente(paciente);
		presupuesto.setEsMensualidad(true);
		presupuesto.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());


		detalle.setCantidad(1);
		detalle.setDientes("");
		detalle.setPresupuesto(presupuesto);
		detalle.setProcedimiento(procedimiento);

		if (paciente.isEsSocio()) {
			detalle.setPrecio(procedimiento.getValorCooperativa());
			detalle.setSubTotal(procedimiento.getValorCooperativa());
			presupuesto.setSumaTotal(procedimiento.getValorCooperativa());
			presupuesto.setValorPagar(procedimiento.getValorCooperativa());
		} else {
			detalle.setPrecio(procedimiento.getValorIndividual());
			detalle.setSubTotal(procedimiento.getValorIndividual());
			presupuesto.setSumaTotal(procedimiento.getValorIndividual());
			presupuesto.setValorPagar(procedimiento.getValorIndividual());
		}

		detalles.add(detalle);
		presupuesto.setPresupuestoDetalle(detalles);

		presupuestos.add(presupuesto);

	}

	private boolean validarPago() {
		int valorPendiente = Integer.parseInt(tPago.gettValorPendiente().getText());
		int valorIngresado = Integer.parseInt(tPago.gettIngresarValor().getText());
		if (valorPendiente < valorIngresado) {
			JOptionPane.showMessageDialog(tPago, "El valor del pago supera el valor pendiente");
			tPago.gettIngresarValor().requestFocus();
			return false;
		}
		if (valorIngresado == 0) {
			JOptionPane.showMessageDialog(tPago, "El valor del pago no es válido");
			tPago.gettIngresarValor().requestFocus();
			return false;
		}

		return true;

	}


	@Override
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
		gestionarPaciente();

	}
	private void gestionarPaciente() {
		if (paciente == null) {
			return;
		}
		estadoInicial(false);
		tPago.getLblPaciente().setText(paciente.getNombre());
		tPago.getLblPacienteApellido().setText(paciente.getApellido());
		calcularDeudas();

	}

	private void abrirBuscadorPaciente() {
		BuscadorPaciente buscador = new BuscadorPaciente();
		buscador.setUpControlador();
		buscador.getControlador().setInterfaz(this);
		buscador.setVisible(true);
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == tPago.getRdPagarMensualidad() && !tPago.getRdPagarMensualidad().isSelected()) {
			if (paciente != null) {
				abrirBuscadorProcedimiento();
			}
		}
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
		case "Buscar":
			abrirBuscadorPaciente();
			break;
		case "BuscarPaciente":
			recuperarPorPaciente();
			break;
		case "ConfirmarPago":
			guardar();
			break;
		case "Cancelar":
			estadoInicial(false);
			break;
		case "Salir":
			salir();
			break;
		default:
			break;
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nuevo() {
		estadoInicial(false);		
	}

	@Override
	public void modificar() {
		estadoInicial(false);

	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardar() {
		if (!tPago.getRdPagarMensualidad().isSelected()) {
			if (!validarPago()) {
				return;
			}
		}

		if (paciente == null) {
			return;
		}		
		generarMensualidad();

		pago = new Pago();
		pago.setFuncionarioQueRegistra(Sesion.getInstance().getFuncionario());
		pago.setPaciente(paciente);
		pago.setFechaPago(new Date());
		pago.setValorPago(Integer.parseInt(tPago.gettIngresarValor().getText()));

		if (tPago.getRdPagarMensualidad().isSelected()) {
			pago.setMensualidad(presupuestos);
			for (int i = 0; i < presupuestos.size(); i++) {
				presupuestos.get(i).setPago(pago);
			}
		}
		try {
			dao.insertar(pago);
			dao.commit();
			estadoInicial(true);
		} catch (Exception e) {
			if (e.getCause().getClass() == ConstraintViolationException.class) {
			}
			dao.rollBack();
		}
	}

	@Override
	public void cancelar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void salir() {
		tPago.dispose();

	}

	@Override
	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;

		gestionarProcedimiento();

	}

	private void gestionarProcedimiento() {
		if (procedimiento == null) {
			tPago.getRdPagarMensualidad().setSelected(false);
			tPago.getlMensualidad().setText("");
			return;
		}
		if (paciente == null) {
			return;
		}

		tPago.getlMensualidad().setText(procedimiento.toString());
		if (paciente.isEsSocio()) {
			tPago.gettIngresarValor().setValue((double) procedimiento.getValorCooperativa());
		} else {
			tPago.gettIngresarValor().setValue((double) procedimiento.getValorIndividual());
		}


	}
	private void abrirBuscadorProcedimiento() {
		BuscadorProcedimiento buscador = new BuscadorProcedimiento();
		buscador.setUpController();
		buscador.gettBuscador().setText("MENSUAL");
		buscador.getController().recuperarPorFiltro();
		buscador.getController().setInterfaz(this);
		buscador.setVisible(true);

		buscador.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if (procedimiento == null) {
					tPago.getRdPagarMensualidad().setSelected(false);
				}

			}
		});
	}

	@Override
	public void setFamiliar(Paciente paciente) {
		// TODO Auto-generated method stub

	}

}


