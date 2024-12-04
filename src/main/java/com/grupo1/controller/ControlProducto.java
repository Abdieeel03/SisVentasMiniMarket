package com.grupo1.controller;

import com.grupo1.models.CategoriaDAO;
import com.grupo1.models.ProductoDAO;
import com.grupo1.models.ProveedorDAO;
import com.grupo1.views.*;

/**
 *
 * @author Abdiel
 */
public class ControlProducto {

    public void showNuevoProducto() {
        vtnInicio.showJPanel(nuevoProducto);
    }

    public void showBuscarProducto() {
        vtnInicio.showJPanel(buscarProducto);
    }
    
    public void showEditarProducto(){
        
    }

    public void accionCancelar() {
        vtnInicio.showJPanel(panelProductos);
    }
    
    public void registrar(){
        
    }
    
    public void actualizarRegistro(){
        
    }
    
    public void eliminarRegistro(){
        
    }
    
    public void buscarRegistro(){
        
    }
    
    public void llenarComboBox(){
        
    }
    
    public void llenarTabla(){
        
    }
    
    public void llenarDatosEditar(int fila){
        
    }
    
    /*Declaracion de variables*/
    private VtnInicio vtnInicio;
    private PanelProductos panelProductos;
    private BuscarProducto buscarProducto;
    private NuevoProducto nuevoProducto;
    private EditarProducto editarProducto;
    private ProductoDAO productoDAO;
    private ProveedorDAO proveedorDAO;
    private CategoriaDAO categoriaDAO;

    /*Constructor*/
    public ControlProducto(VtnInicio vtnInicio, PanelProductos panelProductos, BuscarProducto buscarProducto, NuevoProducto nuevoProducto, EditarProducto editarProducto, ProductoDAO productoDAO, ProveedorDAO proveedorDAO, CategoriaDAO categoriaDAO) {
        this.vtnInicio = vtnInicio;
        this.panelProductos = panelProductos;
        this.buscarProducto = buscarProducto;
        this.nuevoProducto = nuevoProducto;
        this.editarProducto = editarProducto;
        this.productoDAO = productoDAO;
        this.proveedorDAO = proveedorDAO;
        this.categoriaDAO = categoriaDAO;
        
        panelProductos.btnNuevoProducto.addActionListener(e -> showNuevoProducto());
        panelProductos.btnBuscarProducto.addActionListener(e -> showBuscarProducto());
        nuevoProducto.btnRegistrar.addActionListener(e -> registrar());
        nuevoProducto.btnCancelar.addActionListener(e -> accionCancelar());
        buscarProducto.btnCancelar.addActionListener(e -> accionCancelar());
    }
}
