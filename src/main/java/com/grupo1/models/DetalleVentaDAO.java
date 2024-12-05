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

    @Override
    public void create(List<DetalleVenta> detalleVentas) throws Exception {
        try {
            this.conectar();
            String script = "INSERT INTO detalleventa (id_venta, id_producto, cantidad, precioUnitario) VALUES (?,?,?,?)";
            PreparedStatement st = this.conexion.prepareStatement(script);
            for(DetalleVenta d : detalleVentas){
                st.setInt(1, d.getIdVenta());
                st.setInt(2, d.getIdProducto());
                st.setInt(3, d.getCantidad());
                st.setDouble(4, d.getPrecioUnitario());
                st.executeUpdate();
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<DetalleVenta> read() throws Exception {
        List<DetalleVenta> detalleVentas = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT id_detalle, id_venta, id_producto, cantidad, precioUnitario, subtotal FROM detalleventa";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DetalleVenta c = new DetalleVenta();
                c.setIdDetalle(rs.getInt("id_detalle"));
                c.setIdVenta(rs.getInt("id_venta"));
                c.setIdProducto(rs.getInt("id_producto"));
                c.setCantidad(rs.getInt("cantidad"));
                c.setPrecioUnitario(rs.getDouble("precio unitario"));
                c.setSubtotal(rs.getDouble("subtotal"));
                detalleVentas.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return detalleVentas;
    }
}
