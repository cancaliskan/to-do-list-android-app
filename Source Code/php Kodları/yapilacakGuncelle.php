<?php
    $con = mysqli_connect("localhost:3306", "cancaliskan", "Ee6!r3m7", "cancaliskan_toDoList");

    $ID = $_POST["ID"];
    $baslik = $_POST["baslik"];
    $metin = $_POST["metin"];
    $hatirlat = $_POST["hatirlat"];
    $tarih = $_POST["tarih"];
    $saat = $_POST["saat"];
    
    $response = array();
    $response["success"] = false; 

    $guncelle = mysqli_prepare($con,"UPDATE yapilacaklar SET baslik=?,metin=?,hatirlat=?,tarih=?,saat=? WHERE ID=?");
    mysqli_stmt_bind_param($guncelle, "ssssss",$baslik,$metin,$hatirlat,$tarih,$saat ,$ID);
	mysqli_stmt_execute($guncelle);
    $response["success"] = true; 

    
/*    $statement = mysqli_prepare($con, "SELECT * FROM yapilacaklar WHERE ID = ?");
    mysqli_stmt_bind_param($statement, "s", $ID);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $colID, $colEposta, $colBaslik, $colMetin,$colHatirlat, $colTarih, $colSaat);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){*/
      
   // }
    echo json_encode($response);
?>