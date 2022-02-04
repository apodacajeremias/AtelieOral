package atelier.controlador.buscador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import atelier.modelo.entidades.Procedimiento;
import atelier.modelo.entidades.dao.ProcedimientoDao;
import atelier.modelo.entidades.interfaces.ProcedimientoInterface;
import atelier.vista.modelotabla.ModeloTablaProcedimiento;
import atelier.vista.ventana.buscador.BuscadorProcedimiento;

public class BuscadorProcedimientoController implements KeyListener, MouseListener {

	private List<Procedimiento> lista;
	private ProcedimientoDao dao;
	private ModeloTablaProcedimiento mtProcedimiento;
	private BuscadorProcedimiento bProcedimiento;
	private ProcedimientoInterface interfaz;

	public void setInterfaz(ProcedimientoInterface interfaz) {
		this.interfaz = interfaz;
	}

	// METODO CONSTRUCTOR
	public BuscadorProcedimientoController(BuscadorProcedimiento bProcedimiento) {
		this.bProcedimiento = bProcedimiento;

		mtProcedimiento = new ModeloTablaProcedimiento();
		this.bProcedimiento.getTable().setModel(mtProcedimiento);
		dao = new ProcedimientoDao();
		setUpEvents();
		recuperarTodo();
		
		bProcedimiento.getTable().getColumnModel().getColumn(0).setPreferredWidth(250);
	}

	// METODO QUE SIRVE PARA LEVANTAR LOS EVENTOS
	private void setUpEvents() {
		bProcedimiento.gettBuscador().addKeyListener(this);
		bProcedimiento.getTable().addMouseListener(this);
	}

	private void seleccionarRegistro(int posicion) {
		Procedimiento procedimiento = lista.get(posicion);
		interfaz.setProcedimiento(procedimiento);
		bProcedimiento.dispose();

	}

	// METODO QUE RECUPERA CON EL BUSCADOR
	public void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(bProcedimiento.gettBuscador().getText());
		mtProcedimiento.setLista(lista);
		mtProcedimiento.fireTableDataChanged();

	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtProcedimiento.setLista(lista);
		mtProcedimiento.fireTableDataChanged();

	}

	// EVENTO DEL BOTON (ENTER)
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == bProcedimiento.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {

			recuperarPorFiltro();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// EVENTO DEL MOUSE
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == bProcedimiento.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(bProcedimiento.getTable().getSelectedRow());
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
