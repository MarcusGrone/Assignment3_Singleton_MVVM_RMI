package Chat.ClientSide;

import Chat.ClientSide.Utill.Session;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;

public class ChatViewModel
{
  private Model model;
  // private final BooleanProperty isConnected = new SimpleBooleanProperty(false);
  private Session session = Session.getInstance();
  private StringProperty message = new SimpleStringProperty();

  public ChatViewModel(Model model) {
    this.model = model;
    model.addPropertyChangeListener("message", event -> {
      Message message = (Message) event.getNewValue();
      messageProperty().set(message.getName() + message.getMessage());
    });

  }


  public StringProperty messageProperty()
  {
    return message;
  }

  public void sendMessage(String name, String message)
  {
    try
    {
      System.out.println("Message recieved by ViewModel");
      model.sendMessage(name, message);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String getCurrentClient()
  {
    return session.getCurrentClient();
  }

  public void connect(String host, int port, String clientName)
      throws IOException
  {
    model.connectToServer(host, port, clientName);
    session.setCurrentClient(clientName);
  }

  public void messageToNormal()
  {
    model.strategyNormal();
  }

  public void messageToItalic()
  {
    model.strategyItalic();
  }

  public void messageToBold()
  {
    model.strategyBold();
  }

}
