package celsius.Repository;

import celsius.Model.Proyecto;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto,Long> {

    Proyecto findByTitulo(String titulo);

    Proyecto findByEstado(int estado);

}
