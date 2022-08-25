package com.AdopcionMascotas.Repository;

import com.AdopcionMascotas.Entity.Contacto;
import com.AdopcionMascotas.Entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends CrudRepository<Contacto, Long> {

    Contacto findByNombre(String nombre);

}
