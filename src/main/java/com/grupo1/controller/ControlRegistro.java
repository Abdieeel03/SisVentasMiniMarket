package com.grupo1.controller;

import com.grupo1.models.Cliente;
import com.grupo1.models.ClienteDAO;
import com.grupo1.models.DetalleVenta;
import com.grupo1.models.DetalleVentaDAO;
import com.grupo1.models.MedioPago;
import com.grupo1.models.MedioPagoDAO;
import com.grupo1.models.Producto;
import com.grupo1.models.ProductoDAO;
import com.grupo1.models.Venta;
import com.grupo1.models.VentaDAO;
import com.grupo1.views.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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

    public void registrarVenta() throws SQLException, Exception {
        DefaultTableModel model = (DefaultTableModel) panelRegistrarVenta.jTable1.getModel();
        if(model.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun producto!", "AVISO", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Registrar Cliente
        Cliente c = guardarCliente();
        try {
            if (!clienteDAO.verificarExistencia(c)) {
                try {
                    clienteDAO.create(c);
                } catch (Exception ex) {
                    Logger.getLogger(ControlRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Obtener Datos Venta
        Venta v = new Venta();
        v.setFechaVenta(new Date());
        v.setTotal(Double.parseDouble(panelRegistrarVenta.lblTotal.getText()));
        v.setIdMedioPago(medioPagoDAO.obtenerIdporNombre(panelRegistrarVenta.cmbMedioPago.getSelectedItem().toString()));
        v.setIdCliente(c.getId_cliente());
        int idVenta = ventaDAO.create(v);
        v.setId_venta(idVenta);

        //Obtener Datos DetalleVenta
        for (Object fila : model.getDataVector()) {
            Vector<?> vectorFila = (Vector<?>) fila;
            DetalleVenta detalle = new DetalleVenta();
            Producto p = new Producto();
            detalle.setIdVenta(v.getId_venta());
            detalle.setIdProducto(productoDAO.obtenerIdporNombre(String.valueOf(vectorFila.get(0))));
            detalle.setCantidad(Integer.parseInt(String.valueOf(String.valueOf(vectorFila.get(1)))));
            detalle.setPrecioUnitario(Double.parseDouble(String.valueOf(vectorFila.get(2))));
            detalle.setSubtotal(Double.parseDouble(String.valueOf(vectorFila.get(3))));
            v.getDetalleVentas().add(detalle);
            //Actualiza stock del producto
            p.setIdProducto(productoDAO.obtenerIdporNombre(String.valueOf(vectorFila.get(0))));
            int cantidad = Integer.parseInt(String.valueOf(String.valueOf(vectorFila.get(1))));
            productoDAO.actualizarStock(p, cantidad);
        }

        detalleDAO.create(v.getDetalleVentas());

        vtnInicio.mostrarPanel(new PanelPrincipal());
        limpiarDatos();
        model.setRowCount(0);
        JOptionPane.showMessageDialog(null, "Venta Realizada Correctamente", "AVISO", JOptionPane.INFORMATION_MESSAGE);

    }

    public Cliente guardarCliente() {
        Cliente c = new Cliente();
        if (panelRegistrarVenta.txtDocumento.getText().isEmpty() || panelRegistrarVenta.txtNombreCliente.getText().isEmpty()) {
            c.setId_cliente("00000000");
            c.setNombre("Cliente Default");
            return c;
        }
        c.setId_cliente(panelRegistrarVenta.txtDocumento.getText());
        c.setNombre(panelRegistrarVenta.txtNombreCliente.getText());
        return c;
    }

    public void botonCancelar() {
        vtnInicio.mostrarPanel(new PanelPrincipal());
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

    public void calcularCambio() {
        if (panelRegistrarVenta.txtDineroRecibido.getText().isEmpty()) {
            return;
        }
        try {
            Double total = Double.parseDouble(panelRegistrarVenta.lblTotal.getText());
            Double dineroRecibido = Double.parseDouble(panelRegistrarVenta.txtDineroRecibido.getText());
            Double cambio = dineroRecibido - total;
            panelRegistrarVenta.txtCambio.setText(String.valueOf(String.format("%.2f", cambio)));
        } catch (Exception e) {
            panelRegistrarVenta.txtDineroRecibido.setText("");
            JOptionPane.showMessageDialog(null, "Ingrese un número correcto", "AVUSO", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void desbloquearCambio(String item) {
        switch (item) {
            case "Billetera Digital":
                panelRegistrarVenta.txtDineroRecibido.setText("");
                panelRegistrarVenta.txtDineroRecibido.setEditable(false);
                break;
            case "Efectivo":
                panelRegistrarVenta.txtDineroRecibido.setText("");
                panelRegistrarVenta.txtDineroRecibido.setEditable(true);
                break;
            default:
                throw new AssertionError();
        }
    }

    public void limpiarDatos() {
        panelRegistrarVenta.limpiar();
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
        panelRegistrarVenta.btnRegistrarVenta.addActionListener(e -> {
            try {
                registrarVenta();
            } catch (SQLException ex) {
                Logger.getLogger(ControlRegistro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ControlRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
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
        panelRegistrarVenta.txtDineroRecibido.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                calcularCambio();
            }

            public void removeUpdate(DocumentEvent e) {
                calcularCambio();
            }

            public void changedUpdate(DocumentEvent e) {
                calcularCambio();
            }

        });
        panelRegistrarVenta.cmbMedioPago.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String item = (String) e.getItem();
                    desbloquearCambio(item);
                }
            }

        });
    }

}
