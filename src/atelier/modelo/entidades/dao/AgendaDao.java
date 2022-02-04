package atelier.modelo.entidades.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Agenda;

public class AgendaDao extends GenericDao<Agenda> {

	// CONSTRUCTOR
	public AgendaDao() {
		super(Agenda.class);
	}

	public List<Agenda> recuperarTodoOrdenadoHora(Date fecha) {
		getSession().beginTransaction();

		String sql = "from Agenda " + "where fechaAgenda = :fecha " + "order by horaAgenda";

		@SuppressWarnings("unchecked")
		Query<Agenda> query = getSession().createQuery(sql);
		query.setParameter("fecha", fecha);
		List<Agenda> lista = query.getResultList();
		commit();
		return lista;
	}

	public Agenda verificarAgenda(int id, Date fechaAgenda, String horaAgenda, String motivo) {
		getSession().beginTransaction();

		String sql = "from Agenda " + "where medico.id = :id " + "and DATE(fechaAgenda) = :fechaAgenda "
				+ "and horaAgenda = :horaAgenda " + "and motivo = :motivo";

		try {
			@SuppressWarnings("unchecked")
			Query<Agenda> query = getSession().createQuery(sql);
			query.setParameter("id", id);
			query.setParameter("fechaAgenda", fechaAgenda);
			query.setParameter("horaAgenda", horaAgenda);
			query.setParameter("motivo", motivo);
			Agenda agenda = query.getSingleResult();
			commit();
			return agenda;
		} catch (Exception e) {
			rollBack();
			return null;
		}
	}

	public List<Agenda> reporteGeneralAgenda(String motivo, Integer mes, Integer anho) {
		getSession().beginTransaction();

		String sql = "from Agenda "
				+ "where motivo = :motivo "
				+ "and MONTH(fechaAgenda) = :mes "
				+ "and YEAR(fechaAgenda) = :anho "
				+ "order by fechaAgenda DESC, horaAgenda ASC";

		@SuppressWarnings("unchecked")
		Query<Agenda> query = getSession().createQuery(sql);
		query.setParameter("motivo", motivo);
		query.setParameter("mes", mes);
		query.setParameter("anho", anho);
		List<Agenda> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Agenda> reporteMedicoAgenda(int medico, String motivo, Integer mes, Integer anho) {
		getSession().beginTransaction();

		String sql = "from Agenda " 
				+ "where medico.id = :medico " 
				+ "and motivo = :motivo "
				+ "and MONTH(fechaAgenda) = :mes "
				+ "and YEAR(fechaAgenda) = :anho "
				+ "order by fechaAgenda DESC, horaAgenda ASC";

		@SuppressWarnings("unchecked")
		Query<Agenda> query = getSession().createQuery(sql);
		query.setParameter("medico", medico);
		query.setParameter("motivo", motivo);
		query.setParameter("mes", mes);
		query.setParameter("anho", anho);
		List<Agenda> lista = query.getResultList();
		commit();
		return lista;
	}
}
