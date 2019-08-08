package celsius.Controller;

import celsius.Helper.MessageHelper;
import celsius.Model.Job;
import celsius.Model.Usuario;
import celsius.Model.Usuario.Rol;
import celsius.Repository.UsuarioRepository;
import celsius.Repository.JobRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value={"/", ""})
public class TestController {
	
	@Autowired
  UsuarioRepository usuarioRepository;
	
	@Autowired
	JobRepository jobRepository;
	
	private void createUsers() {
		Set<Usuario> usuarios = new HashSet<>();
		Rol roles[] = {Rol.ADMINISTRADOR, Rol.AYUDANTE, Rol.MIEMBRO, Rol.ANONIMO};
		String nombres[] = {"Pedro", "Juan", "Diego", "Luis"};
		String password = "1234";
		for(int i=0; i<nombres.length; i++) {
			Usuario usuario = new Usuario();
			usuario.setRol(roles[i]);
			usuario.setNombre(nombres[i]);
			usuario.setPassword(password);
			usuario.setEnabled(false);
			usuarios.add(usuario);
		}
		usuarioRepository.saveAll(usuarios);
	}
	
	private void createJobs() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		Set<Job> jobs = new HashSet<>();
		String materias_primas[] = {"materia1","materia2","materia3","materia4"};
		String maquinas[] = {"Impresora 3D", "Fresadora", "Torno", "Soplete"};
		for(int j=0; j<usuarios.size(); j++) {
			for(int i=0; i<maquinas.length; i++) {
				Job job = new Job();
				job.setUsuario(usuarios.get(j));
				job.setMaquina(maquinas[i]);
				job.setMateria_prima(materias_primas[i]);
				jobs.add(job);
			}
		}
		jobRepository.saveAll(jobs);
	}
	
	private void cleanUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		for(int i = 0; i < usuarios.size(); i++) {
			usuarios.get(i).setEnabled(false);
		}
		usuarioRepository.saveAll(usuarios);
	}
	
	@GetMapping(value="")
	public String newtest(Model model) {
//		usuarioRepository.deleteAll();
//		jobRepository.deleteAll();
//		createUsers();
//		createJobs();
		if (usuarioRepository.count() == 0) {
			createUsers();
		}
		if (jobRepository.count() == 0) {
			createJobs();
		}
		cleanUsuarios();
		model.addAttribute("usuarios", usuarioRepository.findAll());
		return "testUsuarios";
	}
	
	@GetMapping(value="/select/{id}")
	public ModelAndView newUserChoice(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
    model.addAttribute("usuario", usuario);
    if (usuario.isPresent()) {
      String username = usuario.get().getNombre();
      MessageHelper.addSuccessAttribute(ra, "Logged in as " + username + ".");
    } else {
      MessageHelper.addErrorAttribute(ra, "Error al hacer log in, usuario no encontrado.");
      return new ModelAndView("redirect:/");
    }
    cleanUsuarios();
    usuario.get().setEnabled(true);
    usuarioRepository.save(usuario.get());
    return new ModelAndView("redirect:/proyecto");
}
}
