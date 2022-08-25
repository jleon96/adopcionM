package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Alimentos;
import com.AdopcionMascotas.Repository.AlimentosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlimentosService implements IAlimentosService {

    @Autowired
    private AlimentosRepository alimentosRepository;

    @Override
    public List<Alimentos> getAllAlimentos() {
        return (List<Alimentos>) alimentosRepository.findAll();
    }

    @Override
    public void saveAlimentos(Alimentos A) {
        alimentosRepository.save(A);
    }

    @Override
    public void EliminarAlimentos(long id) {
        alimentosRepository.deleteById(id);
    }

    @Override
    public Alimentos getAlimentosById(long id) {
        return alimentosRepository.findById(id).orElse(null);
    }

}
