package com.grupo1.controller;

import com.grupo1.models.*;
import com.grupo1.views.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdiel
 */
public class Controlador {

    public void iniciarEjecucion() {
        vtnLogin.setVisible(false);
        vtnInicio.showJPanel(new PanelPrincipal());
        vtnInicio.setVisible(true);
    }

    public void showPanelRegistro() {
        controlRegistro.limpiarDatos();
        controlRegistro.llenarComboBox();
        controlRegistro.vaciarTabla();
        vtnInicio.showJPanel(panelRegistrarVenta);
    }

    public void showPanelProducto() {
        if (rol == "C") {
            JOptionPane.showMessageDialog(null, "Usted no puede acceder a este apartado!");
            return;
        }
        vtnInicio.showJPanel(panelProductos);
    }

    public void showPanelProveedores() {
        if (rol == "C") {
            JOptionPane.showMessageDialog(null, "Usted no puede acceder a este apartado!");
            return;
        }
        vtnInicio.showJPanel(panelProveedor);
    }

    public void showPanelUsuarios() {
        if (rol == "C" || rol == "M") {
            JOptionPane.showMessageDialog(null, "Usted no puede acceder a este apartado!");
            return;
        }
        vtnInicio.showJPanel(panelUsuario);
    }

    public void showPanelReporte() {
        if (rol == "C") {
            JOptionPane.showMessageDialog(null, "Usted no puede acceder a este apartado!");
            return;
        }
        controlReporte.mostrarEstadisticas();
        vtnInicio.showJPanel(panelReporte);
    }

    public void verificarLogin() {
        try {

            if (vtnLogin.txtUsuario.getText().isEmpty() || vtnLogin.txtContra.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Datos Incorrectos!");
                vtnLogin.txtUsuario.requestFocus();
                return;
            }

            List<Usuario> usuarios = usuarioDAO.read();

            boolean usuarioEncontrado = false;
            String rol = "";

            for (Usuario user : usuarios) {
                if (user.getUsuario().equals(vtnLogin.txtUsuario.getText())) {
                    if (user.getContraseña().equals(vtnLogin.txtContra.getText())) {
                        usuarioEncontrado = true;
                        rol = obtenerRol(user.getIdRol());
                        break;
                    }
                }
            }

            if (usuarioEncontrado) {
                switch (rol) {
                    case "Administrador":
                        this.rol = "A";
                        break;
                    case "Moderador":
                        this.rol = "M";
                        break;
                    case "Cajero":
                        this.rol = "C";
                        break;
                    default:
                        System.out.println("Rol desconocido.");
                }
                iniciarEjecucion();
            } else {
                JOptionPane.showMessageDialog(null, "Datos Incorrectos!");
            }
        } catch (Exception e) {
        }
    }

    private String obtenerRol(int idRol) {
        switch (idRol) {
            case 1:
                return "Administrador";
            case 2:
                return "Moderador";
            case 3:
                return "Cajero";
            default:
                return "Rol desconocido";
        }
    }

