package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Usuario;
import java.util.List;

public interface IUsuarioService {

    public List<Usuario> getAllUsuario();

    public void saveUsuario(Usuario U);

    public void EliminarUsuario(long ID);

    public Usuario getUsuarioById(long ID);

    public Usuario findByNombre(String nombre);

}
