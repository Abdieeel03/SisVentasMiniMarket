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
    }
    
}
