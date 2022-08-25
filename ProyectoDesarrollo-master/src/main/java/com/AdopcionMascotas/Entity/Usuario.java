package com.AdopcionMascotas.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    private String Apellido1;
    private String Apellido2;
    private int Telefono;
    private String Email;
    private String Password;
    private int Active;
    private String Roles = "";
    private String Permisos = "";
    private String imagen;

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getActive() {
        return Active;
    }

    public void setActive(int Active) {
        this.Active = Active;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String Roles) {
        this.Roles = Roles;
    }

    public String getPermisos() {
        return Permisos;
    }

    public void setPermisos(String Permisos) {
        this.Permisos = Permisos;
    }

    public List<String> getRoleList() {
        if (this.Roles.length() > 0) {
            return Arrays.asList(this.Permisos.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermisosList() {
        if (this.Permisos.length() > 0) {
            return Arrays.asList(this.Permisos.split(","));
        }
        return new ArrayList<>();
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Usuario(long id, String nombre, String Apellido1, String Apellido2, int Telefono, String Email, String Password,  String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.Apellido1 = Apellido1;
        this.Apellido2 = Apellido2;
        this.Telefono = Telefono;
        this.Email = Email;
        this.Password = Password;
    
        this.imagen = imagen;
    }

    public Usuario() {

    }
}
