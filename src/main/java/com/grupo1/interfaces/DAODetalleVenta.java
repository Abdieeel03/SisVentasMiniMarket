package com.grupo1.interfaces;

import com.grupo1.models.DetalleVenta;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAODetalleVenta {

    public void create(List<DetalleVenta> detalleVentas) throws Exception;

    public List<DetalleVenta> read() throws Exception;

    //public void update(DetalleVenta detalleVenta) throws Exception;

    //public void delete(DetalleVenta detalleVenta) throws Exception;
}
