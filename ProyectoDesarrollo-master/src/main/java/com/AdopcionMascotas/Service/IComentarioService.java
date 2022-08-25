package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Comentario;
import java.util.List;

public interface IComentarioService {

    public List<Comentario> getAllComentario();

    public void saveComentario(Comentario C);

    public void EliminarComentario(long ID);

    public Comentario getComentarioById(long ID);
}
