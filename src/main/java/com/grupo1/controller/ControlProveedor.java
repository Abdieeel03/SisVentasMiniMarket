package com.grupo1.controller;

import com.grupo1.models.Proveedor;
import com.grupo1.models.ProveedorDAO;
import com.grupo1.views.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ControlProveedor {

    public void showNuevoProveedor() {
        nuevoProveedor.limpiar();
        vtnInicio.showJPanel(nuevoProveedor);
    }

    public void showBuscarProveedor() {
        try {
            llenarTabla();
        } catch (Exception ex) {
            Logger.getLogger(ControlProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        buscarProveedor.txtBusqueda.setText("");
        vtnInicio.showJPanel(buscarProveedor);
    }

    public void showEditarProveedor(int fila) {
        editarProveedor.limpiar();
        llenarDatosEditar(fila);
        vtnInicio.showJPanel(editarProveedor);
    }

    public void accionCancelar() {
        vtnInicio.showJPanel(panelProveedor);
    }

    public void accionSalir() {
        vtnInicio.showJPanel(panelProveedor);
    }

    public void registrar() {
        if (nuevoProveedor.txtNombre.getText().isEmpty() || nuevoProveedor.txtRUC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el RUC y Nombre!", "AVISO", JOptionPane.ERROR_MESSAGE);
            nuevoProveedor.txtNombre.requestFocus();
            return;
        }
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(nuevoProveedor.txtRUC.getText());
        proveedor.setNombre(nuevoProveedor.txtNombre.getText());
        proveedor.setDireccion(nuevoProveedor.txtDireccion.getText());
        proveedor.setTelefono(nuevoProveedor.txtTelefono.getText());
        proveedor.setPaginaWeb(nuevoProveedor.txtPagina.getText());
        try {
            proveedorDAO.create(proveedor);
            vtnInicio.showJPanel(panelProveedor);
            JOptionPane.showMessageDialog(vtnInicio, "Proveedor creado correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControlProveedor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarRegistro() {
        if (editarProveedor.txtNombre.getText().isEmpty() || editarProveedor.txtRUC.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el RUC y Nombre!", "AVISO", JOptionPane.ERROR_MESSAGE);
            editarProveedor.txtNombre.requestFocus();
            return;
        }
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(editarProveedor.txtRUC.getText());
        proveedor.setNombre(editarProveedor.txtNombre.getText());
        proveedor.setDireccion(editarProveedor.txtDireccion.getText());
        proveedor.setTelefono(editarProveedor.txtTelefono.getText());
        proveedor.setPaginaWeb(editarProveedor.txtPagina.getText());
        try {
            proveedorDAO.update(proveedor);
            vtnInicio.showJPanel(panelProveedor);
            JOptionPane.showMessageDialog(null, "Proveedor actualizado correctamente!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControlProveedor.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarRegistro() {
        int opcion = JOptionPane.showConfirmDialog(vtnInicio,
                "¿Estás seguro de querer eliminar este proveedor?",
                "AVISO",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(editarProveedor.txtRUC.getText());
                proveedorDAO.delete(proveedor);
                vtnInicio.showJPanel(panelProveedor);
                JOptionPane.showMessageDialog(null, "Proveedor eliminado correctamente!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void buscarRegistro() {
        DefaultTableModel model = (DefaultTableModel) buscarProveedor.tableProveedor.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) buscarProveedor.tableProveedor.getRowSorter();
        if (sorter == null) {
            sorter = new TableRowSorter<>(model);
            buscarProveedor.tableProveedor.setRowSorter(sorter);
        }
        String textoBusqueda = buscarProveedor.txtBusqueda.getText().trim();
        if (textoBusqueda.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda, 0));
        }
    }

    public void llenarTabla() throws Exception {
        DefaultTableModel model = (DefaultTableModel) buscarProveedor.tableProveedor.getModel();
        model.setRowCount(0);
        for (Proveedor p : proveedorDAO.read()) {
            Object[] object = new Object[5];
            object[0] = p.getIdProveedor();
            object[1] = p.getNombre();
            object[2] = p.getDireccion();
            object[3] = p.getTelefono();
            object[4] = p.getPaginaWeb();
            model.addRow(object);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        buscarProveedor.tableProveedor.setRowSorter(sorter);
    }

    public void llenarDatosEditar(int fila) {
        editarProveedor.txtRUC.setText((String) buscarProveedor.tableProveedor.getValueAt(fila, 0));
        editarProveedor.txtNombre.setText((String) buscarProveedor.tableProveedor.getValueAt(fila, 1));
        editarProveedor.txtDireccion.setText((String) buscarProveedor.tableProveedor.getValueAt(fila, 2));
        editarProveedor.txtTelefono.setText((String) buscarProveedor.tableProveedor.getValueAt(fila, 3));
        editarProveedor.txtPagina.setText((String) buscarProveedor.tableProveedor.getValueAt(fila, 4));
    }

    /*Declaracion de variables*/
    private VtnInicio vtnInicio;
    private PanelProveedor panelProveedor;
    private BuscarProveedor buscarProveedor;
    private NuevoProveedor nuevoProveedor;
    private EditarProveedor editarProveedor;
    private ProveedorDAO proveedorDAO;

    /*Constructor*/
    public ControlProveedor(VtnInicio vtnInicio, PanelProveedor panelProveedor, BuscarProveedor buscarProveedor, NuevoProveedor nuevoProveedor, EditarProveedor editarProveedor, ProveedorDAO proveedorDAO) {
        this.vtnInicio = vtnInicio;
        this.panelProveedor = panelProveedor;
        this.buscarProveedor = buscarProveedor;
        this.nuevoProveedor = nuevoProveedor;
        this.editarProveedor = editarProveedor;
        this.proveedorDAO = proveedorDAO;
        panelProveedor.btnNuevoProveedor.addActionListener(e -> showNuevoProveedor());
        panelProveedor.btnBuscarProveedor.addActionListener(e -> showBuscarProveedor());
        nuevoProveedor.btnSalir.addActionListener(e -> accionSalir());
        nuevoProveedor.btnRegistrar.addActionListener(e -> registrar());
        buscarProveedor.btnCancelar.addActionListener(e -> accionCancelar());
        buscarProveedor.tableProveedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = buscarProveedor.tableProveedor.getSelectedRow();
                    showEditarProveedor(fila);
                }
            }
        });
        buscarProveedor.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
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
        editarProveedor.btnActualizar.addActionListener(e -> actualizarRegistro());
        editarProveedor.btnEliminar.addActionListener(e -> eliminarRegistro());
        editarProveedor.btnSalir.addActionListener(e -> accionSalir());
    }
}
