package com.grupo1.interfaces;

import com.grupo1.models.Venta;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAOVenta {

    public void create(Venta venta) throws Exception;

    public List<Venta> read() throws Exception;

    public void update(Venta venta) throws Exception;

    public void delete(Venta venta) throws Exception;
}
