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
public class Paciente {
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
	private String nombre;

	@Column(nullable = false)
	private String apellido;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaNac;

	@Column(nullable = true)
	private String sexo;

	@Column(nullable = true)
	private String edad;

	@Column(nullable = false, unique = true)
	private String docIdentidad;

	@Column(nullable = true)
	private String telefono;

	@Column(nullable = true)
	private String direccion;

	@Column(nullable = true)
	private String observacion;

	@Column(nullable = true)
	private String convenio;

	@Column(nullable = true)
	private boolean esSocio;

	@Column(nullable = true)
	private String numeroSocio;

	@ColumnDefault("0")
	@Column(nullable = false)
	private double pagos;

	@ColumnDefault("0")
	@Column(nullable = false)
	private double deudas;

	@ColumnDefault("0")
	@Column(nullable = false)
	private double diferencia;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Presupuesto> presupuesto;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Recetario> recetario;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Agenda> agenda;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Reposo> reposo;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Pago> pago;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
	private List<Familiares> familiares;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
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

	public Funcionario getFuncionarioQueRegistra() {
		return funcionarioQueRegistra;
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

	public String getObservacion() {
		return observacion;
	}

	public String getConvenio() {
		return convenio;
	}

	public boolean isEsSocio() {
		return esSocio;
	}

	public String getNumeroSocio() {
		return numeroSocio;
	}

	public List<Presupuesto> getPresupuesto() {
		return presupuesto;
	}

	public List<Recetario> getRecetario() {
		return recetario;
	}

	public List<Agenda> getAgenda() {
		return agenda;
	}

	public List<Reposo> getReposo() {
		return reposo;
	}

	public List<Pago> getPago() {
		return pago;
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

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public void setEsSocio(boolean esSocio) {
		this.esSocio = esSocio;
	}

	public void setNumeroSocio(String numeroSocio) {
		this.numeroSocio = numeroSocio;
	}

	public void setPresupuesto(List<Presupuesto> presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setRecetario(List<Recetario> recetario) {
		this.recetario = recetario;
	}

	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}

	public void setReposo(List<Reposo> reposo) {
		this.reposo = reposo;
	}

	public void setPago(List<Pago> pago) {
		this.pago = pago;
	}

	public List<Familiares> getFamiliares() {
		return familiares;
	}

	public void setFamiliares(List<Familiares> familiares) {
		this.familiares = familiares;
	}

	public List<Seguimiento> getSeguimientos() {
		return seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public double getPagos() {
		pagos = 0;
		try {
			pagos = pago.stream().filter(p -> p.isEstado() == true && p.isEstadoPago() == true)
					.mapToDouble(x -> x.getValorPago()).sum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pagos;
	}

	public double getDeudas() {
		deudas = 0;
		try {
			deudas = presupuesto.stream().filter(p -> p.getEstado().equalsIgnoreCase("APROBADO") == true)
					.mapToDouble(x -> x.getValorPagar()).sum();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return deudas;
	}

	public double getDiferencia() {
		diferencia = 0;
		try {
			diferencia = getDeudas() - getPagos();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diferencia;
	}

}
