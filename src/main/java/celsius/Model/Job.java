package celsius.Model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

import javax.persistence.*;


@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
public class Job extends Auditable<String>  {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @OneToOne(mappedBy = "job")
  private Resultado resultado;
  
  private String maquina;
  
  private String materia_prima;

  @ManyToOne
  @JoinColumn
  private Usuario usuario;
}