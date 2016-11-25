<?php
include ('Conexion.php');
$codigo = $_GET["codigo"];
$nip = $_GET["nip"];

$sql = "SELECT * FROM alumnos WHERE Codigo = $codigo AND NIP = '$nip'";
$resultado = $conexion->query($sql);

while ($row = $resultado->fetch_array(MYSQLI_NUM)){
        echo json_encode($row);
    }

?>