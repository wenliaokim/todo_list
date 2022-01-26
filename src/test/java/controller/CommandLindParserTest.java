package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CommandLindParserTest {
  private CommandLindParser testParser1;
  private CommandLindParser testParser2;
  private String[] testArgs1;
  private String[] testArgs2;
  private String csvOption;
  private String pathToCsvFile;
  private String addToDoOption;
  private String todoTextOption;
  private String descriptionOfTodo1;
  private String descriptionOfTodo2;
  private String descriptionOfTodo3;
  private String completedOption;
  private String dueOption;
  private String validDueDate;
  private String invalidDueDate;
  private String priorityOption;
  private String validPriorityValue;
  private String invalidPriorityValue;
  private String categoryOption;
  private String category;
  private String completeTodoOption;
  private String validCompleteTodoID;
  private String invalidCompleteTodoID;
  private String displayOption;
  private String showIncompleteOption;
  private String showCategoryOption;
  private String sortByDateOption;
  private String sortByPriorityOption;

  @Before
  public void setUp() throws Exception {
    csvOption = "--csv-file";
    pathToCsvFile = "todos.csv";
    addToDoOption = "--add-todo";
    todoTextOption = "--todo-text";
    descriptionOfTodo1 = "finish hw9";
    descriptionOfTodo2 = "finish";
    descriptionOfTodo3 = "hw9";
    completedOption = "--completed";
    dueOption = "--due";
    validDueDate = "8/5/2021";
    invalidDueDate = "33/30/2020";
    priorityOption = "--priority";
    validPriorityValue = "2";
    invalidPriorityValue = "5";
    categoryOption = "--category";
    category = "school";
    completeTodoOption = "--complete-todo";
    validCompleteTodoID = "3";
    invalidCompleteTodoID = "three";
    displayOption = "--display";
    showIncompleteOption = "--show-incomplete";
    showCategoryOption = "--show-category";
    sortByDateOption = "--sort-by-date";
    sortByPriorityOption = "--sort-by-priority";

    testArgs1 = new String[]{csvOption, pathToCsvFile,
                             addToDoOption,
                             completeTodoOption, validCompleteTodoID,
                             completedOption,
                             todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
                             priorityOption, validPriorityValue,
                             categoryOption, category,
                             dueOption, validDueDate,
                             displayOption, showCategoryOption, category, showIncompleteOption,
                             sortByDateOption};

    testParser1 = new CommandLindParser(testArgs1);
  }

  @Test
  public void getCsvFile() {
    assertEquals(pathToCsvFile, testParser1.getCsvFile());
  }

  @Test
  public void getAddNewToDo() {
    HashMap<String, String> expected = new HashMap<>();
    expected.put("text", descriptionOfTodo2 + " " + descriptionOfTodo3);
    expected.put("completed", "true");
    expected.put("due", validDueDate);
    expected.put("priority", validPriorityValue);
    expected.put("category", category);
    assertEquals(expected, testParser1.getAddNewToDo());
  }

  @Test
  public void getCompleteToDo() {
    List<Integer> expected = new ArrayList<>();
    expected.add(3);
    assertEquals(expected, testParser1.getCompleteToDo());
  }

  @Test
  public void getShowIncomplete() {
    assertTrue(testParser1.getShowIncomplete());
  }

  @Test
  public void getShowCategory() {
    assertEquals(category, testParser1.getShowCategory());
  }

  @Test
  public void getSortByDate() {
    assertTrue(testParser1.getSortByDate());
  }

  @Test
  public void getSortByPriority() {
    assertFalse(testParser1.getSortByPriority());
  }

  @Test
  public void getAddToDoOptionProvided() {
    assertTrue(testParser1.getAddToDoOptionProvided());
  }

  @Test
  public void getCompleteToDoOptionProvided() {
    assertTrue(testParser1.getCompleteToDoOptionProvided());
  }

  @Test
  public void getDisplayOptionProvided() {
    assertTrue(testParser1.getDisplayOptionProvided());
  }

  @Test(expected = InvalidArgumentsException.class)
  public void csvFileOptionProvidedButCsvFileNotProvided1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void csvFileOptionProvidedButCsvFileNotProvided2() throws InvalidArgumentsException {
    testArgs2 = new String[]{addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        csvOption};
    testParser2 = new CommandLindParser(testArgs2);
  }


  @Test(expected = InvalidArgumentsException.class)
  public void todoTextOptionNotFollowedByTextDescription1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void todoTextOptionNotFollowedByTextDescription2() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void dueOptionNotFollowedByValidDate1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, invalidDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void dueOptionNotFollowedByValidDate2() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        priorityOption, validPriorityValue,
        categoryOption, category,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void priorityOptionNotFollowedByValidValue1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        priorityOption, invalidPriorityValue,
        categoryOption, category,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void priorityOptionNotFollowedByValidValue2() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        categoryOption, category,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void categoryOptionNotFollowedByValidCategory1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        categoryOption,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void categoryOptionNotFollowedByValidCategory2() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue,
        categoryOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void completeTodoOptionNotFollowedByValidID1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, invalidCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue,
        categoryOption, category};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void completeTodoOptionNotFollowedByValidID2() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption,
        completedOption,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue,
        categoryOption, category};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void completeTodoOptionNotFollowedByValidID3() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completedOption,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue,
        categoryOption, category,
        completeTodoOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void showCategoryOptionNotFollowedByValidCategory1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue,
        showCategoryOption};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void showCategoryOptionNotFollowedByValidCategory2() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption,
        showCategoryOption,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void csvFileOptionNotProvided() throws InvalidArgumentsException {
    testArgs2 = new String[]{pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category,
        sortByDateOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void sortByPriorityAndSortByDateBothProvided() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category,
        sortByDateOption, sortByPriorityOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, validPriorityValue};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test(expected = InvalidArgumentsException.class)
  public void addTodoOptionProvidedTextNotProvided() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category,
        sortByDateOption,
        dueOption, validDueDate,
        priorityOption, validPriorityValue};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test
  public void branchCaseForOtherValidPriorityValue1() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category,
        sortByPriorityOption,
        todoTextOption, descriptionOfTodo1,
        dueOption, validDueDate,
        priorityOption, "1"};
    testParser2 = new CommandLindParser(testArgs2);
    assertEquals("1", testParser2.getAddNewToDo().get("priority"));
  }

  @Test
  public void branchCaseForOtherValidPriorityValue2() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category,
        sortByPriorityOption,
        dueOption, validDueDate,
        priorityOption, "3",
        todoTextOption, descriptionOfTodo1};
    testParser2 = new CommandLindParser(testArgs2);
    assertEquals("3", testParser2.getAddNewToDo().get("priority"));
  }

  @Test(expected = InvalidArgumentsException.class)
  public void branchCaseForInvalidCsvFile() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, "cs",
        completeTodoOption, validCompleteTodoID,
        completedOption,
        displayOption, showCategoryOption, category,
        sortByPriorityOption,
        dueOption, validDueDate,
        priorityOption, "3",
        todoTextOption, descriptionOfTodo1};
    testParser2 = new CommandLindParser(testArgs2);
  }

  @Test
  public void testEquals() throws InvalidArgumentsException {
    assertTrue(testParser1.equals(testParser1));
    assertFalse(testParser1.equals(null));
    assertFalse(testParser1.equals(testArgs1));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertTrue(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, "anothertestfile.csv",
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completeTodoOption, "4",
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));

    testArgs2 = new String[]{csvOption, pathToCsvFile,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        addToDoOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertFalse(testParser1.equals(testParser2));
  }

  @Test
  public void testHashCode() throws InvalidArgumentsException {
    testArgs2 = new String[]{csvOption, pathToCsvFile,
        addToDoOption,
        completeTodoOption, validCompleteTodoID,
        completedOption,
        todoTextOption, descriptionOfTodo2, descriptionOfTodo3,
        priorityOption, validPriorityValue,
        categoryOption, category,
        dueOption, validDueDate,
        displayOption, showCategoryOption, category, showIncompleteOption,
        sortByDateOption};
    testParser2 = new CommandLindParser(testArgs2);
    assertEquals(testParser1.hashCode(), testParser2.hashCode());
  }

  @Test
  public void testToString() throws InvalidArgumentsException {
    HashMap<String, String> expectedHashMap = new HashMap<>();
    expectedHashMap.put("text", descriptionOfTodo2 + " " + descriptionOfTodo3);
    expectedHashMap.put("completed", "true");
    expectedHashMap.put("due", validDueDate);
    expectedHashMap.put("priority", validPriorityValue);
    expectedHashMap.put("category", category);

    List<Integer> expectedCompleteTodo = new ArrayList<>();
    expectedCompleteTodo.add(3);

    String expectedToString = "CommandLindParser{" +
        "csvFile='" + pathToCsvFile + '\'' +
        ", addNewToDo=" + expectedHashMap +
        ", completeToDo=" + expectedCompleteTodo +
        ", showIncomplete=" + true +
        ", showCategory='" + category + '\'' +
        ", sortByDate=" + true +
        ", sortByPriority=" + false +
        ", isAddToDoOptionProvided=" + true +
        ", isCompleteToDoOptionProvided=" + true +
        ", isDisplayOptionProvided=" + true +
        '}';
    assertEquals(expectedToString, testParser1.toString());
  }
}











