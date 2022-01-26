package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Processes command line arguments to get the information the user input.
 */
public class CommandLindParser {

  public static final int VALUE_ZERO = 0;
  public static final int VALUE_ONE = 1;
  public static final int VALUE_TWO = 2;
  public static final String HIGHEST_PRIORITY = "1";
  public static final String MIDDLE_PRIORITY = "2";
  public static final String LOWEST_PRIORITY = "3";

  private String csvFile;
  private HashMap<String, String> addNewToDo;
  private List<Integer> completeToDo;
  private Boolean showIncomplete;
  private String showCategory;
  private Boolean sortByDate;
  private Boolean sortByPriority;
  private Boolean isAddToDoOptionProvided;
  private Boolean isCompleteToDoOptionProvided;
  private Boolean isDisplayOptionProvided;

  /**
   * Constructor for the CommandLineParser class.
   * @param args the array of arguments
   * @throws InvalidArgumentsException if the combination of arguments is illegal
   */
  public CommandLindParser(String[] args) throws InvalidArgumentsException {
    this.initializeAddNewToDo();
    this.initializeCompleteToDo();
    this.initializeDisplay();

    this.processArgs(args);
    this.checkArgsValid();
  }

  /**
   * Gets the path to the csv file.
   * @return a String representing the path to the csv file
   */
  public String getCsvFile() {
    return this.csvFile;
  }

  /**
   * Gets the hashmap containing the information about the new to-do the user want to add.
   * @return a hashmap containing the information about the new to-do the user want to add
   */
  public HashMap<String, String> getAddNewToDo() {
    return this.addNewToDo;
  }

  /**
   * Gets the list containing the IDs of todos which the user want to make be completed.
   * @return a list containing the IDs of todos which the user want to make be completed
   */
  public List<Integer> getCompleteToDo() {
    return this.completeToDo;
  }

  /**
   * Gets the boolean value about if the --show-incomplete option is provided in the arguments.
   * @return true or false about if the --show-incomplete option is provided in the arguments
   */
  public Boolean getShowIncomplete() {
    return this.showIncomplete;
  }

  /**
   * Gets the category following the --show-category option if provided, or null if not provided.
   * @return the category following the --show-category option if provided, or null if not provided
   */
  public String getShowCategory() {
    return this.showCategory;
  }

  /**
   * Gets the boolean value about if the --sort-by-date option is provided.
   * @return true or false about if the --sort-by-date option is provided
   */
  public Boolean getSortByDate() {
    return this.sortByDate;
  }

  /**
   * Gets the boolean value about if the --sort-by-priority option is provided.
   * @return true or false about if the --sort-by-priority option is provided
   */
  public Boolean getSortByPriority() {
    return this.sortByPriority;
  }

  /**
   * Gets the boolean value about if the this option is provided.
   * @return true or false about if the this option is provided
   */
  public Boolean getAddToDoOptionProvided() {
    return this.isAddToDoOptionProvided;
  }

  /**
   * Gets the boolean value about if the this option option is provided.
   * @return true or false about if the this option option is provided
   */
  public Boolean getCompleteToDoOptionProvided() {
    return this.isCompleteToDoOptionProvided;
  }

  /**
   * Gets the boolean value about if the --display option is provided.
   * @return true or false about if the --display option is provided
   */
  public Boolean getDisplayOptionProvided() {
    return this.isDisplayOptionProvided;
  }

  /**
   * A helper function which initializes addNewToDo hashmap and the values in it.
   */
  private void initializeAddNewToDo() {
    this.isAddToDoOptionProvided = false;
    this.addNewToDo = new HashMap<>();
    this.addNewToDo.put("text", "?");
    this.addNewToDo.put("completed", "false");
    this.addNewToDo.put("due", "?");
    this.addNewToDo.put("priority", "?");
    this.addNewToDo.put("category", "?");
  }

  /**
   * A helper function which initializes completeToDo list.
   */
  private void initializeCompleteToDo() {
    this.isCompleteToDoOptionProvided = false;
    this.completeToDo = new ArrayList<>();
  }

  /**
   * A helper function which initializes the values related to the display function.
   */
  private void initializeDisplay() {
    this.isDisplayOptionProvided = false;
    this.showIncomplete = false;
    this.sortByDate = false;
    this.sortByPriority = false;
  }

