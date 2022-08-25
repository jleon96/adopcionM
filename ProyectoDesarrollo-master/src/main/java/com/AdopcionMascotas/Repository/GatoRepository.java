
package com.AdopcionMascotas.Repository;

import com.AdopcionMascotas.Entity.Gato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GatoRepository extends CrudRepository<Gato, Long> {
    
}
