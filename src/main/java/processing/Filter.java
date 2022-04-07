package processing;

import processing.indexing.IndexEntry;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Filter {

  public static Deque<Long> findFilteredAddresses(List<IndexEntry>  indexEntries, String filterExp) {

    Deque<Long> answer = new LinkedList<>();

    int start = 0;
    int end = indexEntries.size() - 1;
    int mid;

    while (start < end) {

      mid = start + (end - start) / 2;

      IndexEntry entry = indexEntries.get(mid);

      if(entry.startsWith(filterExp)) {

        for (int i = mid; i >= 0; i--) {

          entry = indexEntries.get(i);

          if(entry.startsWith(filterExp)){
            answer.offerFirst(entry.address);
          } else {
            break;
          }

        }

        for (int i = mid + 1; i <= indexEntries.size() - 1; i++) {

          entry = indexEntries.get(i);

          if(entry.startsWith(filterExp)){
            answer.offerLast(entry.address);
          } else {
            break;
          }
        }
        return answer;
      } else {

        int compareResult = filterExp.compareTo(entry.string);

        if(compareResult < 0){
          end = mid - 1;
        } else if(compareResult > 0) {
          start = mid + 1;
        }

      }

    }

    return answer;

  }

}
