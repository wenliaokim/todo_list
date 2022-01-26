package view;

import java.util.Collections;
import java.util.List;
import model.ToDo;
import view.filter.CategoryFilter;
import view.filter.IncompleteFilter;
import view.comparator.DateComparator;
import view.comparator.PriorityComparator;

/**
 * The class of Display is the display a list of todos.
 */
public class Display {
  public static final int EMPTY_LIST = 0;

  /**
   * Sort the list of todos by date and sort the list of todos by priority but not at the same time.
   * Category Filter and Incomplete Filter arguments can be combined.
   * If the list of todos is not sort by date and priority,
   * then display the the list of todos by ID order.
   * @param toDoList - List, the list of todos.
   * @param incomplete - Boolean, incomplete.
   * @param category - String, the category.
   * @param sortByDate - Boolean, sorting by date.
   * @param sortByPriority - Boolean, sorting by priority.
   */
  public static void printToDos(List<ToDo> toDoList, Boolean incomplete, String category,
      Boolean sortByDate, Boolean sortByPriority) {
    List<ToDo> newList = toDoList;
    if (incomplete) {
      newList = IncompleteFilter.filter(newList);
    }
    if (category != null) {
      newList = CategoryFilter.filter(newList, category);
    }
    if (sortByDate) {
      Collections.sort(newList, new DateComparator());
    } else if (sortByPriority) {
      Collections.sort(newList, new PriorityComparator());
    }
    printToDo(newList);
  }

  /**
   * Print out the list of todos.
   * @param toDoList - List, the list of todos.
   */
  private static void printToDo(List<ToDo> toDoList) {
    if (toDoList.size() == EMPTY_LIST)
      System.out.println("There is no expected todo");
    else {
      for (ToDo toDo : toDoList) {
        System.out.println(toDo);
      }
    }
  }
}
