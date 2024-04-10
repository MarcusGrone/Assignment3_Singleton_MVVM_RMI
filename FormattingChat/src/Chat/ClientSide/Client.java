package Chat.ClientSide;

import Chat.ClientSide.Message;
import Chat.Shared.ClientInterface;
import Chat.Shared.ServerInterface;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements ClientInterface, Serializable
{
  private Model model;
  private ServerInterface server;

  public Client(Model model)
  {
    this.model = model;
    try
    {
      UnicastRemoteObject.exportObject(this, 0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (ServerInterface) registry.lookup("ChatServer");
      server.registreClient(this);
    }
    catch (RemoteException | NotBoundException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void sendMessage(Message messageContent)
      throws RemoteException
  {
    server.sendMessage(messageContent);
  }

  @Override public void update(Message message) throws RemoteException
  {
    model.update(message);
  }
}
