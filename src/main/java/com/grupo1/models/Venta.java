package com.grupo1.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venta {

    private int id_venta;
    private Date fechaVenta;
    private double total;
    private int idMedioPago;
    private String idCliente;
    private MedioPago medioPago;
    private Cliente cliente;
    private List<DetalleVenta> detalleVentas;

    public Venta() {
        this.detalleVentas = new ArrayList<>();
    }

    public Venta(DetalleVenta detalleVenta) {
        this.detalleVentas.add(detalleVenta);
        detalleVenta.setVenta(this);
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(int idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

}
