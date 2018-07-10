import java.util.*;

/*Class used to run the tests of each class altogether*/

class Test{
  Record recordTest = new Record();
  Table tableTest = new Table();
  Print printTest = new Print();

  //Testing
  public static void main(String[] args){
    Test program = new Test();
    program.run();
    System.out.println("Tests passed");
  }

  void run(){
    recordTest.test();
    tableTest.test();
    printTest.test();
  }

}
