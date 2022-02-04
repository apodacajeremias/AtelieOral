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

import atelier.controlador.util.EventosUtil;

@Entity
public class PresupuestoDetalle {
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
	private int cantidad;

	@Column(nullable = true)
	private int precio;

	@Column(nullable = true)
	private int subTotal;

	@Column(nullable = true)
	private String dientes;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Procedimiento procedimiento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Presupuesto presupuesto;

	@ColumnDefault("false")
	@Column(nullable = false)
	private boolean estaFinalizado;

	@OneToMany(mappedBy = "presupuestoDetalle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Seguimiento> seguimientos;

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

	public int getCantidad() {
		return cantidad;
	}

	public int getPrecio() {
		return precio;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public String getDientes() {
		return dientes;
	}

	public Procedimiento getProcedimiento() {
		return procedimiento;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
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

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public void setDientes(String dientes) {
		this.dientes = dientes;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public List<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public boolean isEstaFinalizado() {
		return estaFinalizado;
	}

	public void setEstaFinalizado(boolean estaFinalizado) {
		this.estaFinalizado = estaFinalizado;
	}

	@Override
	public String toString() {
		if (fechaRegistro == null) {
			return presupuesto.getId() + "." + id;
		}
		return presupuesto.getId() + "." + id + " - " + EventosUtil.formatoFecha(fechaRegistro);
	}

}
