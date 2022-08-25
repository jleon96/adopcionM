package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Alimentos;
import java.util.List;

public interface IAlimentosService {

    public List<Alimentos> getAllAlimentos();

    public void saveAlimentos(Alimentos A);

    public void EliminarAlimentos(long id);
    
    public Alimentos getAlimentosById(long id);
}
