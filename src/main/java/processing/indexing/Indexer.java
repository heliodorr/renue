package processing.indexing;

import input.ReadingUtils;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Indexer {

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

      int readed = 0;
      while((readed = bis.read(buff)) != -1){

        for(int i = 0; i < readed; i++, fileCounter++){

          if(buff[i] == '\n'){

            String str = new String(s,0, sPointer);
            str = str.split(",")[desiredCol];

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

}



