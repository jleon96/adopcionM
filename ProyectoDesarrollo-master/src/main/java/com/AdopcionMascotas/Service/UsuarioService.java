package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Usuario;
import com.AdopcionMascotas.Repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsuario() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public void saveUsuario(Usuario U) {
        usuarioRepository.save(U);
    }

    @Override
    public void EliminarUsuario(long ID) {
        usuarioRepository.deleteById(ID);
    }

    @Override
    public Usuario getUsuarioById(long IDUsuario) {
        return usuarioRepository.findById(IDUsuario).orElse(null);
    }

    @Override
    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }
}
