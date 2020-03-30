//---------------------------------------------------------------------
//Developers Names: Tomas Stevens, Divya Vardhan Singh
//Purpose: Developing homework 4's database and problems
//Github:tomas-stevens, (divyas)
//Time spent on project: Tomas: N/A, Divya: N/A hours
//---------------------------------------------------------------------

import java.io.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.FileReader;



import java.sql.*;

public class jdbc_example {

    // The instance variables for the class
    private Connection connection;
    private Statement statement;
    static String NL = System.lineSeparator();
    static int agent_val = 210;

    // The constructor for the class
    public jdbc_example() {
        connection = null;
        statement = null;
    }



    // Connect to the database
    public void connect(String Username, String mysqlPassword) throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Username + "?" +
                    "user=" + Username + "&password=" + mysqlPassword);
            //connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Username +
             //       "?user=" + Username + "&password=" + mysqlPassword);
        }
        catch (Exception e) {
            throw e;
        }
    }

    // Disconnect from the database
    public void disConnect() throws SQLException {
        connection.close();
        statement.close();
    }

    // Execute an SQL query passed in as a String parameter
    // and print the resulting relation
    public void query(String q) {
        try {
            ResultSet resultSet = statement.executeQuery(q);
            System.out.println("\n---------------------------------");
            System.out.println("Query: \n" + q + "\n\nResult: ");
            print(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Print the results of a query with attribute names on the first line
    // Followed by the tuples, one per line
    public void print(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numColumns = metaData.getColumnCount();

        printHeader(metaData, numColumns);
        printRecords(resultSet, numColumns);
    }

    // Print the attribute names
    public void printHeader(ResultSetMetaData metaData, int numColumns) throws SQLException {
        for (int i = 1; i <= numColumns; i++) {
            if (i > 1)
                System.out.print(",  ");
            System.out.print(metaData.getColumnName(i));
        }
        System.out.println();
    }

    // Print the attribute values for all tuples in the result
    public void printRecords(ResultSet resultSet, int numColumns) throws SQLException {
        String columnValue;
        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                if (i > 1)
                    System.out.print(",  ");
                columnValue = resultSet.getString(i);
                System.out.print(columnValue);
            }
            System.out.println("");
        }
    }

    // Insert into any table, any values from data passed in as String parameters
    public void insert(String table, String values) {
        String query = "INSERT into " + table + " values (" + values + ")" ;
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Add_agent_city(){
        String temp[] = new String[4];
        Scanner inp = new Scanner(System.in);

        //gathering information
        System.out.println(" you have chosen to add an agent to a city " + 
            "please input the required details"+ NL +
            "what is the agents name?");
        temp[1] = inp.next();
        System.out.println("What is the city he is in?");
        temp[2] = inp.next();
        System.out.println("What is the zipcode of this city");
        temp[3] = inp.next();
        System.out.println("are you sure this is what you would like to add? (y,n)");
        System.out.println("'" + temp[1] + "', '" + temp[2] + "', " + temp[3] + "?");
        
        //confirm
        if(inp.next().equals("y") || inp.next().equals("Y")){
            String a = agent_val + ", " + "'" + temp[1] + "', '" + temp[2] + "', " + temp[3];
            insert("AGENTS", a);
        }
        else
            System.out.println("returning to menu");
            String query1 = "SELECT * from AGENTS";
            query(query1);



        }





    public  void menu() throws IOException{
        System.out.println("Hello, and welcome to database homework 1. I will be your guide!" + NL+
        "below are 9 options you have a choice between. Please select one of these for the next step!" +NL+
        "1: Find all agents and clients in a given city." +NL+
        "2: Add a new client, then purchase an available policy from a particular agent." +NL+
        "3: List all policies sold by a particular agent." +NL+
        "4: Cancel a policy." +NL+
        "5: Add a new agent to a city." +NL+  
        "6: Exit the program." +NL);

        Switch_select();
    }

    public  void Switch_select() throws IOException {
        Scanner inp = new Scanner(System.in);
        int query = inp.nextInt();
        switch(query){
            case 1:

                System.out.println("hello");
                menu();
                break;

            case 2:


                menu();
                break;

            case 3: 


                menu();
                break;

            case 4:


                menu();
                break;

            case 5: 
                Add_agent_city();

                menu();
                break;

            case 6:
                System.out.println("Thank you for using our database, HW #4 program!"   //done
                +NL +"Goodbye my friend (^.^)/");
                break;

            default:
                System.out.println("Please select the right numbers! <(^.^<) Try again!");   //done
                Switch_select();
                break;
        }
    }





    // The main program", that tests the methods
    public static void main(String[] args) throws SQLException, IOException{
        String Username = "ts025";              // Change to your own username
        String mysqlPassword = "";    // Change to your own mysql Password

      

        jdbc_example test = new jdbc_example();
        test.connect(Username, mysqlPassword);
        test.initDatabase(Username, mysqlPassword, Username);
        String query1 = "SELECT * from AGENTS";

        test.menu();
      
        test.query(query1);
        test.disConnect();
    }



    // Remove all records and fill them with values for testing
    // Assumes that the tables are already created
    public void initDatabase(String Username, String Password, String SchemaName) throws SQLException {
        statement = connection.createStatement();
        System.out.println("begun initilizing new database");
        statement.executeUpdate("DELETE from POLICIES_SOLD");
        statement.executeUpdate("DELETE from POLICY");
        statement.executeUpdate("DELETE from CLIENTS");
        statement.executeUpdate("DELETE from AGENTS");
        

        insert("CLIENTS", "101, 'CHRIS', 'DALLAS', 43214");
        insert("CLIENTS", "102, 'OLIVIA', 'BOSTON', 83125");
        insert("CLIENTS", "103, 'ETHAN', 'FAYETTEVILLE', 72701");
        insert("CLIENTS", "104, 'DANIEL', 'NEWYORK', 53421");
        insert("CLIENTS", "105, 'TAYLOR', 'ROGERS', 78291");
        insert("CLIENTS", "106, 'CLAIRE', 'PHOENIX', 85011");


        insert("AGENTS", "201, 'ANDREW', 'DALLAS', 43214");
        insert("AGENTS", "202, 'PHILIP', 'PHOENIX', 85011");
        insert("AGENTS", "203, 'JERRY', 'BOSTON', 83125");
        insert("AGENTS", "204, 'BRYAN', 'ROGERS', 78291");
        insert("AGENTS", "205, 'TOMMY', 'DALLAS', 43214");
        insert("AGENTS", "206, 'BRANT', 'FAYETTEVILLE', 72701");
        insert("AGENTS", "207, 'SMITH', 'ROGERS', 78291");
        
        insert("POLICY", "301,'CIGNAHEALTH', 'DENTAL', 5");
        insert("POLICY", "302, 'GOLD', 'LIFE', 8");
        insert("POLICY", "303, 'WELLCARE', 'HOME', 10");
        insert("POLICY", "304, 'UNITEDHEALTH', 'HEALTH', 7");
        insert("POLICY", "305, 'UNITEDCAR', 'VEHICLE', 9");

        insert("POLICIES_SOLD", "401,204,106,303,'2020-01-02', 2000.00");
        insert("POLICIES_SOLD", "402,201,105,305,'2019-08-11',1500.00");
        insert("POLICIES_SOLD", "403,203,106,301,'2019-09-08',3000.00");
        insert("POLICIES_SOLD", "404,207,101,305,'2019-06-21',1500.00");
        insert("POLICIES_SOLD", "405,203,104,302,'2019-11-14',4500.00");
        insert("POLICIES_SOLD", "406,207,105,305,'2019-12-25', 1500.00");
        insert("POLICIES_SOLD", "407,205,103,304,'2020-10-15',5000.00");
        insert("POLICIES_SOLD", "408,204,103,304,'2020-02-15',5000.00");
        insert("POLICIES_SOLD", "409,203,103,304,'2020-01-10',5000.00");
        insert("POLICIES_SOLD", "410,202,103,303,'2020-01-30',2000.00");

        System.out.println("finished initilizing new database");

    }
}
