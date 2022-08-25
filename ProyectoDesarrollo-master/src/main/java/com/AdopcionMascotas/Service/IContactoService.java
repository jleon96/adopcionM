package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Contacto;

import java.util.List;

public interface IContactoService {

    public List<Contacto> getAllContacto();

    public void saveContacto(Contacto C);

    public void EliminarContacto(long ID);

    public Contacto getUsuarioById(long ID);

    public Contacto findByNombre(String nombre);
}
