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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Procedimiento {
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

	@Column(nullable = false, unique = true)
	private String descripcion;

	@Column(nullable = false)
	private int valorIndividual;

	@Column(nullable = false)
	private int valorCooperativa;

	@Column(nullable = false)
	private int valorFamiliar;

	@OneToMany(mappedBy = "procedimiento", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<PresupuestoDetalle> detalle;
	
	@OneToMany(mappedBy = "procedimiento", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Seguimiento> seguimientos;

	@Override
	public String toString() {
		return descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public int getValorIndividual() {
		return valorIndividual;
	}

	public int getValorCooperativa() {
		return valorCooperativa;
	}

	public int getValorFamiliar() {
		return valorFamiliar;
	}

	public List<PresupuestoDetalle> getDetalle() {
		return detalle;
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

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setValorIndividual(int valorIndividual) {
		this.valorIndividual = valorIndividual;
	}

	public void setValorCooperativa(int valorCooperativa) {
		this.valorCooperativa = valorCooperativa;
	}

	public void setValorFamiliar(int valorFamiliar) {
		this.valorFamiliar = valorFamiliar;
	}

	public void setDetalle(List<PresupuestoDetalle> detalle) {
		this.detalle = detalle;
	}

}
