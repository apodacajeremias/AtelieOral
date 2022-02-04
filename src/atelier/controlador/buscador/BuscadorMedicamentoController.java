package atelier.controlador.buscador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import atelier.modelo.entidades.Medicamento;
import atelier.modelo.entidades.dao.MedicamentoDao;
import atelier.modelo.entidades.interfaces.MedicamentoInterface;
import atelier.vista.modelotabla.ModeloTablaMedicamento;
import atelier.vista.ventana.buscador.BuscadorMedicamento;

public class BuscadorMedicamentoController implements KeyListener, MouseListener {

	// ATRIBUTOS
	private BuscadorMedicamento bMedicamento;
	private ModeloTablaMedicamento mtMedicamento;
	private MedicamentoDao dao;
	private List<Medicamento> lista;
	private MedicamentoInterface interfaz;
	private Medicamento medicamento;

	public void setInterfaz(MedicamentoInterface interfaz) {
		this.interfaz = interfaz;
	}

	// CONSTRUCTOR
	public BuscadorMedicamentoController(BuscadorMedicamento bMedicamento) {
		this.bMedicamento = bMedicamento;
		mtMedicamento = new ModeloTablaMedicamento();
		this.bMedicamento.getTable().setModel(mtMedicamento);
		dao = new MedicamentoDao();
		// agregamos escuchadores de evento
		setUpEvents();
		recuperarTodo();
	}

	// METODO QUE LEVANTA LOS EVENTOS
	private void setUpEvents() {
		bMedicamento.gettBuscador().addKeyListener(this);
		bMedicamento.getTable().addMouseListener(this);
	}

	// METODO QUE RECUPERA DATOS POR FILTRO EN EL BUSCADOR
	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(bMedicamento.gettBuscador().getText());
		mtMedicamento.setLista(lista);
		mtMedicamento.fireTableDataChanged();
	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtMedicamento.setLista(lista);
		mtMedicamento.fireTableDataChanged();

	}

	private void seleccionarRegistro(int posicion) {
		if (posicion < 0) {
			return;
		}
		medicamento = lista.get(posicion);
		interfaz.setMedicamento(medicamento);
		bMedicamento.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// EVENTO DEL BOTON AL DAR CLIC RECUPERA POR FILTRO
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == bMedicamento.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	// EVENTO DEL MOUSE AL DAR DOBLE CLIC VA A SELECCIONAR UN REGISTRO
	@Override
	public void mouseClicked(MouseEvent e) {
		// doble clic en un registro de la tabla
		if (e.getSource() == bMedicamento.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(bMedicamento.getTable().getSelectedRow());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
