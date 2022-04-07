package processing.indexing;

public class IndexEntry implements Comparable<IndexEntry> {

  public final String string;
  public final long address;

  protected IndexEntry(String string, long address) {
    this.string = string;
    this.address = address;
  }

  public boolean startsWith(String str) {
    return this.string.startsWith(str);
  }

  @Override
  public int compareTo(IndexEntry o) {
    return this.string.compareTo(o.string);
  }

}