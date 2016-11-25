
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 22-09-2016 a las 14:08:17
-- Versión del servidor: 10.0.22-MariaDB
-- Versión de PHP: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `u258526589_cusur`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE IF NOT EXISTS `alumnos` (
  `Codigo` int(10) NOT NULL,
  `Nip` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`Codigo`, `Nip`) VALUES
(212464142, 'jorge'),
(212464141, 'jorge');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `boleta`
--

CREATE TABLE IF NOT EXISTS `boleta` (
  `Codigo` int(10) NOT NULL,
  `Materia` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Ordinario` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Kardex_O` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Extraordinario` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Kardex_E` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `boleta`
--

INSERT INTO `boleta` (`Codigo`, `Materia`, `Ordinario`, `Kardex_O`, `Extraordinario`, `Kardex_E`) VALUES
(212464142, 'Tesis 2', '55 - Cincuenta y Cinco', 'Si', '', ''),
(212464142, 'Practicas Profesionales', '100 - Cien', 'Si', '', ''),
(212464141, 'jorge', 'jorge', 'jorge', 'jorge', 'jorge');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE IF NOT EXISTS `horario` (
  `Codigo` int(10) NOT NULL,
  `Materia` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Horario` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Lunes` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Martes` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Miercoles` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Jueves` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Viernes` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Sabado` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Edificio` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Aula` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Profesor` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Fecha_Inicio` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Fecha_Fin` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`Codigo`, `Materia`, `Horario`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`, `Sabado`, `Edificio`, `Aula`, `Profesor`, `Fecha_Inicio`, `Fecha_Fin`) VALUES
(212464142, 'Programacion 2', '10:00 - 11:00', 'L', '', '', 'J', '', '', 'KDF', '4', 'Daniel Arechiga', '15/AGO/2016', '15/DIC/2016'),
(212464141, 'Lenguajes Algoritmicos', '09:00 - 10:00', 'L', '', '', 'J', '', '', 'KDF', '4', 'Jorge Luis Gutierrez Bautista', '15/AGO/2016', '15/DIC/2016');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `info_alumno`
--

CREATE TABLE IF NOT EXISTS `info_alumno` (
  `Codigo` int(10) NOT NULL,
  `Nombre` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Situacion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Nivel` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Admision` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Ultimo_Ciclo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Carrera` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Centro` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Sede` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Creditos` int(10) NOT NULL,
  `Creditos_Minimos` int(10) NOT NULL,
  `Creditos_Maximos` int(10) NOT NULL,
  `Porcentaje_Creditos` float NOT NULL,
  `Promedio` float NOT NULL,
  `Nombre_Imagen` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `info_alumno`
--

INSERT INTO `info_alumno` (`Codigo`, `Nombre`, `Situacion`, `Nivel`, `Admision`, `Ultimo_Ciclo`, `Carrera`, `Centro`, `Sede`, `Creditos`, `Creditos_Minimos`, `Creditos_Maximos`, `Porcentaje_Creditos`, `Promedio`, `Nombre_Imagen`) VALUES
(212464141, 'Jhoana Lizeth Pizano Ocampo', 'Alumno', 'Licenciatura', '2012B', '2016A', 'Lic. en Agronegocios', 'Centro Universitario del Sur', 'Ciudad Guzman', 310, 300, 320, 101, 94.02, '212464142.jpg'),
(212464142, 'Jorge Luis Gutierrez Bautista', 'Egresado', 'Licenciatura', '2012B', '2016A', 'Lic. en Ingeneria en Telematica', 'Centro Universitario del Sur', 'Ciudad Guzman', 310, 300, 320, 101, 94.02, '212464142.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `kardex`
--

CREATE TABLE IF NOT EXISTS `kardex` (
  `Codigo` int(10) NOT NULL,
  `NRC` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `Clave` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `Materia` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Calificacion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Tipo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Creditos` int(3) NOT NULL,
  `Fecha` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Calendario` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `kardex`
--

INSERT INTO `kardex` (`Codigo`, `NRC`, `Clave`, `Materia`, `Calificacion`, `Tipo`, `Creditos`, `Fecha`, `Calendario`) VALUES
(212464142, '12323', '12312', 'Programacion 2', '100 (Cien)', 'Ordinario (OE)', 8, '15/DIC/2012', 'Calendario 12B'),
(212464141, '123', '123', 'Lenguajes Algoritmicos', '100 (Cien)', 'Ordinario (OE)', 10, '12/DIC/2012', 'Calendario 12B');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticias`
--

CREATE TABLE IF NOT EXISTS `noticias` (
  `Nombre_Imagen` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Titulo` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Descripcion` longtext COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `noticias`
--

INSERT INTO `noticias` (`Nombre_Imagen`, `Titulo`, `Descripcion`) VALUES
('http://www.cusur.udg.mx/es/sites/default/files/simulacro_cusur_1_de_6.jpg', '', ''),
('conferencia_historia_e_ideologia_de_la_udeg_1_de_6 (1).jpg', '', ''),
('conferencia_historia_e_ideologia_de_la_udeg_1_de_6%20(1).jpg (1).jpg', '', ''),
('', 'hola', 'hola'),
('', '', ''),
('', 'm', 'm');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orden_pago`
--

CREATE TABLE IF NOT EXISTS `orden_pago` (
  `Codigo` int(10) NOT NULL,
  `Cuenta` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Concepto` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Descripcion` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Fecha` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Fecha_Vencimiento` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Monto` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `orden_pago`
--

INSERT INTO `orden_pago` (`Codigo`, `Cuenta`, `Concepto`, `Descripcion`, `Fecha`, `Fecha_Vencimiento`, `Monto`) VALUES
(212464142, '12313', '$150', 'Cooperación para la Cruz Roja Opcionalmente Obligatoria', '12/AGO/2016', '12/DIC/2016', '150'),
(212464141, '123123', '12312', '12312', '123123', '123123', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_notificaciones`
--

CREATE TABLE IF NOT EXISTS `registro_notificaciones` (
  `Token` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `Primero` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Becas` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `registro_notificaciones`
--

INSERT INTO `registro_notificaciones` (`Token`, `Primero`, `Becas`) VALUES
('s', NULL, NULL),
('212464142', NULL, NULL),
('21246mm4142', NULL, NULL),
('jorge', NULL, NULL),
('eUTr8nYD8vQ:APA91bHhx3ILZe0VQ8-VcfzQynMsScb1HWPpfAiHvtNEyZ964Uf1PRxRjuyRvSmq1uX8F3z_6EXli-oq7U4IYHcciljw3fbfo_yI6utoodIlFuHurmmK2nKM6VjGLqJR6R8WsU3elaFC', NULL, NULL),
('', NULL, NULL),
('hola', NULL, NULL),
('No se guardo el token del dispositivo', NULL, NULL),
('f_LykVRd-ZE:APA91bF-qMZOT1TLxurj6jc4U3gM9y8rxbKLDasYkYBL_6yVChwV4Ay6MG1W2ssqCSj99jofYpRpxclgquGpJ3pNd8a6454JKAK1xGHJgbNRxmCc9AuwKuRd85bxIfQnMfXXlaYDwJIx', ' ', 'x'),
('coCTUw-e3-A:APA91bF-dB38lBy1XDmtIJrBbcDZ3jr-DD7Rh14QuhHy8Da3iBx8IZRjbjgS6ImpJQg33MaOdedljv1Zcwcvu2gutsUxea_-m6jghbIykq189oWWVyMHnLyiuK2zX2PnqvA7wi0rn3ya', ' ', ' '),
('eYbEAtZnTOI:APA91bFSKQpenIKPY_nQgj4HGkIQbdkGT3tR3FCTA0FXKS50w8Zk5DUgXHWM4HNXOVfeKbN18Pylgdk1NMC8yb4xquEBt8Ho0jBkOUsdoU1t6GikuRHJW9PsyAp6YhhGEEr8-Jya4sXe', 'x', 'x');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temas_notificaciones`
--

CREATE TABLE IF NOT EXISTS `temas_notificaciones` (
  `Temas` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `temas_notificaciones`
--

INSERT INTO `temas_notificaciones` (`Temas`) VALUES
('Becas'),
('Primero');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
