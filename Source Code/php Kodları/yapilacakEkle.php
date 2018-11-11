<?php
    $con = mysqli_connect("localhost:3306", "cancaliskan", "Ee6!r3m7", "cancaliskan_toDoList");
    
    $eposta = $_POST["eposta"];
    $baslik = $_POST["baslik"];
    $metin = $_POST["metin"];
    $hatirlat = $_POST["hatirlat"];
    $tarih = $_POST["tarih"];
    $saat = $_POST["saat"];


    $statement = mysqli_prepare($con, "INSERT INTO yapilacaklar (eposta, baslik, metin,hatirlat,tarih,saat) VALUES (?, ?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssssss", $eposta,$baslik,$metin,$hatirlat,$tarih,$saat);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    //mysqli_stmt_bind_result($statement, $colID, $colIsim, $colEposta, $colParola);
    $response["success"] = true;  

    echo json_encode($response);
?>