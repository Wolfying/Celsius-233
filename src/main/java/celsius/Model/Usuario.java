package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


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
    
    @ManyToMany
    private Set<Proyecto> proyectos;

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
}