  /**
   * Processes the argumentS. Reads the arguments and catch all the cases of options.
   * @param args the array of arguments
   * @throws InvalidArgumentsException if the combination of arguments is illegal
   */
  private void processArgs(String[] args) throws InvalidArgumentsException {
    for (int i = VALUE_ZERO; i <args.length; i++) {
      switch(args[i]) {
        case "--csv-file":
          if (i == args.length - VALUE_ONE || !this.isCsvFileValid(args[i + VALUE_ONE]))
            throw new InvalidArgumentsException("--csv-file not followed by path to the csv file");
          this.csvFile = args[i + VALUE_ONE];
          break;
        case "--add-todo":
          this.isAddToDoOptionProvided = true;
          break;
        case "--todo-text":
          if (i == args.length - VALUE_ONE || this.isArgAnOption(args[i + VALUE_ONE]))
            throw new InvalidArgumentsException("--todo-text not followed by text description");
          String textOfToDo = this.extractTextOrCategoryOfToDo(args, i + VALUE_ONE);
          this.addNewToDo.replace("text", textOfToDo);
          break;
        case "--completed":
          this.addNewToDo.replace("completed", "true");
          break;
        case "--due":
          if (i == args.length - VALUE_ONE)
            throw new InvalidArgumentsException("--due provided, but not followed by (valid) due "
                + "date. The date should be format: MM/dd/year");
          this.checkIsDateValidAndAddDate(args[i + VALUE_ONE]);
          break;
        case "--priority":
          if (i == args.length - VALUE_ONE || !this.isPriorityValueValid(args[i + VALUE_ONE]))
            throw new InvalidArgumentsException("--due provided, but not followed by (valid) "
                + "priority value. The priority value should be 1, 2 or 3");
          this.addNewToDo.replace("priority", args[i + VALUE_ONE]);
          break;
        case "--category":
          if (i == args.length - VALUE_ONE || this.isArgAnOption(args[i + VALUE_ONE]))
            throw new InvalidArgumentsException("--category provided, but not followed by "
                + "category");
          String category = this.extractTextOrCategoryOfToDo(args, i + VALUE_ONE);
          this.addNewToDo.replace("category", category);
          break;
        case "--complete-todo":
          this.isCompleteToDoOptionProvided = true;
          if (i == args.length - VALUE_ONE || this.isArgAnOption(args[i + VALUE_ONE]))
            throw new InvalidArgumentsException("--complete-todo provided, but not followed by the "
                + "ID of the todo");
          this.addToCompleteIds(args[i + VALUE_ONE]);
          break;
        case "--display":
          this.isDisplayOptionProvided = true;
          break;
        case "--show-incomplete":
          this.showIncomplete = true;
          break;
        case "--show-category":
          if (i == args.length - VALUE_ONE || this.isArgAnOption(args[i + VALUE_ONE]))
            throw new InvalidArgumentsException("--show-category provided, but not followed by the "
                + "category");
          this.showCategory = this.extractTextOrCategoryOfToDo(args, i + VALUE_ONE);
          break;
        case "--sort-by-date":
          this.sortByDate = true;
          break;
        case "--sort-by-priority":
          this.sortByPriority = true;
          break;
      }
    }
  }

  /**
   * A helper function checking if the combination of arguments is legal.
   * @throws InvalidArgumentsException if the combination of arguments is illegal
   */
  private void checkArgsValid() throws InvalidArgumentsException {
    if (this.csvFile == null)
      throw new InvalidArgumentsException("the --csv-file option is not provided");
    if (this.isAddToDoOptionProvided && this.addNewToDo.get("text").equals("?"))
      throw new InvalidArgumentsException("--add-todo provided, but --todo-text is not provided");
    if (this.sortByPriority && this.sortByDate)
      throw new InvalidArgumentsException("--sort-by-date and --sort-by-priority can not be "
          + "provided at the same time");
  }

  /**
   * A helper function checking if the argument is a csv file.
   * @param filePath the argument to be checked
   * @return true or false
   */
  private boolean isCsvFileValid(String filePath) {
    int start = filePath.length() - ".csv".length();
    int end = filePath.length();
    if (start <= VALUE_ZERO) return false;
    return filePath.substring(start, end).equals(".csv");
  }

