package atelier.modelo.entidades.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Reposo;

public class ReposoDao extends GenericDao<Reposo> {

	public ReposoDao() {
		super(Reposo.class);
	}

	public List<Reposo> recuperarPorCodigo(int id) {
		getSession().beginTransaction();
		String sql = "from Reposo " + "where id = :id ";

		@SuppressWarnings("unchecked")
		Query<Reposo> query = getSession().createQuery(sql);
		query.setParameter("id", id);

		List<Reposo> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Reposo> reporteMedicoReposo(int id, boolean estado, Integer mes, Integer anho) {
		getSession().beginTransaction();
		List<Reposo> lista = new ArrayList<Reposo>();
		String sql = "from Reposo " 
				+ "where funcionarioQueRegistra.id = :id " 
				+ "and estado = :estado "
				+ "and MONTH(fechaReposo) = :mes " 
				+ "and YEAR(fechaReposo) = :anho " 
				+ "order by id";
		try {
			@SuppressWarnings("unchecked")
			Query<Reposo> query = getSession().createQuery(sql);
			query.setParameter("id", id);
			query.setParameter("estado", estado);
			query.setParameter("mes", mes);
			query.setParameter("anho", anho);
			lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollBack();
			return lista;
		}
	}

	public List<Reposo> reporteGeneralReposo(boolean estado, Integer mes, Integer anho) {
		getSession().beginTransaction();
		List<Reposo> lista = new ArrayList<Reposo>();
		String sql = "from Reposo " 
				+ "where estado = :estado " 
				+ "and MONTH(fechaReposo) = :mes "
				+ "and YEAR(fechaReposo) = :anho " 
				+ "order by id";
		try {
			@SuppressWarnings("unchecked")
			Query<Reposo> query = getSession().createQuery(sql);
			query.setParameter("estado", estado);
			query.setParameter("mes", mes);
			query.setParameter("anho", anho);
			lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollBack();
			return lista;
		}
	}
}
