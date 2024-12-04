package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOVenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class VentaDAO extends Database implements DAOVenta {

   public  List<Venta> read() throws Exception {
        List<Venta> venta = new ArrayList<>();
        try {
            this.conectar();
            //id_venta	fechaVenta	total	id_medioPago	id_cliente	
	
            String script = "SELECT id_venta, fechaVenta, total, id_medioPago, id_cliente FROM venta";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Venta c = new Venta();
                c.setId_venta(rs.getString("id_venta"));
                c.setFechaVenta(rs.getDate("fechaVenta"));
                c.setTotal(rs.getDouble("total"));
                c.setIdMedioPago(rs.getString("id_medioPago"));
                c.setIdCliente(rs.getString("id_cliente"));
                venta.add(c);
            }
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
        return venta;
    }

    public void create(Venta venta) throws Exception {
        
    }

    public void update(Venta venta) throws Exception {
        
    }

    public void delete(Venta venta) throws Exception {
         
    }

}
