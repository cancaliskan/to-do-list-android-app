<?php
    $con = mysqli_connect("localhost:3306", "cancaliskan", "Ee6!r3m7", "cancaliskan_toDoList");
    
    $eski_ePosta = $_POST["eski_ePosta"];
    $isim = $_POST["isim"];
    $eposta = $_POST["eposta"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM kullanicilar WHERE eposta = ?");
    mysqli_stmt_bind_param($statement, "s", $eski_ePosta);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colID, $colIsim, $colEposta, $colParola);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if ($eski_ePosta == $colEposta) {
			$guncelle = mysqli_prepare($con,"UPDATE kullanicilar SET isim=?, eposta=? WHERE eposta=?");
			mysqli_stmt_bind_param($guncelle, "sss", $isim,$eposta,$eski_ePosta);
			mysqli_stmt_execute($guncelle);
            $response["success"] = true;  
        }
    }
    echo json_encode($response);
?>