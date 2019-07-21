package celsius.Controller;

import celsius.Model.Proyecto;
import celsius.Repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public String getProyecto(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoRepository.findById(id));
        return "proyecto/index";
    }

    // GET crear nuevo proyecto
    @GetMapping(value = {"/new", "/nuevo"})
    public String newProyecto(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        // Estado inicial del proyecto
        model.addAttribute("estado_inicial", Proyecto.Estado.EN_ESPERA);
        return "proyecto/new";
    }

    // POST guardar nuevo proyecto
    @PostMapping(value={"/create", "/crear"})
    public String saveProyecto(@ModelAttribute Proyecto proyecto, BindingResult errors, Model model) {
        System.out.println(proyecto);
        System.out.println(proyecto.getEstado());
        if (!errors.hasErrors()) {
            proyectoRepository.save(proyecto);
            model.addAttribute("proyectos", proyectoRepository.findAll());
        } else {
            model.addAttribute("proyecto", new Proyecto());
            model.addAttribute("estado_inicial", Proyecto.Estado.EN_ESPERA);
        }
        return (errors.hasErrors() ? "proyecto/new" : "proyecto/list");
    }

    // GET editar proyecto
    @GetMapping(value = {"/edit/{id}", "/editar/{id}"})
    public String editProyecto(@PathVariable Long id, Model model) {
        model.addAttribute("proyecto", proyectoRepository.findById(id));
        return "proyecto/edit";
    }

    // POST actualizar proyecto
    @PostMapping(value={"/update", "/actualizar"})
    public String updateProyecto(@ModelAttribute Proyecto proyecto, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            proyectoRepository.save(proyecto);
            model.addAttribute("proyectos", proyectoRepository.findAll());
        }
        return (errors.hasErrors() ? "proyecto/edit" : "proyecto/list");
    }

    // GET listado de proyectos
    @GetMapping(value = {"/listado", "/lista", "/list", ""})
    public String listProyecto(Model model) {
    model.addAttribute("proyectos", proyectoRepository.findAll());
        return "proyecto/list";
    }

}
