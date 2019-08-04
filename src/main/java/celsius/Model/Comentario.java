package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


import java.util.Date;
import java.util.Set;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Comentario extends Auditable<String>  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String texto;

    @ManyToOne
    @JoinColumn
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn
    private Usuario creador;

    @ManyToOne
    @JoinColumn
    private Resultado resultado;
}
