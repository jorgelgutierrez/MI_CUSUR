<?php
include ('Conexion.php');

$sql = "SELECT * FROM noticias ORDER BY Id_Noticia DESC LIMIT 0, 3";
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