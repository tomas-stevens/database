

<html>
<head>
<link rel="stylesheet" href="../utilities/styles.css">

</head>


<body>
<div id="navbar">
  <a href="../Home.php">Home</a>
  <a href="add_stud.php">Enter new student</a>
  <a href="new_cour.php">Enter a new course</a>
  <a href="add_enroll.php">add an application to enrollment </a>
  <a href="View_stud.php">view all students</a>
  <a href="View_cour.php">view all courses from a specific department</a>
  <a href="View_stud_cour.php">View all courses for a given student</a>
</div> 
<h1>(\/) (;,,,;) (\/) zoidberg will be your guide!! </h1>
<h3>Enter information about a student to enter it into the database:</h3>

<form action="add_stud.php" method="post">
    ID: <input type="number" name="ID"><br>
    student name: <input type="text" name="Stud_name"><br>
    Major: <input type="text" name="Major"><br>
    <input name="submit" type="submit" >
</form>
<br><br>

</body>
</html>

<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $name = escapeshellarg($_POST[ID]);
    $type = escapeshellarg($_POST[Stud_name]);
    $city = escapeshellarg($_POST[Major]);
    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar DBweb ' . $name . ' ' . $type . ' ' . $city;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    echo "<p>command: $command <p>";
 
    // run jdbc_insert_restaurant.exe
    system($command);           
}
?>






