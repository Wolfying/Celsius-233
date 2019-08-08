package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Usuario extends Auditable<String>   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
		@Enumerated(EnumType.ORDINAL)
    private Rol rol;
		

    private String nombre;

    private String apellidos;

    private String correo;
    
    private boolean enabled = false;
    
    private String password;
    @Transient
    private String passwordConfirm;
    
//    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//		private List<UsuarioProyecto> proyectos = new ArrayList<>();
    
    @OneToMany(mappedBy = "usuario")
		private Set<UsuarioProyecto> usuarioProyectos = new HashSet<UsuarioProyecto>();

		public Set<UsuarioProyecto> getUsuarioProyectos() {
			return usuarioProyectos;
		}

		public void setUsuarioProyectos(Set<UsuarioProyecto> usuarioProyectos) {
			this.usuarioProyectos = usuarioProyectos;
		}

		@OneToMany(mappedBy = "creador")
    private Set<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    private Set<Job> jobs;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    
    public static enum Rol {
      ADMINISTRADOR("Administrador", "warning"),
      AYUDANTE("Ayudante", "danger"),
      MIEMBRO("Miembro", "success"),
      ANONIMO("Anónimo", "info");

      private final String nombre;
      private final String etiqueta;

      Rol(String nombre, String etiqueta) {
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
    
    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Rol getRol() {
			return rol;
		}

		public void setRol(Rol rol) {
			this.rol = rol;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellidos() {
			return apellidos;
		}

		public void setApellidos(String apellidos) {
			this.apellidos = apellidos;
		}

		public String getCorreo() {
			return correo;
		}

		public void setCorreo(String correo) {
			this.correo = correo;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		

//		public List<UsuarioProyecto> getProyectos() {
//			return proyectos;
//		}
//
//		public void setProyectos(List<UsuarioProyecto> proyectos) {
//			this.proyectos = proyectos;
//		}

		public Set<Comentario> getComentarios() {
			return comentarios;
		}

		public void setComentarios(Set<Comentario> comentarios) {
			this.comentarios = comentarios;
		}

		public Set<Job> getJobs() {
			return jobs;
		}

		public void setJobs(Set<Job> jobs) {
			this.jobs = jobs;
		}

}
