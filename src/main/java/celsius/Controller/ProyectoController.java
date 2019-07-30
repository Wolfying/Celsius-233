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
    public String getProyecto(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        //ERROR
        MessageHelper.addErrorAttribute(ra, "Error al ir a la página del proyecto. Proyecto no encontrado.");
        return "";
      }
      List<Comentario> comentarios = comentarioRepository.findComentariosProyecto(optProyecto.get());
      List<Resultado> resultados = resultadoRepository.findResultados(optProyecto.get());
      List<List<Comentario>> resultados_comentarios = comentarioRepository.findComentariosResultados(resultados);
      model.addAttribute("proyecto", optProyecto.get());
      model.addAttribute("comentarios", comentarios);
      model.addAttribute("resultados", resultados);
      model.addAttribute("resultados_comentarios", resultados_comentarios);
      model.addAttribute("contador_comentarios", comentarios.size());
      model.addAttribute("contador_resultados", resultados.size());
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
    public String editProyecto(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        //ERROR
        MessageHelper.addErrorAttribute(ra, "Error al ir al formulario de edición del proyecto. Proyecto no encontrado.");
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
        System.out.println(errors);
      }
      System.out.println(proyecto.getEstado());
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

    // POST guarda nuevo resultado
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
    


    // GET editar proyecto
    @GetMapping(value = {"/resolve/{id}", "/resolver/{id}"})
    public String resolveProyecto(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
      Optional<Proyecto> optProyecto = proyectoRepository.findById(id);
      if (!optProyecto.isPresent()) {
        //ERROR
        MessageHelper.addErrorAttribute(ra, "Error al ir al formulario de selección del proyecto. Proyecto no encontrado.");
        return "";
      }
      model.addAttribute("comentario", new Comentario());
      model.addAttribute("proyecto", optProyecto.get());
      return "proyecto/resolve";
    }

    // POST actualizar proyecto
    @PostMapping(value={"/update_status", "/actualizar_estado"})
    public String updateStatusProyecto(@ModelAttribute("proyecto") Proyecto proyecto, @ModelAttribute("comentario") Comentario comentario, BindingResult errors, Model model, RedirectAttributes ra) {
      if (!errors.hasErrors()) {
      	comentario.setProyecto(proyecto);
        comentarioRepository.save(comentario);
        proyectoRepository.save(proyecto);
        model.addAttribute("proyectos", proyectoRepository.findAll());
        MessageHelper.addSuccessAttribute(ra, "Proyecto actualizado con éxito.");
      } else {
        MessageHelper.addErrorAttribute(ra, "Error al actualizar proyecto.");
      }
      return (errors.hasErrors() ? "redirect:/proyecto/resolve/"+proyecto.getId() : "redirect:/proyecto/index/"+proyecto.getId());
    }
}
