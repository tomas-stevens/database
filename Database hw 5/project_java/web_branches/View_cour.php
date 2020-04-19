

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
<br><br>
<form action="View_cour.php" method="post">
Please enter the DeptCode you wish to look up and find courses on!: <input type="number" name="DeptCode"><br><br>
    <input name="submit" type="submit" >
</form>
</body>
</html>

<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $D = escapeshellarg($_POST[DeptCode]);
    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar view_cour ' . $D;

    // remove dangerous characters from command to protect web server
    $command = escapeshellcmd($command);
    echo "<p>command: $command <p>";
 
    system($command);           
}
?>
