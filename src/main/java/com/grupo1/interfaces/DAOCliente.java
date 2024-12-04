package com.grupo1.interfaces;

import com.grupo1.models.Cliente;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAOCliente {

    public void create(Cliente cliente) throws Exception;

    public List<Cliente> read() throws Exception;

    //public void update(Cliente cliente) throws Exception;

    //public void delete(Cliente cliente) throws Exception;
}
