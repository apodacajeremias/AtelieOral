package atelier.modelo.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Reposo {
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

	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date fechaReposo = new Date();

	@Column(nullable = true)
	private String comentario;

	@Column(nullable = true)
	private String tiempo;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;

	@JoinColumn(name = "receta_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Recetario receta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Funcionario getFuncionarioQueRegistra() {
		return funcionarioQueRegistra;
	}

	public void setFuncionarioQueRegistra(Funcionario funcionarioQueRegistra) {
		this.funcionarioQueRegistra = funcionarioQueRegistra;
	}

	public Date getFechaReposo() {
		return fechaReposo;
	}

	public void setFechaReposo(Date fechaReposo) {
		this.fechaReposo = fechaReposo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Recetario getReceta() {
		return receta;
	}

	public void setReceta(Recetario receta) {
		this.receta = receta;
	}

	@Override
	public String toString() {
		return "Reposo [id=" + id + ", fechaRegistro=" + fechaRegistro + ", estado=" + estado
				+ ", funcionarioQueRegistra=" + funcionarioQueRegistra + ", fechaReposo=" + fechaReposo
				+ ", comentario=" + comentario + ", tiempo=" + tiempo + ", paciente=" + paciente + ", receta=" + receta
				+ "]";
	}

}
