package atelier.modelo.sesion;

import java.util.List;

import javax.swing.JOptionPane;

import atelier.modelo.entidades.Configuracion;
import atelier.modelo.entidades.Funcionario;

public class Sesion {
	private static Sesion sesion;

	private Funcionario funcionario;

	private List<Funcionario> funcionarios;
	
	private Configuracion configuracion;

	private Sesion() {
	}

	// synchronized por si dos o mas hilos llaman al metodo al mismo tiempo
	// entonces el primero ejecuta y el segundo aguarda
	public synchronized static Sesion getInstance() {
		if (sesion == null) {
			sesion = new Sesion();
		}
		return sesion;
	}

	public Funcionario getFuncionario() {
		if (funcionario == null) {
			JOptionPane.showMessageDialog(null, "Sin información de acceso.");
		}
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Configuracion getConfiguracion() {
		if (configuracion == null) {
			JOptionPane.showMessageDialog(null, "Sin información de configuración.");
		}
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}
	
	

}
