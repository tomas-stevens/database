//---------------------------------------------------------------------
//Developers Names: Tomas Stevens, Divya Vardhan Singh
//Purpose: Developing homework 1's database and problems
//Github:tomas-stevens, (divyas)
//Resources:??
//Time spent on project:Tomas: 35 mins, Divya: 5 mins
//---------------------------------------------------------------------
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class Main {

    //global VAR's
    static String FILENAME = "input.txt";
    static String NL = System.lineSeparator();
    static FileInputStream instream = null;
    static FileOutputStream outstream = null;




        public static void CopyFile(String Path_1, String Path_2)
        {


        	try{
        	    File infile =new File(Path_1);
        	    File outfile =new File(Path_2);

        	    instream = new FileInputStream(infile);
        	    outstream = new FileOutputStream(outfile);

        	    byte[] buffer = new byte[1024];

        	    int length;
        	    /*copying the contents from input stream to
        	     * output stream using read and write methods
        	     */
        	    while ((length = instream.read(buffer)) > 0){
        	    	outstream.write(buffer, 0, length);
        	    }

        	    //Closing the input/output file streams
        	    instream.close();
        	    outstream.close();

        	    System.out.println("File copied successfully!!");

        	}catch(IOException ioe){
        		ioe.printStackTrace();
        	 }
        }



    // add binary search here for record finding.
//    we pick the key
    /*Get record number n-th (from 1 to 4360) */


//    //public static String getRecord(RandomAccessFile Din, int recordNum) throws IOException
//    public static String getRecord(RandomAccessFile Din, int recordNum) throws IOException
//    {
//        String record = "NOT_FOUND";
//        if ((recordNum >=1) && (recordNum <= NUM_RECORDS))
//        {
//            Din.seek(0); // return to the top fo the file
//            Din.skipBytes(recordNum * RECORD_SIZE);
//            record = Din.readLine();
//        }
//        return record;
//    }
//
//    /*Binary Search record id */
//    public static String binarySearch(RandomAccessFile Din, String id) throws IOException
//    {
//        int Low = 0;
//        int High = NUM_RECORDS-1;
//        int Middle;
//        String MiddleId;
//        String record = "NOT_FOUND";
//        boolean Found = false;
//
//        while (!Found && (High >= Low))
//        {
//            Middle = (Low + High) / 2;
//            record = getRecord(Din, Middle+1);
//            MiddleId = record.substring(0,5);
//
//            int result = MiddleId.compareTo(id);
//            if (result == 0)   // ids match
//                Found = true;
//            else if (result < 0)
//                Low = Middle + 1;
//            else
//                High = Middle - 1;
//        }
//        if (Found)
//            return record;
//        else
//            return "NOT_FOUND";
//    }
//}













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

            menu();
            break;
        case 2:
            //open database

            //make sure no database is open already!


            System.out.println("Please enter the name of the file you with to open!");
            FILENAME = inp.next();
            RandomAccessFile Din = new RandomAccessFile(FILENAME + ".config", "r");
            RandomAccessFile Din1 = new RandomAccessFile(FILENAME + ".data", "r");
            RandomAccessFile Din2 = new RandomAccessFile(FILENAME + ".overflow", "r");

            menu();
            break;
        case 3:

            System.out.println("closing current files/databases");  //untested but done
            //Din.close();  <-- closses the current files/databases
            //Din1.close();
            //Din2.close();

            menu();
            break;
        case 4:

            //easy
            //display record
            menu();
            break;
        case 5:
            //easy-med
            //update record
            menu();
            break;
        case 6:
            System.out.println("please enter the name of the database file you wish to create a report with!");
            FILENAME = inp.next() + ".data";



            // form which should display the first ten records nicely formatted, in sorted order by primary key.


            CopyFile(FILENAME, "C:\\Users\\tomas\\Documents\\GitHub\\database\\database hw 1\\report.txt"); //<-- works


            // form which should display the first ten records nicely formatted, in sorted order by primary key.

            menu();
            break;
        case 7:

            //med-hard
            //add a record
            menu();
            break;
        case 8:

            //easy-med
            //delete a record
            menu();
            break;
        case 9:
            System.out.println("Thank you for using our database, HW #1 program!"   //done
                    +NL +"Goodbye my friend (^.^)/");
            break;
        default:
            System.out.println("Please select the right numbers! <(^.^<) Try again!");   //done
            Switch_select();
            break;
    }
}










    public static void main(String[] args) throws IOException
    {
        menu();

    }
}
