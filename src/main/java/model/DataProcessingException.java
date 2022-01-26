package model;

/**
 * An exception class handling the error during the data processing.
 */
public class DataProcessingException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.
   * @param message the specified detail message
   */
  public DataProcessingException(String message) {
    super(message);
  }
}
