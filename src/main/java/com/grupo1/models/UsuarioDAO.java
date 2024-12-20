package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOUsuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class UsuarioDAO extends Database implements DAOUsuario {

    @Override
    public void create(Usuario usuario, String nombreRol) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO usuario(nombre, id_rol, usuario, contraseña, descripcion) VALUES(?,?,?,?,?)"
            );

            usuario.setIdRol(obtenerIdRolPorNombre(nombreRol));

            st.setString(1, usuario.getNombre());
            st.setInt(2, usuario.getIdRol());
            st.setString(3, usuario.getUsuario());
            st.setString(4, usuario.getContraseña());
            st.setString(5, usuario.getDescripcion());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Usuario> read() throws Exception {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            this.conectar();
            String sql = "SELECT id_usuario, nombre, id_rol, usuario, contraseña, descripcion FROM usuario";
            PreparedStatement st = this.conexion.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setUsuario(rs.getString("usuario"));
                u.setContraseña(rs.getString("contraseña"));
                u.setDescripcion(rs.getString("descripcion"));
                usuarios.add(u);
            }
            st.close();
            rs.close();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return usuarios;
    }

    @Override
    public void update(Usuario usuario, String nombreRol, int idUsuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE usuario SET nombre = ?, id_rol = ?, usuario = ?, descripcion = ? WHERE id_usuario = ?;"
            );
            usuario.setIdRol(obtenerIdRolPorNombre(nombreRol));
            st.setString(1, usuario.getNombre());
            st.setInt(2, usuario.getIdRol());
            st.setString(3, usuario.getUsuario());
            st.setString(4, usuario.getDescripcion());
            st.setInt(5, idUsuario);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void delete(int idUsuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM usuario WHERE id_usuario = ?;"
            );
            st.setInt(1, idUsuario);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public int obtenerIdRolPorNombre(String nombreRol) throws Exception {
        int idRol = -1;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT id_rol FROM rol WHERE nombre = ?");
            st.setString(1, nombreRol);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                idRol = rs.getInt("id_rol");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return idRol;
    }

}
