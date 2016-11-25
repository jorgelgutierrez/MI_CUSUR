<?php

include ('Conexion.php');
$mensaje = $_POST["mensaje"];
$titulo = $_POST["titulo"];
$tema = $_POST["tema"];

$path_to_fcm = "https://fcm.googleapis.com/fcm/send";
$server_key = "AIzaSyDNzpBeJn9zrjAYprSx15bsa_lfuEt4E68";

if( $_SERVER['REQUEST_METHOD'] == "POST" ){

if($tema == "Todos"){
    $sql = "SELECT Token FROM registro_notificaciones";
}else{
    $sql = "SELECT Token FROM registro_notificaciones WHERE $tema='x'";
}
$result = mysqli_query($conexion,$sql);

while($row = mysqli_fetch_array($result)){

    $key = $row['Token'];

$headers = array(

    'Authorization:key='.$server_key,
    'Content-Type:application/json'
);

$fields = array('to'=>$key,
               'notification'=>array('title'=>$titulo,'body'=>$mensaje."#".$tema));

$payload = json_encode($fields);

$curl_session = curl_init();
curl_setopt($curl_session, CURLOPT_URL, $path_to_fcm);
curl_setopt($curl_session, CURLOPT_POST, true);
curl_setopt($curl_session, CURLOPT_HTTPHEADER, $headers);
curl_setopt($curl_session, CURLOPT_RETURNTRANSFER, true);
curl_setopt($curl_session, CURLOPT_SSL_VERIFYHOST, 0);
curl_setopt($curl_session, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($curl_session, CURLOPT_IPRESOLVE, CURL_IPRESOLVE_V4);
curl_setopt($curl_session, CURLOPT_POSTFIELDS, $payload);

curl_exec($curl_session);
curl_close($curl_session);

}
}

mysqli_close($conexion);

?>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Basic Form</title>

	<link rel="stylesheet" href="assets/demo.css">
	<link rel="stylesheet" href="assets/form-basic.css">

    <script type="text/javascript">
        function validarFormulario() {
            var txttitulo = document.getElementById('titulo').value;
            var txtmensaje = document.getElementById('mensaje').value;
            if (txttitulo == "" || txtmensaje == "") { 
                        alert("Llenar Completamente el Formulario");
                        return false; // Si no se verifican las condiciones no se enviará el formulario
                    } else { 
                            alert("Notificacion Enviada");
                            return true; // Si se verifican las condiciones enviar formulario
                            }
        }
    </script>

</head>
<ul>
        <li><a href="Formulario_Enviar_Notificaciones.php" class="active">Enviar Notificaciones</a></li>
        <li><a href="Formulario_Agregar_Tema_Notificaciones.php">Agregar Tema</a></li>
        <li><a href="Formulario_Eliminar_Tema_Notificaciones.php">Eliminar Tema</a></li>
        <li><a href="Formulario_Agregar_Noticias.php">Agregar Noticias</a></li>
    </ul>

<body>

    
    <div class="main-content">

        <!-- You only need this form and the form-basic.css -->

        <form name="formulario" class="form-basic" method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>" onsubmit="return validarFormulario()">

            <div class="form-title-row">
                <h1>Envio de Notificaciones MICUSur</h1>
            </div>

            <div class="form-row">
                <label>
                    <span>Titulo:</span>
                    <input type="text" name="titulo" id="titulo">
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Mensaje:</span>
                    <input type="text" name="mensaje" id="mensaje">
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Elegir a quien enviar:</span>
                    <select name="tema">
                        <option>Todos</option>
                        <?php
                            include ('Conexion.php');
                            $sql = "SELECT Temas FROM temas_notificaciones";
                            $resultado = $conexion->query($sql);

                            while ($row = mysqli_fetch_array($resultado)){
                                    echo "<option>".$row['Temas']."</option>";
                                }
                        ?>
                    </select>
                </label>
            </div>

            <div class="form-row">
                <button type="submit">Enviar Notificacion</button>
            </div>

        </form>

    </div>

</body>

</html>
