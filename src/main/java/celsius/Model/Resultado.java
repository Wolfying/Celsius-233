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

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getTitulo() {
			return titulo;
		}

		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}

		public Proyecto getProyecto() {
			return proyecto;
		}

		public void setProyecto(Proyecto proyecto) {
			this.proyecto = proyecto;
		}

		public LocalTime getTiempo_dedicado() {
			return tiempo_dedicado;
		}

		public void setTiempo_dedicado(LocalTime tiempo_dedicado) {
			this.tiempo_dedicado = tiempo_dedicado;
		}

		public Set<Comentario> getComentarios() {
			return comentarios;
		}

		public void setComentarios(Set<Comentario> comentarios) {
			this.comentarios = comentarios;
		}

		public Job getJob() {
			return job;
		}

		public void setJob(Job job) {
			this.job = job;
		}
    
    
}
