package com.AdopcionMascotas.Service;


import com.AdopcionMascotas.Entity.Donacion;
import com.AdopcionMascotas.Repository.DonacionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonacionService implements IDonacionService {

    @Autowired
    private DonacionRepository donacionRepository;

    @Override
    public List<Donacion> getAllDonacion() {
        return (List<Donacion>) donacionRepository.findAll();
    }

    @Override
    public void saveDonacion(Donacion D) {
        donacionRepository.save(D);
    }

    @Override
    public void delete(long id) {
        donacionRepository.deleteById(id);
    }

    @Override
    public Donacion getDonacionById(long id) {
        return donacionRepository.findById(id).orElse(null);
    }

}
