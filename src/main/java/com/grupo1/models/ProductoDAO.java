package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOProducto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class ProductoDAO extends Database implements DAOProducto {

    @Override
    public void create(Producto producto, List<String> ids) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO producto(nombre, precio_compra, precio_venta, stock, id_categoria, id_proveedor) VALUES(?,?,?,?,?,?)"
            );
            producto.setIdCategoria(obtenerIdCategoria(ids.get(0)));
            producto.setIdProveedor(obtenerIdProveedor(ids.get(1)));
            st.setString(1, producto.getNombre());
            st.setDouble(2, producto.getPrecioCompra());
            st.setDouble(3, producto.getPrecioVenta());
            st.setInt(4, producto.getStock());
            st.setInt(5, producto.getIdCategoria());
            st.setString(6, producto.getIdProveedor());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Producto> read() throws Exception {
        List<Producto> producto = new ArrayList<>();
        try {
            this.conectar();
            String script = "SELECT id_producto, nombre, precio_compra, precio_venta, stock, id_categoria, id_proveedor FROM producto";
            PreparedStatement st = this.conexion.prepareStatement(script);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Producto c = new Producto();
                c.setIdProducto(rs.getInt("id_producto"));
                c.setNombre(rs.getString("nombre"));
                c.setPrecioCompra(rs.getDouble("precio_compra"));
                c.setPrecioVenta(rs.getDouble("precio_venta"));
                c.setStock(rs.getInt("stock"));
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setIdProveedor(rs.getString("id_proveedor"));
                producto.add(c);
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return producto;
    }

    @Override
    public void update(Producto producto, List<String> ids) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE producto SET nombre = ?, precio_compra = ?, precio_venta = ?, stock = ?, id_categoria = ?, id_proveedor = ? WHERE id_producto = ?;"
            );
            producto.setIdCategoria(obtenerIdCategoria(ids.get(0)));
            producto.setIdProveedor(obtenerIdProveedor(ids.get(1)));
            System.out.println(producto.getNombre());
            System.out.println(producto.getPrecioCompra());
            System.out.println(producto.getPrecioVenta());
            System.out.println(producto.getStock());
            System.out.println(producto.getIdCategoria());
            System.out.println(producto.getIdProveedor());
            System.out.println(producto.getIdProducto());
            st.setString(1, producto.getNombre());
            st.setDouble(2, producto.getPrecioCompra());
            st.setDouble(3, producto.getPrecioVenta());
            st.setInt(4, producto.getStock());
            st.setInt(5, producto.getIdCategoria());
            st.setString(6, producto.getIdProveedor());
            st.setInt(7, producto.getIdProducto());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void delete(Producto producto) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM producto WHERE id_producto = ?;"
            );
            st.setInt(1, producto.getIdProducto());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
    }

    public int obtenerIdCategoria(String nombre) throws SQLException {
        int idCategoria = -1;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT id_categoria FROM categoria WHERE nombre = ?");
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                idCategoria = rs.getInt("id_categoria");
            }
        } catch (Exception e){
            e.printStackTrace();
        }  finally {
            this.cerrar();
        }
        return idCategoria;
    }

    public String obtenerIdProveedor(String nombre) throws SQLException {
        String idProveedor = "";
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("SELECT id_proveedor FROM proveedor WHERE nombre = ?");
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                idProveedor = rs.getString("id_proveedor");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            this.cerrar();
        }
        return idProveedor;
    }

}
