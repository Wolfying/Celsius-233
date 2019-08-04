package celsius.Model;

import lombok.AccessLevel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
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

    @ManyToOne
    @JoinColumn
    private Proyecto proyecto;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tiempo_dedicado;

    @OneToMany(mappedBy = "resultado", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
}
