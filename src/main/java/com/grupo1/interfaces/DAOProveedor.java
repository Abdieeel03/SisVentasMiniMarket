package com.grupo1.interfaces;

import com.grupo1.models.Proveedor;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAOProveedor {

    public void create(Proveedor proveedor) throws Exception;

    public List<Proveedor> read() throws Exception;

    public void update(Proveedor proveedor) throws Exception;

    public void delete(Proveedor proveedor) throws Exception;
}
