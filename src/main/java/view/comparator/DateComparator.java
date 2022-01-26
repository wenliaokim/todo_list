package view.comparator;

import java.time.LocalDate;
import java.util.Comparator;
import model.ToDo;

/**
 * The class of Date Comparator.
 * This class compares two given object by using the dates.
 */
public class DateComparator implements Comparator<ToDo> {

  /**
   * Compares two todos by the dates.
   * Compares its two arguments for order. Returns a negative integer, zero, or a positive integer
   * as the first argument is less than, equal to, or greater than the second.
   * @param o1 the first object to be compared.
   * @param o2 the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument is less than,
   * equal to, or greater than the second.
   */
  @Override
  public int compare(ToDo o1, ToDo o2) {
    if(o1.getDate() == null && o2.getDate() == null)
      return 0;
    if(o1.getDate() == null && o2.getDate() != null)
      return 1;
    if(o2.getDate() == null && o1.getDate() != null)
      return -1;
    return o1.getDate().compareTo(o2.getDate());
  }
}
