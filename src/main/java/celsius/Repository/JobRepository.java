package celsius.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import celsius.Model.Job;
import celsius.Model.Proyecto;
import celsius.Model.Resultado;
import celsius.Model.Usuario;

public interface JobRepository extends JpaRepository<Job,Long> {
	
	@Query("SELECT j FROM Job j WHERE j.usuario = :usuario")
	public List<Job> findJobs(@Param("usuario") Usuario usuario);
	
}
