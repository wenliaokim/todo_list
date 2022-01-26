package view;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.ToDo;
import org.junit.Before;
import org.junit.Test;

public class DisplayTest {

  private Display testDisplay;
  List<ToDo> listOfToDo1;
  List<ToDo> listOfToDo2;
  private ToDo toDo1;
  private ToDo toDo2;
  private ToDo toDo3;
  private ToDo toDo4;
  private ToDo toDo5;
  private String testCategory1;

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Before
  public void setUp() throws Exception {

    testCategory1 = "home";
    testDisplay = new Display();

    toDo1 = new ToDo(1, "ID1", false,
        LocalDate.of(2021, 8, 20), 3, "school");
    toDo2 = new ToDo(2, "ID2", false,
        LocalDate.of(2021, 8, 15), 2, "home");
    toDo3 = new ToDo(3, "ID3", false,
        LocalDate.of(2021, 8, 10), 1, "school");
    toDo4 = new ToDo(4, "ID4", false,
        LocalDate.of(2021, 8, 5), 1, "school");
    toDo5 = new ToDo(5, "ID5", true,
        LocalDate.of(2021, 8, 1), 1, "school");
    listOfToDo1 = new ArrayList<>();
    listOfToDo1.add(toDo1);
    listOfToDo1.add(toDo2);
    listOfToDo1.add(toDo3);
    listOfToDo1.add(toDo4);

    listOfToDo2 = new ArrayList<>();
    listOfToDo2.add(toDo4);
    listOfToDo2.add(toDo5);

    System.setOut(new PrintStream(output));
  }

  @Test
  public void printToDos() {
    Integer testID = 2;
    String textID = "ID2";
    Integer testPriority = 2;
    LocalDate testDate = LocalDate.of(2021, 8, 15);
    String testCategory = "home";
    String outputString = "ToDo{" +
        "Id=" + testID +
        ", text='" + textID + '\'' +
        ", completed=" + false +
        ", date=" + testDate +
        ", priority=" + testPriority +
        ", category='" + testCategory + '\'' +
        '}' + '\n';
    testDisplay.printToDos(listOfToDo1, false, "home", false, false);
    assertEquals(outputString, output.toString());
  }

  @Test
  public void printToDoIncomplete() {
    Integer testID = 2;
    String textID = "ID2";
    Integer testPriority = 2;
    LocalDate testDate = LocalDate.of(2021, 8, 15);
    String testCategory = "home";
    String outputString = "ToDo{" +
        "Id=" + testID +
        ", text='" + textID + '\'' +
        ", completed=" + false +
        ", date=" + testDate +
        ", priority=" + testPriority +
        ", category='" + testCategory + '\'' +
        '}' + '\n';
    testDisplay.printToDos(listOfToDo1, true, "home", false, false);
    assertEquals(outputString, output.toString());
  }

  @Test
  public void printToDoCategorySchool() {
    Integer testID1 = 1;
    Integer testID3 = 3;
    Integer testID4 = 4;
    String textID1 = "ID1";
    String textID3 = "ID3";
    String textID4 = "ID4";

    Integer testPriority1 = 3;
    Integer testPriority3 = 1;
    Integer testPriority4 = 1;
    LocalDate testDate1 = LocalDate.of(2021, 8, 20);
    LocalDate testDate3 = LocalDate.of(2021, 8, 10);
    LocalDate testDate4 = LocalDate.of(2021, 8, 5);
    String testCategory = "school";
    String outputString = "ToDo{" +
        "Id=" + testID1 +
        ", text='" + textID1 + '\'' +
        ", completed=" + false +
        ", date=" + testDate1 +
        ", priority=" + testPriority1 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID3 +
        ", text='" + textID3 + '\'' +
        ", completed=" + false +
        ", date=" + testDate3 +
        ", priority=" + testPriority3 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID4 +
        ", text='" + textID4 + '\'' +
        ", completed=" + false +
        ", date=" + testDate4 +
        ", priority=" + testPriority4 +
        ", category='" + testCategory + '\'' +
        '}' + '\n';
    testDisplay.printToDos(listOfToDo1, false, "school", false, false);
    assertEquals(outputString, output.toString());
  }

  @Test
  public void printToDoCategoryHome() {
    Integer testID = 2;
    String textID = "ID2";
    Integer testPriority = 2;
    LocalDate testDate = LocalDate.of(2021, 8, 15);
    String testCategory = "home";
    String outputString = "ToDo{" +
        "Id=" + testID +
        ", text='" + textID + '\'' +
        ", completed=" + false +
        ", date=" + testDate +
        ", priority=" + testPriority +
        ", category='" + testCategory + '\'' +
        '}' + '\n';
    testDisplay.printToDos(listOfToDo1, false, "home", false, false);
    assertEquals(outputString, output.toString());
  }

