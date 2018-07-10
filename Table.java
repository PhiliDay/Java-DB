import java.util.*;

/*Class to collect the individual records to create a table.  Includes information
about keys*/

class Table{

  private List<Record> tab = new ArrayList<Record>();
  private List<Integer> key = new ArrayList<Integer>();
  private List<String> col = new ArrayList<String>();
  private int colNum = 0;
  private int testNumber = 1;
  private int idNum = 0;
  private int colWidth = 0;

  void insertRow(String data){
    tab.add(newRow(data));
  }

    int tableSize(){
      return tab.size();
    }

//Used to calculate the padding needed for the table
    int maxWidth(){
      return colWidth;
    }

//Used to calculate the number of columns in a table
    int maxCol() {
      return colNum;
    }

//Creates the autoincrement of keys as well as creating a new row
  private Record newRow(String data) {
    Record createNew = new Record();
    String[] input = data.split(",");

    String idNumb = String.valueOf(idNum);

  //If the autoID is already in place, then do not put add the record
  for(int i = 0; i < input.length; i++){
    if(!input[0].matches("\\d+")){
      if(!isDuplicate(idNumb)){
        createNew.enterRecordAtPoint(0, idNumb);
        key.add(idNum);
        idNum++;
      }

      createNew.enterRecord(input[i]);

      //Update the column width
      if(input[i].length() > colWidth){
        colWidth = input[i].length();
      }
    }
  }
    return createNew;
  }

//Checks to see if that record is already there
  private boolean isDuplicate(String idKey){
    if(key.contains(Integer.parseInt(idKey))){
      return true;
    }
    return false;
  }

  void updateRow(int position, String data){
    try{
    tab.add(position, newRow(data));
    }catch (IndexOutOfBoundsException e){
    System.err.println("Out of bounds" + e);
    }
  }

//Creates an ID column
  void createColumnName(String data){
    String[] inputName = data.split(",");

    this.colNum = inputName.length;

    for(int i = 0; i < colNum; i++){
      col.add(inputName[i]);
      if(inputName[i].length() > colWidth){
        colWidth = inputName[i].length();
      }
    }
    if(!inputName[0].equals("autoID")){
      col.add(0,"autoID");
      colNum++;
    }
  }

//User selects what number column they want to delete
  void deleteColumn(int position){
      col.remove(position);
    for(int i = 0; i < tableSize(); i++){
      tab.get(i).deleteRecord(position);
      tab.remove(i);
      tab.add(i, tab.get(i));
    }
    colNum--;
  }

  Record selectRow(int position){
  try{
    tab.get(position);
  }catch (IndexOutOfBoundsException e){
      System.err.println("Out of bounds" + e);
    }
    return tab.get(position);
  }

  void removeRow(int position){
    try{
      tab.remove(position);
    }catch (IndexOutOfBoundsException e){
      System.err.println("Out of bounds" + e);
      }
  }

  //Goes through the index of the table and removes the rows wanted
  void removeMultipleRows(int start, int finish){
    try{
      for(int i = finish; i >= start; i--){
        tab.remove(i);
      }
    }catch (IndexOutOfBoundsException e){
      System.err.println("Out of bounds" + e);
    }
  }


  //Gathers all the information needed to print
  String tableOut(){
  return getRecordToTable(getColNameToTable());
  }

  void getTablefromFile(List<String> input){
    for(int i = 1; i < input.size(); i++){
      insertRow(input.get(i));
    }
  }

//Ensures that the column input is seperated by commas
   String getColNameToTable(){
     String r = "";
    for(int i = 0; i < colNum; i++){
      r = r + col.get(i) + ",";
    }
      return r.substring(0, r.length()) + "\n";
   }

//Ensures that the input is seperated by commas
   String getRecordToTable(String r){
    for(int i = 0; i < tableSize(); i++){
     Record rec = tab.get(i);
      for(int j = 0; j < rec.recordSize(); j++){
        r = r + rec.getRecord(j) + ",";
      }
        r = r.substring(0, r.length()) + "\n";
    }
    return r;
  }

  public static void main(String[] args){
    Table test = new Table();
       test.test();
       System.out.println("Tests Passed");
  }

   void test(){
    testTableSize();
    tab.clear();
    testEnterRow();
    tab.clear();
    testRemoveRow();
    tab.clear();
    testRemoveRow();
    tab.clear();
    testRemoveMultipleRows();
    tab.clear();
    testCreateColName();
    tab.clear();
    testColWidth();
    tab.clear();
    testDeleteCol();
  }

  void claim(boolean b){
  if(!b) throw new Error("Test " + testNumber + "fails");
  testNumber++;
  }

  private void testTableSize(){
    claim(tableSize() == 0);
    insertRow("Vince");
    claim(tableSize() == 1);
  }

  private void testEnterRow(){
    claim(tableSize() == 0);
    insertRow("Chudleigh");
    claim(tableSize() == 1);
    insertRow("Vince");
    claim(tableSize() == 2);
    insertRow("Rosie");
    claim(tableSize() == 3);
  }

  private void testRemoveRow(){
    claim(tableSize() == 0);
    insertRow("Chudleigh");
    insertRow("Vince");
    insertRow("Rosie");
    claim(tableSize() == 3);
    removeRow(0);
    claim(tableSize() == 2);
  }

  private void testRemoveMultipleRows(){
    claim(tableSize() == 0);
    insertRow("Chudleigh");
    insertRow("Vince");
    insertRow("Rosie");
    insertRow("Jem");
    claim(tableSize() == 4);
    removeMultipleRows(0,1);
    claim(tableSize() == 2);
  }

  private void testDeleteCol(){
    createColumnName("Name,Hair,Eye");
    claim(colNum == 4);
    deleteColumn(1);
    claim(colNum == 3);
  }

  private void testCreateColName(){
    createColumnName("Name,Hair,Eye");
    claim(maxCol() == 4);
    claim(col.get(1).equals("Name"));
    claim(col.get(3).equals("Eye"));
  }

//Includes autoID as an extra column
  private void testColWidth(){
    createColumnName("Name,Hair,Eye");
    claim(maxCol() == 4);
    assert(getColNameToTable().equals("autoID,Name,Hair,Eye"));
  }

}
