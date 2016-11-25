<?php

include ('Conexion.php');
$tema = $_POST["tema"];

if( $_SERVER['REQUEST_METHOD'] == "POST" ){
    $sql_insertar_tema = "insert into temas_notificaciones values ('$tema')";
    mysqli_query($conexion,$sql_insertar_tema);

    $sql_insertar_tema_tabla = "alter table registro_notificaciones add $tema varchar (100)";
    mysqli_query($conexion,$sql_insertar_tema_tabla);

    mysqli_close($conexion);
}

?>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Notificaciones</title>

    <link rel="stylesheet" href="assets/demo.css">
    <link rel="stylesheet" href="assets/form-basic.css">

    <script type="text/javascript">
        function validarFormulario() {
            var txttema = document.getElementById('tema').value;
            if (txttema == "") { 
                        alert("Agregue un tema");
                        return false; // Si no se verifican las condiciones no se enviará el formulario
                    } else { 
                            alert("Tema de Notificacion Agregado");
                            return true; // Si se verifican las condiciones enviar formulario
                            }
        }
    </script>

</head>
<ul>
        <li><a href="Formulario_Enviar_Notificaciones.php">Enviar Notificaciones</a></li>
        <li><a href="Formulario_Agregar_Tema_Notificaciones.php" class="active">Agregar Tema</a></li>
        <li><a href="Formulario_Eliminar_Tema_Notificaciones.php">Eliminar Tema</a></li>
        <li><a href="Formulario_Agregar_Noticias.php">Agregar Noticias</a></li>
    </ul>

<body>

    
    <div class="main-content">

        <!-- You only need this form and the form-basic.css -->

        <form class="form-basic" method="post" action="<?php echo $_SERVER['PHP_SELF']; ?> " onsubmit="return validarFormulario()">

            <div class="form-title-row">
                <h1>Agregar Tema Notificaciones MICUSur</h1>
            </div>

            <div class="form-row">
                <label>
                    <span>Tema:</span>
                    <input type="text" name="tema" id="tema">
                </label>
            </div>

            <div class="form-row">
                <button type="submit" onClick="TemaAgregado()">Agregar Notificacion</button>
            </div>

        </form>

    </div>

</body>

</html>
