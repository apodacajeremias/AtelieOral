package atelier.modelo.sesion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import atelier.controlador.util.ConexionReporte;
import atelier.modelo.entidades.Agenda;
import atelier.modelo.entidades.Configuracion;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.Pago;
import atelier.modelo.entidades.Presupuesto;
import atelier.modelo.entidades.PresupuestoDetalle;
import atelier.modelo.entidades.Recetario;
import atelier.modelo.entidades.Reposo;
import atelier.modelo.entidades.dao.AgendaDao;
import atelier.modelo.entidades.dao.PagoDao;
import atelier.modelo.entidades.dao.PresupuestoDao;
import atelier.modelo.entidades.dao.RecetarioDao;
import atelier.modelo.entidades.dao.ReposoDao;
import atelier.vista.reporte.ReporteAgenda;
import atelier.vista.reporte.ReportePago;
import atelier.vista.reporte.ReportePresupuesto;
import atelier.vista.reporte.ReporteReceta;
import atelier.vista.reporte.ReporteReposo;
import net.sf.jasperreports.engine.JRException;

public class Metodos {

	private static Metodos metodo;

	private Configuracion configuracion = Sesion.getInstance().getConfiguracion();

	private Metodos() {
	}

	public synchronized static Metodos getInstance() {
		if (metodo == null) {
			metodo = new Metodos();
		}
		return metodo;
	}

