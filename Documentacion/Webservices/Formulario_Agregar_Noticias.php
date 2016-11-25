<?php

include ('Conexion.php');
$titulo = $_POST["titulo"];
$descripcion = $_POST["descripcion"];
$nombre_imagen = $_FILES["archivo"]["name"];
$sinopsis = $_POST["sinopsis"];
$fecha = date("D, d M Y - H:i ")."-- Comunicación Social";

if( $_SERVER['REQUEST_METHOD'] == "POST" ){

    $sql_insertar = "insert into noticias (Nombre_Imagen,Titulo,Descripcion,Sinopsis,Fecha_Post) values ('$nombre_imagen','$titulo','$descripcion','$sinopsis','$fecha')";
    mysqli_query($conexion,$sql_insertar);

    $archivo = $_FILES["archivo"]["tmp_name"];
    $destino = "Imagenes/".$_FILES["archivo"]["name"];

    move_uploaded_file($archivo, $destino);
}

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
            var file = document.getElementById('file').value;
            if (file == "") { 
                        alert("La Imagen es obligatoria para enviar la noticia");
                        return false; // Si no se verifican las condiciones no se enviará el formulario
                    } else { 
                            alert("Noticia Agregada");
                            return true; // Si se verifican las condiciones enviar formulario
                            }
        }
    </script>

</head>
<ul>
        <li><a href="Formulario_Enviar_Notificaciones.php">Enviar Notificaciones</a></li>
        <li><a href="Formulario_Agregar_Tema_Notificaciones.php">Agregar Tema</a></li>
        <li><a href="Formulario_Eliminar_Tema_Notificaciones.php">Eliminar Tema</a></li>
        <li><a href="Formulario_Agregar_Noticias.php"  class="active">Agregar Noticias</a></li>
    </ul>

<body>

    
    <div class="main-content">

        <!-- You only need this form and the form-basic.css -->

        <form class="form-basic" method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>" enctype="multipart/form-data" onsubmit="return validarFormulario()">

            <div class="form-title-row">
                <h1>Agregar Noticias App MICUSur</h1>
            </div>

            <div class="form-row">
                <label>
                    <span>Titulo:</span>
                    <input type="text" name="titulo">
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Sinopsis:</span>
                    <input type="text" name="sinopsis">
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Descripcion:</span>
                    <textarea name="descripcion"></textarea>
                </label>
            </div>

            <input type="file" name="archivo" id="file">

            <div class="form-row">
                <button type="submit" onClick="NoticiaAgregada()">Agregar Noticia</button>
            </div>

        </form>

    </div>

</body>

</html>
