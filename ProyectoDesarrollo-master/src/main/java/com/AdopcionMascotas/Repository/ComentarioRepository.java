package com.AdopcionMascotas.Repository;

import com.AdopcionMascotas.Entity.Comentario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long> {

}
