<?php

include ('Conexion.php');
$token = $_POST["token"];
$temas = $_POST["temas"];

$sql_eliminar = "DELETE FROM registro_notificaciones WHERE Token='$token'";
mysqli_query($conexion,$sql_eliminar);

if($temas == "nada"){
    $sql_insertar = "INSERT INTO registro_notificaciones (Token) VALUES ('$token')";
    mysqli_query($conexion,$sql_insertar);

}else{ 
    $sql_insertar = "insert into registro_notificaciones values ('$token'";
        $array = explode(",",$temas);
        $array = array_reverse($array,true);
        foreach ($array as $valor) {
                $verificar = substr($valor,0,7);
                if($verificar == "checked"){
                    echo "x";
                    $sql_insertar = $sql_insertar ." ,'x' ";
                }else{
                    echo "no";
                    $sql_insertar = $sql_insertar ." ,' ' ";
                } 
        }
    $sql_insertar = $sql_insertar .")";
    mysqli_query($conexion,$sql_insertar);
}

mysqli_close($conexion);

?>