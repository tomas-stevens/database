import java.sql.*;

/*
jdbc_insert_restaurant.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class new_cour 
{
   // The main program that inserts a restaurant
   public static void main(String[] args) throws SQLException 
   {
      String Username = "ts025";              // Change to your own username
      String mysqlPassword = "Lordbubba123";    // Change to your own mysql Password

      // Connect to the database
      jdbc_db myDB = new jdbc_db();
      myDB.connect(Username, mysqlPassword);
      myDB.initDatabase();				    

      // For debugging purposes:  Show the database before the insert
      StringBuilder builder = new StringBuilder();
      String query1 = "SELECT * from Course";
      builder.append("<br> Table course before:" + myDB.query(query1) + "<br>");       

      // Parse input string to get restauranrestaurant Name and Address
      String deptCode = "0";
      String cour_num = "TYPE";
      String title = "CITY";
      String credit = "credit";

      // Read command line arguments
      // args[0] is the first parameter
      deptCode = args[0];
      cour_num = args[1];
      title = args[2];
      credit = args[3];
      


      // Insert the new restaurant
      String input = deptCode + ", " + cour_num + ", '" + title + "', '" + credit + "'";             
      myDB.insert("Course", input);    // insert new restaurant

      // For debugging purposes:  Show the database after the insert
      builder.append("<br><br><br> Table Course after:" + myDB.query(query1));
      System.out.println(builder.toString());     

      myDB.disConnect();
   }
}
