package atelier.modelo.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Funcionario {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro = new Date();

	@ColumnDefault("true")
	@Column(nullable = false)
	private boolean estado = true;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNac;

	@Column
	private String sexo;

	@Column
	private String edad;

	@Column(nullable = false, unique = true) // not null y no repetible
	private String docIdentidad;

	@Column(nullable = false)
	private String telefono;

	@Column(nullable = true) // Puede estar vacío
	private String direccion;

	@ColumnDefault("Sin cargo.")
	@Column(nullable = false)
	private String tipoFuncionario;

	@Column(nullable = false, unique = true)
	private String usuario;

	@Column(nullable = false)
	private String contras;

	@ColumnDefault("Sin acceso.")
	@Column(nullable = false)
	private String tipoAcceso;
	
	@OneToMany(mappedBy = "funcionarioQueRegistra", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Seguimiento> seguimientos;

	@Override
	public String toString() {
		return nombre + " " + apellido;
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

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public String getSexo() {
		return sexo;
	}

	public String getEdad() {
		return edad;
	}

	public String getDocIdentidad() {
		return docIdentidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTipoFuncionario() {
		return tipoFuncionario;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getContras() {
		return contras;
	}

	public String getTipoAcceso() {
		return tipoAcceso;
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public void setDocIdentidad(String docIdentidad) {
		this.docIdentidad = docIdentidad;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setContras(String contras) {
		this.contras = contras;
	}

	public void setTipoAcceso(String tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	
	

}
