package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Objects;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table
public class UsuarioProyecto extends Auditable<String> {
	@EmbeddedId
	private UsuarioProyectoId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("proyecto_id")
	private Proyecto proyecto;
	
	private Tipo tipo;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public UsuarioProyecto() {}
	
	UsuarioProyecto(Usuario usuario, Proyecto proyecto) {
		this.usuario = usuario;
		this.proyecto = proyecto;
		this.id = new UsuarioProyectoId(usuario.getId(), proyecto.getId());
	}
	
	public static enum Tipo {
    JEFE("Jefe de Proyecto", "warning"),
    MIEMBRO("Miembro", "info"),
    MENTOR("Mentor", "success");

    private final String nombre;
    private final String etiqueta;

    Tipo(String nombre, String etiqueta) {
      this.nombre = nombre;
      this.etiqueta = etiqueta;
    }

    public String getNombre() {
      return nombre;
    }

    public String getEtiqueta() {
      return etiqueta;
    }
    
    
    public String toString() {
      return this.nombre;
    }
  }
	
	@Override
  public boolean equals(Object o) {
      if (this == o) return true;

      if (o == null || getClass() != o.getClass())
          return false;

      UsuarioProyecto that = (UsuarioProyecto) o;
      return Objects.equals(usuario, that.usuario) && Objects.equals(proyecto, that.proyecto);
  }

  @Override
  public int hashCode() {
      return Objects.hash(usuario, proyecto);
  }

	public UsuarioProyectoId getId() {
		return id;
	}

	public void setId(UsuarioProyectoId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
  
}
