package celsius.Repository;

import celsius.Model.Usuario;
import celsius.Model.UsuarioProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioProyectoRepository extends JpaRepository<UsuarioProyecto,Long> {
	
	@Query("SELECT up FROM UsuarioProyecto up WHERE up.usuario = :usuario")
	public UsuarioProyecto findByUsuario(@Param("usuario") Usuario usuario);
}
