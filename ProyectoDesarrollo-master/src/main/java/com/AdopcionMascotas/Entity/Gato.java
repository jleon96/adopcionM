package com.AdopcionMascotas.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gatos")
public class Gato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String Nombre;
    private String Raza;
    private String Edad;
    private String Genero;
    private String Descripcion;
    private String Estado;
    private String imagen;

    public long getID() {
        return id;
    }

    public void setID(long ID) {
        this.id = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String Raza) {
        this.Raza = Raza;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String Edad) {
        this.Edad = Edad;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Gato(long ID, String Nombre, String Raza, String Edad, String Genero, String Descripcion, String Estado, String imagen) {
        this.id = ID;
        this.Nombre = Nombre;
        this.Raza = Raza;
        this.Edad = Edad;
        this.Genero = Genero;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
        this.imagen = imagen;
    }

    public Gato() {
    }


    
    

}
