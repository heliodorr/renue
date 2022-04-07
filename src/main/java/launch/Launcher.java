package launch;

import input.Validator;
import java.io.IOException;
import java.util.Deque;
import java.util.List;
import processing.Filter;
import output.Printer;
import processing.indexing.IndexEntry;
import processing.indexing.Indexer;

/**
 * Класс для запуска приложения и подсчета времени.
 */
public class Launcher {

  public static void launch(String[] args) throws IOException {

    int desiredCol = 0;
    String filterExp = "";

    desiredCol = Validator.getColumnNum(args);
    System.out.println("Enter query:");
    filterExp = Validator.getFilterExp();


    long indexingTime = System.currentTimeMillis();
    List<IndexEntry> indexEntries = Indexer.indexing(desiredCol);
    indexingTime = System.currentTimeMillis() - indexingTime;

    long filteringTime = System.currentTimeMillis();
    Deque<Long> addresses = Filter.findFilteredAddresses(indexEntries, filterExp);
    filteringTime = System.currentTimeMillis() - filteringTime;

    Printer.printRows(addresses);

    System.out.format("%d lines were found\n", addresses.size());
    System.out.format("Indexing column %d took %d ms\n", (desiredCol+1), indexingTime);
    System.out.format("Search and filtering took %d ms\n", filteringTime);

  }



}
