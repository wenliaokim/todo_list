package model;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DataProcessor class that gives the functionality that stores the old/new information.
 */
public class DataProcessor {

  public static final int FIRST_ELEMENT = 0;
  public static final int EMPTY_LIST = 0;
  public static final int REGEX_FIRST_FIND = 1;
  public static final int TODO_STARTING_IN_LIST = 1;
  public static final int LOWEST_PRIORITY = 3;

  public static final String QUESTION_MARK = "?";
  public static final String TEXT = "text";
  public static final String COMPLETED = "completed";
  public static final String DUE = "due";
  public static final String PRIORITY = "priority";
  public static final String CATEGORY = "category";
  public static final String TRUE = "true";
  public static final String DATE_FORMATTER = "M/d/yyyy";
  public static final String TRIPLE_QUOTES = "\"\"\"";
  public static final String DELIMITER = ",";

  private List<ToDo> toDoList;
  private List<List<String>> csvToDoList;
  private List<String> header;
  private Map<Integer, List<String>> idMappingList = new HashMap<>();
  private Map<Integer, ToDo> idMappingToDo = new HashMap<>();

  /**
   * The constructor for the data processor class.
   *
   * @param csv the list of string of the lines in the csv file.
   * @throws DataProcessingException raised when the size of string list is zero.
   */
  public DataProcessor(List<String> csv) throws DataProcessingException {
    if (csv.size() == EMPTY_LIST) {
      throw new DataProcessingException("The csv file is EMPTY!!!");
    } else {
      this.toDoList = new ArrayList<>();
      this.csvToDoList = new ArrayList<>();
      this.header = this.covertString(csv.get(FIRST_ELEMENT));
      this.initialTodoList(csv);
    }
  }

  /**
   * Return the header of this Data Processor.
   *
   * @return  a List of String
   */
  public List<String> getHeader() {
    return this.header;
  }

  /**
   * Return the list of Todos gotten from the csv file
   *
   * @return a list of To-do
   */
  public List<ToDo> getToDoList() {
    return this.toDoList;
  }

  /**
   * Return the csvToDoList of the data processor.
   *
   * @return a list of list of string
   */
  public List<List<String>> getCsvToDoList() {
    return this.csvToDoList;
  }

  /**
   * Get the ID-CSVString map
   * @return a map with Integer be keys, and a list of string be values
   */
  public Map<Integer, List<String>> getIdMappingList() {
    return this.idMappingList;
  }

  /**
   * Get the ID of To-do map
   * @return a map with Integer be keys, and to-do be values
   */
  public Map<Integer, ToDo> getIdMappingToDo() {
    return this.idMappingToDo;
  }

  /**
   * Helper method that converts the string into list.
   *
   * @param toConvert - String, the string needs to be converted into a list.
   * @return the list of strings
   */
  private List<String> covertString(String toConvert) {
    List<String> copy = new ArrayList<>();
    Pattern p = Pattern.compile("\"+([^\"]*)\"+");
    Matcher m = p.matcher(toConvert);
    while (m.find()) {
      copy.add(m.group(REGEX_FIRST_FIND));
    }
    return copy;
  }

  /**
   * helper method that puts the todos into the list
   *
   * @param csv a list of strings
   */
  private void initialTodoList(List<String> csv) {
    for (int i = TODO_STARTING_IN_LIST; i < csv.size(); i++) {
      List<String> todoInfo = this.covertString(csv.get(i));
      if (todoInfo.size() == this.header.size() - 1)
      // add the id to the todoInfo because the number is not triple quotes wrapped
      {
        todoInfo.add(0, String.valueOf(i));
      }
      this.idMappingList.put(i, todoInfo);
      this.csvToDoList.add(todoInfo);

      this.idMappingToDo.put(i, helperAddTodoToList(todoInfo));
    }
  }

  /**
   * Helper method that convert te string into the To DO data structure.
   *
   * @param todoInfo a list of strings
   * @return to-do object
   */
  private ToDo helperAddTodoToList(List<String> todoInfo) {
    int textIndex = this.header.indexOf(TEXT);
    int completeIndex = this.header.indexOf(COMPLETED);
    int dueIndex = this.header.indexOf(DUE);
    int priorityIndex = this.header.indexOf(PRIORITY);
    int categoryIndex = this.header.indexOf(CATEGORY);

    String text = todoInfo.get(textIndex);
    Boolean completed = todoInfo.get(completeIndex).equals(TRUE);
    LocalDate due =
        (todoInfo.get(dueIndex).equals(QUESTION_MARK)) ? null
            : this.convertStringToLocalDate(todoInfo.get(dueIndex));
    Integer priority =
        (todoInfo.get(priorityIndex).equals(QUESTION_MARK)) ? LOWEST_PRIORITY
            : Integer.parseInt(todoInfo.get(priorityIndex));
    String category =
        (todoInfo.get(categoryIndex).equals(QUESTION_MARK)) ? null : todoInfo.get(categoryIndex);

    ToDo newTodo = new ToDo(Integer.parseInt(todoInfo.get(0)), text, completed, due, priority,
        category);
    this.toDoList.add(newTodo);
    return newTodo;
  }

  /**
   * Private method that converts the String into LocalDate
   *
   * @param localDate - String
   * @return - LocalDate
   */
  private LocalDate convertStringToLocalDate(String localDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
    return LocalDate.parse(localDate, formatter);
  }

