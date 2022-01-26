package view.filter;

import java.util.ArrayList;
import java.util.List;
import model.ToDo;

/**
 * The class of Incomplete Filter.
 * This class has a filter method.
 */
public class IncompleteFilter {

  /**
   * This method is a filter of toDoList by incomplete. When the completed is true,
   * then remove it from toDoList.
   * @param toDoList - List, representing toDoList.
   * @return toDoList after filter.
   */
  public static List<ToDo> filter(List<ToDo> toDoList) {
    List<ToDo> filterToDoList = new ArrayList<>(toDoList);
    filterToDoList.removeIf(toDo -> toDo.getCompleted());
    return filterToDoList;
  }
}