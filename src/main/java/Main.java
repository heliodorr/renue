import launch.Launcher;
import input.Props;
import input.Validator;
import java.io.IOException;


public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {

    int desiredColumn = 0;
    String filterExp = "";

    try {
      desiredColumn = Validator.getColumnNum(args);
      System.out.println("Enter query:");
      filterExp = Validator.getFilterExp();

      Launcher.launch(filterExp, desiredColumn);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

}
