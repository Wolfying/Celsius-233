package celsius.Repository;

import celsius.Model.Comentario;
import celsius.Model.Proyecto;
import celsius.Model.Resultado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
	
	@Query("SELECT c FROM Comentario c WHERE c.proyecto = :proyecto")
	public List<Comentario> findComentarios(@Param("proyecto") Proyecto proyecto);
	
	@Query("SELECT c FROM Comentario c WHERE c.proyecto = :proyecto AND c.resultado = NULL")
	public List<Comentario> findComentariosProyecto(@Param("proyecto") Proyecto proyecto);
	
	@Query("SELECT c FROM Comentario c WHERE c.resultado = :resultado")
	public List<Comentario> findComentariosResultado(@Param("resultado") Resultado resultado);
	
	@Query("SELECT c FROM Comentario c WHERE c.resultado IN :resultados")
	public List<List<Comentario>> findComentariosResultados(@Param("resultados") List<Resultado> resultados);
}