    public void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(vtnInicio,
                "¿Estás seguro de querer cerrar sesión?",
                "Confirmar cierre",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            vtnInicio.setVisible(false);
            vtnLogin.limpiar();
            vtnLogin.setVisible(true);
        }
    }

    /*Declaracion de variables*/
    //Ventanas
    private VtnLogin vtnLogin;
    private VtnInicio vtnInicio;
    //Registro
    private PanelRegistrarVenta panelRegistrarVenta;
    private ControlRegistro controlRegistro;
    //Productos
    private PanelProductos panelProductos;
    private BuscarProducto buscarProducto;
    private NuevoProducto nuevoProducto;
    private EditarProducto editarProducto;
    private VtnSeleccionarProducto vtnSeleccionar;
    private ControlProducto controlProducto;
    //Proveedor
    private PanelProveedor panelProveedor;
    private BuscarProveedor buscarProveedor;
    private NuevoProveedor nuevoProveedor;
    private EditarProveedor editarProveedor;
    private ControlProveedor controlProveedor;
    //Usuarios
    private PanelUsuario panelUsuario;
    private BuscarUsuario buscarUsuario;
    private NuevoUsuario nuevoUsuario;
    private EditarUsuario editarUsuario;
    private ControlUsuario controlUsuario;
    //Reporte
    private ReporteDeVentas panelReporte;
    private ControlReporte controlReporte;
    //Rol
    private String rol;
    //DAO
    private CategoriaDAO categoriaDAO;
    private ClienteDAO clienteDAO;
    private DetalleVentaDAO detalleDAO;
    private MedioPagoDAO medioPagoDAO;
    private ProductoDAO productoDAO;
    private ProveedorDAO proveedorDAO;
    private RolDAO rolDAO;
    private UsuarioDAO usuarioDAO;
    private VentaDAO ventaDAO;

    //Constructor Principal
    public Controlador(VtnInicio vtnInicio, VtnLogin vtnLogin, CategoriaDAO categoriaDAO, ClienteDAO clienteDAO, DetalleVentaDAO detalleDAO, MedioPagoDAO medioPagoDAO, ProductoDAO productoDAO, ProveedorDAO proveedorDAO, RolDAO rolDAO, UsuarioDAO usuarioDAO, VentaDAO ventaDAO) {
        this.vtnInicio = vtnInicio;
        this.vtnLogin = vtnLogin;
        this.categoriaDAO = categoriaDAO;
        this.clienteDAO = clienteDAO;
        this.detalleDAO = detalleDAO;
        this.medioPagoDAO = medioPagoDAO;
        this.productoDAO = productoDAO;
        this.proveedorDAO = proveedorDAO;
        this.rolDAO = rolDAO;
        this.usuarioDAO = usuarioDAO;
        this.ventaDAO = ventaDAO;
        vtnLogin.btnIngresar.addActionListener(e -> verificarLogin());
        vtnInicio.btnGenerarVenta.addActionListener(e -> showPanelRegistro());
        vtnInicio.btnProductos.addActionListener(e -> showPanelProducto());
        vtnInicio.btnProveedores.addActionListener(e -> showPanelProveedores());
        vtnInicio.btnUsuarios.addActionListener(e -> showPanelUsuarios());
        vtnInicio.btnReporteVentas.addActionListener(e -> showPanelReporte());
        vtnInicio.btnCerrarSesion.addActionListener(e -> cerrarSesion());
        vtnLogin.txtUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verificarLogin();
                }
            }
        });
        vtnLogin.txtContra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verificarLogin();
                }
            }
        });

        /*Instanciar demás paneles y controladores*/
        //Registro
        panelRegistrarVenta = new PanelRegistrarVenta();
        vtnSeleccionar = new VtnSeleccionarProducto();
        controlRegistro = new ControlRegistro(vtnInicio, vtnSeleccionar, panelRegistrarVenta, productoDAO, ventaDAO, detalleDAO,clienteDAO, medioPagoDAO);
        //Productos
        panelProductos = new PanelProductos();
        nuevoProducto = new NuevoProducto();
        buscarProducto = new BuscarProducto();
        editarProducto = new EditarProducto();
        controlProducto = new ControlProducto(vtnInicio, panelProductos, buscarProducto, nuevoProducto, editarProducto, productoDAO, proveedorDAO, categoriaDAO);
        //Proveedor
        panelProveedor = new PanelProveedor();
        nuevoProveedor = new NuevoProveedor();
        buscarProveedor = new BuscarProveedor();
        editarProveedor = new EditarProveedor();
        controlProveedor = new ControlProveedor(vtnInicio, panelProveedor, buscarProveedor, nuevoProveedor, editarProveedor, proveedorDAO);
        //Usuarios
        panelUsuario = new PanelUsuario();
        nuevoUsuario = new NuevoUsuario();
        buscarUsuario = new BuscarUsuario();
        editarUsuario = new EditarUsuario();
        controlUsuario = new ControlUsuario(vtnInicio, panelUsuario, buscarUsuario, nuevoUsuario, editarUsuario, usuarioDAO, rolDAO);
        //Reporte
        panelReporte = new ReporteDeVentas();
        controlReporte = new ControlReporte(vtnInicio, panelReporte, productoDAO);
    }
}
