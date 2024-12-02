package com.grupo1.interfaces;

import com.grupo1.models.Categoria;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAOCategoria {

    //public void create(Categoria categoria) throws Exception;

    public List<Categoria> read() throws Exception;

    //public void update(Categoria categoria) throws Exception;

    //public void delete(Categoria categoria) throws Exception;

}
