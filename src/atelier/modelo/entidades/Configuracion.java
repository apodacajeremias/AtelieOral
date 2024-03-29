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

@Entity
public class Configuracion {
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
	private String empresa;
	
	@Column(nullable = true)
	private String telefono;
	
	@Column(nullable = true)
	private String registroUnico;
	
	@Column(nullable = true)
	private String titular;
	
	@Column(nullable = true)
	private String registroProfesional;
	
	@Column(nullable = true)
	private String cedulaTitular;
	
	@Column(nullable = true)
	private String ubicacion;

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

	public String getEmpresa() {
		return empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getRegistroUnico() {
		return registroUnico;
	}

	public String getTitular() {
		return titular;
	}

	public String getRegistroProfesional() {
		return registroProfesional;
	}

	public String getCedulaTitular() {
		return cedulaTitular;
	}

	public String getUbicacion() {
		return ubicacion;
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

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setRegistroUnico(String registroUnico) {
		this.registroUnico = registroUnico;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public void setRegistroProfesional(String registroProfesional) {
		this.registroProfesional = registroProfesional;
	}

	public void setCedulaTitular(String cedulaTitular) {
		this.cedulaTitular = cedulaTitular;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
