package com.AdopcionMascotas.Repository;


import com.AdopcionMascotas.Entity.Donacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DonacionRepository extends CrudRepository<Donacion, Long> {

}
