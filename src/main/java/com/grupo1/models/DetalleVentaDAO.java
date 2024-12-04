package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAODetalleVenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class DetalleVentaDAO extends Database implements DAODetalleVenta {

    public  List<DetalleVenta> read() throws Exception {
        List<DetalleVenta> detalleventas = new ArrayList<>();
        try {
            this.conectar();
            //id_detalle	id_venta	id_producto	cantidad	precioUnitario	subtotal	

            String script = "SELECT id_detalle, id_venta, id_producto, cantidad, precioUnitario, subtotal FROM detalle venta";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                DetalleVenta c = new DetalleVenta();
                c.setIdDetalle(rs.getString("id_detalle"));
                c.setIdVenta(rs.getString("id_venta"));
                c.setIdProducto(rs.getString("id_producto"));
                c.setCantidad(rs.getInt("cantidad"));
                c.setPrecioUnitario(rs.getDouble("precio unitario"));
                c.setSubtotal(rs.getDouble("subtotal"));
                detalleventas.add(c);
            }
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
        return detalleventas;
    }

    public void create(DetalleVenta detalleVenta) throws Exception {
        
    }

    public void update(DetalleVenta detalleVenta) throws Exception {
        
    }

    public void delete(DetalleVenta detalleVenta) throws Exception {
        
    }

}
