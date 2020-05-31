package col.com.gm.servicio;

import col.com.gm.dao.iPersonaDAO;
import col.com.gm.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service   //para hacer posible la inyeccion al controlador
public class PersonaServiceImpl implements PersonaService{

    //Inyeccion para clase de tipo personaDao
    @Autowired
    private iPersonaDAO personaDao;
    
    @Override
    @Transactional(readOnly = true)                          //Solo lee informacion en la db, no modifica
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional                                      //Debe hacer commit o rollback
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)                       //Solo lee informacion en la db
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
