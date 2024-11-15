package com.grupo1.controller;

import com.grupo1.models.*;
import com.grupo1.views.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdiel
 */
public class Controlador {

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
        vtnInicio.btnGenerarVenta.addActionListener(e -> showPanelRegistro());
        vtnInicio.btnProductos.addActionListener(e -> showPanelProducto());
        vtnInicio.btnProveedores.addActionListener(e -> showPanelProveedores());
        vtnInicio.btnUsuarios.addActionListener(e -> showPanelUsuarios());
        vtnInicio.btnReporteVentas.addActionListener(e -> showPanelReporte());
        vtnInicio.btnCerrarSesion.addActionListener(e -> cerrarSesion());
        vtnLogin.btnAdministrador.addActionListener(e -> iniciarSesionA());
        vtnLogin.btnModerador.addActionListener(e -> iniciarSesionM());
        vtnLogin.btnCajero.addActionListener(e -> iniciarSesionC());

        /*Instanciar demás paneles y controladores*/
        //Registro
        panelRegistrarVenta = new PanelRegistrarVenta();
        vtnSeleccionar = new VtnSeleccionarProducto();
        controlRegistro = new ControlRegistro(vtnInicio, vtnSeleccionar, panelRegistrarVenta);
        //Productos
        panelProductos = new PanelProductos();
        nuevoProducto = new NuevoProducto();
        buscarProducto = new BuscarProducto();
        editarProducto = new EditarProducto();
        controlProducto = new ControlProducto(vtnInicio, panelProductos, buscarProducto, nuevoProducto, editarProducto);
        //Proveedor
        panelProveedor = new PanelProveedor();
        nuevoProveedor = new NuevoProveedor();
        buscarProveedor = new BuscarProveedor();
        editarProveedor = new EditarProveedor();
        controlProveedor = new ControlProveedor(vtnInicio, panelProveedor, buscarProveedor, nuevoProveedor, editarProveedor);
        //Usuarios
        panelUsuario = new PanelUsuario();
        nuevoUsuario = new NuevoUsuario();
        buscarUsuario = new BuscarUsuario();
        editarUsuario = new EditarUsuario();
        controlUsuario = new ControlUsuario(vtnInicio, panelUsuario, buscarUsuario, nuevoUsuario, editarUsuario);
        //Reporte
        panelReporte = new ReporteDeVentas();
        controlReporte = new ControlReporte(vtnInicio, panelReporte);

    }

    public void iniciarEjecucion() {
        vtnLogin.setVisible(false);
        vtnInicio.showJPanel(new PanelPrincipal());
        vtnInicio.setVisible(true);
    }

    public void iniciarSesionA() {
        rol = "A";
        iniciarEjecucion();
    }

    public void iniciarSesionM() {
        rol = "M";
        iniciarEjecucion();
    }

    public void iniciarSesionC() {
        rol = "C";
        iniciarEjecucion();
    }

    public void showPanelRegistro() {
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
        vtnInicio.showJPanel(panelReporte);
    }

    public void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(vtnInicio,
                "¿Estás seguro de querer cerrar sesión?",
                "Confirmar cierre",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            vtnInicio.setVisible(false);
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
}
