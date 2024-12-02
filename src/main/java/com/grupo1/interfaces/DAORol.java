package com.grupo1.interfaces;

import com.grupo1.models.Rol;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAORol {

    //public void create(Rol rol) throws Exception;

    public List<Rol> read() throws Exception;

    //public void update(Rol rol) throws Exception;

    //public void delete(Rol rol) throws Exception;
}
