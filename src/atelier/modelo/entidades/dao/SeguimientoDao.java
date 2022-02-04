package atelier.modelo.entidades.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Seguimiento;

public class SeguimientoDao extends GenericDao<Seguimiento> {

	public SeguimientoDao() {
		super(Seguimiento.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Seguimiento> recuperarSeguimientoPaciente(int paciente) {
		getSession().beginTransaction();
		List<Seguimiento> lista = new ArrayList<Seguimiento>();
		String sql = "from Seguimiento " 
				+ "where paciente.id = :paciente "
				+ "order by id DESC";
		try {
			@SuppressWarnings("unchecked")
			Query<Seguimiento> query = getSession().createQuery(sql);
			query.setParameter("paciente", paciente);
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
