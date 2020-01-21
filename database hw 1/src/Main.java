//---------------------------------------------------------------------
//Developers Names: Tomas Stevens, Divya Vardhan Singh
//Purpose: Developing homework 1's database and problems
//Github:
//Resources:??
//---------------------------------------------------------------------
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.Scanner;


public class Main {

    //global VAR
    static String FILENAME = "input.txt";
    static String NL = System.lineSeparator();

    // add binary search here for record finding.
    //we pick the key
















public static void menu() throws  IOException
{
    System.out.println("Hello, and welcome to database homework 1. I will be your guide!" + NL+
            "below are 9 options you have a choice between. Please select one of these for the next step!" +NL+
            "1: Create new database." +NL+
            "2: Open database." +NL+
            "3: Close database." +NL+
            "4: Display database." +NL+
            "5: Update database." +NL+
            "6: Create Report." +NL+
            "7: Add a record." +NL+
            "8: Delete a record." +NL+
            "9: Exit the program." +NL);
    Switch_select();
}

public static void Switch_select() throws IOException
{

    Scanner inp = new Scanner(System.in);
    int query = inp.nextInt();
    switch(query){
        case 1:
            //create database

            break;
        case 2:
            //open database

            //make sure no database is open already!


            System.out.println("Please enter the name of the CSV file you with to open!");
            FILENAME = inp.next();
            RandomAccessFile Din = new RandomAccessFile(FILENAME + ".config", "r");
            RandomAccessFile Din1 = new RandomAccessFile(FILENAME + ".data", "r");
            RandomAccessFile Din2 = new RandomAccessFile(FILENAME + ".overflow", "r");
            break;
        case 3:
            System.out.println("closing current files/databases");
            //Din.close();  <-- closses the current files/databases
            //Din1.close();
            //Din2.close();

            break;
        case 4:


            //display record
            break;
        case 5:

            //update record
            break;
        case 6:


            //create report
            break;
        case 7:


            //add a report
            break;
        case 8:


            //delete a record
            break;
        case 9:
            System.out.println("Thank you for using our database, HW #1 program!"
                    +NL +"Goodbye my friend (^.^)/");


            break;
        default:
            System.out.println("Please select the right numbers! <(^.^<) Try again!");
            Switch_select();
            break;
    }
}










    public static void main(String[] args) throws IOException
    {
        menu();

    }
}
