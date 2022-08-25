package com.AdopcionMascotas.Service;

import com.AdopcionMascotas.Entity.Comentario;
import com.AdopcionMascotas.Repository.ComentarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService implements IComentarioService {

    @Autowired
    private ComentarioRepository CR;

    @Override
    public List<Comentario> getAllComentario() {
        return (List<Comentario>) CR.findAll();
    }

    @Override
    public void saveComentario(Comentario C) {
        CR.save(C);
    }

    @Override
    public void EliminarComentario(long ID) {
        CR.deleteById(ID);
    }

    @Override
    public Comentario getComentarioById(long ID) {
        return CR.findById(ID).orElse(null);
    }

}
