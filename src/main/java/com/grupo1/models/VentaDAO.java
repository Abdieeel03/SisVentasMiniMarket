package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOVenta;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class VentaDAO extends Database implements DAOVenta {

    @Override
    public int create(Venta venta) throws Exception {
        try {
            this.conectar();
            String script = "INSERT INTO venta (fechaVenta, total, id_medioPago, id_cliente) VALUES (?,?,?,?)";
            PreparedStatement st = this.conexion.prepareStatement(script, Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
            st.setDouble(2, venta.getTotal());
            st.setInt(3, venta.getIdMedioPago());
            st.setString(4, venta.getIdCliente());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return -1;
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
