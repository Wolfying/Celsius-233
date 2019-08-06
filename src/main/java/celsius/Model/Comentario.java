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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTexto() {
			return texto;
		}

		public void setTexto(String texto) {
			this.texto = texto;
		}

		public Proyecto getProyecto() {
			return proyecto;
		}

		public void setProyecto(Proyecto proyecto) {
			this.proyecto = proyecto;
		}

		public Usuario getCreador() {
			return creador;
		}

		public void setCreador(Usuario creador) {
			this.creador = creador;
		}

		public Resultado getResultado() {
			return resultado;
		}

		public void setResultado(Resultado resultado) {
			this.resultado = resultado;
		}
    
    
}
