<?php
include ('Conexion.php');
$codigo = $_GET["codigo"];

$sql = "SELECT * FROM info_alumno WHERE Codigo = $codigo";
$resultado = $conexion->query($sql);

while ($row = $resultado->fetch_array(MYSQLI_NUM)){
        echo json_encode($row);
    }

?>