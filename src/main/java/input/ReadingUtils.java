package input;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadingUtils {

  public static BufferedReader getBuffReader() throws IOException {
    return Files.newBufferedReader(Path.of(Props.getDataFilename()));
  }

  public static BufferedInputStream getBuffInputStream() throws IOException {
    return new BufferedInputStream(new FileInputStream(Props.getDataFilename()));
  }

  public static RandomAccessFile getRandomAccessFile() throws IOException {
    return new RandomAccessFile(Props.getDataFilename(),"r");
  }




}
