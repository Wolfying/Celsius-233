package hello.Repository;

import hello.Model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByUsername(String username);

    Usuario findByCorreo(String correo);

    Usuario findById(int i);


}
