<?php
	require("password.php");

    $connect = mysqli_connect("localhost:3306", "cancaliskan", "Ee6!r3m7", "cancaliskan_toDoList");
	
	$isim = $_POST["isim"];
    $eposta = $_POST["eposta"];
    $parola = $_POST["parola"];

 	function registerUser() {
        global $connect, $isim, $eposta, $parola;
        $passwordHash = password_hash($parola, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO kullanicilar (isim, eposta, parola) VALUES (?, ?, ?)");
     	mysqli_stmt_bind_param($statement, "sss", $isim, $eposta, $passwordHash);
    	mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);     
    }

	function usernameAvailable() {
        global $connect, $eposta;
        $statement = mysqli_prepare($connect, "SELECT * FROM kullanicilar WHERE eposta = ?"); 
        mysqli_stmt_bind_param($statement, "s", $eposta);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement); 
        if ($count < 1){
            return true; 
        }else {
            return false; 
        }
    }

    
    $response = array();
    $response["success"] = false;

	if (usernameAvailable()){
			registerUser();
			$response["success"] = true;  
		}

	echo json_encode($response);
?>