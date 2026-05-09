package com.veltrix.model;

import java.time.LocalDateTime;

public class Pago {
//Atributos
    private int idPago;
    private int idPedido;
    private String metodoPago;
    private int monto;
    private String estado;
    private LocalDateTime fechaPago;

//Constructor vacío
    public Pago() {
}
//Constructor con parámetros
    public Pago(int idPago, int idPedido, String metodoPago,
                int monto, String estado, LocalDateTime fechaPago) {
        this.idPago = idPago;
        this.idPedido = idPedido;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.estado = estado;
        this.fechaPago = fechaPago;
    }
//Getters y Setters
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }
}