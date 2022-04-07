package output;

import input.ReadingUtils;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Deque;

public class Printer {

  /**
   *Выводит записи в консоль по адресам(кол-ву байт от начала файла)
   */
    public static void printRows(Deque<Long> addresses) throws IOException {

      try(RandomAccessFile raf = ReadingUtils.getRandomAccessFile()) {

        for (long adr : addresses) {

          raf.seek(adr);
          String forOut = raf.readLine();

          System.out.println(forOut);

        }

      }

    }

}
