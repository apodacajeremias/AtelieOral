package atelier.modelo.entidades.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Familiares;
import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.Pago;
import atelier.modelo.entidades.Presupuesto;
import atelier.modelo.entidades.Recetario;
import atelier.modelo.entidades.Seguimiento;

public class PacienteDao extends GenericDao<Paciente> {

	public PacienteDao() {
		super(Paciente.class);
	}

	public List<Paciente> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();

		String sql = "from Paciente "
				+ "where upper(nombre) like :nombre "
				+ "or upper(apellido) like :apellido "
				+ "or docidentidad like :nroci "
				+ "order by nombre";

		@SuppressWarnings("unchecked")
		Query<Paciente> query = getSession().createQuery(sql);
		query.setParameter("nombre", "%" + filtro.toUpperCase() + "%");
		query.setParameter("apellido", "%" + filtro.toUpperCase() + "%");


		@SuppressWarnings("unused")
		int id = 0;
		try {
			id = Integer.parseInt(filtro);
		} catch (Exception e) {
		}
		query.setParameter("nroci", filtro);

		List<Paciente> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Paciente> recuperarPorRangos(String nombreD, String nombreH, int indiceOrden) {
		String[] opcionesOrden = { "nombre", "id" };
		System.out.println(opcionesOrden[indiceOrden]);
		getSession().beginTransaction();

		String sql = "from Paciente "
				+ "where nombre between :nombreD and :nombreH "
				
				+ "order by "
				+ opcionesOrden[indiceOrden];

		@SuppressWarnings("unchecked")
		Query<Paciente> query = getSession().createQuery(sql);

		query.setParameter("nombreD", nombreD);
		query.setParameter("nombreH", nombreH);

		List<Paciente> lista = query.getResultList();
		commit();
		return lista;

	}

	public long verificarPacienteEnUso(Paciente paciente) {
		getSession().beginTransaction();
		String sql = "select count(paciente) from Presupuesto where paciente.id = :pacienteId";

		@SuppressWarnings("unchecked")
		Query<Long> query = getSession().createQuery(sql);
		query.setParameter("pacienteId", paciente.getId());
		long cant = query.getSingleResult();
		commit();
		System.out.println(cant);
		return cant;
	}

	public List<Paciente> recuperarTodoOrdenadoPorNombre() {

		getSession().beginTransaction();

		String sql = "from Paciente order by nombre";

		@SuppressWarnings("unchecked")
		Query<Paciente> query = getSession().createQuery(sql);

		List<Paciente> lista = query.getResultList();

		commit();

		return lista;
	}
	
	public List<Presupuesto> recuperarPresupuestoPorPaciente(int paciente) {
		getSession().beginTransaction();
		
		List<Presupuesto> lista = new ArrayList<Presupuesto>();
		try {
			String sql = "from Presupuesto "
					+ "where paciente.id = :paciente "
					+ "order by id";
			@SuppressWarnings("unchecked")
			Query<Presupuesto> query = getSession().createQuery(sql);
			query.setParameter("paciente", paciente);
			lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			rollBack();
			return lista;
		}
	}
	
	public List<Presupuesto> recuperarPresupuestoPorFamiliar(int paciente) {
		getSession().beginTransaction();
		
		List<Presupuesto> lista = new ArrayList<Presupuesto>();
		try {
			String sql = "from Familiares "
					+ "where paciente.id = :paciente "
					+ "order by id";
			@SuppressWarnings("unchecked")
			Query<Familiares> query = getSession().createQuery(sql);
			query.setParameter("paciente", paciente);
			List<Familiares> l = query.getResultList();
			commit();
			for (int i = 0; i < l.size(); i++) {
				lista.add(l.get(i).getPresupuesto());
			}
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			rollBack();
			return lista;
		}
	}
	
	public List<Pago> recuperarPagoPorPaciente(int paciente) {
		getSession().beginTransaction();
		
		List<Pago> lista = new ArrayList<Pago>();
		try {
			String sql = "from Pago "
					+ "where paciente.id = :paciente "
					+ "order by id";
			@SuppressWarnings("unchecked")
			Query<Pago> query = getSession().createQuery(sql);
			query.setParameter("paciente", paciente);
			lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			rollBack();
			return lista;
		}
	}
	
	public List<Seguimiento> recuperarSeguimientoPorPaciente(int paciente) {
		getSession().beginTransaction();
		
		List<Seguimiento> lista = new ArrayList<Seguimiento>();
		try {
			String sql = "from Seguimiento "
					+ "where paciente.id = :paciente "
					+ "order by id";
			@SuppressWarnings("unchecked")
			Query<Seguimiento> query = getSession().createQuery(sql);
			query.setParameter("paciente", paciente);
			lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			rollBack();
			return lista;
		}
	}
	
	public List<Recetario> recuperarRecetaPorPaciente(int paciente) {
		getSession().beginTransaction();
		
		List<Recetario> lista = new ArrayList<Recetario>();
		try {
			String sql = "from Recetario "
					+ "where paciente.id = :paciente "
					+ "order by id";
			@SuppressWarnings("unchecked")
			Query<Recetario> query = getSession().createQuery(sql);
			query.setParameter("paciente", paciente);
			lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			rollBack();
			return lista;
		}
	}

}
