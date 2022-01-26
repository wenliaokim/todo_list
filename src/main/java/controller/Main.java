package controller;

import java.util.List;
import model.DataProcessingException;
import model.DataProcessor;
import model.FileProcessor;
import model.ReadFileException;
import model.ToDo;
import view.Display;

/**
 * A Main class.
 */
public class Main {

  /**
   * A main function.
   * @param args array of input arguments
   */
  public static void main(String[] args) {
    try {
      CommandLindParser parser = new CommandLindParser(args);
      FileProcessor toDoCSVFile = new FileProcessor(parser.getCsvFile());
      DataProcessor data = new DataProcessor(toDoCSVFile.getLines());

      if (parser.getAddToDoOptionProvided()) {
        data.addANewTodo(parser.getAddNewToDo(), parser.getCsvFile());
      }

      if (parser.getCompleteToDoOptionProvided()) {
        data.completeAnExistingTodo(parser.getCompleteToDo(), parser.getCsvFile());
      }

      List<ToDo> toDoList = data.getToDoList();
      Display.printToDos(toDoList, parser.getShowIncomplete(), parser.getShowCategory(),
          parser.getSortByDate(), parser.getSortByPriority());

    } catch (InvalidArgumentsException | ReadFileException | DataProcessingException e) {
      System.out.println(e.getMessage());
    }
  }
}