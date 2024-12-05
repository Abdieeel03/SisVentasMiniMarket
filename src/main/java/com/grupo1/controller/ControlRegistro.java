package com.grupo1.controller;

import com.grupo1.views.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdiel
 */
public class ControlRegistro {
    
    public ControlRegistro(VtnInicio vtnInicio, VtnSeleccionarProducto vtnSeleccionar, PanelRegistrarVenta panelRegistrarVenta) {
        this.vtnInicio = vtnInicio;
        this.vtnSeleccionar = vtnSeleccionar;
        this.panelRegistrarVenta = panelRegistrarVenta;
        panelRegistrarVenta.txtNombreProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarProducto();
            }
        });
        panelRegistrarVenta.btnRegistrarVenta.addActionListener(e -> registrarVenta());
        panelRegistrarVenta.btnCancelar.addActionListener(e -> botonCancelar());
    }
    
    public void seleccionarProducto() {
        System.out.println("PROBANDO");
        vtnSeleccionar.setVisible(false);
        panelRegistrarVenta.txtNombreProducto.setText("Probando");
    }
    
    public void buscarProducto() {
        vtnSeleccionar.setVisible(true);
    }
    
    public void registrarVenta() {
        
    }
    
    public void botonCancelar() {
        vtnInicio.showJPanel(new PanelPrincipal());
        panelRegistrarVenta.txtNombreProducto.setText("");
    }

    /*Declaracion de variables*/
    private VtnInicio vtnInicio;
    private VtnSeleccionarProducto vtnSeleccionar;
    private PanelRegistrarVenta panelRegistrarVenta;
    
}
