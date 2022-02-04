package atelier.controlador.buscador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import atelier.modelo.entidades.Funcionario;
import atelier.modelo.entidades.dao.UsuarioDao;
import atelier.modelo.entidades.interfaces.FuncionarioInterface;
import atelier.vista.modelotabla.ModeloTablaFuncionario;
import atelier.vista.ventana.buscador.BuscadorFuncionario;

public class BuscadorFuncionarioController implements KeyListener, MouseListener {

	// ATRIBUTOS
	private BuscadorFuncionario bFuncionario;
	private ModeloTablaFuncionario mtFuncionario;
	private UsuarioDao dao;
	private List<Funcionario> lista;
	private FuncionarioInterface interfaz;
	private Funcionario funcionario;

	// METODO QUE INSTANCIA LA INTERFAZ PARA BUSCADOR VENDEDOR
	public void setInterfaz(FuncionarioInterface interfaz) {
		this.interfaz = interfaz;
	}

	// METODO CONSTRUCTOR
	public BuscadorFuncionarioController(BuscadorFuncionario bFuncionario) {
		this.bFuncionario = bFuncionario;
		mtFuncionario = new ModeloTablaFuncionario();
		this.bFuncionario.getTable().setModel(mtFuncionario);
		dao = new UsuarioDao();
		// agregamos escuchadores de evento
		setUpEvents();
		recuperarTodo();
	}

	// METODO PARA LEVANTAR LOS EVENTOS
	private void setUpEvents() {
		bFuncionario.gettBuscador().addKeyListener(this);
		bFuncionario.getTable().addMouseListener(this);
	}

	// METODO QUE RECUPERA POR FILTRO UNA LISTA DE VENDEDOR
	private void recuperarPorFiltro() {
		lista = dao.recuperarPorFiltro(bFuncionario.gettBuscador().getText());
		mtFuncionario.setLista(lista);
		mtFuncionario.fireTableDataChanged();
	}

	private void recuperarTodo() {
		lista = dao.recuperarTodo();
		mtFuncionario.setLista(lista);
		mtFuncionario.fireTableDataChanged();

	}

	private void seleccionarRegistro(int posicion) {
		funcionario = lista.get(posicion);
		// System.out.println(categoria.toString());
		// Se pasa la categoria seleccionada a la interfaz
		interfaz.setFuncionario(funcionario);
		bFuncionario.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// EVENTO DEL BOTON AL DAR (ENTER)
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == bFuncionario.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			recuperarPorFiltro();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	// EVENTO DEL MOUSE AL DAR DOBLE CLICK
	@Override
	public void mouseClicked(MouseEvent e) {
		// doble clic en un registro de la tabla
		if (e.getSource() == bFuncionario.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(bFuncionario.getTable().getSelectedRow());
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
