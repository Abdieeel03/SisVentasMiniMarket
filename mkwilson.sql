-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-12-2024 a las 18:05:56
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mkwilson`
--
CREATE DATABASE IF NOT EXISTS `mkwilson` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `mkwilson`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id_categoria`, `nombre`) VALUES
(8, 'Accesorios'),
(1, 'Alimentos'),
(15, 'Automotriz'),
(2, 'Bebidas'),
(7, 'Calzado'),
(13, 'Deportes'),
(4, 'Electrodomésticos'),
(9, 'Farmacia'),
(11, 'Ferretería'),
(10, 'Juguetería'),
(3, 'Limpieza'),
(14, 'Mascotas'),
(12, 'Papelería'),
(6, 'Ropa'),
(5, 'Tecnología');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` varchar(12) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`) VALUES
('00000000', 'Cliente Default'),
('70549834', 'MARCELO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleventa`
--

CREATE TABLE `detalleventa` (
  `id_detalle` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL CHECK (`cantidad` > 0),
  `precioUnitario` decimal(10,2) NOT NULL CHECK (`precioUnitario` >= 0),
  `subtotal` decimal(10,2) GENERATED ALWAYS AS (`cantidad` * `precioUnitario`) STORED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalleventa`
--

INSERT INTO `detalleventa` (`id_detalle`, `id_venta`, `id_producto`, `cantidad`, `precioUnitario`) VALUES
(14, 8, 173, 5, 8.00),
(15, 8, 165, 4, 35.00),
(16, 9, 109, 5, 3.00),
(17, 9, 110, 4, 5.00),
(18, 10, 109, 5, 3.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mediopago`
--

CREATE TABLE `mediopago` (
  `id_medioPago` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mediopago`
--

INSERT INTO `mediopago` (`id_medioPago`, `nombre`) VALUES
(2, 'Billetera Digital'),
(1, 'Efectivo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio_compra` decimal(10,2) NOT NULL CHECK (`precio_compra` >= 0),
  `precio_venta` decimal(10,2) NOT NULL CHECK (`precio_venta` >= 0),
  `stock` int(11) NOT NULL DEFAULT 0 CHECK (`stock` >= 0),
  `id_categoria` int(11) NOT NULL,
  `id_proveedor` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `precio_compra`, `precio_venta`, `stock`, `id_categoria`, `id_proveedor`) VALUES
(102, 'Arroz Blanco 1kg', 2.50, 3.50, 100, 1, '1012345678'),
(103, 'Fideos Espagueti 500g', 1.80, 2.80, 80, 1, '1012345678'),
(104, 'Lentejas 1kg', 3.00, 4.50, 70, 1, '1019876543'),
(105, 'Azúcar Rubia 1kg', 2.20, 3.20, 90, 1, '1019876543'),
(106, 'Harina de Trigo 1kg', 2.80, 3.80, 50, 1, '1016789123'),
(107, 'Aceite Vegetal 1L', 4.50, 6.00, 60, 1, '1016789123'),
(108, 'Sal de Mesa 500g', 1.00, 1.80, 120, 1, '1012345678'),
(109, 'Avena 500g', 2.00, 3.00, 100, 1, '1019876543'),
(110, 'Frijoles Canario 1kg', 3.50, 5.00, 70, 1, '1012345678'),
(111, 'Mantequilla 250g', 3.00, 4.20, 60, 1, '1019876543'),
(112, 'Cereal en Hojuelas 500g', 5.50, 7.50, 40, 1, '1016789123'),
(113, 'Galletas Integrales 300g', 4.20, 6.00, 80, 1, '1016789123'),
(114, 'Cacao en Polvo 500g', 6.00, 8.50, 50, 1, '1012345678'),
(115, 'Leche Condensada 400g', 4.00, 5.50, 70, 1, '1019876543'),
(116, 'Café Instantáneo 200g', 6.50, 9.00, 40, 1, '1016789123'),
(117, 'Mayonesa 500g', 3.50, 5.00, 60, 1, '1019876543'),
(118, 'Salsa de Tomate 500g', 2.50, 3.80, 100, 1, '1012345678'),
(119, 'Sopa Instantánea (paquete)', 1.50, 2.20, 150, 1, '1019876543'),
(120, 'Atún en Lata 180g', 3.20, 4.50, 80, 1, '1012345678'),
(121, 'Mermelada de Fresa 400g', 3.80, 5.50, 60, 1, '1016789123'),
(122, 'Agua Mineral 500ml', 1.00, 1.50, 200, 2, '1012348910'),
(123, 'Gaseosa 1.5L', 3.50, 5.00, 150, 2, '1012348910'),
(124, 'Jugo de Naranja 1L', 3.00, 4.50, 100, 2, '1014567892'),
(125, 'Energizante 250ml', 2.50, 4.00, 80, 2, '1014567892'),
(126, 'Té Helado 500ml', 2.00, 3.00, 120, 2, '1012348910'),
(127, 'Cerveza 1L', 5.00, 7.50, 60, 2, '1016789456'),
(128, 'Vino Tinto 750ml', 20.00, 30.00, 40, 2, '1016789456'),
(129, 'Whisky 750ml', 50.00, 75.00, 20, 2, '1016789456'),
(130, 'Agua Tónica 1L', 4.00, 6.00, 70, 2, '1012348910'),
(131, 'Café Listo 330ml', 3.00, 4.50, 90, 2, '1014567892'),
(132, 'Jugo de Manzana 1L', 3.20, 4.80, 100, 2, '1012348910'),
(133, 'Soda 500ml', 1.50, 2.00, 150, 2, '1014567892'),
(134, 'Chocolate Bebida 200ml', 2.00, 3.50, 120, 2, '1014567892'),
(135, 'Leche de Almendras 1L', 8.00, 12.00, 40, 2, '1016789456'),
(136, 'Limonada 500ml', 2.50, 4.00, 80, 2, '1012348910'),
(137, 'Gaseosa 2.5L', 6.00, 9.00, 60, 2, '1016789456'),
(138, 'Ron 750ml', 25.00, 35.00, 30, 2, '1016789456'),
(139, 'Champaña 750ml', 45.00, 70.00, 15, 2, '1016789456'),
(140, 'Agua con Gas 500ml', 1.80, 2.50, 100, 2, '1012348910'),
(141, 'Batido de Frutas 1L', 6.00, 8.50, 60, 2, '1014567892'),
(142, 'Detergente en Polvo 1kg', 5.00, 7.00, 100, 3, '1018934765'),
(143, 'Lavavajillas Líquido 1L', 4.00, 6.00, 90, 3, '1017638492'),
(144, 'Esponja Multiuso (pack)', 2.50, 4.00, 150, 3, '1013462897'),
(145, 'Trapeador Microfibra', 6.00, 9.00, 80, 3, '1018934765'),
(146, 'Desinfectante de Pisos 1L', 4.50, 6.50, 100, 3, '1017638492'),
(147, 'Jabón Líquido para Ropa 1L', 5.00, 8.00, 70, 3, '1013462897'),
(148, 'Bolsa de Basura 10 unidades', 3.00, 5.00, 120, 3, '1018934765'),
(149, 'Limpiador de Vidrios 500ml', 4.00, 6.00, 90, 3, '1017638492'),
(150, 'Cera para Pisos 1L', 8.00, 12.00, 40, 3, '1013462897'),
(151, 'Ambientador en Spray 300ml', 4.50, 6.50, 80, 3, '1018934765'),
(152, 'Cepillo para Limpieza', 3.00, 5.00, 100, 3, '1017638492'),
(153, 'Toallas de Papel (rollo)', 2.50, 3.50, 150, 3, '1013462897'),
(154, 'Limpiador Multiuso 1L', 6.00, 9.00, 60, 3, '1018934765'),
(155, 'Quitamanchas Líquido 500ml', 5.00, 7.00, 80, 3, '1017638492'),
(156, 'Cloro 1L', 3.00, 4.50, 100, 3, '1013462897'),
(157, 'Plancha de Ropa', 50.00, 75.00, 20, 4, '1012346789'),
(158, 'Licuadora Básica', 120.00, 160.00, 15, 4, '1018945637'),
(159, 'Sandwichera Eléctrica', 80.00, 110.00, 25, 4, '1013459821'),
(160, 'Audífonos Estéreo', 20.00, 30.00, 50, 5, '1019283746'),
(161, 'Cargador Universal', 15.00, 25.00, 60, 5, '1014758392'),
(162, 'Mouse Óptico', 10.00, 18.00, 80, 5, '1013859472'),
(163, 'Lentes de Sol', 30.00, 45.00, 50, 6, '1012847395'),
(164, 'Cartera de Mano', 40.00, 60.00, 30, 6, '1012847395'),
(165, 'Sombrero de Paja', 25.00, 35.00, 20, 6, '1013948572'),
(166, 'Guantes de Invierno', 20.00, 30.00, 40, 6, '1013948572'),
(167, 'Cinturón de Cuero', 35.00, 50.00, 25, 6, '1014857936'),
(168, 'Pulsera de Bisutería', 15.00, 25.00, 50, 6, '1014857936'),
(169, 'Paraguas Compacto', 25.00, 40.00, 30, 6, '1012847395'),
(170, 'Bufanda de Lana', 30.00, 45.00, 20, 6, '1013948572'),
(171, 'Billetera de Cuero', 40.00, 60.00, 35, 6, '1014857936'),
(172, 'Anillo de Plata', 50.00, 70.00, 15, 6, '1014857936'),
(173, 'Paracetamol 500mg', 5.00, 8.00, 100, 7, '1019475832'),
(174, 'Ibuprofeno 400mg', 6.00, 9.00, 90, 7, '1019475832'),
(175, 'Crema Antiséptica 50g', 10.00, 15.00, 50, 7, '1018394752'),
(176, 'Jarabe para la Tos 200ml', 12.00, 18.00, 40, 7, '1018394752'),
(177, 'Algodón 100g', 4.00, 6.00, 80, 7, '1015748392'),
(178, 'Vendas Elásticas', 7.00, 10.00, 60, 7, '1015748392'),
(179, 'Alcohol Antiséptico 1L', 10.00, 14.00, 70, 7, '1019475832'),
(180, 'Pastillas Antialérgicas', 15.00, 20.00, 50, 7, '1018394752'),
(181, 'Protector Solar SPF50', 30.00, 45.00, 20, 7, '1015748392'),
(182, 'Vitamina C 1g', 8.00, 12.00, 100, 7, '1019475832'),
(183, 'Alimento para Perro 3kg', 25.00, 35.00, 80, 8, '1013847592'),
(184, 'Alimento para Gato 3kg', 30.00, 45.00, 60, 8, '1013847592'),
(185, 'Collar Antipulgas', 15.00, 25.00, 100, 8, '1019374856'),
(186, 'Cama para Mascotas', 50.00, 75.00, 30, 8, '1019374856'),
(187, 'Juguete para Perros (pelota)', 10.00, 15.00, 150, 8, '1012948573'),
(188, 'Juguete para Gatos (ratón)', 8.00, 12.00, 120, 8, '1012948573'),
(189, 'Shampoo para Mascotas 500ml', 20.00, 30.00, 40, 8, '1013847592'),
(190, 'Cepillo para Mascotas', 10.00, 15.00, 100, 8, '1019374856'),
(191, 'Snacks para Perros (paquete)', 12.00, 18.00, 90, 8, '1012948573'),
(192, 'Arena para Gatos 10kg', 20.00, 35.00, 50, 8, '1012948573'),
(193, 'Correa para Perros', 25.00, 40.00, 60, 8, '1013847592'),
(194, 'Bebedero Automático', 35.00, 50.00, 20, 8, '1019374856'),
(195, 'Rascador para Gatos', 45.00, 60.00, 15, 8, '1012948573'),
(196, 'Transportadora para Mascotas', 70.00, 100.00, 10, 8, '1013847592'),
(197, 'Jaula para Aves', 50.00, 80.00, 25, 8, '1019374856');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` varchar(12) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `paginaWeb` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_proveedor`, `nombre`, `direccion`, `telefono`, `paginaWeb`) VALUES
('1012345665', 'MultiProductos', 'Av. El Sol 165 Chorrillos', '987123231', ''),
('1012345678', 'Distribuidora Alimentos SAC', 'Av. Gran Mercado 123, Lima', '987654311', 'www.alimentosdistrib.com'),
('1012346789', 'Electro Hogar', 'Av. Tecnología 123, Lima', '987654320', 'www.electrohogar.com'),
('1012348910', 'Bebidas del Norte', 'Av. Hidratación 123, Piura', '987654314', 'www.bebidasnorte.com'),
('1012839465', 'Estilo Andino', 'Calle Cultura 456, Cusco', '987654327', 'www.estiloandino.com'),
('1012847395', 'Complementos SAC', 'Av. Estilo 123, Lima', '987654332', 'www.complementossac.com'),
('1012948573', 'Mascota Feliz', 'Jr. Patitas 789, Chiclayo', '987654340', 'www.mascotafeliz.com'),
('1013459821', 'Innovación SAC', 'Jr. Avance 789, Ica', '987654322', 'www.innovacionsac.com'),
('1013462897', 'Brillo y Limpieza', 'Jr. Brillante 789, Arequipa', '987654319', 'www.brillolimpieza.com'),
('1013847592', 'Pet Distribuidor', 'Av. Mascotas 123, Lima', '987654338', 'www.petdistribuidor.com'),
('1013857294', 'Tendencias SAC', 'Jr. Pasarela 789, Trujillo', '987654328', 'www.tendenciassac.com'),
('1013859472', 'Innovación Andina', 'Jr. Software 789, Cusco', '987654325', 'www.innovacionandina.com'),
('1013948572', 'Accesorios Andinos', 'Calle Accesorios 456, Arequipa', '987654333', 'www.accesoriosandinos.com'),
('1014567892', 'Elixir del Sur', 'Calle Bebidas 456, Tacna', '987654315', 'www.elixirsur.com'),
('1014758392', 'Gadget Central', 'Calle Tecnología 456, Arequipa', '987654324', 'www.gadgetcentral.com'),
('1014759263', 'Moda Urbana', 'Av. Fashion 123, Lima', '987654326', 'www.modaurbana.com'),
('1014857936', 'Detalles Únicos', 'Jr. Originalidad 789, Chiclayo', '987654334', 'www.detallesunicos.com'),
('1014957382', 'Footwear World', 'Calle Pisadas 456, Lima', '987654330', 'www.footwearworld.com'),
('1015748392', 'Salud Total SAC', 'Jr. Medicinas 789, Lima', '987654337', 'www.saludtotalsac.com'),
('1015823947', 'Andes Shoes', 'Jr. Montañas 789, Cusco', '987654331', 'www.andesshoes.com'),
('1016789123', 'Sabores Andinos', 'Jr. Tradición 789, Arequipa', '987654313', 'www.saboresandinos.com'),
('1016789456', 'Refrescos Andinos', 'Jr. Sierra 789, Huancayo', '987654316', 'www.refrescosandinos.com'),
('1017382954', 'Zapatos del Sur', 'Av. Calzado 123, Arequipa', '987654329', 'www.zapatosdelsur.com'),
('1017638492', 'Distribuidora Higiene', 'Calle Limpieza 456, Trujillo', '987654318', 'www.higienedistrib.com'),
('1018394752', 'FarmAndes', 'Calle Bienestar 456, Cusco', '987654336', 'www.farmandes.com'),
('1018934765', 'Productos Limpieza SAC', 'Av. Aseo 123, Lima', '987654317', 'www.limpiezasac.com'),
('1018945637', 'TecnoDistribuidor', 'Calle Futuro 456, Chiclayo', '987654321', 'www.tecnodistribuidor.com'),
('1019283746', 'Tech World', 'Av. Digital 123, Lima', '987654323', 'www.techworld.com'),
('1019374856', 'Hogar Animal', 'Calle Amigos 456, Arequipa', '987654339', 'www.hogaranimal.com'),
('1019475832', 'Medicinas del Norte', 'Av. Salud 123, Piura', '987654335', 'www.medicinasnorte.com'),
('1019876543', 'Comercializadora Gourmet', 'Calle Central 456, Cusco', '987654312', 'www.gourmetdistrib.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre`) VALUES
(1, 'Administrador'),
(3, 'Cajero'),
(2, 'Moderador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre`, `id_rol`, `usuario`, `contraseña`, `descripcion`) VALUES
(2, 'Leslie Tinoco', 1, 'leslie123', '12345', 'Control Total del Sistema'),
(3, 'Marcelo Postigo', 1, 'marcelo123', '12345', 'Control Total del Sistema'),
(4, 'Sergio Tafur', 1, 'sergio123', '12345', 'Control Total del Sistema'),
(5, 'Miriam Laquise', 1, 'miriam123', '12345', 'Control Total del Sistema'),
(6, 'Juan Pedro', 3, 'cajero01', '12345', 'Limitado solo a generar Ventas'),
(7, 'Emilia Galvann', 3, 'cajero02', '12345', 'Limitado solo a generar Ventas'),
(8, 'Maria Cruz', 3, 'cajero03', '12345', 'Limitado solo a generar Ventas'),
(9, 'Carlos Enrique', 2, 'mod01', '12345', 'Limitado en la creacion de usuarios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL,
  `fechaVenta` datetime NOT NULL DEFAULT current_timestamp(),
  `total` decimal(10,2) NOT NULL CHECK (`total` >= 0),
  `id_medioPago` int(11) NOT NULL,
  `id_cliente` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id_venta`, `fechaVenta`, `total`, `id_medioPago`, `id_cliente`) VALUES
(8, '2024-12-06 00:00:00', 212.40, 1, '00000000'),
(9, '2024-12-06 00:00:00', 41.30, 2, '00000000'),
(10, '2024-12-06 00:00:00', 17.70, 1, '00000000');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `idx_detalleVenta_id_venta` (`id_venta`),
  ADD KEY `idx_detalleVenta_id_producto` (`id_producto`);

--
-- Indices de la tabla `mediopago`
--
ALTER TABLE `mediopago`
  ADD PRIMARY KEY (`id_medioPago`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `idx_producto_id_categoria` (`id_categoria`),
  ADD KEY `idx_producto_id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `idx_usuario_id_rol` (`id_rol`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id_venta`),
  ADD KEY `idx_venta_id_medioPago` (`id_medioPago`),
  ADD KEY `idx_venta_id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `mediopago`
--
ALTER TABLE `mediopago`
  MODIFY `id_medioPago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=199;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id_venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalleventa`
--
ALTER TABLE `detalleventa`
  ADD CONSTRAINT `detalleventa_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalleventa_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id_categoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_medioPago`) REFERENCES `mediopago` (`id_medioPago`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
