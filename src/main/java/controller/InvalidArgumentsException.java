package controller;

/**
 * An exception class handling the error when the combination of arguments is not valid.
 */
public class InvalidArgumentsException extends Exception {
  private static final String USAGES = "Usage:"
      + System.lineSeparator()
      + "--csv-file <path/to/file>: The CSV file containing the todos. This option is required."
      + System.lineSeparator()
      + "--add-todo: Add a new todo. If this option is provided, then --todo-text must also be "
      + "provided."
      + System.lineSeparator()
      + "--todo-text <description of todo>: A description of the todo."
      + System.lineSeparator()
      + "--completed: (Optional) Sets the completed status of a new todo to true."
      + System.lineSeparator()
      + "--due <due date>: (Optional) Sets the due date of a new todo (MM/dd/year)."
      + System.lineSeparator()
      + "--priority <1, 2, or 3>: (Optional) Sets the priority of a new todo. The value can be 1, "
      + "2, or 3."
      + System.lineSeparator()
      + "--category <a category name>: (Optional) Sets the category of a new todo. The value can be"
      + " any String. Categories do not need to be pre-defined."
      + System.lineSeparator()
      + "--complete-todo <id>: Mark the Todo with the provided ID as complete."
      + System.lineSeparator()
      + "--display: Display todos. If none of the following optional arguments are provided, "
      + "all todos will be displayed."
      + System.lineSeparator()
      + "--show-incomplete: (Optional) If --display is provided, only incomplete todos will be "
      + "displayed."
      + System.lineSeparator()
      + "--show-category <category>: (Optional) If --display is provided, only todos with the given"
      + " category will be displayed."
      + System.lineSeparator()
      + "--sort-by-date: (Optional) If --display is provided, sort the list of todos by date order"
      + " (ascending). Cannot be combined with --sort-by-priority."
      + System.lineSeparator()
      + "--sort-by-priority: (Optional) If --display is provided, sort the list of todos by "
      + "priority (ascending). Cannot be combined with --sort-by-date."
      + System.lineSeparator()
      + "You can put the option in any order. Also, you can request all three tasks (add, complete,"
      + " and display), for example, you can both add one todo and complete multiple todos."
      + System.lineSeparator();

  private static final String EXAMPLES = "Examples:"
      + System.lineSeparator()
      + "1: --csv-file todos.csv --add-todo --todo-text finish hw9 --complete-todo 3 --display"
      + System.lineSeparator()
      + "2: --csv-file todos.csv --add-todo --todo-text finish hw9 --complete-todo 3 "
      + "--complete-todo 4 --display --completed --due 8/12/2017 --priority 2 --category "
      + "5004 homework --show-incomplete --show-category school --sort-by-date"
      + System.lineSeparator();

  /**
   * Constructs a new exception with the specified detail message.  The cause is not initialized,
   * and may subsequently be initialized by a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the
   * {@link #getMessage()} method.
   */
  public InvalidArgumentsException(String message) {
    super(System.lineSeparator() + "Oops! there is an error: " + message + System.lineSeparator()
        + System.lineSeparator() + USAGES + System.lineSeparator() + EXAMPLES);
  }
}
