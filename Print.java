import java.util.*;
import java.io.*;

/*Class to format the table correctly - incorporated column width and
number of columns to ensure perfect alignment*/

class Print{

  Record record = new Record();
  private int testNumber = 1;
  private int colWidth = 0;
  private int colNum = 0;
  private String between = "------------";
  private String between2 = "";

  void printOut(String r){
      String[] row = r.split("\n");

      for(int k = 0; k < colNum; k++){
         System.out.format(" -%s", between);
      }
        System.out.println();

      for(int i = 0; i < row.length; i++){
        String[] words = row[i].split(",");
          for(int j = 0; j < words.length; j++){
            if(j == 0){
              System.out.format("|");
            }
            System.out.format("%-" + colWidth + "s" + "|", words[j]);
          }

            System.out.println();

      for(int k = 0; k < colNum; k++){
        if(i == 0){
            System.out.format(" -%s", between);
        }
      }
        System.out.println();
      }

      for(int k = 0; k < colNum; k++){
        System.out.format(" -%s", between);
      }
        System.out.println();
  }

  void createColNum(int colNum){
      this.colNum = colNum;
  }

  // Ensure column width is large enough for all fields in the table
  void createWidth(int colWidth2){
      if(colWidth2 > colWidth){
          colWidth = colWidth2 + 1;
        for(int i = 0; i < colWidth; i++){
            between2 = between2 + "-";
        }
        between = between2;
      }
  }

  //Testing
  public static void main(String[] args){
    Print test = new Print();

    test.test();
    System.out.println("Tests passed");
  }

  void test(){
    testCreateWidth();
  }

  void claim(boolean b){
    if(!b) throw new Error("Test " + testNumber + "fails");
    testNumber++;
  }

  private void testCreateWidth(){
    createWidth(4);
    claim(colWidth == 5);
    createWidth(15);
    claim(colWidth == 16);
  }

}
