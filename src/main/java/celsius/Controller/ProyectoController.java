package celsius.Controller;

import celsius.Model.Proyecto;
import celsius.Model.Comentario;
import celsius.Repository.ProyectoRepository;
import celsius.Repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value={"/proyecto", "/proyectos"})
public class ProyectoController {

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    @GetMapping(value={"/{id}", "/index/{id}"})
    public String getProyecto(@PathVariable("id") Long id, Model model) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        //ERROR
        return "";
      }
      model.addAttribute("proyecto", optProyecto.get());
      model.addAttribute("comentarios", comentarioRepository.findAll());
      return "proyecto/index";
    }

    // GET crear nuevo proyecto
    @GetMapping(value = {"/new", "/nuevo"})
    public String newProyecto(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("estado_inicial", Proyecto.Estado.EN_ESPERA);
        return "proyecto/new";
    }

    // POST guardar nuevo proyecto
    @PostMapping(value={"/create", "/crear"})
    public String saveProyecto(@ModelAttribute Proyecto proyecto, BindingResult errors, Model model) {
      if (!errors.hasErrors()) {
        proyectoRepository.save(proyecto);
        model.addAttribute("proyectos", proyectoRepository.findAll());
      } else {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("estado_inicial", Proyecto.Estado.EN_ESPERA);
      }
      return (errors.hasErrors() ? "redirect:/proyecto/new" : "redirect:/proyecto/list");
    }

    // GET editar proyecto
    @GetMapping(value = {"/edit/{id}", "/editar/{id}"})
    public String editProyecto(@PathVariable("id") Long id, Model model) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        //ERROR
        return "";
      }
      model.addAttribute("proyecto", optProyecto.get());
      return "proyecto/edit";
    }

    // POST actualizar proyecto
    @PostMapping(value={"/update", "/actualizar"})
    public String updateProyecto(@ModelAttribute Proyecto proyecto, BindingResult errors, Model model) {
      if (!errors.hasErrors()) {
        proyectoRepository.save(proyecto);
        model.addAttribute("proyectos", proyectoRepository.findAll());
      }
      return (errors.hasErrors() ? "redirect:/proyecto/edit" : "redirect:/proyecto/list");
    }

    // GET listado de proyectos
    @GetMapping(value = {"/listado", "/lista", "/list", ""})
    public String listProyecto(Model model) {
      model.addAttribute("proyectos", proyectoRepository.findAll());
      return "proyecto/list";
    }

    //POST eliminar proyecto
    @PostMapping(value={"/delete","/eliminar"})
    public String deleteProyecto(@RequestParam("id") long id, Model model) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        //ERROR
        return "";
      }
      proyectoRepository.delete(optProyecto.get());
      model.addAttribute("proyectos", proyectoRepository.findAll());
      return "redirect:/proyecto/list";
    }

    @ModelAttribute(value = "comentario")
    public Comentario getComentario() {
      return new Comentario();
    }

    // GET guarda nuevo comentario
    @PostMapping(value = {"/comentario/create", "comantario/crear"})
    public String saveComentario(@ModelAttribute("comentario") Comentario comentario, Model model) {
      System.out.println(comentario.getId());
      comentarioRepository.save(comentario);
      return "redirect:/proyecto/index/"+comentario.getProyecto().getId();
    }
}
