package atelier.modelo.entidades.dao;

import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Presupuesto;

public class PresupuestoDao extends GenericDao<Presupuesto> {

	public PresupuestoDao() {
		super(Presupuesto.class);
	}

	public List<Presupuesto> recuperarTodos() {

		getSession().beginTransaction();

		String sql = "from Presupuesto order by id desc";

		@SuppressWarnings("unchecked")
		Query<Presupuesto> query = getSession().createQuery(sql);

		List<Presupuesto> lista = query.getResultList();

		commit();

		return lista;
	}

	public List<Presupuesto> recuperarTodos(String estado) {

		getSession().beginTransaction();

		String sql = "from Presupuesto "
				+ "where estado = :estado "
				+ "order by id desc";

		@SuppressWarnings("unchecked")
		Query<Presupuesto> query = getSession().createQuery(sql);

		query.setParameter("estado", estado);

		List<Presupuesto> lista = query.getResultList();

		commit();

		return lista;
	}

	public List<Presupuesto> recuperarPorPaciente(String paciente) {
		getSession().beginTransaction();

		String sql = "from Presupuesto " + "where paciente.id = :paciente " + "order by id desc";

		@SuppressWarnings("unchecked")
		Query<Presupuesto> query = getSession().createQuery(sql);

		int id = 0;
		try {
			id = Integer.parseInt(paciente);
		} catch (Exception e) {

		}
		query.setParameter("paciente", id);

		System.out.println(id);
		List<Presupuesto> lista = query.getResultList();

		commit();

		return lista;
	}

	public List<Presupuesto> recuperarPorPaciente(String paciente, String estado) {
		getSession().beginTransaction();

		String sql = "from Presupuesto "
				+ "where paciente.id = :paciente "
				+ "and estado = :estado "
				+ "order by id desc";

		@SuppressWarnings("unchecked")
		Query<Presupuesto> query = getSession().createQuery(sql);

		int id = 0;
		try {
			id = Integer.parseInt(paciente);
		} catch (Exception e) {

		}
		query.setParameter("paciente", id);
		query.setParameter("estado", estado);

		try {
			List<Presupuesto> lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			rollBack();
			return null;
		}
	}

	public List<Presupuesto> recuperarporCodigo(String codigo) {
		getSession().beginTransaction();

		String sql = "from Presupuesto " + "where id = :id " + "order by id desc";

		@SuppressWarnings("unchecked")
		Query<Presupuesto> query = getSession().createQuery(sql);

		int id = 0;
		try {
			id = Integer.parseInt(codigo);
		} catch (Exception e) {

		}
		query.setParameter("id", id);

		System.out.println(id);
		List<Presupuesto> lista = query.getResultList();

		commit();

		return lista;
	}

	public List<Presupuesto> reporteFuncionarioPresupuesto(String comparador, Integer funcionario, String estado, Integer mes, Integer anho) {
		getSession().beginTransaction();
		String sql = "from Presupuesto " 
				+ "where funcionarioQueRegistra = :funcionario "
				+ "and estado "+comparador+" :estado "
				+ "and MONTH(fechaPresupuesto) = :mes "
				+ "and YEAR(fechaPresupuesto) = :anho "
				+ "order by id desc";

		@SuppressWarnings("unchecked")
		Query<Presupuesto> query = getSession().createQuery(sql);
		query.setParameter("funcionario", funcionario);
		query.setParameter("estado", estado);
		query.setParameter("mes", mes);
		query.setParameter("anho", anho);
		List<Presupuesto> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Presupuesto> reporteGeneralPresupuesto(String comparador, String estado, Integer mes, Integer anho) {
		getSession().beginTransaction();
		String sql = "from Presupuesto " 
				+ "where estado "+comparador+" :estado "
				+ "and MONTH(fechaPresupuesto) = :mes "
				+ "and YEAR(fechaPresupuesto) = :anho "
				+ "order by id desc";

		@SuppressWarnings("unchecked")
		Query<Presupuesto> query = getSession().createQuery(sql);
		query.setParameter("estado", estado);
		query.setParameter("mes", mes);
		query.setParameter("anho", anho);
		List<Presupuesto> lista = query.getResultList();
		commit();
		return lista;
	}

}