  @Test
  public void printCategoryIsNull() {
    Integer testID1 = 1;
    Integer testID2 = 2;
    Integer testID3 = 3;
    Integer testID4 = 4;
    String textID1 = "ID1";
    String textID2 = "ID2";
    String textID3 = "ID3";
    String textID4 = "ID4";

    Integer testPriority1 = 3;
    Integer testPriority2 = 2;
    Integer testPriority3 = 1;
    Integer testPriority4 = 1;
    LocalDate testDate1 = LocalDate.of(2021, 8, 20);
    LocalDate testDate2 = LocalDate.of(2021, 8, 15);
    LocalDate testDate3 = LocalDate.of(2021, 8, 10);
    LocalDate testDate4 = LocalDate.of(2021, 8, 5);
    String testCategory = "school";
    String outputString = "ToDo{" +
        "Id=" + testID1 +
        ", text='" + textID1 + '\'' +
        ", completed=" + false +
        ", date=" + testDate1 +
        ", priority=" + testPriority1 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID2 +
        ", text='" + textID2 + '\'' +
        ", completed=" + false +
        ", date=" + testDate2 +
        ", priority=" + testPriority2 +
        ", category='" + "home" + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID3 +
        ", text='" + textID3 + '\'' +
        ", completed=" + false +
        ", date=" + testDate3 +
        ", priority=" + testPriority3 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID4 +
        ", text='" + textID4 + '\'' +
        ", completed=" + false +
        ", date=" + testDate4 +
        ", priority=" + testPriority4 +
        ", category='" + testCategory + '\'' +
        '}' + '\n';
    testDisplay.printToDos(listOfToDo1, false, null, false, false);
    assertEquals(outputString, output.toString());
  }

  @Test
  public void PrintSortByDate() {
    Integer testID1 = 1;
    Integer testID2 = 2;
    Integer testID3 = 3;
    Integer testID4 = 4;
    String textID1 = "ID1";
    String textID2 = "ID2";
    String textID3 = "ID3";
    String textID4 = "ID4";

    Integer testPriority1 = 3;
    Integer testPriority2 = 2;
    Integer testPriority3 = 1;
    Integer testPriority4 = 1;
    LocalDate testDate1 = LocalDate.of(2021, 8, 20);
    LocalDate testDate2 = LocalDate.of(2021, 8, 15);
    LocalDate testDate3 = LocalDate.of(2021, 8, 10);
    LocalDate testDate4 = LocalDate.of(2021, 8, 5);
    String testCategory = "school";
    String outputString =  "ToDo{" +
        "Id=" + testID4 +
        ", text='" + textID4 + '\'' +
        ", completed=" + false +
        ", date=" + testDate4 +
        ", priority=" + testPriority4 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID3 +
        ", text='" + textID3 + '\'' +
        ", completed=" + false +
        ", date=" + testDate3 +
        ", priority=" + testPriority3 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID2 +
        ", text='" + textID2 + '\'' +
        ", completed=" + false +
        ", date=" + testDate2 +
        ", priority=" + testPriority2 +
        ", category='" + "home" + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID1 +
        ", text='" + textID1 + '\'' +
        ", completed=" + false +
        ", date=" + testDate1 +
        ", priority=" + testPriority1 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' ;
    testDisplay.printToDos(listOfToDo1, false, null, true, false);
    assertEquals(outputString, output.toString());

  }

  @Test
  public void PrintSortByPriority() {
    Integer testID1 = 1;
    Integer testID2 = 2;
    Integer testID3 = 3;
    Integer testID4 = 4;
    String textID1 = "ID1";
    String textID2 = "ID2";
    String textID3 = "ID3";
    String textID4 = "ID4";

    Integer testPriority1 = 3;
    Integer testPriority2 = 2;
    Integer testPriority3 = 1;
    Integer testPriority4 = 1;
    LocalDate testDate1 = LocalDate.of(2021, 8, 20);
    LocalDate testDate2 = LocalDate.of(2021, 8, 15);
    LocalDate testDate3 = LocalDate.of(2021, 8, 10);
    LocalDate testDate4 = LocalDate.of(2021, 8, 5);
    String testCategory = "school";
    String outputString = "ToDo{" +
        "Id=" + testID3 +
        ", text='" + textID3 + '\'' +
        ", completed=" + false +
        ", date=" + testDate3 +
        ", priority=" + testPriority3 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID4 +
        ", text='" + textID4 + '\'' +
        ", completed=" + false +
        ", date=" + testDate4 +
        ", priority=" + testPriority4 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID2 +
        ", text='" + textID2 + '\'' +
        ", completed=" + false +
        ", date=" + testDate2 +
        ", priority=" + testPriority2 +
        ", category='" + "home" + '\'' +
        '}' + '\n' +
        "ToDo{" +
        "Id=" + testID1 +
        ", text='" + textID1 + '\'' +
        ", completed=" + false +
        ", date=" + testDate1 +
        ", priority=" + testPriority1 +
        ", category='" + testCategory + '\'' +
        '}' + '\n' ;
    testDisplay.printToDos(listOfToDo1, false, null, false, true);
    assertEquals(outputString, output.toString());
  }

}

