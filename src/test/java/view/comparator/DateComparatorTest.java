package view.comparator;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.DataProcessor;
import model.FileProcessor;
import model.ToDo;
import org.junit.Before;
import org.junit.Test;

public class DateComparatorTest {
  private ToDo o1;
  private ToDo o2;
  private DateComparator testComparator1;
  private DateComparator testComparator2;

  @Before
  public void setUp() throws Exception {
    testComparator1 = new DateComparator();
    o1 = new ToDo(1, "Finish HW9", false,
        LocalDate.of(2021, 8, 2), 1, "school");
    o2 = new ToDo(2, "Finish HW10", false,
        LocalDate.of(2021, 8, 2), 1, "school");
  }

  @Test
  public void compare1() {
    o1 = new ToDo(1, "Finish HW9", false,
        LocalDate.of(2021, 8, 2), 1, "school");
    o2 = new ToDo(2, "Finish HW10", false,
        LocalDate.of(2021, 8, 2), 1, "school");
    assertEquals(testComparator1.compare(o1, o2), 0);
  }

  @Test
  public void compare2() {
    o1 = new ToDo(1, "Finish HW9", false,
        LocalDate.of(2021, 8, 2), 1, "school");
    o2 = new ToDo(2, "Finish HW10", false,
        LocalDate.of(2020, 9, 2), 1, "school");
    assertEquals(testComparator1.compare(o1, o2), 1);
  }

  @Test
  public void compare3() {
    o1 = new ToDo(1, "Finish HW9", false,
        LocalDate.of(2020, 8, 2), 3, "school");
    o2 = new ToDo(2, "Finish HW10", false,
        LocalDate.of(2020, 9, 2), 1, "school");
    assertEquals(testComparator1.compare(o1, o2), -1);
  }

  @Test
  public void compareObject2IsNull() {
    o2 = new ToDo(null, null, null,
        null, null, null);
    assertEquals(testComparator1.compare(o1, o2), -1);
  }

  @Test
  public void compareBothObjectAreNull() {
   o1 = new ToDo(null, null, null,
        null, null, null);
   o2 = new ToDo(null, null, null,
        null, null, null);
    assertEquals(testComparator1.compare(o1, o2), 0);
  }

  @Test
  public void compareObject1IsNull() {
   o1 = new ToDo(null, null, null,
        null, null, null);
    assertEquals(testComparator1.compare(o1, o2), 1);
  }
}