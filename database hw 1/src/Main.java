//---------------------------------------------------------------------
//Developers Names: Tomas Stevens, Divya Vardhan Singh
//Purpose: Developing homework 1's database and problems
//Github:tomas-stevens, (divyas)
//Resources:??
//Time spent on project: Tomas: 11 hours
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
    static String FILENAME = "Fortune_500_HQ";
    static String NL = System.lineSeparator();
    static int RECORD_SIZE = 112;
    static int NUM_RECORDS = 499;

    private static RandomAccessFile Din1;
    private static RandomAccessFile Din2;
    private static RandomAccessFile Din3;

    private static int name = -40, rank = -5, state = -5, city = -20, zip = -20, emplyoees = -20;


    public static String getRecord(RandomAccessFile Din, int recordNum) throws IOException
    {
        String record = "NOT_FOUND";
        if ((recordNum >=1) && (recordNum <= NUM_RECORDS))
        {
            Din.seek(0);
            Din.skipBytes(recordNum * RECORD_SIZE);
            record = Din.readLine();
            System.out.println(record.substring(5,45));
        }
        return record;
    }


    /*Binary Search record id */
    public static String binarySearch(RandomAccessFile Din, String id) throws IOException
    {
        int Low = 0;
        int High = NUM_RECORDS-1;
        int Middle;
        String MiddleId;
        String record = "NOT_FOUND";
        boolean Found = false;

        while (!Found && (High >= Low))
        {
            Middle = (Low + High) / 2;
            record = getRecord(Din, Middle+1);
            MiddleId = record.substring(5,45);                                         //  Fortune_500_HQ
            MiddleId.trim();
            int result = MiddleId.compareTo(id);
            if (result == 0)   // ids match
                Found = true;
            else if (result < 0)
                Low = Middle + 1;
            else
                High = Middle - 1;
        }
        if (Found)
            return record;
        else
            return "NOT_FOUND";
    }

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

    public static void Create_config(String FILENAME, String Path_2) throws IOException {

           File f1=new File(FILENAME); //Creation of File Descriptor for input file
           File f2 =new File(Path_2);
           FileReader fr=new FileReader(f1);  //Creation of File Reader object
           FileWriter fw = new FileWriter(f2);
           BufferedReader br = new BufferedReader(fr);    //Creation of File Reader object
           BufferedWriter bw = new BufferedWriter(fw);

           int linecount=0;            //Initializing linecount as zero
           String s;

           while((s=br.readLine())!=null)    //Reading Content from the file line by line
           {
               linecount++;               //For each line increment linecount by one
           }
           NUM_RECORDS = linecount;
           bw.write(NUM_RECORDS);
           bw.newLine();
           bw.write("number of records");
           bw.newLine();

           bw.write("Naming convention: rank " +rank+ ", name " +name+ ", city " +city+ ", State " +state+ ", zip "+zip+ ", employees "+ emplyoees );


           bw.close();
           fr.close();

           System.out.println(".Config created successfully");

       }

    public static void Create_Data(String Path_1, String Path_2){
           try {
               BufferedWriter writer = new BufferedWriter(new FileWriter(Path_2));
               BufferedReader reader = new BufferedReader(new FileReader(Path_1));
               reader.readLine(); // skips first line
               for (int i = 0; i < 500; i++){

                   String line = reader.readLine();
                   String[] temp = line.split(",");
                   writer.write(String.format("%1$"+rank+"s",temp[0]));
                   writer.write(String.format("%1$"+name+"s",temp[1]));
                   writer.write(String.format("%1$"+city+"s",temp[2]));
                   writer.write(String.format("%1$"+state+"s",temp[3]));
                   writer.write(String.format("%1$"+zip+"s",temp[4]));
                   writer.write(String.format("%1$"+emplyoees+"s",temp[5]));
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
               System.out.println(".overflow created successfully");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    public static void Create_database(String FILENAME){

        try {
            Create_Data(FILENAME+".csv", FILENAME + ".data");
            Create_Overflow(FILENAME);
            Create_config(FILENAME + ".data", FILENAME + ".config");


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

    public static void Switch_select() throws IOException {

    Scanner inp = new Scanner(System.in);
    int query = inp.nextInt();
    switch(query){
        case 1:
            //create database
            System.out.println("Please enter the name of the CSV file you with to open, without the .CSV!");
            FILENAME = inp.next();

            Create_database(FILENAME);  // <------ finally finished

            menu();
            break;
        case 2:

            //open database

            //make sure no database is open already!


            System.out.println("Please enter the name of the file you with to open!");
            FILENAME = inp.next();
            Din1 = new RandomAccessFile(FILENAME + ".config", "rw");
            Din2 = new RandomAccessFile(FILENAME + ".data", "rw");
            Din3 = new RandomAccessFile(FILENAME + ".overflow", "rw");

            menu();
            break;
        case 3:

            System.out.println("closing current files/databases");  //untested but done
            Din1.close();  //<-- closses the current files/databases
            Din2.close();
            Din3.close();

            menu();
            break;
        case 4:
            System.out.println("Please enter the name of the company in the database you wish to find!");
            String temp_find_name = inp.next();
            String Record = binarySearch(Din2, temp_find_name);
            System.out.println("getRecord(n): \n" + Record + "\n\n");

            menu();
            break;
        case 5:
            //easy-med
            //https://stackoverflow.com/questions/4614227/how-to-add-a-new-line-of-text-to-an-existing-file-in-java
            //update record
            menu();
            break;
        case 6:
            CopyFile(FILENAME + ".data", "report.txt");                                        //<-- works
            menu();
            break;

        case 7:

            Record = getRecord(Din2,5);
            System.out.println("getRecord(N): \n" + Record + "\n\n");

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
