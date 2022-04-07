package launch;

import java.io.IOException;
import java.util.Deque;
import java.util.List;
import processing.Filter;
import output.Printer;
import processing.indexing.IndexEntry;
import processing.indexing.Indexer;

public class Launcher {

  public static void launch(String filterExp, int desiredCol) throws IOException {

    long indexingTime = System.currentTimeMillis();
    List<IndexEntry> indexEntries = Indexer.indexing(desiredCol);
    indexingTime = System.currentTimeMillis() - indexingTime;

    long filteringTime = System.currentTimeMillis();
    Deque<Long> addresses = Filter.findFilteredAddresses(indexEntries, filterExp);
    filteringTime = System.currentTimeMillis() - filteringTime;

    Printer.printRows(addresses);

    System.out.format("Was found %d lines\n", addresses.size());
    System.out.format("Indexing column %d took %d ms\n", (desiredCol+1), indexingTime);
    System.out.format("Filtering took %d ms\n", filteringTime);

  }



}
