package celsius.Model;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String texto;

    private Date fecha_creacion;

    private Date fecha_modificacion;

    @ManyToMany
    private Set<Usuario> creador;

    @ManyToMany(mappedBy = "comentarios")
    private Set<Proyecto> proyectos;

    @OneToMany(mappedBy = "comentario")
    private Set<Resultado> resultados;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Set<Usuario> getCreador() {
        return creador;
    }

    public void setCreador(Set<Usuario> creador) {
        this.creador = creador;
    }

    public Set<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(Set<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Set<Resultado> getResultados() {
        return resultados;
    }

    public void setResultados(Set<Resultado> resultados) {
        this.resultados = resultados;
    }
}
