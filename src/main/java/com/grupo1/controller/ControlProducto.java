package com.grupo1.controller;

import com.grupo1.models.Categoria;
import com.grupo1.models.CategoriaDAO;
import com.grupo1.models.Producto;
import com.grupo1.models.ProductoDAO;
import com.grupo1.models.Proveedor;
import com.grupo1.models.ProveedorDAO;
import com.grupo1.views.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Abdiel
 */
public class ControlProducto {

    public void showNuevoProducto() {
        nuevoProducto.limpiar();
        llenarComboBox();
        vtnInicio.mostrarPanel(nuevoProducto);
    }

    public void showBuscarProducto() throws Exception {
        llenarTabla();
        buscarProducto.txtBusqueda.setText("");
        vtnInicio.mostrarPanel(buscarProducto);
    }

    public void showEditarProducto(int fila) {
        editarProducto.limpiar();
        llenarComboBox();
        llenarDatosEditar(fila);
        vtnInicio.mostrarPanel(editarProducto);
    }

    public void accionCancelar() {
        vtnInicio.mostrarPanel(panelProductos);
    }

    public void registrar() {
        if (nuevoProducto.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre!", "AVISO", JOptionPane.ERROR_MESSAGE);
            nuevoProducto.txtNombre.requestFocus();
            return;
        }
        if (nuevoProducto.txtPrecioCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Precio de Compra!", "AVISO", JOptionPane.ERROR_MESSAGE);
            nuevoProducto.txtPrecioCompra.requestFocus();
            return;
        }
        if (nuevoProducto.txtPrecioVenta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Precio de Venta!", "AVISO", JOptionPane.ERROR_MESSAGE);
            nuevoProducto.txtPrecioVenta.requestFocus();
            return;
        }
        if ((int) nuevoProducto.spnStock.getValue() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese el Stock!", "AVISO", JOptionPane.ERROR_MESSAGE);
            nuevoProducto.spnStock.requestFocus();
            return;
        }
        if (nuevoProducto.cmbCategoria.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione la categoria!", "AVISO", JOptionPane.ERROR_MESSAGE);
            nuevoProducto.cmbCategoria.requestFocus();
            return;
        }
        if (nuevoProducto.cmbProveedor.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Proveedor", "AVISO", JOptionPane.ERROR_MESSAGE);
            nuevoProducto.cmbProveedor.requestFocus();
            return;
        }
        Producto p = new Producto();
        List<String> ids = new ArrayList<>();
        p.setNombre(nuevoProducto.txtNombre.getText());
        p.setPrecioCompra(Double.parseDouble(nuevoProducto.txtPrecioCompra.getText()));
        p.setPrecioVenta(Double.parseDouble(nuevoProducto.txtPrecioVenta.getText()));
        p.setStock(Integer.parseInt(nuevoProducto.spnStock.getValue().toString()));
        ids.add(nuevoProducto.cmbCategoria.getSelectedItem().toString());
        ids.add(nuevoProducto.cmbProveedor.getSelectedItem().toString());
        try {
            productoDAO.create(p, ids);
            vtnInicio.mostrarPanel(panelProductos);
            JOptionPane.showMessageDialog(vtnInicio, "Producto registrado correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void actualizarRegistro() {
        if (editarProducto.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre!", "AVISO", JOptionPane.ERROR_MESSAGE);
            editarProducto.txtNombre.requestFocus();
            return;
        }
        if (editarProducto.txtPrecioCompra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Precio de Compra!", "AVISO", JOptionPane.ERROR_MESSAGE);
            editarProducto.txtPrecioCompra.requestFocus();
            return;
        }
        if (editarProducto.txtPrecioVenta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Precio de Venta!", "AVISO", JOptionPane.ERROR_MESSAGE);
            editarProducto.txtPrecioVenta.requestFocus();
            return;
        }
        if ((int) editarProducto.spnStock.getValue() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese el Stock!", "AVISO", JOptionPane.ERROR_MESSAGE);
            editarProducto.spnStock.requestFocus();
            return;
        }
        if (editarProducto.cmbCategoria.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione la categoria!", "AVISO", JOptionPane.ERROR_MESSAGE);
            editarProducto.cmbCategoria.requestFocus();
            return;
        }
        if (editarProducto.cmbProveedor.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Proveedor", "AVISO", JOptionPane.ERROR_MESSAGE);
            editarProducto.cmbProveedor.requestFocus();
            return;
        }
        Producto p = new Producto();
        List<String> ids = new ArrayList<>();
        p.setIdProducto(Integer.parseInt(editarProducto.txtID.getText()));
        p.setNombre(editarProducto.txtNombre.getText());
        p.setPrecioCompra(Double.parseDouble(editarProducto.txtPrecioCompra.getText()));
        p.setPrecioVenta(Double.parseDouble(editarProducto.txtPrecioVenta.getText()));
        p.setStock(Integer.parseInt(editarProducto.spnStock.getValue().toString()));
        ids.add(editarProducto.cmbCategoria.getSelectedItem().toString());
        ids.add(editarProducto.cmbProveedor.getSelectedItem().toString());
        try {
            productoDAO.update(p, ids);
            vtnInicio.mostrarPanel(panelProductos);
            JOptionPane.showMessageDialog(vtnInicio, "Producto actualizado correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarRegistro() {
        int opcion = JOptionPane.showConfirmDialog(vtnInicio,
                "¿Estás seguro de eliminar este producto?",
                "AVISO",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                Producto p = new Producto();
                p.setIdProducto(Integer.parseInt(editarProducto.txtID.getText()));
                productoDAO.delete(p);
                vtnInicio.mostrarPanel(panelProductos);
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void buscarRegistro() {
        DefaultTableModel model = (DefaultTableModel) buscarProducto.table.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) buscarProducto.table.getRowSorter();
        if (sorter == null) {
            sorter = new TableRowSorter<>(model);
            buscarProducto.table.setRowSorter(sorter);
        }
        String textoBusqueda = buscarProducto.txtBusqueda.getText().trim();
        if (textoBusqueda.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda, 1));
        }
    }

    public void llenarComboBox() {
        try {
            List<Categoria> categorias = categoriaDAO.read();
            List<Proveedor> proveedores = proveedorDAO.read();

            nuevoProducto.cmbCategoria.removeAllItems();
            nuevoProducto.cmbProveedor.removeAllItems();
            editarProducto.cmbCategoria.removeAllItems();
            editarProducto.cmbProveedor.removeAllItems();

            for (Categoria c : categorias) {
                nuevoProducto.cmbCategoria.addItem(c.getNombre());
                editarProducto.cmbCategoria.addItem(c.getNombre());
            }

            for (Proveedor p : proveedores) {
                nuevoProducto.cmbProveedor.addItem(p.getNombre());
                editarProducto.cmbProveedor.addItem(p.getNombre());
            }
        } catch (Exception e) {
        }
    }

    public void llenarTabla() throws Exception {
        DefaultTableModel model = (DefaultTableModel) buscarProducto.table.getModel();
        model.setRowCount(0);
        for (Producto p : productoDAO.read()) {
            Object[] object = new Object[7];
            object[0] = p.getIdProducto();
            object[1] = p.getNombre();
            object[2] = p.getPrecioCompra();
            object[3] = p.getPrecioVenta();
            object[4] = p.getStock();
            object[5] = categoriaDAO.getNombre(p.getIdCategoria());
            object[6] = proveedorDAO.getNombre(p.getIdProveedor());
            model.addRow(object);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        buscarProducto.table.setRowSorter(sorter);
    }

    public void llenarDatosEditar(int fila) {
        editarProducto.txtID.setText(String.valueOf(buscarProducto.table.getValueAt(fila, 0)));
        editarProducto.txtNombre.setText(String.valueOf(buscarProducto.table.getValueAt(fila, 1)));
        editarProducto.txtPrecioCompra.setText(String.valueOf(buscarProducto.table.getValueAt(fila, 2)));
        editarProducto.txtPrecioVenta.setText(String.valueOf(buscarProducto.table.getValueAt(fila, 3)));
        editarProducto.spnStock.setValue(Integer.valueOf(String.valueOf(buscarProducto.table.getValueAt(fila, 4))));
        editarProducto.cmbCategoria.setSelectedItem(String.valueOf(buscarProducto.table.getValueAt(fila, 5)));
        editarProducto.cmbProveedor.setSelectedItem(String.valueOf(buscarProducto.table.getValueAt(fila, 6)));
    }

    /*Declaracion de variables*/
    private VtnInicio vtnInicio;
    private PanelProductos panelProductos;
    private BuscarProducto buscarProducto;
    private NuevoProducto nuevoProducto;
    private EditarProducto editarProducto;
    private ProductoDAO productoDAO;
    private ProveedorDAO proveedorDAO;
    private CategoriaDAO categoriaDAO;

    /*Constructor*/
    public ControlProducto(VtnInicio vtnInicio, PanelProductos panelProductos, BuscarProducto buscarProducto, NuevoProducto nuevoProducto, EditarProducto editarProducto, ProductoDAO productoDAO, ProveedorDAO proveedorDAO, CategoriaDAO categoriaDAO) {
        this.vtnInicio = vtnInicio;
        this.panelProductos = panelProductos;
        this.buscarProducto = buscarProducto;
        this.nuevoProducto = nuevoProducto;
        this.editarProducto = editarProducto;
        this.productoDAO = productoDAO;
        this.proveedorDAO = proveedorDAO;
        this.categoriaDAO = categoriaDAO;

        panelProductos.btnNuevoProducto.addActionListener(e -> showNuevoProducto());
        panelProductos.btnBuscarProducto.addActionListener(e -> {
            try {
                showBuscarProducto();
            } catch (Exception ex) {
                Logger.getLogger(ControlProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        nuevoProducto.btnRegistrar.addActionListener(e -> registrar());
        nuevoProducto.btnCancelar.addActionListener(e -> accionCancelar());
        buscarProducto.btnCancelar.addActionListener(e -> accionCancelar());
        buscarProducto.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = buscarProducto.table.getSelectedRow();
                    showEditarProducto(fila);
                }
            }
        });
        buscarProducto.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                buscarRegistro();
            }

            public void removeUpdate(DocumentEvent e) {
                buscarRegistro();
            }

            public void changedUpdate(DocumentEvent e) {
                buscarRegistro();
            }

        });
        editarProducto.btnActualizar.addActionListener(e -> actualizarRegistro());
        editarProducto.btnEliminar.addActionListener(e -> eliminarRegistro());
        editarProducto.btnCancelar.addActionListener(e -> accionCancelar());
    }
}
