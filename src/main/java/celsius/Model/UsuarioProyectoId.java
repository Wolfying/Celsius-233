package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Embeddable
public class UsuarioProyectoId implements Serializable {
		@Column(name="usuario_id")
		private Long usuario_id;
		
		@Column(name="proyecto_id")
		private Long proyecto_id;
		
		private UsuarioProyectoId() {}
		
		public UsuarioProyectoId(Long usuario_id, Long proyecto_id) {
			this.usuario_id = usuario_id;
			this.proyecto_id = proyecto_id;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			UsuarioProyectoId that = (UsuarioProyectoId) o;
			return Objects.equals(usuario_id, that.usuario_id) && Objects.equals(proyecto_id, that.proyecto_id);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(usuario_id, proyecto_id);
		}

		public Long getUsuario_id() {
			return usuario_id;
		}

		public void setUsuario_id(Long usuario_id) {
			this.usuario_id = usuario_id;
		}

		public Long getProyecto_id() {
			return proyecto_id;
		}

		public void setProyecto_id(Long proyecto_id) {
			this.proyecto_id = proyecto_id;
		}
		
}
