package view.comparator;

import static org.junit.Assert.*;

import java.time.LocalDate;
import model.ToDo;
import org.junit.Before;
import org.junit.Test;

public class PriorityComparatorTest {
  private ToDo o1;
  private ToDo o2;
  private PriorityComparator testComparator1;

  @Before
  public void setUp() throws Exception {
    testComparator1 = new PriorityComparator();
    o1 = new ToDo(1, "Finish HW9", false,
        LocalDate.of(2021, 8, 2), 1, "school");
    o2 = new ToDo(2, "Finish HW10", false,
        LocalDate.of(2021, 8, 2), 3, "school");
  }

  @Test
  public void compare() {
    assertEquals(-1, testComparator1.compare(o1, o2));
  }
}