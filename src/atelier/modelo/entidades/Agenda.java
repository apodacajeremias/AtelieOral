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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import atelier.controlador.util.EventosUtil;

@Entity
public class Agenda {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = new Date();

	@ColumnDefault("true")
	@Column(nullable = false)
	private boolean estado = true;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Funcionario funcionarioQueRegistra;

	@Column(nullable = false) // not null
	@Temporal(TemporalType.DATE)
	private Date fechaAgenda;

	@Column(nullable = false) // not null
	private String horaAgenda;

	@Column(nullable = false)
	private String motivo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Funcionario medico;

	@Override
	public String toString() {
		return EventosUtil.formatoFecha(fechaAgenda) + " " + horaAgenda;
	}

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

	public Date getFechaAgenda() {
		return fechaAgenda;
	}

	public String getHoraAgenda() {
		return horaAgenda;
	}

	public String getMotivo() {
		return motivo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Funcionario getMedico() {
		return medico;
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

	public void setFechaAgenda(Date fechaAgenda) {
		this.fechaAgenda = fechaAgenda;
	}

	public void setHoraAgenda(String horaAgenda) {
		this.horaAgenda = horaAgenda;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setMedico(Funcionario medico) {
		this.medico = medico;
	}

}
