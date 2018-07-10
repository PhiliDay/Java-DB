import java.util.*;
import java.io.*;

/*Class enables the methods to be used to insert data to a txt file and read the data
from a txt file, destination specified by the user in the Database class*/

class ChangeFile{

  //Reads data from a file and returns it.
  List<String> readFile(String fileName){
    List<String> read = new ArrayList<String>();
        try{
          BufferedReader inputReader = new BufferedReader(new FileReader(fileName));
          Scanner inputR = new Scanner(inputReader);

        /*Needs to check to see if there is a next line but gets rid of it
        after adding the line*/
        while((inputR.hasNextLine())){
            read.add(inputR.nextLine());
        }
        inputR.close();
        }catch(IOException e){
           System.out.println("There was an error: " + e);
         }
           return read;
  }

//Writes to a file
 public static void writeFile(String fileName, String addData){
  try{
    BufferedWriter outputWriter = new BufferedWriter(new FileWriter(fileName, false));
    outputWriter.write(addData);
    outputWriter.close();
   }catch(IOException e){
     System.out.println("There was an error: " + e);
    }
  }
}
