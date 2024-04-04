package Chat.ClientSide;

import Chat.ClientSide.Strategy.BoldStrat;
import Chat.ClientSide.Strategy.ItalicStrat;
import Chat.ClientSide.Strategy.NormalStrat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.net.Socket;

public class Model implements Subject
{
  private ClientConnection clientConnection;
  private Socket socket;

  private PropertyChangeSupport support;
  private Message message;

  public Model()
  {
    support = new PropertyChangeSupport(true);
    message = new Message("", "");
  }

  public boolean connectToServer(String host, int port, String clientName)
      throws IOException
  {
    try
    {
      this.socket = new Socket(host, port);
      this.clientConnection = new ClientConnection(socket, message -> {
        support.firePropertyChange("message", null, message);
      });
      System.out.println("From Model: Client has connected.");
      new Thread(clientConnection).start();
      return true;
    }
    catch (IOException e)
    {
      System.out.println(e);
      return false;
    }
  }

  public void sendMessage(String name, String messageContent) throws IOException {
    Message messageToSend = new Message(name, messageContent);
    messageToSend.setChatStrategy(message.getChatStrategy());
    clientConnection.send(messageToSend.formatMessage());
  }

  @Override
  public void addPropertyChangeListener(String name, PropertyChangeListener listener) {
    support.addPropertyChangeListener(name, listener);
  }

  public void strategyBold()
  {
    message.setChatStrategy(new BoldStrat());
    support.firePropertyChange("strategyUpdated", null, new BoldStrat());
  }

  public void strategyItalic()
  {
    message.setChatStrategy(new ItalicStrat());
    support.firePropertyChange("strategyUpdated", null,
        new ItalicStrat());
  }

  public void strategyNormal()
  {
    message.setChatStrategy(new NormalStrat());
    support.firePropertyChange("strategyUpdated", null,
        new NormalStrat());
  }

}
