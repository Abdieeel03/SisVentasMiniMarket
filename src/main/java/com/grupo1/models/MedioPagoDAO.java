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

    @Override
    public List<MedioPago> read() throws Exception {
        List<MedioPago> MedioPagos = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT id_medioPago, nombre FROM mediopago";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MedioPago m = new MedioPago();
                m.setIdMedioPago(rs.getString("id_medioPago"));
                m.setNombre(rs.getString("nombre"));
                MedioPagos.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return MedioPagos;
    }

}
