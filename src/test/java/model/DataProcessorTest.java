package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jdk.vm.ci.meta.Local;
import org.junit.Before;
import org.junit.Test;

public class DataProcessorTest {

  private DataProcessor testDataProcessor;
  private DataProcessor testDataProcessor1;

  private List<ToDo> expectedTodoList = new ArrayList<>();
  private List<List<String>> expectedCsvToDoList = new ArrayList<>();
  private List<String> expectedHeader = new ArrayList<>();
  private Map<Integer, List<String>> expectedIdMappingList = new HashMap<>();
  private Map<Integer, ToDo> expectedIdMappingToDo = new HashMap<>();

  @Before
  public void setUp() throws Exception {
    FileProcessor toDoCSVFile = new FileProcessor("./testfiles/todosTestDataProcessor.csv");
    testDataProcessor = new DataProcessor(toDoCSVFile.getLines());
    testDataProcessor1 = new DataProcessor(toDoCSVFile.getLines());

    expectedHeader.add("id");
    expectedHeader.add("text");
    expectedHeader.add("completed");
    expectedHeader.add("due");
    expectedHeader.add("priority");
    expectedHeader.add("category");

    ToDo first = new ToDo(1, "Finish HW9", false, LocalDate.of(2021, 8, 2), 1, "school");
    expectedTodoList.add(first);

    List<String> toAdd = new ArrayList<>();
    toAdd.add("1");
    toAdd.add("Finish HW9");
    toAdd.add("false");
    toAdd.add("8/2/2021");
    toAdd.add("1");
    toAdd.add("school");
    expectedCsvToDoList.add(toAdd);

    expectedIdMappingList.put(1, toAdd);
    expectedIdMappingToDo.put(1, first);
  }

  @Test
  public void getHeader() {
    assertEquals(expectedHeader, testDataProcessor.getHeader());
  }

  @Test
  public void addANewTodo() throws Exception {
    Map<String, String> first = new HashMap<>();
    first.put("text", "Finish OA");
    first.put("completed", "false");
    first.put("due", "9/2/2021");
    first.put("priority", "1");
    first.put("category", "intern");

    testDataProcessor.addANewTodo(first, "./testfiles/todosTestDataProcessorAddANewToDo.csv");
    ToDo second = new ToDo(2, "Finish OA", false, LocalDate.of(2021, 9, 2), 1, "intern");
    expectedTodoList.add(second);

    List<String> secondList = new ArrayList<>();
    secondList.add("2");
    secondList.add("Finish OA");
    secondList.add("false");
    secondList.add("9/2/2021");
    secondList.add("1");
    secondList.add("intern");

    expectedCsvToDoList.add(secondList);
    expectedIdMappingToDo.put(2, second);
    expectedIdMappingList.put(2, secondList);

    assertEquals(expectedTodoList, testDataProcessor.getToDoList());
    assertEquals(expectedCsvToDoList, testDataProcessor.getCsvToDoList());
    assertEquals(expectedIdMappingToDo, testDataProcessor.getIdMappingToDo());
    assertEquals(expectedIdMappingList, testDataProcessor.getIdMappingList());
  }

  @Test
  public void completeAnExistingTodo() throws DataProcessingException {
    List<Integer> ids = new ArrayList<>();
    ids.add(1);
    testDataProcessor.completeAnExistingTodo(ids,
        "./testfiles/todosTestDataProcessorCompleteAnExistingTodo.csv");
    expectedIdMappingList.get(1).set(2, "true");
    expectedIdMappingToDo.get(1).setCompleted(true);
    assertEquals(expectedIdMappingList.get(1), testDataProcessor.getCsvToDoList().get(0));
    assertEquals(expectedIdMappingToDo.get(1), testDataProcessor.getToDoList().get(0));
  }


  @Test
  public void testEqualsSelf() {
    assertTrue(testDataProcessor.equals(testDataProcessor));
  }

  @Test
  public void testEqualsDiffDataType() {
    assertFalse(testDataProcessor.equals("123"));
  }

  @Test
  public void testEqualsSameField() throws Exception {
    assertTrue(testDataProcessor.equals(testDataProcessor1));
  }

  @Test
  public void testEqualsDiffTodo() {
    ToDo temp = new ToDo(2, "ana", false, LocalDate.of(2020, 12, 1), 5, "category");
    testDataProcessor1.getToDoList().add(temp);
    assertFalse(testDataProcessor.equals(testDataProcessor1));
  }

  @Test
  public void testEqualsDiffCsvList() {
    testDataProcessor1.getCsvToDoList().add(new ArrayList<>());
    assertFalse(testDataProcessor.equals(testDataProcessor1));
  }

  @Test
  public void testEqualsDiffHeader() {
    testDataProcessor1.getHeader().add("123");
    assertFalse(testDataProcessor.equals(testDataProcessor1));
  }

  @Test
  public void testEqualsDiffMapList() {
    testDataProcessor1.getIdMappingList().put(1, new ArrayList<>());
    assertFalse(testDataProcessor.equals(testDataProcessor1));
  }

  @Test
  public void testEqualsDiffMapToDo() {
    testDataProcessor1.getIdMappingToDo().put(1, null);
    assertFalse(testDataProcessor.equals(testDataProcessor1));
  }

  @Test
  public void testHashCode() {
    assertTrue(testDataProcessor.hashCode() == testDataProcessor1.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "DataProcessor{" +
        "toDoList=" + expectedTodoList +
        ", csvToDoList=" + expectedCsvToDoList +
        ", header=" + expectedHeader +
        ", idMappingList=" + expectedIdMappingList +
        ", idMappingToDo=" + expectedIdMappingToDo +
        '}';
    assertEquals(testDataProcessor.toString(), expected);
  }
}