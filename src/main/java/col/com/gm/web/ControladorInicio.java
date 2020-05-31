package col.com.gm.web;

import col.com.gm.domain.Persona;
import col.com.gm.servicio.PersonaService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorInicio {
    
    //@Value("${index.saludo}")
    //private String saludo;
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    public String inicio(Model model){
        
        var personas = personaService.listarPersonas();
        log.info("Ejecutando y mostrando log");
        model.addAttribute("personas", personas);
        return "index";
    }
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){  //Inyeccion de objeto tipo persona con solo ponerlo en los parametros
        return "modificar";
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String  editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
