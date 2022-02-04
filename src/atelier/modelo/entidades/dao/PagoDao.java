package atelier.modelo.entidades.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Pago;

public class PagoDao extends GenericDao<Pago> {

	// CONSTRUCTOR
	public PagoDao() {
		super(Pago.class);
	}

	public List<Pago> recuperarPorFecha(Date fecha) {
		getSession().beginTransaction();
		String sql = "from Pago " + "where fechaPago = :fecha " + "order by id";

		@SuppressWarnings("unchecked")
		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("fecha", fecha);
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Pago> recuperarPorPaciente(int id) {
		getSession().beginTransaction();

		String sql = "from Pago " + "where paciente.id = :id " + "and estado = true " + "order by id";

		@SuppressWarnings("unchecked")
		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("id", id);
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Pago> recuperarTodoPorEstado(boolean estado) {
		getSession().beginTransaction();

		String sql = "from Pago " + "where estado = :estado " + "order by id";

		@SuppressWarnings("unchecked")
		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("estado", estado);
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Pago> recuperarEstadoPorPaciente(int paciente, boolean estado) {
		getSession().beginTransaction();

		String sql = "from Pago " + "where paciente.id = :paciente " + "and estado = :estado " + "order by id";

		@SuppressWarnings("unchecked")
		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("paciente", paciente);
		query.setParameter("estado", estado);
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Pago> reporteFuncionarioPago(int funcionario, boolean estadoPago, Integer mes, Integer anho) {
		getSession().beginTransaction();

		String sql = "from Pago " 
				+ "where funcionarioQueRegistra.id = :funcionario " 
				+ "and estadoPago = :estadoPago "
				+ "and MONTH(fechaPago) = :mes "
				+ "and YEAR(fechaPago) = :anho "
				+ "order by id";

		@SuppressWarnings("unchecked")
		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("funcionario", funcionario);
		query.setParameter("estadoPago", estadoPago);		
		query.setParameter("mes", mes);
		query.setParameter("anho", anho);
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}

	public List<Pago> reporteGeneralPago(boolean estadoPago, Integer mes, Integer anho) {
		getSession().beginTransaction();

		String sql = "from Pago " 
				+ "where estadoPago = :estadoPago "
				+ "and MONTH(fechaPago) = :mes "
				+ "and YEAR(fechaPago) = :anho "
				+ "order by id";

		@SuppressWarnings("unchecked")
		Query<Pago> query = getSession().createQuery(sql);
		query.setParameter("estadoPago", estadoPago);		
		query.setParameter("mes", mes);
		query.setParameter("anho", anho);
		List<Pago> lista = query.getResultList();
		commit();
		return lista;
	}
}
