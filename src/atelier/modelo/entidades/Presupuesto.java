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
public class Presupuesto {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPresupuesto = new Date();

	@ManyToOne
	@JoinColumn(nullable = true)
	private Funcionario funcionarioQueRegistra;

	@Column(nullable = true)
	private int sumaTotal;

	@Column(nullable = true)
	private int descuento;

	@Column(nullable = true)
	private int valorPagar;

	@Column(nullable = true)
	private String emitidoPor;

	@Column(nullable = true)
	private String estado = "PENDIENTE";
	
	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean esMensualidad;
	
	@ManyToOne
	@JoinColumn(nullable = true)
	private Pago pago;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Paciente paciente;
	
	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean tieneDescuentoPorConvenio = false;
	
	@Column(nullable = true)
	private String informacionConvenio;

	@OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Familiares> familiares;

	@OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<PresupuestoDetalle> presupuestoDetalle;

	public int getId() {
		return id;
	}

	public Date getFechaPresupuesto() {
		return fechaPresupuesto;
	}

	public Funcionario getFuncionarioQueRegistra() {
		return funcionarioQueRegistra;
	}

	public int getSumaTotal() {
		return sumaTotal;
	}

	public int getDescuento() {
		return descuento;
	}

	public int getValorPagar() {
		return valorPagar;
	}

	public String getEmitidoPor() {
		return emitidoPor;
	}

	public String getEstado() {
		return estado;
	}

	public Pago getPago() {
		return pago;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public List<Familiares> getFamiliares() {
		return familiares;
	}

	public List<PresupuestoDetalle> getPresupuestoDetalle() {
		return presupuestoDetalle;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFechaPresupuesto(Date fechaPresupuesto) {
		this.fechaPresupuesto = fechaPresupuesto;
	}

	public void setFuncionarioQueRegistra(Funcionario funcionarioQueRegistra) {
		this.funcionarioQueRegistra = funcionarioQueRegistra;
	}

	public void setSumaTotal(int sumaTotal) {
		this.sumaTotal = sumaTotal;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public void setValorPagar(int valorPagar) {
		this.valorPagar = valorPagar;
	}

	public void setEmitidoPor(String emitidoPor) {
		this.emitidoPor = emitidoPor;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setFamiliares(List<Familiares> familiares) {
		this.familiares = familiares;
	}

	public void setPresupuestoDetalle(List<PresupuestoDetalle> presupuestoDetalle) {
		this.presupuestoDetalle = presupuestoDetalle;
	}

	public boolean isTieneDescuentoPorConvenio() {
		return tieneDescuentoPorConvenio;
	}

	public String getInformacionConvenio() {
		return informacionConvenio;
	}

	public void setTieneDescuentoPorConvenio(boolean descuentoConvenio) {
		this.tieneDescuentoPorConvenio = descuentoConvenio;
	}

	public void setInformacionConvenio(String informacionConvenio) {
		this.informacionConvenio = informacionConvenio;
	}

	public boolean isEsMensualidad() {
		return esMensualidad;
	}

	public void setEsMensualidad(boolean esMensualidad) {
		this.esMensualidad = esMensualidad;
	}
	
	

	
}
