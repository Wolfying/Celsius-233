package celsius.Model;

import lombok.AccessLevel;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Proyecto extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Estado estado;

    @Column(columnDefinition = "TEXT")
    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios;

		@ManyToMany
		private Set<Usuario> miembros;
    
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
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
