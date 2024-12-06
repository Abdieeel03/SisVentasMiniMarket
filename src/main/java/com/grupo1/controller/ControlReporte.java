package com.grupo1.controller;

import com.grupo1.models.Producto;
import com.grupo1.models.ProductoDAO;
import com.grupo1.models.Venta;
import com.grupo1.views.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abdiel
 */
public class ControlReporte {

    public void mostrarEstadisticas() {
        DefaultTableModel masVendidos = (DefaultTableModel) panelReporte.tableMasVendidos.getModel();
        DefaultTableModel ultimosVendidos = (DefaultTableModel) panelReporte.tableUltimas.getModel();
        masVendidos.setRowCount(0);
        ultimosVendidos.setRowCount(0);
        try {
            //Tabla mas vendidos
            for (Producto p : productoDAO.obtenerProductoMasVendidos()) {
                Object[] object = new Object[3];
                object[0] = p.getNombre();
                object[1] = p.getIdCategoria(); //TOTAL VENDIDO
                object[2] = p.getStock();
                masVendidos.addRow(object);
            }

            for (Venta v : productoDAO.obtenerVentasOrdenadasPorFecha()) {
                Object[] object = new Object[3];
                object[0] = v.getIdCliente(); // NOMBRE PRODUCTO
                object[1] = v.getFechaVenta();
                object[2] = v.getTotal();
                ultimosVendidos.addRow(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ControlReporte(VtnInicio vtnInicio, ReporteDeVentas panelReporte, ProductoDAO productoDAO) {
        this.vtnInicio = vtnInicio;
        this.panelReporte = panelReporte;
        this.productoDAO = productoDAO;
    }

    /*Declaracion de variables*/
    private VtnInicio vtnInicio;
    private ReporteDeVentas panelReporte;
    private ProductoDAO productoDAO;
}
