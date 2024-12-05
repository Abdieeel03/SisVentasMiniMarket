package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOCategoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class CategoriaDAO extends Database implements DAOCategoria {

    @Override
    public List<Categoria> read() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT id_categoria, nombre FROM categoria";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre"));
                categorias.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return categorias;
    }

    public String getNombre(int id) throws SQLException {
        String nombre = null;
        try {
            this.conectar();
            String query = "SELECT nombre FROM categoria WHERE id_categoria = ?";
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return nombre;
    }
}
