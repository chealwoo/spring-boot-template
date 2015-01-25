<?php

 if (!empty($_POST))
 {
	$data = $_POST['data'];

	echo $data . "</br>";

	$ajaxlog = json_decode($data);
	$logstr = "[" . $ajaxlog[0]->level . "]" . $ajaxlog[0]->timestamp . ", " . $ajaxlog[0]->message;

	echo $logstr . "</br>";

/*
logger
timestamp
level
url
message

[{"logger":"[anonymous]","timestamp":1387473611926,"level":"ERROR","url":"http://www.webui.me/tools/log4javascript/demos/ajax.html","message":"shot and drop slotting a by"}]
*/

	$myFile = "testFile.txt";
    $fh = fopen($myFile, 'a') or die("can't open file");
//    fwrite($fh, var_export($ajaxlog,true));
//    fwrite($fh, $ajaxlog . "\r\n");
    fwrite($fh, $logstr . "\r\n");
    fclose($fh);
}
?>