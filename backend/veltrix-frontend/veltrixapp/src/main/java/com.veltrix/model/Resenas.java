package com.veltrix.model;

import java.time.LocalDateTime; 

public class Resenas {

    //Atributos
    private int idResena;
    private int idCliente;
    private int idProducto;
    private String comentario;
    private int calificacion;
    private LocalDateTime fechaResena;
//Constructor vacío
    public Resenas() {
    }
//Constructor con parámetros
    public Resenas(int idResena, int idCliente, int idProducto,
                    String comentario, int calificacion,
                    LocalDateTime fechaResena) {
        this.idResena = idResena;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fechaResena = fechaResena;
    }
//Getters y Setters
    public int getIdResena() {
        return idResena;
    }

    public void setIdResena(int idResena) {
        this.idResena = idResena;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDateTime getFechaResena() {
        return fechaResena;
    }

    public void setFechaResena(LocalDateTime fechaResena) {
        this.fechaResena = fechaResena;
    }
}