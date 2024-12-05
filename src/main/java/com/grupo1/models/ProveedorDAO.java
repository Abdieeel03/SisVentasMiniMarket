package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOProveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class ProveedorDAO extends Database implements DAOProveedor {

    public void create(Proveedor proveedor) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO proveedor(id_proveedor, nombre, direccion, telefono, paginaWeb) VALUES(?,?,?,?,?)"
            );
            st.setString(1, proveedor.getIdProveedor());
            st.setString(2, proveedor.getNombre());
            st.setString(3, proveedor.getDireccion());
            st.setString(4, proveedor.getTelefono());
            st.setString(5, proveedor.getPaginaWeb());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
    }

    public List<Proveedor> read() throws Exception {
        List<Proveedor> proveedores = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT id_proveedor, nombre, direccion, telefono, paginaWeb FROM proveedor";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setIdProveedor(rs.getString("id_proveedor"));
                p.setNombre(rs.getString("nombre"));
                p.setDireccion(rs.getString("direccion"));
                p.setTelefono(rs.getString("telefono"));
                p.setPaginaWeb(rs.getString("paginaWeb"));
                proveedores.add(p);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
        return proveedores;
    }

    public void update(Proveedor proveedor) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ?, paginaWeb = ? WHERE id_proveedor = ?;"
            );
            st.setString(1, proveedor.getNombre());
            st.setString(2, proveedor.getDireccion());
            st.setString(3, proveedor.getTelefono());
            st.setString(4, proveedor.getPaginaWeb());
            st.setString(5, proveedor.getIdProveedor());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public void delete(Proveedor proveedor) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM proveedor WHERE id_proveedor = ?;"
            );
            st.setString(1, proveedor.getIdProveedor());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public String getNombre(String id) throws SQLException {
        String nombre = null;
        try {
            this.conectar();
            String query = "SELECT nombre FROM proveedor WHERE id_proveedor = ?";
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
        return nombre;
    }

}
