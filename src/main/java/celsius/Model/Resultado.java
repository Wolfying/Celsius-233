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
public class Resultado extends Auditable<String>   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    @Column(columnDefinition = "TEXT")
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String subtitulo;

    @ManyToOne
    @JoinColumn
    private Proyecto proyecto;

    @OneToMany(mappedBy = "resultado", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios;
}
