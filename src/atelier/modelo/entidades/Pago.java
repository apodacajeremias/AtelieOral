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
public class Pago {
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
	private Date fechaPago;

	@Column(nullable = true)
	private int valorPago;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;

	@Column(nullable = false)
	private boolean estadoPago = true;

	@OneToMany(mappedBy = "pago", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Presupuesto> mensualidad;

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

	public Date getFechaPago() {
		return fechaPago;
	}

	public int getValorPago() {
		return valorPago;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public boolean isEstadoPago() {
		return estadoPago;
	}

	public List<Presupuesto> getMensualidad() {
		return mensualidad;
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

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public void setValorPago(int valorPago) {
		this.valorPago = valorPago;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
	}

	public void setMensualidad(List<Presupuesto> mensualidad) {
		this.mensualidad = mensualidad;
	}

	@Override
	public String toString() {
		return "Pago [id=" + id + ", fechaRegistro=" + fechaRegistro + ", estado=" + estado
				+ ", funcionarioQueRegistra=" + funcionarioQueRegistra + ", fechaPago=" + fechaPago + ", valorPago="
				+ valorPago + ", paciente=" + paciente + ", estadoPago=" + estadoPago;
	}
	
	

}
