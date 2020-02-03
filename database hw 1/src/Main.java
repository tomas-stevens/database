//---------------------------------------------------------------------
//Developers Names: Tomas Stevens, Divya Vardhan Singh
//Purpose: Developing homework 1's database and problems
//Github:tomas-stevens, (divyas)
//Resources:stack overflow, geekforgeek, other sites
//Time spent on project: Tomas: 12 hours, Divya, 10 hours
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
    static boolean Is_Open = false;

    private static RandomAccessFile Din1;
    private static RandomAccessFile Din2;
    private static RandomAccessFile Din3;

    private static int name = -40, rank = -5, state = -5, city = -20, zip = -20, emplyoees = -20;

    public static void Add_Record(RandomAccessFile Din2, RandomAccessFile Din3) throws IOException {
        // get info from user
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME + ".overflow", true));
        Scanner inp = new Scanner(System.in);

        String[] temp = new String[6];
        System.out.println("Please enter the Rank of the company");
        temp[0] = inp.next();
        System.out.println("Please enter the name of the company");
        temp[1] = inp.next();
        System.out.println("Please enter the City of the company");
        temp[2] = inp.next();
        System.out.println("Please enter the State of the company");
        temp[3] = inp.next();
        System.out.println("Please enter the ZIP of the company");
        temp[4] = inp.next();
        System.out.println("Please enter the number of the employees in the company");
        temp[5] = inp.next();
        //form string from user info
        writer.write(String.format("%1$"+rank+"s",temp[0]));
        writer.write(String.format("%1$"+name+"s",temp[1]));
        writer.write(String.format("%1$"+city+"s",temp[2]));
        writer.write(String.format("%1$"+state+"s",temp[3]));
        writer.write(String.format("%1$"+zip+"s",temp[4]));
        writer.write(String.format("%1$"+emplyoees+"s",temp[5]));
        writer.newLine();
        writer.close();

        Overflow_handler();
    }

    public static void Delete_record(RandomAccessFile Din2) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME + ".overflow", true));
        Scanner inp = new Scanner(System.in);
        System.out.println("Please enter the name of the company you wish to delete from the database");
        String Record = binarySearch(Din2, inp.next());
        if (!Record.equals("NOT_FOUND")) {
            System.out.println("Is this the record you wish to Delete? (y/n)" + NL + Record);
            if (inp.next().equals("y") || inp.next().equals("Y")) {
                String[] temp = new String[6];
                temp[0] = Record.substring(0,5).trim();
                temp[1] = Record.substring(5,45).trim();
                temp[2] = Record.substring(45,65).trim();
                temp[3] = Record.substring(65,70).trim();
                temp[4] = Record.substring(70,90).trim();
                temp[5] = Record.substring(90,110).trim();
                //delete
                writer.write(String.format("%1$"+rank+"s","-1"));
                writer.write(String.format("%1$"+name+"s",temp[1]));
                writer.write(String.format("%1$"+city+"s",temp[2]));
                writer.write(String.format("%1$"+state+"s",temp[3]));
                writer.write(String.format("%1$"+zip+"s",temp[4]));
                writer.write(String.format("%1$"+emplyoees+"s",temp[5]));
                writer.newLine();
                writer.close();
            }
            else {
                System.out.println("Returning to menu");
            }
        }
        else
        System.out.println("Record not found");

        Overflow_handler();
    }

    public static void Update_record(RandomAccessFile Din2, RandomAccessFile Din3) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME + ".overflow", true));
        Scanner inp = new Scanner(System.in);
        System.out.println("Please enter the name of the company you wish to update from the database");
        String Record = binarySearch(Din2, inp.next());


        if (!Record.equals("NOT_FOUND")) {
            System.out.println("Is this the record you wish to update?" + NL + Record);

            if (inp.next().equals("y") || inp.next().equals("Y")) {
                System.out.println("please enter the updated information");
                String[] temp = new String[6];
                System.out.println("Please enter the Rank of the company");
                temp[0] = inp.next();
                temp[1] = Record.substring(5,45).trim();
                System.out.println("Please enter the City of the company");
                temp[2] = inp.next();
                System.out.println("Please enter the State of the company");
                temp[3] = inp.next();
                System.out.println("Please enter the ZIP of the company");
                temp[4] = inp.next();
                System.out.println("Please enter the number of the employees in the company");
                temp[5] = inp.next();

                //update
                writer.write(String.format("%1$"+rank+"s",temp[0]));
                writer.write(String.format("%1$"+name+"s",temp[1]));
                writer.write(String.format("%1$"+city+"s",temp[2]));
                writer.write(String.format("%1$"+state+"s",temp[3]));
                writer.write(String.format("%1$"+zip+"s",temp[4]));
                writer.write(String.format("%1$"+emplyoees+"s",temp[5]));
                writer.newLine();
                writer.close();
            }
            else {
                System.out.println("Returning to menu");
            }
        }
        else
            System.out.println("Record not found");
        Overflow_handler();
    }

    public static void Overflow_handler() throws IOException {
        File F_temp = new File(FILENAME + ".temp");


        int linecount = 0;
        String[] Temp_overflow = new String[5];
        String s;
        BufferedReader overflow_reader = new BufferedReader(new FileReader(FILENAME + ".overflow"));
        while ((overflow_reader.readLine()) != null)    //Reading Content from the file line by line
        {
            linecount++;               //For each line increment linecount by one
        }


        if (linecount == 4) {
            for (int i = 0; i < linecount; i++) {
                Temp_overflow[i] = overflow_reader.readLine();  // reads in data from overflow
            }
            overflow_reader.close();


            System.out.println("overflow is full, merging now." + NL);

            for (int j = 0; j < linecount; j++) {
                //files to open and close each loop
               // BufferedWriter data_writer = new BufferedWriter(new FileWriter(FILENAME + ".data"));
                BufferedReader Data_reader = new BufferedReader(new FileReader(FILENAME + ".data"));
                BufferedWriter temp_writer = new BufferedWriter(new FileWriter(F_temp));
                BufferedReader temp_reader = new BufferedReader(new FileReader(F_temp));

                while ((s = Data_reader.readLine()) != null) {
                    s = s.substring(5, 45);
                    if (Temp_overflow[j].substring(5, 45).compareTo(s) == 0) {     // update record / delete record

                        if (Temp_overflow[j].substring(0, 2).equals("-1")) { //delete

                            //do nothing
                            while ((s = Data_reader.readLine()) != null) {
                                temp_writer.write(s);
                                temp_writer.newLine();
                            }
                        } else {                                                //update
                                temp_writer.write(s);
                                temp_writer.newLine();
                            while ((s = Data_reader.readLine()) != null) {
                                temp_writer.write(s);
                                temp_writer.newLine();
                            }

                        }
                    }
                    else if (Temp_overflow[j].substring(5, 45).compareTo(s) > 0) {  //add record


                            //write rest of the file down
                            while ((s = Data_reader.readLine()) != null) {
                                temp_writer.write(s);
                                temp_writer.newLine();
                            }


                        }
                    temp_writer.write(s);
                    temp_writer.newLine();
                    }
                    //write back to .data

                    //close for next loop
                    //data_writer.close();
                    Data_reader.close();
                    temp_reader.close();
                    temp_writer.close();

                }

                System.out.println("merge Successful");
                // update config file for new line count
                Create_config(FILENAME + ".data", FILENAME + ".config", FILENAME + ".overflow");

                //erases file.
                PrintWriter pw = new PrintWriter(FILENAME + ".overflow");
                pw.close();

            }
        }

    public static String getRecord(RandomAccessFile Din, int recordNum) throws IOException{
        String record = "NOT_FOUND";
        if ((recordNum >=1) && (recordNum <= NUM_RECORDS))
        {
            Din.seek(0);
            Din.skipBytes(recordNum * RECORD_SIZE);
            record = Din.readLine();
        }
        return record;
    }

    public static String binarySearch(RandomAccessFile Din, String id) throws IOException{
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
            MiddleId = MiddleId.trim();
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

    public static void Create_config(String FILENAME, String Path_2, String Overflow) throws IOException {

           File f1=new File(FILENAME); //Creation of File Descriptor for input file
           File f2 =new File(Path_2);
           File f3 = new File(Overflow);
           FileReader fr=new FileReader(f1);  //Creation of File Reader object
           FileReader fr1 = new FileReader(f3);
           FileWriter fw = new FileWriter(f2);
           BufferedReader br = new BufferedReader(fr);    //Creation of File Reader object
           BufferedReader br1 = new BufferedReader(fr1);
           BufferedWriter bw = new BufferedWriter(fw);

           int linecount=0;            //Initializing linecount as zero
           String s;

           while((s=br.readLine())!=null)    //Reading Content from the file line by line
           {
               linecount++;               //For each line increment linecount by one
           }
           while((s=br1.readLine())!=null)    //Reading Content from the file line by line
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
            Create_config(FILENAME + ".data", FILENAME + ".config", FILENAME + ".overflow");


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
            if(!Is_Open) {
                System.out.println("Please enter the name of the file you with to open!");
                FILENAME = inp.next();
                Din1 = new RandomAccessFile(FILENAME + ".config", "rw");
                Create_config(FILENAME + ".data", FILENAME + ".config", FILENAME + ".overflow"); // handles reading in record number.
                Din2 = new RandomAccessFile(FILENAME + ".data", "rw");
                Din3 = new RandomAccessFile(FILENAME + ".overflow", "rw");
                Is_Open = true;
            }
        else
            System.out.println("database is already open!");

        menu();

            break;
        case 3:
            if (Is_Open) {
                //try catch not open
                System.out.println("closing current files/databases");  //untested but done
                Create_config(FILENAME + ".data", FILENAME + ".config", FILENAME + ".overflow"); // updates config
                Din1.close();  //<-- closes the current files/databases
                Din2.close();
                Din3.close();
                Is_Open = false;
            }
            else
                System.out.println("No database to close");
            menu();
            break;
        case 4:
            if(Is_Open) {
                System.out.println("Please enter the name of the company in the database you wish to find!");
                String temp_find_name = inp.next();
                String Record = binarySearch(Din2, temp_find_name);
                System.out.println("getRecord(n): \n" + Record + "\n\n");
            }
            else
                System.out.println("please open a database");
            menu();
            break;
        case 5:

            Update_record(Din2,Din3);

            menu();
            break;
        case 6:
            if(Is_Open)
            CopyFile(FILENAME + ".data", "report.txt");                                        //<-- works
            else
                System.out.println("please open a database");
            menu();

        case 7:
            if(Is_Open) {
                Add_Record(Din2,Din3);
            }
            else
                System.out.println("please open a database");
            menu();

            break;
        case 8:
            Delete_record(Din2);
            menu();

            break;
        case 9:
            if(!Is_Open){
            System.out.println("Thank you for using our database, HW #1 program!"   //done
                +NL +"Goodbye my friend (^.^)/");
                break;
            }
            else {
                System.out.println("please close the database first!");
                menu();
            }
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
