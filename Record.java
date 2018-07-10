import java.util.*;

/*Class created to store the individual elements of each record, treating
all the different record elements the same*/

class Record{

  private List<String> obj = new ArrayList <String>();
  private int testNumber = 1;

  void enterRecord(String s){
    obj.add(s);
  }

  String getRecord(int i){
    return obj.get(i);
  }

//The user can add a field at a specific point
  void enterRecordAtPoint(int i, String s){
    obj.add(i,s);
  }

  //Returns size of the record
   int recordSize(){
      return obj.size();
   }

  void deleteRecord(int i){
    obj.remove(i);
  }

  //Checks to see whether the record is present
  boolean containsRecord(String s){
    return obj.contains(s);
  }

//Testing
  public static void main(String[] args){
    Record test = new Record();
       test.test();
       System.out.println("Tests passed");
  }

  void test(){
    testSize();
    obj.clear();
    testEnterRecord();
    obj.clear();
    testContainsRecords();
  }

  void claim(boolean b){
  if(!b) throw new Error("Test " + testNumber + "fails");
  testNumber++;
  }

  private void testSize(){
    claim(recordSize() == 0);
    obj.add("Vince");
    claim(recordSize() == 1);
  }

  private void testEnterRecord(){
    claim(recordSize() == 0);
    enterRecord("Chudleigh");
    assert(getRecord(0).equals("Chudleigh"));
    enterRecord("Rosie");
    assert(getRecord(1).equals("Rosie"));
  }

  private void testContainsRecords(){
    enterRecord("James");
    assert(containsRecord("James") == true);
    assert(containsRecord("Robert") == false);
    enterRecord("Robert");
    assert(containsRecord("Robert") == true);
  }
}
