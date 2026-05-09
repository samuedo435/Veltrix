package com.veltrix.model;

import java.time.LocalDateTime;

public class Pedido {

    // Atributos
    private int idPedido;
    private int idCliente;
    private LocalDateTime fechaPedido;
    private String estado;
    private int total;

    // Constructor vacío
    public Pedido() {
    }

    // Constructor con parámetros
    public Pedido(int idPedido, int idCliente,
                LocalDateTime fechaPedido,
                String estado, int total) {

        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.total = total;
    }

    // Getters y Setters

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}