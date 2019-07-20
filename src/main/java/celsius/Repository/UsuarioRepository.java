package celsius.Repository;

import celsius.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByCorreo(String correo);

    Usuario findByRol(Long rol);


}
