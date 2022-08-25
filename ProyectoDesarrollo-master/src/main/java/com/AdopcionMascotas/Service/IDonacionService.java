package com.AdopcionMascotas.Service;


import com.AdopcionMascotas.Entity.Donacion;
import java.util.List;

public interface IDonacionService {

    public List<Donacion> getAllDonacion();

    public void saveDonacion(Donacion D);

    public void delete(long id);

    public Donacion getDonacionById(long id);

}
