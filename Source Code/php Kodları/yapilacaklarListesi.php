<?php
$dsn = 'mysql:host=localhost;dbname=cancaliskan_toDoList;port=3306';
$usr = 'cancaliskan';
$pwd = 'Ee6!r3m7';
$db = new PDO($dsn, $usr, $pwd);
$db->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);

$eposta = $_POST["eposta"];

$query = $db->prepare('SELECT * FROM yapilacaklar WHERE eposta = :eposta');
$query->execute(array( 'eposta' => $eposta ));
$response["yapilacaklarListesi"] = $query->fetchAll();

echo json_encode($response);