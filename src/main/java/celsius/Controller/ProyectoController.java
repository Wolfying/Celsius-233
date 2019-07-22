package celsius.Controller;

import celsius.Model.Proyecto;
import celsius.Model.Comentario;
import celsius.Model.Resultado;
import celsius.Repository.ProyectoRepository;
import celsius.Repository.ComentarioRepository;
import celsius.Repository.ResultadoRepository;
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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import celsius.Helper.MessageHelper;

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

    @Autowired
    ResultadoRepository resultadoRepository;

    @ModelAttribute(value = "comentario")
    public Comentario getComentario() {
      return new Comentario();
    }

    @ModelAttribute(value = "resultado")
    public Resultado getResultado() {
      return new Resultado();
    }

    @GetMapping(value={"/{id}", "/index/{id}"})
    public String getProyecto(@PathVariable("id") Long id, Model model) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        //ERROR
        return "";
      }
      model.addAttribute("proyecto", optProyecto.get());
      model.addAttribute("comentarios", comentarioRepository.findAll());
      model.addAttribute("resultados", resultadoRepository.findAll());
      model.addAttribute("contador_comentarios", comentarioRepository.count());
      model.addAttribute("contador_resultados", resultadoRepository.count());
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
    public String saveProyecto(@ModelAttribute Proyecto proyecto, BindingResult errors, Model model, RedirectAttributes ra) {
      if (!errors.hasErrors()) {
        proyectoRepository.save(proyecto);
        model.addAttribute("proyectos", proyectoRepository.findAll());
        MessageHelper.addSuccessAttribute(ra, "Proyecto creado con éxito.");
      } else {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("estado_inicial", Proyecto.Estado.EN_ESPERA);
        MessageHelper.addErrorAttribute(ra, "Error al crear proyecto.");
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
    public String updateProyecto(@ModelAttribute Proyecto proyecto, BindingResult errors, Model model, RedirectAttributes ra) {
      if (!errors.hasErrors()) {
        proyectoRepository.save(proyecto);
        model.addAttribute("proyectos", proyectoRepository.findAll());
        MessageHelper.addSuccessAttribute(ra, "Proyecto actualizado con éxito.");
      } else {
        MessageHelper.addErrorAttribute(ra, "Error al actualizar proyecto.");
      }
      return (errors.hasErrors() ? "redirect:/proyecto/edit/"+proyecto.getId() : "redirect:/proyecto/list");
    }

    // GET listado de proyectos
    @GetMapping(value = {"/listado", "/lista", "/list", ""})
    public String listProyecto(Model model) {
      model.addAttribute("proyectos", proyectoRepository.findAll());
      return "proyecto/list";
    }

    //POST eliminar proyecto
    @PostMapping(value={"/delete","/eliminar"})
    public String deleteProyecto(@RequestParam("id") long id, Model model, RedirectAttributes ra) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        MessageHelper.addErrorAttribute(ra, "Error al eliminar proyecto, proyecto no encontrado.");
      } else {
        MessageHelper.addErrorAttribute(ra, "Proyecto eliminado con éxito.");
      }
      proyectoRepository.delete(optProyecto.get());
      model.addAttribute("proyectos", proyectoRepository.findAll());
      return "redirect:/proyecto/list";
    }

    // POST guarda nuevo comentario
    @PostMapping(value = {"/comentario/create", "/comantario/crear"})
    public String saveComentario(@ModelAttribute("comentario") Comentario comentario, BindingResult errors, Model model, RedirectAttributes ra) {
      comentarioRepository.save(comentario);
      if (!errors.hasErrors()) {
        MessageHelper.addSuccessAttribute(ra, "Comentario creado con éxito.");
      } else {
        MessageHelper.addErrorAttribute(ra, "Error al crear comentario.");
      }
      return "redirect:/proyecto/index/"+comentario.getProyecto().getId();
    }

    // POST guarda nuevo comentario
    @PostMapping(value = {"/resultado/create", "/resultado/crear"})
    public String saveComentario(@ModelAttribute("resultado") Resultado resultado, BindingResult errors, Model model, RedirectAttributes ra) {
      resultadoRepository.save(resultado);
      if (!errors.hasErrors()) {
        MessageHelper.addSuccessAttribute(ra, "Resultado creado con éxito.");
      } else {
        MessageHelper.addErrorAttribute(ra, "Error al crear resultado.");
      }
      return "redirect:/proyecto/index/"+resultado.getProyecto().getId();
    }
}
