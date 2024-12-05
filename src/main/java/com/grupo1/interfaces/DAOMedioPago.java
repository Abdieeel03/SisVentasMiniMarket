package com.grupo1.interfaces;

import com.grupo1.models.MedioPago;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAOMedioPago {

    //public void create(MedioPago medioPago) throws Exception;

    public List<MedioPago> read() throws Exception;

    //public void update(MedioPago medioPago) throws Exception;

    //public void delete(MedioPago medioPago) throws Exception;
}
