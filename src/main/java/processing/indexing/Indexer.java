package processing.indexing;

import input.ReadingUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Indexer {

  /**
   * Индексирует файл, т.е. ассоциирует адрес строки в файле(кол-во байт от начала файла)
   * и значения строки в желаемой колонке.
   *
   * Возвращает List<IndexEntry>, который отсортирован по строкам в каждом
   * IndexEntry.
   */
  public static List<IndexEntry> indexing(int desiredCol) throws IOException {

    List<IndexEntry> entryList = new ArrayList<>(8000);
    getColumnData(entryList, desiredCol);
    entryList.sort(IndexEntry::compareTo);
    return  entryList;

  }

  private static void getColumnData(List<IndexEntry> list, int desiredCol)
      throws IOException {

    try(BufferedInputStream bis = ReadingUtils.getBuffInputStream()) {

      long address = 0;
      long fileCounter = 0;

      byte[] buff = new byte[8192];

      byte[] s = new byte[256];
      int sPointer = 0;
      int[] offsetAndLength = new int[2];

      int readed = 0;
      while((readed = bis.read(buff)) != -1){

        for(int i = 0; i < readed; i++, fileCounter++){

          if(buff[i] == '\n'){

            setStringPosition(s, sPointer, desiredCol, offsetAndLength);
            String str = new String(s,offsetAndLength[0], offsetAndLength[1]);
            IndexEntry entry = new IndexEntry(str, address);

            list.add(entry);

            address = fileCounter+1;
            sPointer = 0;

          } else {
            s[sPointer] = buff[i];
            sPointer++;
          }

        }

      }

    }

  }

  private static void setStringPosition (
      byte[] s,
      int strOffset,
      int desiredCol,
      int[] offSetAndLength
  ) {

    int currentCol = 0;
    int length = 0;
    for (int i = 0; i < strOffset; i++) {

      if(currentCol == desiredCol){

        offSetAndLength[0] = i;
        while (s[i] != ',' && s[i] != '\n'){
          i++;
          length++;
        }
        offSetAndLength[1] = length;
        break;

      } else if(s[i]==',') {

        currentCol++;
      }

    }



  }


}



