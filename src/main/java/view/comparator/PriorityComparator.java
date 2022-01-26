package view.comparator;

import java.util.Comparator;
import model.ToDo;

/**
 * The class of Priority Comparator.
 * This class compares two given object by using the priorities.
 */
public class PriorityComparator implements Comparator<ToDo> {

  /**
   * Compares two todos by the priority.
   * Compares its two arguments for order.  Returns a negative integer, zero, or a positive integer
   * as the first argument is less than, equal to, or greater than the second.
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than,
   * equal to, or greater than the second. The highest priority is one.
   */
  @Override
  public int compare(ToDo o1, ToDo o2) {
    return o1.getPriority().compareTo(o2.getPriority());
  }
}
