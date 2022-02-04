package atelier.modelo.entidades.dao;

import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Recetario;

public class RecetarioDao extends GenericDao<Recetario> {

	public RecetarioDao() {
		super(Recetario.class);
	}

	public List<Recetario> recuperarTodos() {

		getSession().beginTransaction();

		String sql = "from Recetario order by id desc";

		@SuppressWarnings("unchecked")
		Query<Recetario> query = getSession().createQuery(sql);

		List<Recetario> lista = query.getResultList();

		commit();

		return lista;
	}

	public List<Recetario> recuperarporPaciente(String paciente) {
		getSession().beginTransaction();

		String sql = "from Recetario " + "where paciente.id = :paciente " + "order by id desc";

		@SuppressWarnings("unchecked")
		Query<Recetario> query = getSession().createQuery(sql);

		int id = 0;
		try {
			id = Integer.parseInt(paciente);
		} catch (Exception e) {
			// TODO: handle exception
		}
		query.setParameter("paciente", id);

		System.out.println(id);
		List<Recetario> lista = query.getResultList();

		commit();

		return lista;
	}

	public List<Recetario> reporteMedicoReceta(int funcionario, boolean estado, Integer mes, Integer anho) {
		getSession().beginTransaction();
		String sql = "from Recetario " 
				+ "where funcionarioQueRegistra.id = :funcionario "
				+ "and estado = :estado "
				+ "and MONTH(fechaReceta) = :mes "
				+ "and YEAR(fechaReceta) = :anho " 
				+ "order by id desc";
		try {
			@SuppressWarnings("unchecked")
			Query<Recetario> query = getSession().createQuery(sql);
			query.setParameter("funcionario", funcionario);
			query.setParameter("estado", estado);
			query.setParameter("mes", mes);
			query.setParameter("anho", anho);
			List<Recetario> lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollBack();
			return null;
		}
	}
	
	public List<Recetario> reporteGeneralReceta(boolean estado, Integer mes, Integer anho) {
		getSession().beginTransaction();
		String sql = "from Recetario " 
				+ "where estado = :estado "
				+ "and MONTH(fechaReceta) = :mes "
				+ "and YEAR(fechaReceta) = :anho " 
				+ "order by id desc";
		try {
			@SuppressWarnings("unchecked")
			Query<Recetario> query = getSession().createQuery(sql);
			query.setParameter("estado", estado);
			query.setParameter("mes", mes);
			query.setParameter("anho", anho);
			List<Recetario> lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollBack();
			return null;
		}
	}
}
