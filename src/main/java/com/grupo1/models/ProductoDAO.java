package com.grupo1.models;

import com.grupo1.database.Database;
import com.grupo1.interfaces.DAOProducto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public class ProductoDAO extends Database implements DAOProducto {

    public void create(Producto producto) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO producto(nombre, precio_compra, precio_venta, stock, id_categoria, id_proveedor) VALUES(?,?,?,?,?,?)"
            );
            st.setString(1, producto.getNombre());
            st.setDouble(2, producto.getPrecioCompra());
            st.setDouble(3, producto.getPrecioVenta());
            st.setInt(4, producto.getStock());
            st.setInt(5, producto.getIdCategoria());
            st.setString(6, producto.getIdProveedor());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
        } finally {
            this.cerrar();
        }
    }

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
                c.setNombre(rs.getString("id_nombre"));
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
        } finally {
            this.cerrar();
        }
        return producto;
    }

    public void update(Producto producto) throws Exception {

    }

    public void delete(Producto producto) throws Exception {

    }

}
