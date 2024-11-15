package com.grupo1.controller;

import com.grupo1.views.*;

/**
 *
 * @author Abdiel
 */
public class ControlUsuario {
    
    private VtnInicio vtnInicio;
    private PanelUsuario panelUsuario;
    private BuscarUsuario buscarUsuario;
    private NuevoUsuario nuevoUsuario;
    private EditarUsuario editarUsuario;

    public ControlUsuario(VtnInicio vtnInicio, PanelUsuario panelUsuario, BuscarUsuario buscarUsuario, NuevoUsuario nuevoUsuario, EditarUsuario editarUsuario) {
        this.vtnInicio = vtnInicio;
        this.panelUsuario = panelUsuario;
        this.buscarUsuario = buscarUsuario;
        this.nuevoUsuario = nuevoUsuario;
        this.editarUsuario = editarUsuario;
        panelUsuario.btnNuevoUsuario.addActionListener(e -> showNuevoUsuario());
        panelUsuario.btnBuscarUsuario.addActionListener(e -> showBuscarUsuario());
        nuevoUsuario.btnCancelar.addActionListener(e -> accionCancelar());
        buscarUsuario.btnCancelar.addActionListener(e -> accionCancelar());
    }
    public void showNuevoUsuario() {
        vtnInicio.showJPanel(nuevoUsuario);
    }

    public void showBuscarUsuario() {
        vtnInicio.showJPanel(buscarUsuario);
    }

    public void accionCancelar() {
        vtnInicio.showJPanel(panelUsuario);
    }
    
}
