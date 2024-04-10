package Chat.ClientSide;

import Chat.ClientSide.Utill.ViewContFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.NotBoundException;

public class ChatViewController
{

  private ChatViewModel chatViewModel;
  private ViewContFactory viewContFactory;
  @FXML public TextField nameField;
  @FXML public TextField messageInputTextField;
  @FXML public TextArea textArea;

  public ChatViewController(ChatViewModel chatViewModel,
      ViewContFactory viewContFactory)
  {
    this.chatViewModel = chatViewModel;
    this.viewContFactory = viewContFactory;
  }

  public void initialize() {
    chatViewModel.messageProperty().addListener((obs, oldMessage, newMessage) -> {
      onMessageRecieved(newMessage);
    });
  }

  public void onMessageRecieved(String message)
  {
    Platform.runLater(() -> {
      textArea.appendText(message + "\n");
    });
  }

  public void onNameFieldButtonPressed() throws IOException, NotBoundException
  {
    String clientName = nameField.getText();
    chatViewModel.connect("localhost", 1099, clientName);
    nameField.clear();
  }

  public void onMessageSendButtonPressed()
  {
    String message = messageInputTextField.getText();
    chatViewModel.sendMessage(chatViewModel.getCurrentClient(), message);
    messageInputTextField.clear();
  }

  public void onNormalChatButtonPressed()
  {
    chatViewModel.messageToNormal();
  }

  public void onBoldChatButtonPressed()
  {
    chatViewModel.messageToBold();
  }

  public void onItalicButtonPressed()
  {
    chatViewModel.messageToItalic();
  }

}