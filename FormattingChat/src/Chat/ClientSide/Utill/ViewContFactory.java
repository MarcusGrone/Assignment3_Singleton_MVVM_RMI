package Chat.ClientSide.Utill;

import Chat.ClientSide.ChatViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewContFactory
{
  private ViewModelFactory viewModelFactory;
  private ChatViewController chatViewController;

  private Stage stage;

  private FXMLLoader fxmlLoader;

  public ViewContFactory(ViewModelFactory viewModelFactory,
      Stage chatStage)
  {
    this.viewModelFactory = viewModelFactory;
  }

  public ChatViewController getChatView() throws IOException
  {
    if (chatViewController == null)
    {
      fxmlLoader = new FXMLLoader(getClass().getResource(
          "/Chat/ClientSide/Chat.fxml"));
      fxmlLoader.setControllerFactory(controllerClass -> new ChatViewController(
          viewModelFactory.getChatViewModel(), this));

      stage = new Stage();
      Scene mainViewScene = new Scene(fxmlLoader.load());
      stage.setTitle("GoHappyChat");
      stage.setScene(mainViewScene);
      stage.show();
      chatViewController = fxmlLoader.getController();
    }
    return chatViewController;
  }
}



