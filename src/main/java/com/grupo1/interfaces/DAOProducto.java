package com.grupo1.interfaces;

import com.grupo1.models.Producto;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAOProducto {

    public void create(Producto producto) throws Exception;

    public List<Producto> read() throws Exception;

    public void update(Producto producto) throws Exception;

    public void delete(Producto producto) throws Exception;
}
