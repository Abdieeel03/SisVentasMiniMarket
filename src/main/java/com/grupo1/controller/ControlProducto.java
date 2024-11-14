package com.grupo1.controller;

import com.grupo1.views.*;

/**
 *
 * @author Abdiel
 */
public class ControlProducto {

    private VtnInicio vtnInicio;
    private PanelProductos panelProductos;
    private BuscarProducto buscarProducto;
    private NuevoProducto nuevoProducto;
    private EditarProducto editarProducto;

    public ControlProducto(VtnInicio vtnInicio, PanelProductos panelProductos, BuscarProducto buscarProducto, NuevoProducto nuevoProducto, EditarProducto editarProducto) {
        this.vtnInicio = vtnInicio;
        this.panelProductos = panelProductos;
        this.buscarProducto = buscarProducto;
        this.nuevoProducto = nuevoProducto;
        this.editarProducto = editarProducto;
        panelProductos.btnNuevoProducto.addActionListener(e -> showNuevoProducto());
        panelProductos.btnBuscarProducto.addActionListener(e -> showBuscarProducto());
        nuevoProducto.btnCancelar.addActionListener(e -> accionCancelar());
        buscarProducto.btnCancelar.addActionListener(e -> accionCancelar());
    }

    public void showNuevoProducto() {
        vtnInicio.showJPanel(nuevoProducto);
    }

    public void showBuscarProducto() {
        vtnInicio.showJPanel(buscarProducto);
    }

    public void accionCancelar() {
        vtnInicio.showJPanel(panelProductos);
    }
}
