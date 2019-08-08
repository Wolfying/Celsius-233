package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

import javax.persistence.*;


@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Job extends Auditable<String>  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @OneToOne(mappedBy = "job")
  private Resultado resultado;
  
  private String maquina;
  
  private String materia_prima;

  @ManyToOne
  @JoinColumn
  private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public String getMaquina() {
		return maquina;
	}

	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}

	public String getMateria_prima() {
		return materia_prima;
	}

	public void setMateria_prima(String materia_prima) {
		this.materia_prima = materia_prima;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
  
  
}