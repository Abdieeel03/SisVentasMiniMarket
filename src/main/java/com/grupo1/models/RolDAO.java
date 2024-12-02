package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAORol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class RolDAO extends Database implements DAORol {

    public List<Rol> read() throws Exception {
        List<Rol> roles = new ArrayList<>();
        try {
            this.conectar();

            PreparedStatement st = this.conexion.prepareStatement("SELECT id_rol, nombre FROM rol");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int idRol = rs.getInt("id_rol");
                String nombre = rs.getString("nombre");
                Rol rol = new Rol(idRol, nombre);
                roles.add(rol);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al leer los roles desde la base de datos", e);
        } finally {
            this.cerrar();
        }
        return roles;
    }

    public String getNombrePorId(int idRol) throws Exception {
        try {
            this.conectar();
            String query = "SELECT nombre FROM Rol WHERE id_rol = ?";
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, idRol);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("nombre");
            }
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
        return null; // O puedes lanzar una excepción si no existe el rol
    }

}
