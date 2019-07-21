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
    private Long rol;

    private String nombre;

    private String apellidos;

    private String correo;

    private String password;

    @Transient
    private String passwordConfirm;
    
    @ManyToMany
    private Set<Proyecto> proyectos;

    @ManyToMany(mappedBy = "creador")
    private Set<Comentario> comentarios;

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
