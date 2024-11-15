package com.grupo1.interfaces;

import com.grupo1.models.Usuario;
import java.util.List;

/**
 *
 * @author Abdiel
 */
public interface DAOUsuario {

    public void create(Usuario usuario) throws Exception;

    public List<Usuario> read() throws Exception;

    public void update(Usuario usuario) throws Exception;

    public void delete(Usuario usuario) throws Exception;
}
