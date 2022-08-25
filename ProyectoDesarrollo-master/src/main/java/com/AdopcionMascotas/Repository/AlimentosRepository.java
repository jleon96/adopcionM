package com.AdopcionMascotas.Repository;

import com.AdopcionMascotas.Entity.Alimentos;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentosRepository extends CrudRepository<Alimentos, Long> {

}