  /**
   * A helper function checking if the argument is an option
   * @param argument the argument to be checked
   * @return true or false
   */
  private boolean isArgAnOption(String argument) {
    return argument.length() > VALUE_TWO && argument.startsWith("--");
  }

  /**
   * A helper function which extract the message of text description or category.
   * @param args the array of the input arguments
   * @param startIndex the index where the message starts
   * @return the string of the text description or category
   */
  private String extractTextOrCategoryOfToDo(String[] args, int startIndex) {
    String str = args[startIndex];
    for (int i = startIndex + VALUE_ONE; i < args.length; i++) {
      if (this.isArgAnOption(args[i]))
        return str;
      else str = str.concat(" " + args[i]);
    }
    return str;
  }

  /**
   * A helper function checking if the date input is valid.
   * @param date the input argument to be checked
   * @throws InvalidArgumentsException if the argument is not following the required date format
   */
  private void checkIsDateValidAndAddDate(String date) throws InvalidArgumentsException {
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    format.setLenient(false);
    try {
      format.parse(date);
      this.addNewToDo.replace("due", date);
    } catch (ParseException e) {
      throw new InvalidArgumentsException("--due not followed by (valid) date format. The date "
          + "should be MM/dd/year");
    }
  }

  /**
   * A helper function checking if the provided priority value is valid.
   * @param argument the argument to be checked
   * @return true or false
   */
  private boolean isPriorityValueValid(String argument) {
    return argument.equals(HIGHEST_PRIORITY) || argument.equals(MIDDLE_PRIORITY)
        || argument.equals(LOWEST_PRIORITY);
  }

  /**
   * A helper function checking if the provided complete id is valid, if so, add to the
   * completeToDo list
   * @param argument the argument to be checked/added
   * @throws InvalidArgumentsException if the argument is not a number
   */
  private void addToCompleteIds(String argument) throws InvalidArgumentsException {
    try {
      Integer id = Integer.parseInt(argument);
      this.completeToDo.add(id);
    } catch (NumberFormatException e) {
      throw new InvalidArgumentsException("--complete-todo not followed by a number");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommandLindParser that = (CommandLindParser) o;
    return Objects.equals(this.getCsvFile(), that.getCsvFile()) && Objects
        .equals(this.getAddNewToDo(), that.getAddNewToDo()) && Objects
        .equals(this.getCompleteToDo(), that.getCompleteToDo()) && Objects
        .equals(this.getShowIncomplete(), that.getShowIncomplete()) && Objects
        .equals(this.getShowCategory(), that.getShowCategory()) && Objects
        .equals(this.getSortByDate(), that.getSortByDate()) && Objects
        .equals(this.getSortByPriority(), that.getSortByPriority()) && Objects
        .equals(this.isAddToDoOptionProvided, that.isAddToDoOptionProvided) && Objects
        .equals(this.isCompleteToDoOptionProvided, that.isCompleteToDoOptionProvided) && Objects
        .equals(this.isDisplayOptionProvided, that.isDisplayOptionProvided);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getCsvFile(), this.getAddNewToDo(), this.getCompleteToDo(),
        this.getShowIncomplete(),
        this.getShowCategory(), this.getSortByDate(), this.getSortByPriority(),
        this.isAddToDoOptionProvided, this.isCompleteToDoOptionProvided,
        this.isDisplayOptionProvided);
  }

  @Override
  public String toString() {
    return "CommandLindParser{" +
        "csvFile='" + this.csvFile + '\'' +
        ", addNewToDo=" + this.addNewToDo +
        ", completeToDo=" + this.completeToDo +
        ", showIncomplete=" + this.showIncomplete +
        ", showCategory='" + this.showCategory + '\'' +
        ", sortByDate=" + this.sortByDate +
        ", sortByPriority=" + this.sortByPriority +
        ", isAddToDoOptionProvided=" + this.isAddToDoOptionProvided +
        ", isCompleteToDoOptionProvided=" + this.isCompleteToDoOptionProvided +
        ", isDisplayOptionProvided=" + this.isDisplayOptionProvided +
        '}';
  }
}
