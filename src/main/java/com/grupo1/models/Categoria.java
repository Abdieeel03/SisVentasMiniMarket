package com.grupo1.models;

import java.util.List;

public class Categoria {

    private int id_categoria;
    private String nombre;
    private List<Producto> productos;

    public Categoria() {
    }

    public int getIdCategoria() {
        return id_categoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.id_categoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
