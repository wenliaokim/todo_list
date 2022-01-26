package model;

/**
 * An exception class handling the error during reading files.
 */
public class ReadFileException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.
   * @param message the specified detail message
   */
  public ReadFileException(String message) {
    super(message);
  }
}
