<?php
    require("password.php");
    $con = mysqli_connect("localhost:3306", "cancaliskan", "Ee6!r3m7", "cancaliskan_toDoList");
    
    $eposta = $_POST["eposta"];
    $parola = $_POST["parola"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM kullanicilar WHERE eposta = ?");
    mysqli_stmt_bind_param($statement, "s", $eposta);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colID, $colIsim, $colEposta, $colParola);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($parola, $colParola)) {
            $response["success"] = true;  
            $response["isim"] = $colIsim;
            $response["eposta"] = $colEposta;
            $response["parola"] = $parola;
        }
    }
    echo json_encode($response);
?>