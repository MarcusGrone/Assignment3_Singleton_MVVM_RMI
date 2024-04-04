package Chat.ClientSide.Utill;

import static javafx.application.Application.launch;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    ModelFactory modelFactory = new ModelFactory();
    ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
    ViewContFactory viewContFactory = new ViewContFactory(viewModelFactory, primaryStage);

    viewContFactory.getChatView();
  }
}