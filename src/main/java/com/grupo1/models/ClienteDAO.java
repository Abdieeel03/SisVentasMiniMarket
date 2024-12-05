package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOCliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class ClienteDAO extends Database implements DAOCliente {

    @Override
    public void create(Cliente cliente) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO cliente(id_cliente, nombre) VALUES(?,?)"
            );
            st.setString(1, cliente.getId_cliente());
            st.setString(2, cliente.getNombre());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Cliente> read() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT id_cliente, nombre FROM cliente";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId_cliente(rs.getString("id_cliente"));
                cl.setNombre(rs.getString("nombre"));
                clientes.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return clientes;
    }

}
