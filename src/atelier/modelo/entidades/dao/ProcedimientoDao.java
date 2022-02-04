package atelier.modelo.entidades.dao;

import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Procedimiento;

public class ProcedimientoDao extends GenericDao<Procedimiento> {

	public ProcedimientoDao() {

		super(Procedimiento.class);
	}
	
	public List<Procedimiento>recuperarPorFiltro(String filtro){
		getSession().beginTransaction();
		String sql = "from Procedimiento where upper(descripcion) like :descripcion or id= :id order by descripcion";
		
		@SuppressWarnings("unchecked")
		Query<Procedimiento>query = getSession().createQuery(sql);
		query.setParameter("descripcion", "%"+filtro.toUpperCase()+"%");
		int id=0;
		
		try {
			id=Integer.parseInt(filtro);
		} catch (Exception e) {
			
		}
		
		query.setParameter("id", id);
		
		List<Procedimiento>lista = query.getResultList();
		commit();
		return lista;
		
	}
	
	
	
	public List<Procedimiento> recuperarPorRangos(String dDesde, String dHasta, int idDesde, int idHasta, int indiceOrden) {
		String[] opcionesOrden = {"id", "descripcion"};
		getSession().beginTransaction();
		String sql = "from Procedimiento where descripcion between :dDesde and :dHasta "
				
				+ "and id between :idDesde and :idHasta "
				+ "order by "+opcionesOrden[indiceOrden];
		
		@SuppressWarnings("unchecked")
		Query<Procedimiento> query = getSession().createQuery(sql);
		
		query.setParameter("dDesde", dDesde);
		query.setParameter("dHasta", dHasta);
	
		query.setParameter("idDesde", idDesde);
		query.setParameter("idHasta", idHasta);
		
		List<Procedimiento> lista = query.getResultList();
		commit();
		return lista;

	}
	
	public List<Procedimiento> recuperarTodoOrdenadoPorNombre() {

		getSession().beginTransaction();

		String sql = "from Procedimiento order by descripcion";

		@SuppressWarnings("unchecked")
		Query<Procedimiento> query = getSession().createQuery(sql);

		List<Procedimiento> lista = query.getResultList();

		commit();

		return lista;

	}


}
