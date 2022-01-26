package model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * This class representing TODOs Application.
 * This class contains Integer id, String text, Boolean completed, LocalDate date, Integer priority,
 * and String category.
 */
public class ToDo {

  private Integer id;
  private String text;
  private Boolean completed;
  private LocalDate date;
  private Integer priority;
  private String category;

  /**
   * Constructor of ToDos class.
   * @param id - Integer, the id of the number of todos.
   * @param text - String, the text of todos.
   * @param completed - Boolean, true if it completed, otherwise false.
   * @param date - LocalDate, the date of todos.
   * @param priority - Integer, the number of priority.
   * @param category - String, the category of todos.
   */
  public ToDo(Integer id, String text, Boolean completed, LocalDate date, Integer priority,
      String category) {
    this.id = id;
    this.text = text;
    this.completed = completed;
    this.date = date;
    this.priority = priority;
    this.category = category;
  }

  /**
   * Gets the id of todos.
   * @return the id of todos.
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Gets the text of todos.
   * @return the text of todos.
   */
  public String getText() {
    return this.text;
  }

  /**
   * Gets the completed of todos.
   * @return true if it completed, otherwise false.
   */
  public Boolean getCompleted() {
    return this.completed;
  }

  /**
   * Gets the date of todos.
   * @return the date of todos.
   */
  public LocalDate getDate() {
    return this.date;
  }

  /**
   * Gets the priority of todos.
   * @return the priority of todos.
   */
  public Integer getPriority() {
    return this.priority;
  }

  /**
   * Gets the category of todos.
   * @return the category of todos.
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Sets the completed of todos.
   * @param completed - Boolean, true if it completed, otherwise false.
   */
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ToDo)) {
      return false;
    }
    ToDo toDo = (ToDo) o;
    return Objects.equals(this.getId(), toDo.getId()) && Objects
        .equals(this.getText(), toDo.getText()) && Objects
        .equals(this.getCompleted(), toDo.getCompleted()) && Objects
        .equals(this.getDate(), toDo.getDate()) && Objects
        .equals(this.getPriority(), toDo.getPriority()) && Objects
        .equals(this.getCategory(), toDo.getCategory());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(this.getId(), this.getText(), this.getCompleted(), this.getDate(), this.getPriority(),
            this.getCategory());
  }

  @Override
  public String toString() {
    return "ToDo{" +
        "Id=" + this.id +
        ", text='" + this.text + '\'' +
        ", completed=" + this.completed +
        ", date=" + this.date +
        ", priority=" + this.priority +
        ", category='" + this.category + '\'' +
        '}';
  }
}
