package com.grupo1.controller;

import com.grupo1.models.Rol;
import com.grupo1.models.RolDAO;
import com.grupo1.models.Usuario;
import com.grupo1.models.UsuarioDAO;
import com.grupo1.views.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Abdiel
 */
public class ControlUsuario {

    private int idUsuario;

    public void showNuevoUsuario() {
        llenarComboBox();
        vtnInicio.mostrarPanel(nuevoUsuario);
    }

    public void showBuscarUsuario() throws Exception {
        llenarTabla();
        buscarUsuario.txtBusqueda.setText("");
        vtnInicio.mostrarPanel(buscarUsuario);
    }

    public void showEditarUsuario(int fila) {
        llenarComboBox();
        llenarDatosEditar(fila);
        vtnInicio.mostrarPanel(editarUsuario);
    }

    public void accionCancelar() {
        vtnInicio.mostrarPanel(panelUsuario);
    }

    public void registrar() throws Exception {

        if (nuevoUsuario.txtNombre.getText().isEmpty() || nuevoUsuario.txtUsuario.getText().isEmpty() || nuevoUsuario.txtContraseña.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos!");
            nuevoUsuario.txtNombre.requestFocus();
            return;
        }

        String nombreRol = nuevoUsuario.cmbRol.getSelectedItem().toString();
        Usuario usuario = new Usuario();
        usuario.setNombre(nuevoUsuario.txtNombre.getText());
        usuario.setUsuario(nuevoUsuario.txtUsuario.getText());
        usuario.setContraseña(nuevoUsuario.txtContraseña.getText());
        usuario.setDescripcion(nuevoUsuario.txtaDescripcion.getText());
        try {
            usuarioDAO.create(usuario, nombreRol);
            vtnInicio.mostrarPanel(panelUsuario);
            nuevoUsuario.limpiar();
            JOptionPane.showMessageDialog(vtnInicio, "Usuario creado correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarRegistro() {
        if (editarUsuario.txtNombre.getText().isEmpty() || editarUsuario.txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese todos los datos!");
            editarUsuario.txtNombre.requestFocus();
            return;
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(editarUsuario.txtNombre.getText());
        String nombreRol = editarUsuario.cmbRol.getSelectedItem().toString();
        usuario.setUsuario(editarUsuario.txtUsuario.getText());
        usuario.setDescripcion(editarUsuario.txtaDescripcion.getText());
        try {
            usuarioDAO.update(usuario, nombreRol, idUsuario);
            vtnInicio.mostrarPanel(panelUsuario);
            editarUsuario.limpiar();
            JOptionPane.showMessageDialog(vtnInicio, "Usuario actualizado correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarRegistro() {
        int opcion = JOptionPane.showConfirmDialog(vtnInicio,
                "¿Estás seguro de eliminar este usuario?",
                "AVISO",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                usuarioDAO.delete(idUsuario);
                vtnInicio.mostrarPanel(panelUsuario);
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente!", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                editarUsuario.limpiar();
            } catch (Exception ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(vtnInicio, "Ocurrio un error inesperado", "AVISO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buscarRegistro() {
        DefaultTableModel model = (DefaultTableModel) buscarUsuario.table.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) buscarUsuario.table.getRowSorter();
        if (sorter == null) {
            sorter = new TableRowSorter<>(model);
            buscarUsuario.table.setRowSorter(sorter);
        }
        String textoBusqueda = buscarUsuario.txtBusqueda.getText().trim();
        if (textoBusqueda.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda, 1));
        }
    }

    public void llenarComboBox() {
        try {
            List<Rol> roles = rolDAO.read();

            nuevoUsuario.cmbRol.removeAllItems();
            editarUsuario.cmbRol.removeAllItems();

            for (Rol rol : roles) {
                nuevoUsuario.cmbRol.addItem(rol.toString());
                editarUsuario.cmbRol.addItem(rol.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void llenarTabla() throws Exception {
        DefaultTableModel model = (DefaultTableModel) buscarUsuario.table.getModel();
        model.setRowCount(0);
        for (Usuario u : usuarioDAO.read()) {
            Object[] object = new Object[4];
            object[0] = u.getIdUsuario();
            object[1] = u.getNombre();
            object[2] = rolDAO.getNombrePorId(u.getIdRol());
            object[3] = u.getUsuario();
            model.addRow(object);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        buscarUsuario.table.setRowSorter(sorter);
    }

    public void llenarDatosEditar(int fila) {
        idUsuario = (int) buscarUsuario.table.getValueAt(fila, 0);
        editarUsuario.txtNombre.setText((String) buscarUsuario.table.getValueAt(fila, 1));
        editarUsuario.cmbRol.setSelectedItem((String) buscarUsuario.table.getValueAt(fila, 2));
        editarUsuario.txtUsuario.setText((String) buscarUsuario.table.getValueAt(fila, 3));
    }

    public void llenarDescripcion(String selectedItem) {
        switch (selectedItem) {
            case "Administrador":
                nuevoUsuario.txtaDescripcion.setText("Control Total del Sistema");
                editarUsuario.txtaDescripcion.setText("Control Total del Sistema");
                break;
            case "Moderador":
                nuevoUsuario.txtaDescripcion.setText("Limitado en la creacion de usuarios");
                editarUsuario.txtaDescripcion.setText("Limitado en la creacion de usuarios");
                break;
            case "Cajero":
                nuevoUsuario.txtaDescripcion.setText("Limitado solo a generar Ventas");
                editarUsuario.txtaDescripcion.setText("Limitado solo a generar Ventas");
                break;
            default:
                nuevoUsuario.txtaDescripcion.setText("Seleccione un rol.");
                break;
        }
    }

    //Declaracion de variables
    private VtnInicio vtnInicio;
    private PanelUsuario panelUsuario;
    private BuscarUsuario buscarUsuario;
    private NuevoUsuario nuevoUsuario;
    private EditarUsuario editarUsuario;
    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    //Controlador
    public ControlUsuario(VtnInicio vtnInicio, PanelUsuario panelUsuario, BuscarUsuario buscarUsuario, NuevoUsuario nuevoUsuario, EditarUsuario editarUsuario, UsuarioDAO usuarioDAO, RolDAO rolDAO) {
        this.vtnInicio = vtnInicio;
        this.panelUsuario = panelUsuario;
        this.buscarUsuario = buscarUsuario;
        this.nuevoUsuario = nuevoUsuario;
        this.editarUsuario = editarUsuario;
        this.usuarioDAO = usuarioDAO;
        this.rolDAO = rolDAO;
        panelUsuario.btnNuevoUsuario.addActionListener(e -> showNuevoUsuario());
        panelUsuario.btnBuscarUsuario.addActionListener(e -> {
            try {
                showBuscarUsuario();
            } catch (Exception ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        nuevoUsuario.btnCancelar.addActionListener(e -> accionCancelar());
        nuevoUsuario.btnRegistrar.addActionListener(e -> {
            try {
                registrar();
            } catch (Exception ex) {
                Logger.getLogger(ControlUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        nuevoUsuario.cmbRol.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    llenarDescripcion(selectedItem);
                }
            }
        });

        buscarUsuario.btnCancelar.addActionListener(e -> accionCancelar());
        buscarUsuario.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = buscarUsuario.table.getSelectedRow();
                    showEditarUsuario(fila);
                }
            }
        });
        buscarUsuario.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
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

        editarUsuario.cmbRol.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedItem = (String) e.getItem();
                    llenarDescripcion(selectedItem);
                }
            }
        });
        editarUsuario.btnEliminar.addActionListener(e -> eliminarRegistro());
        editarUsuario.btnActualizar.addActionListener(e -> actualizarRegistro());
        editarUsuario.btnCancelar.addActionListener(e -> accionCancelar());
    }
}
