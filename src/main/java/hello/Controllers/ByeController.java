package hello.Controllers;

import hello.Model.Usuario;
import hello.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bye")
public class ByeController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/show")
    public String Show(){

        Optional<Usuario> a = usuarioRepository.findById(1L);
        return a.get().getUsername();
    }


}
