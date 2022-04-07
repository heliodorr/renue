package input;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *Для валидации и/или получения данных
 */

public class Validator {

  private static final char QUOTE = '"';

  /**
   * @return номер колонки для индексирования
   */
  public static int getColumnNum(String[] args) throws IOException, IllegalArgumentException {

    int columnNum = 0;

    if(args.length == 0) {

      columnNum = Props.getDefaultColumn();

    } else if(args.length > 1) {

      throw new IllegalArgumentException("Too many params");

    } else {
      try {
        columnNum = Integer.parseInt(args[0]) - 1;
      } catch (NumberFormatException e){
        throw new NumberFormatException("Column parameter is not an integer number");
      }
    }

    try(BufferedReader br = ReadingUtils.getBuffReader()) {
      int columnsAmount = br.readLine().split(",").length;

      if(columnNum < 0 || columnNum >= columnsAmount){
        throw new IllegalArgumentException("Incorrect column number was given");
      }
    }

    return columnNum;

  }

  /**
   *@return фильтр от пользователя
   */
  public static String getFilterExp() throws IOException {

    String answer = "";

    try(Scanner scanner = new Scanner(System.in)) {
      answer = scanner.nextLine();
    }

    if(Props.quoteIgnored()){
      answer = QUOTE + answer;
    }

    return answer;

  }

}
