package atelier.controlador.buscador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import atelier.modelo.entidades.Paciente;
import atelier.modelo.entidades.dao.PacienteDao;
import atelier.modelo.entidades.interfaces.PacienteInterface;
import atelier.vista.modelotabla.ModeloTablaPaciente;
import atelier.vista.ventana.VentanaPacientePerfil;
import atelier.vista.ventana.buscador.BuscadorPaciente;

public class BuscadorPacienteControlador implements KeyListener, MouseListener {

	// ATRIBUTOS
	private BuscadorPaciente bPaciente;
	private ModeloTablaPaciente mtPaciente;
	private PacienteDao dao;
	private List<Paciente> lista;
	private PacienteInterface interfaz;
	private Paciente paciente;

	private boolean familiar = false;

	public void setInterfaz(PacienteInterface interfaz) {
		this.interfaz = interfaz;
	}

	// CONSTRUCTOR
	public BuscadorPacienteControlador(BuscadorPaciente bPaciente) {
		this.bPaciente = bPaciente;
		mtPaciente = new ModeloTablaPaciente();
		this.bPaciente.getTable().setModel(mtPaciente);

		dao = new PacienteDao();

		// agregamos escuchadores de evento
		setUpEvents();
		recuperarTodo();
	}

	// METODO QUE LEVANTA LOS EVENTOS
	private void setUpEvents() {
		bPaciente.gettBuscador().addKeyListener(this);
		bPaciente.getTable().addMouseListener(this);
	}

	// METODO QUE RECUPERA DATOS POR FILTRO EN EL BUSCADOR
	private void recuperarPorFiltro() {
		paciente = null;
		lista = dao.recuperarPorFiltro(bPaciente.gettBuscador().getText());
		mtPaciente.setLista(lista);
	}

	private void recuperarTodo() {
		lista = dao.recuperarTodoOrdenadoPorNombre();
		mtPaciente.setLista(lista);

	}

	private void seleccionarRegistro(int posicion) {
		if (posicion < 0) {
			return;
		}
		paciente = lista.get(posicion);

		if (familiar) {
			interfaz.setFamiliar(paciente);
			bPaciente.dispose();
		}
		if (!familiar) {
			try {
				interfaz.setPaciente(paciente);
				bPaciente.dispose();
			} catch (Exception e) {
				abrirVentanaPacientePerfil(paciente);
				e.printStackTrace();
				return;
			}
		}
	}

	private void abrirVentanaPacientePerfil(Paciente p) {
		VentanaPacientePerfil ventana = new VentanaPacientePerfil();
		ventana.setUpControlador();
		ventana.getControlador().setPaciente(p);
		ventana.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// EVENTO DEL BOTON AL DAR CLIC RECUPERA POR FILTRO
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == bPaciente.gettBuscador() && e.getKeyCode() == KeyEvent.VK_ENTER) {
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
		if (e.getSource() == bPaciente.getTable() && e.getClickCount() == 2) {
			seleccionarRegistro(bPaciente.getTable().getSelectedRow());
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

	public void setFamiliar(boolean familiar) {
		this.familiar = familiar;
	}

}
