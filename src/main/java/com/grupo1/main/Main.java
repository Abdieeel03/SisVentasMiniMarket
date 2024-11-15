package com.grupo1.main;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.grupo1.controller.Controlador;
import com.grupo1.models.*;
import com.grupo1.views.VtnInicio;
import com.grupo1.views.VtnLogin;

/**
 *
 * @author Abdiel
 */
public class Main {

    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        
        /*Ventanas*/
        VtnInicio vtnInicio = new VtnInicio();
        VtnLogin vtnLogin = new VtnLogin();
        
        /*Inicializacion DAOs*/
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        DetalleVentaDAO detalleDAO = new DetalleVentaDAO();
        MedioPagoDAO medioPagoDAO = new MedioPagoDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        RolDAO rolDAO = new RolDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        VentaDAO ventaDAO = new VentaDAO();
        
        /*Controlador Principal*/
        Controlador controlador = new Controlador(vtnInicio, vtnLogin, categoriaDAO, clienteDAO, detalleDAO, medioPagoDAO, productoDAO, proveedorDAO, rolDAO, usuarioDAO, ventaDAO);
        
        /*Mostrar Ventana*/
        vtnLogin.setVisible(true);
    }
}
