package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOMedioPago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class MedioPagoDAO extends Database implements DAOMedioPago {

    public void create(MedioPago medioPago) throws Exception {
    }

    public List<MedioPago> read() throws Exception {
        List<MedioPago> MedioPagos = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT idMedioPago, nombre FROM mediopago";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MedioPago m = new MedioPago();
                m.setIdMedioPago(rs.getString("id_MedioPago"));
                m.setNombre(rs.getString("nombre"));
                MedioPagos.add(m);
            }
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
        return MedioPagos;
    }

    public void update(MedioPago medioPago) throws Exception {
    }

    public void delete(MedioPago medioPago) throws Exception {
    }

}
