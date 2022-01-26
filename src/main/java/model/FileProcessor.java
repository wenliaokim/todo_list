package model;
import java.util.*;
import java.io.*;

/**
 * A FileProcessor class which processes the input file.
 */
public class FileProcessor {
  private List<String> lines;

  /**
   * The constructor of FileProcessor class
   * @param filename - String, the file name of the file which needs to be processed
   * @throws ReadFileException - Raised when file not found and IO issue happens.
   */
  public FileProcessor(String filename) throws ReadFileException {
    this.lines = new ArrayList<>();
    this.processArgs(filename);
  }

  /**
   * Get the list of after-processed lines.
   * @return the arraylist of the after-processed lines
   */
  public List<String> getLines() {
    return this.lines;
  }

  /**
   * Helper method that helps process the file.
   * @param filename - String, the file name of the file which needs to be processed
   * @throws ReadFileException - Raised when file not found and IO issue happens.
   */
  private void processArgs(String filename) throws ReadFileException {
    if(filename == null) {
      return;
    }
    try( BufferedReader inputFile = new BufferedReader(new FileReader(filename))){
      String line;
      while((line = inputFile.readLine()) != null) {
        lines.add(line);
      }
    } catch (FileNotFoundException e) {
      throw new ReadFileException(filename + " file not found.");
    } catch (IOException e) {
      throw new ReadFileException("Something went wrong when handling " + filename);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FileProcessor)) {
      return false;
    }
    FileProcessor that = (FileProcessor) o;
    return Objects.equals(this.getLines(), that.getLines());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getLines());
  }

  @Override
  public String toString() {
    return "FileProcessor{" +
        "lines=" + this.lines +
        '}';
  }
}
