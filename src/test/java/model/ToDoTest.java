package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

public class ToDoTest {

  private ToDo testTodo;
  private ToDo testTodo1;
  private Integer expectedId;
  private String expectedText;
  private Boolean expectedCompleted;
  private LocalDate expectedDate;
  private Integer expectedPriority;
  private String expectedCategory;

  @Before
  public void setUp() throws Exception {
    expectedId = 1;
    expectedText = "123";
    expectedCompleted = false;
    expectedDate = LocalDate.of(2021, 8, 3);
    expectedPriority = 1;
    expectedCategory = "home";

    testTodo = new ToDo(expectedId, expectedText, expectedCompleted, expectedDate, expectedPriority,
        expectedCategory);
  }

  @Test
  public void getId() {
    assertEquals(expectedId, testTodo.getId());
  }

  @Test
  public void getText() {
    assertEquals(expectedText, testTodo.getText());
  }

  @Test
  public void getCompleted() {
    assertEquals(expectedCompleted, testTodo.getCompleted());
  }

  @Test
  public void getDate() {
    assertEquals(expectedDate, testTodo.getDate());
  }

  @Test
  public void getPriority() {
    assertEquals(expectedPriority, testTodo.getPriority());
  }

  @Test
  public void getCategory() {
    assertEquals(expectedCategory, testTodo.getCategory());
  }

  @Test
  public void setCompleted() {
    testTodo.setCompleted(true);
    assertEquals(true, testTodo.getCompleted());
  }

  @Test
  public void testEqualsSelf() {
    assertTrue(testTodo.equals(testTodo));
  }

  @Test
  public void testEqualsDiffDataType() {
    assertFalse(testTodo.equals(1));
  }

  @Test
  public void testEqualsSameField() {
    testTodo1 = new ToDo(expectedId, expectedText, expectedCompleted, expectedDate,
        expectedPriority,
        expectedCategory);
    assertEquals(testTodo, testTodo1);
  }

  @Test
  public void testEqualsDiffId() {
    testTodo1 = new ToDo(3, expectedText, expectedCompleted, expectedDate, expectedPriority,
        expectedCategory);
    assertFalse(testTodo.equals(testTodo1));
  }

  @Test
  public void testEqualsDiffText() {
    testTodo1 = new ToDo(expectedId, "1", expectedCompleted, expectedDate, expectedPriority,
        expectedCategory);
    assertFalse(testTodo.equals(testTodo1));
  }

  @Test
  public void testEqualsDiffCompleted() {
    testTodo1 = new ToDo(expectedId, expectedText, true, expectedDate, expectedPriority,
        expectedCategory);
    assertFalse(testTodo.equals(testTodo1));
  }

  @Test
  public void testEqualsDiffDate() {
    testTodo1 = new ToDo(expectedId, expectedText, expectedCompleted, LocalDate.of(2021, 1, 9),
        expectedPriority, expectedCategory);
    assertFalse(testTodo.equals(testTodo1));
  }

  @Test
  public void testEqualsDiffPriority() {
    testTodo1 = new ToDo(expectedId, expectedText, expectedCompleted, expectedDate, 90,
        expectedCategory);
    assertFalse(testTodo.equals(testTodo1));
  }

  @Test
  public void testEqualsDiffCategory() {
    testTodo1 = new ToDo(expectedId, expectedText, expectedCompleted, expectedDate, expectedPriority,
        "1");
    assertFalse(testTodo.equals(testTodo1));
  }

  @Test
  public void testHashCode() {
    testTodo1 = new ToDo(expectedId, expectedText, expectedCompleted, expectedDate, expectedPriority,
        expectedCategory);
    assertTrue(testTodo.hashCode() == testTodo1.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "ToDo{" +
        "Id=" + expectedId +
        ", text='" + expectedText + '\'' +
        ", completed=" + expectedCompleted +
        ", date=" + expectedDate +
        ", priority=" + expectedPriority +
        ", category='" + expectedCategory + '\'' +
        '}';
    assertTrue(testTodo.toString().equals(expected));
  }
}