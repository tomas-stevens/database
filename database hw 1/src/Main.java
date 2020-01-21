//---------------------------------------------------------------------
//Developers Names: Tomas Stevens, Divya Vardhan Singh
//Purpose: Developing homework 1's database and problems
//Github:
//Resources:??
//Time spent on project:15 mins,
//---------------------------------------------------------------------
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.Scanner;


public class Main {

    //global VAR's
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
            System.out.println("Please enter the name of the CSV file you with to open, without the .CSV!");
            FILENAME = inp.next() + ".csv";


            //https://howtodoinjava.com/java/io/how-to-create-a-new-file-in-java/

            //pre-sorted data
            //six fields of data only

            //.config:  contains the number of records in the data file, describes the names, sizes of the fields in order,
            // anything else you want. The first field is assumed to be the key

            //.data:   contains the data records, one per line, with fixed size fields. You may
            // use any separator you want (or no separator). There should be no blank records

            //.overflow: empty
           // <-- start implementing how to upload and split a CSV file.




            // most of the time / work here


            break;
        case 2:
            //open database

            //make sure no database is open already!


            System.out.println("Please enter the name of the file you with to open!");
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

            //easy
            //display record
            break;
        case 5:
            //easy-med
            //update record
            break;
        case 6:

            //easy-med
            //create report
            break;
        case 7:

            //med-hard
            //add a report
            break;
        case 8:

            //easy-med
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
