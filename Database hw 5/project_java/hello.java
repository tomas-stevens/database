public class hello 
{
   public static void main(String[] args) 
   {
   String name = "";

      // get the parameter; args[0] is the first parameter
      name = name + args[0];   

      // echo the name to the web page
      System.out.println("Hello " +  name + "<br> <br>");
    }
}
