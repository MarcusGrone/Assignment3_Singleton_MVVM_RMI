package Chat.ClientSide;

import Chat.ClientSide.Strategy.BoldStrat;
import Chat.ClientSide.Strategy.ItalicStrat;
import Chat.ClientSide.Strategy.NormalStrat;
import Chat.Shared.ClientInterface;
import Chat.Shared.ServerInterface;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Model implements Subject
{
  private ClientInterface client;
  private PropertyChangeSupport support;
  private Message message;

  public Model()
  {
    this.client = new Client(this);
    support = new PropertyChangeSupport(true);
    message = new Message("", "");
  }

  public void sendMessage(String name, String messageContent)
      throws RemoteException
  {
    Message messageToSend = new Message(name, messageContent);
    messageToSend.setChatStrategy(message.getChatStrategy());
    client.sendMessage(messageToSend.formatMessage());

  }

  @Override public void addPropertyChangeListener(String name,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(name, listener);
  }

  public void update(Message message)
  {
    support.firePropertyChange("message",null,message);
  }


  public void strategyBold()
  {
    message.setChatStrategy(new BoldStrat());
    support.firePropertyChange("strategyUpdated", null, new BoldStrat());
  }

  public void strategyItalic()
  {
    message.setChatStrategy(new ItalicStrat());
    support.firePropertyChange("strategyUpdated", null, new ItalicStrat());
  }

  public void strategyNormal()
  {
    message.setChatStrategy(new NormalStrat());
    support.firePropertyChange("strategyUpdated", null, new NormalStrat());
  }

}
