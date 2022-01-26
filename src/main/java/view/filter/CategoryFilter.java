package view.filter;

import java.util.ArrayList;
import java.util.List;
import model.ToDo;

/**
 * The class of Category Filter.
 * This class has a filter method.
 */
public class CategoryFilter {

  /**
   * This method is a filter of toDoList by category. There is not category is not null and
   * category is already exist from the todoList.
   * @param toDoList - List, representing toDoList.
   * @param category - String, representing category.
   * @return toDoList after filter.
   */
  public static List<ToDo> filter(List<ToDo> toDoList, String category) {
    List<ToDo> filterToDoList = new ArrayList<>(toDoList);
    filterToDoList
        .removeIf(toDo -> !(toDo.getCategory() != null && toDo.getCategory().equals(category)));
    return filterToDoList;
  }
}
