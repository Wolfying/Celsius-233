package hello.Controllers;

//import com.sun.jdi.LongValue;
import hello.Model.Usuario;
import hello.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("")
    public String index() {
//        Usuario user = new Usuario();
//        user.setUsername("andres");
//        user.setCorreo("asdasd");
//        usuarioRepository.save(user);
        return "hello";
    }
    @GetMapping("/print")
    public String print(Model model){
        long id = 1;
        Usuario usuario;
        Optional<Usuario> a = usuarioRepository.findById(id);
        if (a!=null){
            usuario= a.get();
            model.addAttribute("message",usuario.getUsername());
            model.addAttribute("user",usuario.getUsername());
            model.addAttribute(usuario);
        }
        return "print";

    }

}