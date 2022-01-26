package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FileProcessorTest {

  private FileProcessor oneArgValid;
  private FileProcessor oneArgInvalid;
  private FileProcessor noArgs;
  private List<String> testLines;
  private FileProcessor testFileProcessor1;
  private FileProcessor testFileProcessor2;

  @Before
  public void setUp() throws Exception {
    testLines = new ArrayList<>();
    String argsValid = "./testfiles/todosTestFileProcessor.csv";
    // String argsInvalid = "test_files/Invalid_format.csv";
    // oneArgValid = new FileProcessor(argsValid);
    // oneArgInvalid = new FileProcessor(argsInvalid);
    testFileProcessor1 = new FileProcessor(argsValid);
    testLines.add(
        "\"\"\"id\"\"\",\"\"\"text\"\"\",\"\"\"completed\"\"\",\"\"\"due\"\"\","
            + "\"\"\"priority\"\"\",\"\"\"category\"\"\"");
    testLines.add(
        "1,\"\"\"Finish HW9\"\"\",\"\"\"false\"\"\",\"\"\"8/2/2021\"\"\","
            + "\"\"\"1\"\"\",\"\"\"school\"\"\"");
  }

  @Test
  public void getLines() {
    assertEquals(testLines, testFileProcessor1.getLines());
  }

  @Test
  public void testEqualsNull() throws ReadFileException {
    noArgs = new FileProcessor(null);
    assertNotEquals(noArgs, null);
  }

  @Test
  public void testEqualsSelf() {
    assertTrue(testFileProcessor1.equals(testFileProcessor1));
  }

  @Test
  public void testDiffDataType() {
    assertFalse(testFileProcessor1.equals(testLines));
  }

  @Test
  public void testValidCSV() throws ReadFileException {
    testFileProcessor2 = new FileProcessor("./testfiles/todosTestFileProcessor.csv");
    assertTrue(testFileProcessor1.equals(testFileProcessor2));
  }

  @Test(expected = ReadFileException.class)
  public void testInvalidCSV() throws ReadFileException {
    testFileProcessor2 = new FileProcessor("./testfiles/todosTestFileProcessor1.csv");
    assertFalse(testFileProcessor1.equals(testFileProcessor2));
  }

  @Test
  public void testHashCode() throws ReadFileException {
    testFileProcessor2 = new FileProcessor("./testfiles/todosTestFileProcessor.csv");
    assertTrue(testFileProcessor1.hashCode() == testFileProcessor2.hashCode());
  }

  @Test
  public void testToString() {
    String testToString = "FileProcessor{" +
        "lines=" + testLines +
        '}';
    assertEquals(testToString, testFileProcessor1.toString());
  }
}