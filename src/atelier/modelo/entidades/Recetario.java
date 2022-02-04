package atelier.modelo.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Recetario {
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

	@Column(nullable = false)
	private String observacion;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date fechaReceta = new Date();

	@OneToMany(mappedBy = "recetario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<RecetarioDetalle> detalle;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;

	@OneToOne(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Reposo reposo;

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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaReceta() {
		return fechaReceta;
	}

	public void setFechaReceta(Date fechaReceta) {
		this.fechaReceta = fechaReceta;
	}

	public List<RecetarioDetalle> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<RecetarioDetalle> detalle) {
		this.detalle = detalle;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Reposo getReposo() {
		return reposo;
	}

	public void setReposo(Reposo reposo) {
		this.reposo = reposo;
	}

	@Override
	public String toString() {
		return "Recetario [id=" + id + ", fechaRegistro=" + fechaRegistro + ", estado=" + estado
				+ ", funcionarioQueRegistra=" + funcionarioQueRegistra + ", observacion=" + observacion
				+ ", fechaReceta=" + fechaReceta + ", detalle=" + detalle + ", paciente=" + paciente + ", reposo="
				+ reposo + "]";
	}

}
