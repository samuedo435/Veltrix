package com.veltrix.model;

public class Producto {

    // Atributos
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int talla;
    private String color;
    private int precio;
    private int stock;
    private int idCategoria;

    // Constructor vacío
    public Producto() {
    }

    // Constructor con parámetros
    public Producto(int idProducto, String nombre, String descripcion,
                    int talla, String color, int precio,
                    int stock, int idCategoria) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
        this.stock = stock;
        this.idCategoria = idCategoria;
    }

    // Getters y Setters

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}