package atelier.modelo.entidades.dao;

import java.util.List;

import org.hibernate.query.Query;

import atelier.modelo.entidades.Medicamento;

public class MedicamentoDao extends GenericDao<Medicamento> {

	//CONSTRUCTOR
	public MedicamentoDao() {
		super(Medicamento.class);
	}

	public List<Medicamento>recuperarPorFiltro(String filtro){
		getSession().beginTransaction();

		String sql = "from Medicamento where upper(nombrecomercial) like :nombre order by nombrecomercial";

		@SuppressWarnings("unchecked")
		Query<Medicamento> query = getSession().createQuery(sql);
		query.setParameter("nombre", "%"+filtro.toUpperCase()+"%");
		List<Medicamento>lista = query.getResultList();
		commit();
		return lista;
	}


	public List<Medicamento> recuperarTodoOrdenadoPorNombre() {

		getSession().beginTransaction();

		String sql="from Medicamento order by nombrecomercial";

		@SuppressWarnings("unchecked")
		Query<Medicamento>query= getSession().createQuery(sql);

		List<Medicamento>lista= query.getResultList();

		commit();

		return lista;


	}

}
