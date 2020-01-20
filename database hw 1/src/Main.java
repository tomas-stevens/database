//---------------------------------------------------------------------
//Developers Names: Tomas Stevens
//Purpose: Developing homework 1's database and problems
//Github:
//Resources:??
//---------------------------------------------------------------------
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class Main {

public static void menu() {
    String NL = System.lineSeparator();
    System.out.println("Hello, and welcome to database homework 1. I will be your guide!" + NL+
            "below are 9 options you have a choice between. Please select one of these for the next step!" +NL+
            "1: Create new database." +NL+
            "2: Open databas    e." +NL+
            "3: Close database." +NL+
            "4: Display database." +NL+
            "5: Update database." +NL+
            "6: Create Report." +NL+
            "7: Add a record." +NL+
            "8: Delete a record." +NL+
            "9: Exit the program.");
    Switch_select();
}

public static void Switch_select(){
    Scanner inp = new Scanner(System.in);
    int query = inp.nextInt();
    switch(query){
        case 1:
            break;
        case 2:
            break;
        case 3:
            break;
        case 4:
            break;
        case 5:
            break;
        case 6:
            break;
        case 7:
            break;
        case 8:
            break;
        case 9:
            break;
        default:
            System.out.println("Please select the right numbers! <(^.^<) Try again!");
            Switch_select();
            break;
    }
}










    public static void main(String[] args)
    {
        menu();
    }
}
