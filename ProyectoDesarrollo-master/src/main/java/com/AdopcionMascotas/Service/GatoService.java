package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Gato;
import com.AdopcionMascotas.Repository.GatoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GatoService implements IGatoService {

    @Autowired
    private GatoRepository gatoRepository;

    @Override
    public List<Gato> getAllGato() {
        return (List<Gato>) gatoRepository.findAll();
    }

    @Override
    public void saveGato(Gato G) {
        gatoRepository.save(G);
    }

    @Override
    public void EliminarGato(long ID) {
        gatoRepository.deleteById(ID);
    }

    @Override
    public Gato getGatoById(long IDGato) {
        return gatoRepository.findById(IDGato).orElse(null);
    }
    
}
