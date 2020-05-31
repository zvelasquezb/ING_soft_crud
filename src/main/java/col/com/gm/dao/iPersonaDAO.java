package col.com.gm.dao;

import col.com.gm.domain.Persona;
import org.springframework.data.repository.CrudRepository;

public interface iPersonaDAO extends CrudRepository<Persona, Long>{
    
}