	public void imprimirAgendaReporte(List<Agenda> agendas, ReporteAgenda ventana) {
		// Instanciando y pasando los parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("TITULO_REPORTE", "REPORTE DE AGENDA");
		parametros.put("SOLICITANTE",
				"El reporte ha sido solicitado por " + Sesion.getInstance().getFuncionario().toString() + ".");

		if (ventana.getRb5().isSelected()) {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Colaborador: " + ventana.getCbColaborador().getSelectedItem());
		} else {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Todos los colaboradores*.");
		}
		parametros.put("DETALLE_REPORTE", false);
		parametros.put("FILTRO_1",
				ventana.getRb3().isSelected() ? "Estado de registros = AGENDADO." : "Estado de registros = CANCELADO.");
		parametros.put("FILTRO_2", "Periodo de Agenda "
				+ new SimpleDateFormat("MMMM, yyyy").format(ventana.getDateChooser().getDate()).toUpperCase());
		parametros.put("ORDENACION", "Ordenación: Fecha Agenda DESCENDIENTE, Hora Agenda ASCENDIENTE");
		parametros.put("ALEATORIO", aleatorio());

		// Creando reportes
		ConexionReporte<Agenda> conexionReporte = new ConexionReporte<Agenda>();
		try {
			conexionReporte.generarReporte(agendas, parametros, "AgendaReporte");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void imprimirPacienteReporte(List<Paciente> pacientes) {
		// Instanciando y pasando los parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("ALEATORIO", aleatorio());

		// Creando reportes
		ConexionReporte<Paciente> conexionReporte = new ConexionReporte<Paciente>();
		try {
			conexionReporte.generarReporte(pacientes, parametros, "Clientes");
			conexionReporte.ventanaReporte.setLocationRelativeTo(null);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimirPagoReporte(List<Pago> pagos, ReportePago ventana) {
		// Instanciando y pasando los parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("TITULO_REPORTE", "REPORTE DE PAGO");
		parametros.put("SOLICITANTE",
				"El reporte ha sido solicitado por " + Sesion.getInstance().getFuncionario().toString() + ".");

		if (ventana.getRb5().isSelected()) {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Colaborador: " + ventana.getCbColaborador().getSelectedItem());
		} else {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Todos los colaboradores*.");
		}
		parametros.put("DETALLE_REPORTE", false);
		parametros.put("FILTRO_1", ventana.getRb3().isSelected() ? "Estado de registros = PAGO VIGENTE."
				: "Estado de registros = PAGO ANULADO.");
		parametros.put("FILTRO_2", "Periodo de Pago "
				+ new SimpleDateFormat("MMMM, yyyy").format(ventana.getDateChooser().getDate()).toUpperCase());
		parametros.put("ORDENACION", "Ordenación: ID");
		parametros.put("ALEATORIO", aleatorio());

		// Creando reportes
		ConexionReporte<Pago> conexionReporte = new ConexionReporte<Pago>();
		try {
			conexionReporte.generarReporte(pagos, parametros, "PagoReporte");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimirPresupuestoReporte(List<Presupuesto> presupuesto, ReportePresupuesto ventana) {
		// Instanciando y pasando los parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("TITULO_REPORTE", "REPORTE DE PRESUPUESTO");
		parametros.put("SOLICITANTE",
				"El reporte ha sido solicitado por " + Sesion.getInstance().getFuncionario().toString() + ".");

		if (ventana.getRb5().isSelected()) {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Colaborador: " + ventana.getCbColaborador().getSelectedItem());
		} else {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Todos los colaboradores*.");
		}
		parametros.put("DETALLE_REPORTE", false);
		parametros.put("FILTRO_1", ventana.getRb3().isSelected() ? "Estado de registros = VIGENTE."
				: "Estado de registros = ANULADO o PENDIENTE.");
		parametros.put("FILTRO_2", "Periodo de Presupuesto "
				+ new SimpleDateFormat("MMMM, yyyy").format(ventana.getDateChooser().getDate()).toUpperCase());
		parametros.put("ORDENACION", "Ordenación: ID");
		parametros.put("ALEATORIO", aleatorio());

		// Creando reportes
		ConexionReporte<Presupuesto> conexionReporte = new ConexionReporte<Presupuesto>();
		try {
			conexionReporte.generarReporte(presupuesto, parametros, "PresupuestoReporte");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimirRecetarioReporte(List<Recetario> recetas, ReporteReceta ventana) {
		// Instanciando y pasando los parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("TITULO_REPORTE", "REPORTE DE RECETA");
		parametros.put("SOLICITANTE",
				"El reporte ha sido solicitado por " + Sesion.getInstance().getFuncionario().toString() + ".");

		if (ventana.getRb5().isSelected()) {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Colaborador: " + ventana.getCbColaborador().getSelectedItem());
		} else {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Todos los colaboradores*.");
		}
		parametros.put("DETALLE_REPORTE", false);
		parametros.put("FILTRO_1", ventana.getRb3().isSelected() ? "Estado de registros = VIGENTE."
				: "Estado de registros = ANULADO.");
		parametros.put("FILTRO_2", "Periodo de emisión "
				+ new SimpleDateFormat("MMMM, yyyy").format(ventana.getDateChooser().getDate()).toUpperCase());
		parametros.put("ORDENACION", "Ordenación: ID");
		parametros.put("ALEATORIO", aleatorio());

		// Creando reportes
		ConexionReporte<Recetario> conexionReporte = new ConexionReporte<Recetario>();
		try {
			conexionReporte.generarReporte(recetas, parametros, "RecetaReporte");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimirReposoReporte(List<Reposo> reposos, ReporteReposo ventana) {
		// Instanciando y pasando los parametros
		HashMap<String, Object> parametros = new HashMap<>();
		parametros.put("TITULO_REPORTE", "REPORTE DE REPOSO");
		parametros.put("SOLICITANTE",
				"El reporte ha sido solicitado por " + Sesion.getInstance().getFuncionario().toString() + ".");

		if (ventana.getRb5().isSelected()) {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Colaborador: " + ventana.getCbColaborador().getSelectedItem());
		} else {
			parametros.put("TIPO_REPORTE", ventana.getRb5().isSelected() ? "Reporte individual." : "Reporte general.");
			parametros.put("COLABORADOR_OBJETIVO", "Todos los colaboradores*.");
		}
		parametros.put("DETALLE_REPORTE", false);
		parametros.put("FILTRO_1", ventana.getRb3().isSelected() ? "Estado de registros = VIGENTE."
				: "Estado de registros = ANULADO.");
		parametros.put("FILTRO_2", "Periodo de Reposo "
				+ new SimpleDateFormat("MMMM, yyyy").format(ventana.getDateChooser().getDate()).toUpperCase());
		parametros.put("ORDENACION", "Ordenación: ID");
		parametros.put("ALEATORIO", aleatorio());

		// Creando reportes
		ConexionReporte<Reposo> conexionReporte = new ConexionReporte<Reposo>();
		try {
			conexionReporte.generarReporte(reposos, parametros, "ReposoReporte");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimirPresupuestoIndividual(Presupuesto presupuesto, JDialog ventana) {
		HashMap<String, Object> parametros = new HashMap<>();
		// Pasando parametros

		parametros.put("TITULAR_EMPRESA", configuracion.getTitular());
		parametros.put("NOMBRE_EMPRESA", configuracion.getEmpresa());
		parametros.put("REGISTRO_PROFESIONAL", configuracion.getRegistroProfesional());
		parametros.put("REGISTRO_UNICO", configuracion.getRegistroUnico());
		parametros.put("CONTACTO_EMPRESA", configuracion.getTelefono());
		parametros.put("TITULAR_EMPRESA_M", configuracion.getTitular().toUpperCase());
		parametros.put("CEDULA_TITULAR", configuracion.getCedulaTitular());
		parametros.put("UBICACION_EMPRESA", configuracion.getUbicacion());
		parametros.put("TEXTO_CONVENIO", textoConvenio(presupuesto.getPaciente()));
		
		List<Presupuesto> ps = new ArrayList<Presupuesto>();
		ps.add(presupuesto);

		// Creando reportes
		ConexionReporte<Presupuesto> conexionReporte = new ConexionReporte<Presupuesto>();
		try {
			conexionReporte.generarReporte(ps, parametros, "PresupuestoDetalle");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void imprimirRecetaIndividual(Recetario receta, JDialog ventana) {
		HashMap<String, Object> parametros = new HashMap<>();
		// Pasando parametros

		parametros.put("TITULAR_EMPRESA", configuracion.getTitular());
		parametros.put("NOMBRE_EMPRESA", configuracion.getEmpresa());
		parametros.put("REGISTRO_PROFESIONAL", configuracion.getRegistroProfesional());
		parametros.put("REGISTRO_UNICO", configuracion.getRegistroUnico());
		parametros.put("CONTACTO_EMPRESA", configuracion.getTelefono());
		parametros.put("TITULAR_EMPRESA_M", configuracion.getTitular().toUpperCase());
		parametros.put("CEDULA_TITULAR", configuracion.getCedulaTitular());
		parametros.put("UBICACION_EMPRESA", configuracion.getUbicacion());
		parametros.put("TEXTO_CONVENIO", "Sin información.");

		List<Recetario> ps = new ArrayList<Recetario>();
		ps.add(receta);

		// Creando reportes
		ConexionReporte<Recetario> conexionReporte = new ConexionReporte<Recetario>();
		try {
			conexionReporte.generarReporte(ps, parametros, "RecetaDetalle");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void imprimirReposoIndividual(Reposo reposo, JDialog ventana) {
		HashMap<String, Object> parametros = new HashMap<>();
		// Pasando parametros

		parametros.put("TITULAR_EMPRESA", configuracion.getTitular());
		parametros.put("NOMBRE_EMPRESA", configuracion.getEmpresa());
		parametros.put("REGISTRO_PROFESIONAL", configuracion.getRegistroProfesional());
		parametros.put("REGISTRO_UNICO", configuracion.getRegistroUnico());
		parametros.put("CONTACTO_EMPRESA", configuracion.getTelefono());
		parametros.put("TITULAR_EMPRESA_M", configuracion.getTitular().toUpperCase());
		parametros.put("CEDULA_TITULAR", configuracion.getCedulaTitular());
		parametros.put("UBICACION_EMPRESA", configuracion.getUbicacion());
		parametros.put("TEXTO_CONVENIO", "Sin información.");
		parametros.put("PACIENTE_REPOSO", reposo.getPaciente().toString().toUpperCase());
		parametros.put("PACIENTE_CI", reposo.getPaciente().getDocIdentidad());
		parametros.put("COMENTARIO_REPOSO", reposo.getComentario());
		parametros.put("TIEMPO_REPOSO", reposo.getTiempo());

		List<Reposo> ps = new ArrayList<Reposo>();
		ps.add(reposo);

		// Creando reportes
		ConexionReporte<Reposo> conexionReporte = new ConexionReporte<Reposo>();
		try {
			conexionReporte.generarReporte(ps, parametros, "ReposoDetalle");
			conexionReporte.ventanaReporte.setLocationRelativeTo(ventana);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void imprimirRecetaReposo(Recetario receta) {
		HashMap<String, Object> parametros = new HashMap<>();
		// Pasando parametros

		parametros.put("TITULAR_EMPRESA", configuracion.getTitular());
		parametros.put("NOMBRE_EMPRESA", configuracion.getEmpresa());
		parametros.put("REGISTRO_PROFESIONAL", configuracion.getRegistroProfesional());
		parametros.put("REGISTRO_UNICO", configuracion.getRegistroUnico());
		parametros.put("CONTACTO_EMPRESA", configuracion.getTelefono());
		parametros.put("TITULAR_EMPRESA_M", configuracion.getTitular().toUpperCase());
		parametros.put("CEDULA_TITULAR", configuracion.getCedulaTitular());
		parametros.put("UBICACION_EMPRESA", configuracion.getUbicacion());
		parametros.put("ALEATORIO", aleatorio());

		List<Recetario> ps = new ArrayList<Recetario>();
		ps.add(receta);

		// Creando reportes
		ConexionReporte<Recetario> conexionReporte = new ConexionReporte<Recetario>();
		try {
			conexionReporte.generarReporte(ps, parametros, "ReposoReceta02");
			conexionReporte.ventanaReporte.setLocationRelativeTo(null);
			conexionReporte.ventanaReporte.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void anularRegistro(Object registro) {
		if (registro instanceof Agenda) {
			int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirma la cancelación de esta agenda?",
					"ATENCION", JOptionPane.YES_NO_OPTION);
			if (aceptaAnular == JOptionPane.YES_OPTION) {
				Agenda anular = (Agenda) registro;
				AgendaDao dao = new AgendaDao();
				anular.setMotivo("CANCELADO");
				anular.setEstado(false);
				try {
					dao.modificar(anular);
					dao.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dao.rollBack();
				}
			}
		}
		if (registro instanceof Pago) {
			int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirma la anulación de este pago?", "ATENCION",
					JOptionPane.YES_NO_OPTION);
			if (aceptaAnular == JOptionPane.YES_OPTION) {
				Pago anular = (Pago) registro;
				PagoDao dao = new PagoDao();
				anular.setEstadoPago(false);
				anular.setEstado(false);
				try {
					dao.modificar(anular);
					dao.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dao.rollBack();
				}
			}
		}
		if (registro instanceof Presupuesto) {
			int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirma el rechazo de este presupuesto?",
					"ATENCION", JOptionPane.YES_NO_OPTION);
			if (aceptaAnular == JOptionPane.YES_OPTION) {
				Presupuesto anular = (Presupuesto) registro;
				PresupuestoDao dao = new PresupuestoDao();
				anular.setEstado("RECHAZADO");
				try {
					dao.modificar(anular);
					dao.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dao.rollBack();
				}
			}

		}
		
		if (registro instanceof PresupuestoDetalle) {
			int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirma la finalizacion de este procedimiento?",
					"ATENCION", JOptionPane.YES_NO_OPTION);
			if (aceptaAnular == JOptionPane.YES_OPTION) {
				PresupuestoDetalle finalizar = (PresupuestoDetalle) registro;
				PresupuestoDao dao = new PresupuestoDao();
				finalizar.setEstaFinalizado(true);
				try {
					dao.modificar(finalizar.getPresupuesto());
					dao.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dao.rollBack();
				}
			}
		}
		if (registro instanceof Recetario) {
			int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirma la anulación de esta receta?", "ATENCION",
					JOptionPane.YES_NO_OPTION);
			if (aceptaAnular == JOptionPane.YES_OPTION) {
				Recetario anular = (Recetario) registro;
				RecetarioDao dao = new RecetarioDao();
				anular.setEstado(false);
				try {
					dao.modificar(anular);
					dao.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dao.rollBack();
				}
			}
		}
		if (registro instanceof Reposo) {
			int aceptaAnular = JOptionPane.showConfirmDialog(null, "¿Confirma la anulación de este reposo?", "ATENCION",
					JOptionPane.YES_NO_OPTION);
			if (aceptaAnular == JOptionPane.YES_OPTION) {
				Reposo anular = (Reposo) registro;
				ReposoDao dao = new ReposoDao();
				anular.setEstado(false);
				try {
					dao.modificar(anular);
					dao.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					dao.rollBack();
				}
			}
		}
		return;
	}

	private String textoConvenio(Paciente paciente) {
		if (paciente.isEsSocio()) {
			return "Este presupuesto tiene descuentos por convenios. Convenio " + paciente.getConvenio()
					+ ". Nro Socio: " + paciente.getNumeroSocio();
		} else {
			return "Este presupuesto no tiene descuentos por convenios.";
		}
	}

	private String aleatorio() {
		// Random instance
		Random random = new Random();
		int number = random.nextInt();
		// number stores the random integer in decimal form
		String hexadecimal = Integer.toHexString(number);
		return hexadecimal;
	}

}
