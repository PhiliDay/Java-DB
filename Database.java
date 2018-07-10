import java.util.*;
import java.io.*;

class Database{

   private ChangeFile cf = new ChangeFile();
   private Table tab = new Table();
   private Print print = new Print();
   boolean bool = false;
   private int testNumber = 1;
   List<String> results = new ArrayList<String>();
   List<String> databases = new ArrayList<String>();
   Test finalTest = new Test();


   public static void main(String[] args){
     Database program = new Database();
     program.run();
     System.out.println("Tests passed");
   }

   private void run(){
     List<String> finishing = new ArrayList<String>();

     //Does all the testing for each class
     Test finalTest = new Test();
     finalTest.run();

     /* Code used to create a table*/



     tab.createColumnName("Name, Age, Hair");
     tab.insertRow("Eddie, 21, Ginger");
     tab.insertRow("Winston, 23, Brown");
     tab.insertRow("Jamie, 23, Brown");

     print.createWidth(tab.maxWidth());
     print.createColNum(tab.maxCol());
     print.printOut(tab.tableOut());

  }

  //Returns a list of all the files/directories
  private void  listContents(String path){
    File folder = new File(path);
    File[] listOfFiles = folder.listFiles();

    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        System.out.println("File " + listOfFiles[i].getName());
      } else if (listOfFiles[i].isDirectory()) {
        System.out.println("Directory " + listOfFiles[i].getName());
      }
    }
  }

  //Create a file
  private void createNewFile(String filename, String directory){

    File file = new File(directory + "/" + filename);

    try{
      if(!file.createNewFile()){
        throw new IOException("Specified File already exists!");
      };
    }catch(IOException e){
      System.out.println("Fail to create file!");
    }
  }

  //Create a new Directory
  private void createNewDirectory(String directory){
    File file = new File(directory);
    try{
      if(!file.mkdir()){
        throw new IOException("Specified Directory already exists!");
      };
    }catch(IOException e){
      System.out.println("Fail to create directory!");
    }
  }

  //Move file to Directory
  private void moveFileToDirectory(String filename, String foldername, String directory)
  {
    File file = new File(directory + "/" + filename);
    try{
      if(!file.renameTo(new File(directory + "/" + foldername + "/" + filename))){
        throw new IOException("Specified Directory already exists!");
      };
    }catch(IOException e){
      System.out.println("Fail to move file to directory!");
    }
  }

  //Delete a file
  private void deleteFile(String filename, String directory){
    try{
      File file = new File(directory + "/" + filename);

        bool = file.delete();
        System.out.println("File deleted");

    }catch(Exception e){
      System.out.println("Exception occured");
      e.printStackTrace();
    }
  }

}
