import java.sql.*;

/*
jdbc_insert_restaurant.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class DBweb 
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
      builder.append("<br> Table Student before:" + myDB.query(query1) + "<br>");       

      // Parse input string to get restauranrestaurant Name and Address
      String restaurant = "4";
      String ID = "0";
      String stud_name = "TYPE";
      String Major = "CITY";

      // Read command line arguments
      // args[0] is the first parameter
      ID = args[0];
      stud_name = args[1];
      Major = args[2];

      // Insert the new restaurant
      String input = ID + ",'" + stud_name + "','" + Major + "'";             
      myDB.insert("Student", input);    // insert new restaurant

      // For debugging purposes:  Show the database after the insert
      builder.append("<br><br><br> Table Restaurant after:" + myDB.query(query1));
      System.out.println(builder.toString());     

      myDB.disConnect();
   }
}
