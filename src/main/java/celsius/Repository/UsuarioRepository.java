package celsius.Repository;

import celsius.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByCorreo(String correo);

    Usuario findByRol(Long rol);

		Usuario findByNombre(String nombre);

		@Query("SELECT u FROM Usuario u WHERE u.enabled = True")
		public Usuario getEnabled();
}
