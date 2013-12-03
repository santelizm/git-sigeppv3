package modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "area_cono_tutor_acad")
public class AreaTutorAcademico {
	
	
	@GeneratedValue
	@Id
	@Column(name = "area_cono_tutor_acad_id", nullable = false)
	private long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "area_conocimiento_id", referencedColumnName = "area_conocimiento_id")
	private AreaConocimiento areaConocimiento;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "tutor_academico_id", referencedColumnName = "tutor_academico_id")
	private TutorAcademico tutorAcademico;
	
	@Column(name = "cantidad_max_tutorar")
	private int tutoriados;
	
	@Column(name = "usuario_id", length = 20)
	private String usuario;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_auditoria")
	private Date fechaAuditoria;
	
	
	@Column(name = "hora_auditoria", length = 15)
	private String horaAuditoria;
	
	
	public AreaTutorAcademico(long id, AreaConocimiento areaConocimiento,
			TutorAcademico tutorAcademico, int tutoriados, String usuario,
			Date fechaAuditoria, String horaAuditoria) {
		super();
		this.id = id;
		this.areaConocimiento = areaConocimiento;
		this.tutorAcademico = tutorAcademico;
		this.tutoriados = tutoriados;
		this.usuario = usuario;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
	}

	public AreaTutorAcademico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AreaConocimiento getAreaConocimiento() {
		return areaConocimiento;
	}

	public void setAreaConocimiento(AreaConocimiento areaConocimiento) {
		this.areaConocimiento = areaConocimiento;
	}

	public TutorAcademico getTutorAcademico() {
		return tutorAcademico;
	}

	public void setTutorAcademico(TutorAcademico tutorAcademico) {
		this.tutorAcademico = tutorAcademico;
	}

	public int getTutoriados() {
		return tutoriados;
	}

	public void setTutoriados(int tutoriados) {
		this.tutoriados = tutoriados;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Date fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	
}
