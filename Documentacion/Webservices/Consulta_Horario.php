<?php
include ('Conexion.php');
$codigo = $_GET["codigo"];

$sql = "SELECT * FROM horario WHERE Codigo = $codigo";
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