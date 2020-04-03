<html>
<body>
<h3>Enter information about a restaurant to add to the database:</h3>

<form action="jdbc_insert_restaurant.php" method="post">
    Name: <input type="text" name="name"><br>
    Type: <input type="text" name="type"><br>
    City: <input type="text" name="city"><br>
    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $name = escapeshellarg($_POST[name]);
    $type = escapeshellarg($_POST[type]);
    $city = escapeshellarg($_POST[city]);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_restaurant ' . $name . ' ' . $type . ' ' . $city;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    echo "<p>command: $command <p>";
 
    // run jdbc_insert_restaurant.exe
    system($command);           
}
?>


