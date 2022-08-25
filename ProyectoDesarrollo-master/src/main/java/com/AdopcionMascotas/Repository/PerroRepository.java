package com.AdopcionMascotas.Repository;

import com.AdopcionMascotas.Entity.Perro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerroRepository extends CrudRepository<Perro, Long> {

}
