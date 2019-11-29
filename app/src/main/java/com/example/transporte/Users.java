package com.example.transporte;

public class Users
{
    String id;
    String nombre;
    String edad;
    String sexo;
    String desc;

    public Users(String id, String nombre, String edad, String sexo, String desc) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getDesc() {
        return desc;
    }
}

