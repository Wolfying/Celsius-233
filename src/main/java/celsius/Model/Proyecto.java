package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Proyecto extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Estado estado;

    private String titulo;

    private String descripcion;

    @ManyToMany(mappedBy = "proyectos")
    private Set<Usuario> miembros;

    @ManyToMany
    private Set<Comentario> comentarios;

    @OneToMany(mappedBy = "under")
    private Set<Resultado> resultados;

    public static enum Estado {
        EN_ESPERA("En espera", "warning"),
        ARPOBADO("Aprobado", "success"),
        RECHAZADO("Rechazado", "danger");

        private final String nombre;
        private final String etiqueta;

        Estado(String nombre, String etiqueta) {
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
