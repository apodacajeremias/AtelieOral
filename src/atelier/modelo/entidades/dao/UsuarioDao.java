package atelier.modelo.entidades.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Configuracion;
import atelier.modelo.entidades.Funcionario;

public class UsuarioDao extends GenericDao<Funcionario> {

	public UsuarioDao() {
		super(Funcionario.class);
	}

	public List<Funcionario> recuperarPorFiltro(String filtro) {
		getSession().beginTransaction();
		String sql = "from Funcionario where upper(nombre) like :nombre or upper(apellido) like :apellido order by nombre";

		@SuppressWarnings("unchecked")
		Query<Funcionario> query = getSession().createQuery(sql);
		query.setParameter("nombre", "%" + filtro.toUpperCase() + "%");
		query.setParameter("apellido", "%" + filtro.toUpperCase() + "%");
		@SuppressWarnings("unused")
		int id = 0;
		try {
			id = Integer.parseInt(filtro);
		} catch (Exception e) {
		}

		List<Funcionario> lista = query.getResultList();
		commit();

		return lista;
	}

	public List<Funcionario> recuperarPorRangos(String nDesde, String nHasta, String aDesde, String aHasta,
			Date fnDesde, Date fnHasta, int idDesde, int idHasta, int indiceOrden) {
		String[] opcionesOrden = { "id", "nombre", "fechaNacimiento" };
		getSession().beginTransaction();
		String sql = "from Funcionario where nombre between :nDesde and :nHasta "
				+ "and apellido between :aDesde and :aHasta "
				+ "and (fechaNacimiento >= :fnDesde or :fnDesdeNula = true) "
				+ "and (fechaNacimiento <= :fnHasta or :fnHastaNula = true) " + "and id between :idDesde and :idHasta "
				+ "order by " + opcionesOrden[indiceOrden];

		@SuppressWarnings("unchecked")
		Query<Funcionario> query = getSession().createQuery(sql);

		query.setParameter("nDesde", nDesde);
		query.setParameter("nHasta", nHasta);
		query.setParameter("aDesde", aDesde);
		query.setParameter("aHasta", aHasta);
		query.setParameter("fnDesde", fnDesde);
		query.setParameter("fnDesdeNula", fnDesde == null);
		query.setParameter("fnHasta", fnHasta);
		query.setParameter("fnHastaNula", fnHasta == null);
		query.setParameter("idDesde", idDesde);
		query.setParameter("idHasta", idHasta);

		List<Funcionario> lista = query.getResultList();
		commit();
		return lista;

	}

	public long verificarFuncionarioEnUso(Funcionario funcionario) {
		getSession().beginTransaction();
		String sql = "select count(funcionario) from Renta where funcionario.id = :funcionarioId and estado = false ";

		@SuppressWarnings("unchecked")
		Query<Long> query = getSession().createQuery(sql);
		query.setParameter("funcionarioId", funcionario.getId());
		long cant = query.getSingleResult();
		commit();
		System.out.println(cant);
		return cant;
	}

	public Funcionario verificarAcceso(String c, String u) {
		getSession().beginTransaction();
		String sql = "from Funcionario where upper(usuario) = :u and upper(contras) = :c";
		try {
			@SuppressWarnings("unchecked")
			Query<Funcionario> query = getSession().createQuery(sql);
			query.setParameter("c", c.toUpperCase());
			query.setParameter("u", u.toUpperCase());
			Funcionario funcionario = query.getSingleResult();
			commit();
			return funcionario;
		} catch (Exception e) {
			rollBack();
			return null;
		}
	}

	public List<Funcionario> buscarMedicos() {
		String medico = "Medico";
		getSession().beginTransaction();
		String sql = "from Funcionario where tipoFuncionario = :medico";
		try {
			@SuppressWarnings("unchecked")
			Query<Funcionario> query = getSession().createQuery(sql);
			query.setParameter("medico", medico);
			List<Funcionario> lista = query.getResultList();
			commit();
			return lista;
		} catch (Exception e) {
			rollBack();
			return null;
		}
	}

	public Configuracion recuperarUltimaConfiguracion() {
		getSession().beginTransaction();
		String sql = "from Configuracion order by id ASC";
		try {
			@SuppressWarnings("unchecked")
			Query<Configuracion> query = getSession().createQuery(sql);
			Configuracion resultado = query.getSingleResult();
			commit();
			return resultado;
		} catch (Exception e) {
			rollBack();
			e.printStackTrace();
			return null;
		}

	}

}
