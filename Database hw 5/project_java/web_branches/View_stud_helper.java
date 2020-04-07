import java.sql.*;

/*
jdbc_insert_restaurant.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class View_stud_helper 
{
   // The main program that inserts a restaurant
   public static void main(String[] args) throws SQLException 
   {
      String Username = "ts025";              // Change to your own username
      String mysqlPassword = "";    // Change to your own mysql Password

      // Connect to the database
      jdbc_db myDB = new jdbc_db();
      myDB.connect(Username, mysqlPassword);
      myDB.initDatabase();				    

      // For debugging purposes:  Show the database before the insert
      StringBuilder builder = new StringBuilder();
      String query1 = "SELECT * from Student";
      builder.append("<br> Table Student:" + myDB.query(query1) + "<br>");       
      System.out.println(builder.toString()); 
      myDB.disConnect();
   }
}
