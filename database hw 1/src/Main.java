//---------------------------------------------------------------------
//Developers Names: Tomas Stevens, Divya Vardhan Singh
//Purpose: Developing homework 1's database and problems
//Github:tomas-stevens, (divyas)
//Resources:??
//Time spent on project: Tomas: 2 hours
//---------------------------------------------------------------------
import java.io.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;
import java.io.FileReader;

public class Main {

    //global VAR's
    static String FILENAME = "input.txt";
    static String NL = System.lineSeparator();
    static FileInputStream instream = null;
    static FileOutputStream outstream = null;
    static int RECORD_SIZE = 71;
    static int NUM_RECORDS = 1;



//need to make it so it only copys first 10 strings
        public static void CopyFile(String Path_1, String Path_2) {

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(Path_2));
                BufferedReader reader = new BufferedReader(new FileReader(Path_1));

                for (int i = 0; i < 10; i++){
                    writer.write(reader.readLine());
                    writer.newLine();

                }
                writer.close();
                System.out.println("File copied successfully!!");
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }



       public static void Create_config(String FILENAME) throws IOException {

           File f1=new File(FILENAME); //Creation of File Descriptor for input file
           int linecount=0;            //Intializing linecount as zero
           FileReader fr=new FileReader(f1);  //Creation of File Reader object
           BufferedReader br = new BufferedReader(fr);    //Creation of File Reader object
           String s;
           br.readLine(); //skips first line
           while((s=br.readLine())!=null)    //Reading Content from the file line by line
           {
               linecount++;               //For each line increment linecount by one
           }
           fr.close();
           NUM_RECORDS = linecount;
       }

       public static void Create_Data(String Path_1, String Path_2){
           try {
               BufferedWriter writer = new BufferedWriter(new FileWriter(Path_2));
               BufferedReader reader = new BufferedReader(new FileReader(Path_1));
               reader.readLine();
               for (int i = 0; i < 500; i++){
                   writer.write(reader.readLine());
                   writer.newLine();

               }
               writer.close();
               System.out.println(".data created successfully!!");
           } catch (IOException ioe) {
               ioe.printStackTrace();
           }
       }

       public static void Create_Overflow(String FILENAME){
           try {
               File file = new File(FILENAME + ".overflow");
               file.createNewFile();
               System.out.println("overflow created successfully");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }


public static void Create_database(String FILENAME){

    try {
        Create_config(FILENAME + ".csv");
        Create_Data(FILENAME+".csv", FILENAME + ".data");
        Create_Overflow(FILENAME);


    } catch (IOException e) {
        e.printStackTrace();
    }

}

public static void menu() throws  IOException{
    System.out.println("Hello, and welcome to database homework 1. I will be your guide!" + NL+
            "below are 9 options you have a choice between. Please select one of these for the next step!" +NL+
            "1: Create new database." +NL+
            "2: Open database." +NL+
            "3: Close database." +NL+
            "4: Display Record." +NL+
            "5: Update Record." +NL+
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
            FILENAME = inp.next();

            Create_database(FILENAME);


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
            System.out.println("Please enter the name of the file you with to open!");
            FILENAME = inp.next();
            System.out.println("Please enter what you wish to find!");
            String temp = inp.next();
           // String Record = binarySearch(FILENAME,temp);

       //     System.out.println("getRecord(n): \n" + Record + "\n\n");

            menu();
            break;
        case 5:
            //easy-med
            //https://stackoverflow.com/questions/4614227/how-to-add-a-new-line-of-text-to-an-existing-file-in-java
            //update record
            menu();
            break;
        case 6:
            System.out.println("please enter the name of the database file you wish to create a report with!");
            FILENAME = inp.next();// + ".data";
            CopyFile(FILENAME, "report.txt"); //<-- works
            menu();
            break;
        case 7:
//            System.out.println("Please enter the name of the file you with to open!");
//            FILENAME = inp.next();
//            String Record = getRecord(FILENAME,1, 0);
//            System.out.println("getRecord(7): \n" + Record + "\n\n");

            //https://stackoverflow.com/questions/4614227/how-to-add-a-new-line-of-text-to-an-existing-file-in-java
            //med-hard
            //add a record
            menu();
            break;
        case 8:
            //bin search
            //delete record

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
