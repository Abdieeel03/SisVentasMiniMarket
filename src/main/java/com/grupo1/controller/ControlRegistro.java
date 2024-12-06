package com.grupo1.controller;

import com.grupo1.models.ClienteDAO;
import com.grupo1.models.DetalleVentaDAO;
import com.grupo1.models.MedioPago;
import com.grupo1.models.MedioPagoDAO;
import com.grupo1.models.Producto;
import com.grupo1.models.ProductoDAO;
import com.grupo1.models.VentaDAO;
import com.grupo1.views.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Abdiel
 */
public class ControlRegistro {

    public void seleccionarProducto(int fila) {
        llenarDatosProducto(fila);
        vtnSeleccionar.setVisible(false);
    }

    public void buscarProducto() {
        vtnSeleccionar.txtBusqueda.setText("");
        llenarTablaSeleccionar();
        vtnSeleccionar.setVisible(true);
    }

    public void registrarVenta() {

    }

    public void botonCancelar() {
        vtnInicio.showJPanel(new PanelPrincipal());
        panelRegistrarVenta.txtNombreProducto.setText("");
    }

    public void llenarComboBox() {
        try {
            List<MedioPago> mediosPago = medioPagoDAO.read();
            panelRegistrarVenta.cmbMedioPago.removeAllItems();
            for (MedioPago m : mediosPago) {
                panelRegistrarVenta.cmbMedioPago.addItem(m.getNombre());
            }
        } catch (Exception e) {
        }
    }

    public void vaciarTabla() {
        DefaultTableModel model = (DefaultTableModel) panelRegistrarVenta.jTable1.getModel();
        model.setRowCount(0);
    }

    public void llenarTablaVenta() {
        DefaultTableModel model = (DefaultTableModel) panelRegistrarVenta.jTable1.getModel();
        Object[] object = new Object[4];
        object[0] = panelRegistrarVenta.txtNombreProducto.getText();
        object[1] = (int) panelRegistrarVenta.spnCantidad.getValue();
        object[2] = panelRegistrarVenta.txtPrecio.getText();
        object[3] = panelRegistrarVenta.txtSubtotal.getText();
        model.addRow(object);
    }

    public void llenarTablaSeleccionar() {
        DefaultTableModel model = (DefaultTableModel) vtnSeleccionar.tblProducto.getModel();
        model.setRowCount(0);
        try {
            for (Producto p : productoDAO.read()) {
                Object[] object = new Object[4];
                object[0] = p.getIdProducto();
                object[1] = p.getNombre();
                object[2] = p.getPrecioVenta();
                object[3] = p.getStock();
                model.addRow(object);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControlRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        vtnSeleccionar.tblProducto.setRowSorter(sorter);
    }

    public void llenarDatosProducto(int fila) {
        panelRegistrarVenta.txtNombreProducto.setText((String) vtnSeleccionar.tblProducto.getValueAt(fila, 1));
        panelRegistrarVenta.txtPrecio.setText(String.valueOf(vtnSeleccionar.tblProducto.getValueAt(fila, 2)));
    }

    public void buscarRegistro() {
        DefaultTableModel model = (DefaultTableModel) vtnSeleccionar.tblProducto.getModel();
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) vtnSeleccionar.tblProducto.getRowSorter();
        if (sorter == null) {
            sorter = new TableRowSorter<>(model);
            vtnSeleccionar.tblProducto.setRowSorter(sorter);
        }
        String textoBusqueda = vtnSeleccionar.txtBusqueda.getText().trim();
        if (textoBusqueda.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda, 1));
        }
    }

    public void agregarProducto() {
        if (panelRegistrarVenta.txtNombreProducto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vtnInicio, "No ha seleccionado ningun producto!", "AVISO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (panelRegistrarVenta.spnCantidad.getValue().equals((int) 0)) {
            JOptionPane.showMessageDialog(vtnInicio, "Seleccione la cantidad de productos!", "AVISO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        llenarTablaVenta();
        calcularTotal();
        panelRegistrarVenta.txtNombreProducto.setText("");
        panelRegistrarVenta.txtPrecio.setText("");
        panelRegistrarVenta.txtSubtotal.setText("");
        panelRegistrarVenta.spnCantidad.setValue((int) 0);
    }

    public void calcularTotal() {
        DefaultTableModel model = (DefaultTableModel) panelRegistrarVenta.jTable1.getModel();
        double subtotales = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object subtotal = model.getValueAt(i, 3);
            if (subtotal != null) {
                subtotales += Double.parseDouble(subtotal.toString());
            }
        }
        panelRegistrarVenta.lblSubTotal.setText(String.format("%.2f", subtotales));
        Double igv = subtotales * 0.18;
        panelRegistrarVenta.lblIGV.setText(String.format("%.2f", igv));
        Double total = subtotales + igv;
        panelRegistrarVenta.lblTotal.setText(String.format("%.2f", total));
    }

    /*Declaracion de variables*/
    private VtnInicio vtnInicio;
    private VtnSeleccionarProducto vtnSeleccionar;
    private PanelRegistrarVenta panelRegistrarVenta;
    private ProductoDAO productoDAO;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleDAO;
    private ClienteDAO clienteDAO;
    private MedioPagoDAO medioPagoDAO;

    //Constructor
    public ControlRegistro(VtnInicio vtnInicio, VtnSeleccionarProducto vtnSeleccionar, PanelRegistrarVenta panelRegistrarVenta, ProductoDAO productoDAO, VentaDAO ventaDAO, DetalleVentaDAO detalleDAO, ClienteDAO clienteDAO, MedioPagoDAO medioPagoDAO) {
        this.vtnInicio = vtnInicio;
        this.vtnSeleccionar = vtnSeleccionar;
        this.panelRegistrarVenta = panelRegistrarVenta;
        this.productoDAO = productoDAO;
        this.ventaDAO = ventaDAO;
        this.detalleDAO = detalleDAO;
        this.clienteDAO = clienteDAO;
        this.medioPagoDAO = medioPagoDAO;
        panelRegistrarVenta.txtNombreProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscarProducto();
            }
        });
        panelRegistrarVenta.btnRegistrarVenta.addActionListener(e -> registrarVenta());
        panelRegistrarVenta.btnCancelar.addActionListener(e -> botonCancelar());
        panelRegistrarVenta.btnAgregarProducto.addActionListener(e -> agregarProducto());
        panelRegistrarVenta.spnCantidad.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (panelRegistrarVenta.txtNombreProducto.getText().isEmpty()) {
                    return;
                }
                int cantidad = (int) panelRegistrarVenta.spnCantidad.getValue();
                if (cantidad != 0) {
                    double subtotal = cantidad * Double.parseDouble(panelRegistrarVenta.txtPrecio.getText());
                    panelRegistrarVenta.txtSubtotal.setText(String.valueOf(String.format("%.2f", subtotal)));
                }
            }
        });
        vtnSeleccionar.tblProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = vtnSeleccionar.tblProducto.getSelectedRow();
                    seleccionarProducto(fila);
                }
            }
        });
        vtnSeleccionar.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
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
    }

}
