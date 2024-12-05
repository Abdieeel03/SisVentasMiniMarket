package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOVenta;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class VentaDAO extends Database implements DAOVenta {

    @Override
    public void create(Venta venta) throws Exception {
        try {
            this.conectar();
            String script = "INSERT INTO venta (fechaVenta, total, id_medioPago, id_cliente) VALUES (?,?,?,?)";
            PreparedStatement st = this.conexion.prepareStatement(script);
            st.setDate(1, (Date) venta.getFechaVenta());
            st.setDouble(2, venta.getTotal());
            st.setInt(3, venta.getIdMedioPago());
            st.setString(4, venta.getIdCliente());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Venta> read() throws Exception {
        List<Venta> venta = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT id_venta, fechaVenta, total, id_medioPago, id_cliente FROM venta";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Venta c = new Venta();
                c.setId_venta(rs.getInt("id_venta"));
                c.setFechaVenta(rs.getDate("fechaVenta"));
                c.setTotal(rs.getDouble("total"));
                c.setIdMedioPago(rs.getInt("id_medioPago"));
                c.setIdCliente(rs.getString("id_cliente"));
                venta.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return venta;
    }

}
