package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Contacto;
import com.AdopcionMascotas.Repository.ContactoRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoService implements IContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Override
    public List<Contacto> getAllContacto() {
        return (List<Contacto>) contactoRepository.findAll();
    }

    @Override
    public void saveContacto(Contacto C) {
        contactoRepository.save(C);
    }

    @Override
    public void EliminarContacto(long ID) {
        contactoRepository.deleteById(ID);
    }

    @Override
    public Contacto getUsuarioById(long ID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Contacto findByNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
