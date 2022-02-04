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
public class RecetarioDetalle {
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
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Recetario recetario;
	
	@Column(nullable=false)
	private String horas;
	
	@Column(nullable=false)
	private String dias;
		
	@ManyToOne
	@JoinColumn(nullable = false)
	private Medicamento medicamento;

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

	public String getHoras() {
		return horas;
	}

	public String getDias() {
		return dias;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public Recetario getRecetario() {
		return recetario;
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

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public void setRecetario(Recetario recetario) {
		this.recetario = recetario;
	}
	
	
	
}
