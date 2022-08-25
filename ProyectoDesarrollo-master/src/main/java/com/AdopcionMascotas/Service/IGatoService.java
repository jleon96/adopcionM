package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Gato;
import java.util.List;

public interface IGatoService {

    public List<Gato> getAllGato();

    public void saveGato(Gato G);

    public void EliminarGato(long ID);

    public Gato getGatoById(long ID);
}
