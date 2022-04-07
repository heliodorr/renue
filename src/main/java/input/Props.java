package input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Класс для получения настроек
 */
public class Props {

  private static final String PROPERTY_FILENAME = "props.properties";

  private static final String DEFAULT_COLUMN_PROPERTY = "column";
  private static final String DATA_FILENAME_PROPERTY = "filename";
  private static final String QUOTEIGONRE_PROPERTY = "quoteignore";

  private static boolean INCORRECT_INIT;

  private static final Properties APP_PROPERTIES;

  static {

    InputStream is = Props
        .class
        .getClassLoader()
        .getResourceAsStream(PROPERTY_FILENAME);

    APP_PROPERTIES = new Properties(2);
    try {
      APP_PROPERTIES.load(is);
      INCORRECT_INIT = false;
    } catch (IOException e) {
      INCORRECT_INIT = true;
    }

  }

  protected static int getDefaultColumn() throws IOException {
    if(INCORRECT_INIT) {
      throw new IOException();
    }

    String temp = APP_PROPERTIES.getProperty(DEFAULT_COLUMN_PROPERTY);

    return Integer.parseInt(temp);

  }


  public static String getDataFilename() throws IOException {

    String fileName = APP_PROPERTIES.getProperty(DATA_FILENAME_PROPERTY);
    boolean fileNotExists = Files.notExists(Path.of(fileName));

    if(INCORRECT_INIT || fileNotExists) {
      throw new IOException();
    }

    return APP_PROPERTIES.getProperty(DATA_FILENAME_PROPERTY);

  }

  protected static boolean quoteIgnored() throws IOException {
    if(INCORRECT_INIT) {
      throw new IOException();
    }

    String temp = APP_PROPERTIES.getProperty(QUOTEIGONRE_PROPERTY);

    return Boolean.parseBoolean(temp);

  }

}
