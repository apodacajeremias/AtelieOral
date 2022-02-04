package atelier.modelo.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Seguimiento {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = new Date();
	
	@Column(nullable = false)
	private boolean estado = true;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionarioQueRegistra;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;

	@Column(nullable = false)
	private String comentario;
	
	@Column(nullable = false)
	private String diente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private PresupuestoDetalle presupuestoDetalle;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Procedimiento procedimiento;

	public int getId() {
		return id;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public boolean isEstado() {
		return estado;
	}

	public Funcionario getFuncionarioQueRegistra() {
		return funcionarioQueRegistra;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public String getComentario() {
		return comentario;
	}

	public String getDiente() {
		return diente;
	}

	public PresupuestoDetalle getPresupuestoDetalle() {
		return presupuestoDetalle;
	}

	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public void setFuncionarioQueRegistra(Funcionario funcionarioQueRegistra) {
		this.funcionarioQueRegistra = funcionarioQueRegistra;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setDiente(String diente) {
		this.diente = diente;
	}

	public void setPresupuestoDetalle(PresupuestoDetalle presupuestoDetalle) {
		this.presupuestoDetalle = presupuestoDetalle;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

}