  /**
   * Add a new to do into the list of todos. Meanwhile, write the to do into the csv file.
   *
   * @param inputMap a map with String be keys, and String be values
   * @param csvPath  String
   * @throws DataProcessingException - raised when there's issues with the csv file
   */
  public void addANewTodo(Map<String, String> inputMap, String csvPath)
      throws DataProcessingException {

    int size = this.toDoList.size() + 1;

    String text = inputMap.get(TEXT);
    Boolean completed = inputMap.get(COMPLETED).equals(TRUE);
    LocalDate due =
        inputMap.get(DUE).equals(QUESTION_MARK) ? null
            : this.convertStringToLocalDate(inputMap.get(DUE));
    Integer priority =
        inputMap.get(PRIORITY).equals(QUESTION_MARK) ? LOWEST_PRIORITY
            : Integer.parseInt(inputMap.get(PRIORITY));
    String category =
        inputMap.get(CATEGORY).equals(QUESTION_MARK) ? null : inputMap.get(CATEGORY);

    ToDo newToDo = new ToDo(size, text, completed, due, priority, category);
    this.toDoList.add(newToDo);
    this.idMappingToDo.put(size, newToDo);

    List<String> stringList = new ArrayList<>();
    stringList.add(String.valueOf(size));
    stringList.add(text);
    stringList.add(inputMap.get(COMPLETED));
    stringList.add(inputMap.get(DUE));
    stringList.add(inputMap.get(PRIORITY));
    stringList.add(inputMap.get(CATEGORY));

    this.csvToDoList.add(stringList);
    this.idMappingList.put(size, stringList);
    // open the csv as the append mode
    String newLine = this.catToANewString(stringList);
    this.appendToCsv(csvPath, newLine);
  }

  /**
   * Helper method that concatenates the list to a string which can be written into the csv file
   *
   * @param toBeConverted - a list of string
   * @return - String
   */
  private String catToANewString(List<String> toBeConverted) {
    String newLine = System.lineSeparator() + toBeConverted.get(FIRST_ELEMENT);
    for (int i = 1; i < toBeConverted.size(); i++) {
      newLine += DELIMITER + TRIPLE_QUOTES + toBeConverted.get(i) + TRIPLE_QUOTES;
    }
    return newLine;
  }

  /**
   * Helper method that appends the new to do to the end of the csv file.
   *
   * @param path    - String
   * @param newLine - String
   * @throws DataProcessingException - raised when there's issue with the csv file.
   */
  private void appendToCsv(String path, String newLine) throws DataProcessingException {
    try (BufferedWriter output = new BufferedWriter(
        new FileWriter(path, true))) {
      output.append(newLine);
    } catch (FileNotFoundException e) {
      throw new DataProcessingException("---Add a new Todo---: csv file not found!");
    } catch (IOException e) {
      throw new DataProcessingException("---Add a new Todo---: Something went wrong!");
    }
  }

  /**
   * Change the completed status to "true" given the input list of ID.
   *
   * @param completed - a list of integers
   * @param path      - String
   * @throws DataProcessingException when the id does not exist
   */
  public void completeAnExistingTodo(List<Integer> completed, String path)
      throws DataProcessingException {

    // update completed status
    int completeIndex = this.header.indexOf(COMPLETED);
    for (int i = 0; i < completed.size(); i++) {
      int csvIndex = completed.get(i);
      if (csvIndex > this.csvToDoList.size()) {
        throw new DataProcessingException(
            "---Complete todos---: ID: " + csvIndex + " Not found");
      } else {
        this.idMappingList.get(csvIndex).set(completeIndex, "true");
        this.idMappingToDo.get(csvIndex).setCompleted(true);
      }
    }

    // convert string to csv style
    String headerString = TRIPLE_QUOTES + this.header.get(FIRST_ELEMENT) + TRIPLE_QUOTES;
    for (int j = 1; j < this.header.size(); j++) {
      headerString += DELIMITER + TRIPLE_QUOTES + this.header.get(j) + TRIPLE_QUOTES;
    }

    // write the string into the csv
    try (BufferedWriter output = new BufferedWriter(new FileWriter(path))) {
      output.write(headerString);
      for (int m = 0; m < this.csvToDoList.size(); m++) {
        output.write(catToANewString(this.csvToDoList.get(m)));
      }
    } catch (FileNotFoundException e) {
      throw new DataProcessingException("---Complete todos---: csv file not found!");
    } catch (IOException e) {
      throw new DataProcessingException("---Complete todos---: Something went wrong!");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DataProcessor)) {
      return false;
    }
    DataProcessor that = (DataProcessor) o;
    return Objects.equals(this.getToDoList(), that.getToDoList()) && Objects
        .equals(this.getCsvToDoList(), that.getCsvToDoList()) && Objects
        .equals(this.getHeader(), that.getHeader()) && Objects
        .equals(this.getIdMappingList(), that.getIdMappingList()) && Objects
        .equals(this.getIdMappingToDo(), that.getIdMappingToDo());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(this.getToDoList(), this.getCsvToDoList(), this.getHeader(), this.getIdMappingList(),
            this.getIdMappingToDo());
  }

  @Override
  public String toString() {
    return "DataProcessor{" +
        "toDoList=" + this.toDoList +
        ", csvToDoList=" + this.csvToDoList +
        ", header=" + this.header +
        ", idMappingList=" + this.idMappingList +
        ", idMappingToDo=" + this.idMappingToDo +
        '}';
  }
}