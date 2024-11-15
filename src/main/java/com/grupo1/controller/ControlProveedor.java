package com.grupo1.controller;

import com.grupo1.views.*;

/**
 *
 * @author Abdiel
 */
public class ControlProveedor {

    private VtnInicio vtnInicio;
    private PanelProveedor panelProveedor;
    private BuscarProveedor buscarProveedor;
    private NuevoProveedor nuevoProveedor;
    private EditarProveedor editarProveedor;

    public ControlProveedor(VtnInicio vtnInicio, PanelProveedor panelProveedor, BuscarProveedor buscarProveedor, NuevoProveedor nuevoProveedor, EditarProveedor editarProveedor) {
        this.vtnInicio = vtnInicio;
        this.panelProveedor = panelProveedor;
        this.buscarProveedor = buscarProveedor;
        this.nuevoProveedor = nuevoProveedor;
        this.editarProveedor = editarProveedor;
        panelProveedor.btnNuevoProveedor.addActionListener(e -> showNuevoProducto());
        panelProveedor.btnBuscarProveedor.addActionListener(e -> showBuscarProducto());
        nuevoProveedor.btnSalir.addActionListener(e -> accionSalir());
        buscarProveedor.btnCancelar.addActionListener(e -> accionCancelar());
    }

    public void showNuevoProducto() {
        vtnInicio.showJPanel(nuevoProveedor);
    }

    public void showBuscarProducto() {
        vtnInicio.showJPanel(buscarProveedor);
    }

    public void accionCancelar() {
        vtnInicio.showJPanel(panelProveedor);
    }

    public void accionSalir() {
        vtnInicio.showJPanel(panelProveedor);
    }
}
