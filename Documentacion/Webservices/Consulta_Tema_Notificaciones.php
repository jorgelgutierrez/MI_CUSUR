<?php
include ('Conexion.php');

$sql = "SHOW COLUMNS FROM registro_notificaciones";
$resultado = $conexion->query($sql);

$array = array();

$i = 0;

while ($row = mysqli_fetch_assoc($resultado)){
        $array[$i] = $row;
        $i++;
    }
if($array!=null){
    echo json_encode($array);
}

?>