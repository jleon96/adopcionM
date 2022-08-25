package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Perro;
import com.AdopcionMascotas.Repository.PerroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerroService implements IPerroService {

    @Autowired
    private PerroRepository perroRepository;

    @Override
    public List<Perro> getAllPerro() {
        return (List<Perro>) perroRepository.findAll();
    }

    @Override
    public void savePerro(Perro P) {
        perroRepository.save(P);
    }

    @Override
    public void EliminarPerro(long ID) {
        perroRepository.deleteById(ID);
    }

    @Override
    public Perro getPerroById(long ID) {
        return perroRepository.findById(ID).orElse(null);
    }

}
