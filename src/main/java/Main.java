import launch.Launcher;
import input.Props;
import input.Validator;
import java.io.IOException;


public class Main {

  public static void main(String[] args) {

    try {
      Launcher.launch(args);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

}
