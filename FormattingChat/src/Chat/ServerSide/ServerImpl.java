package Chat.ServerSide;

import Chat.ClientSide.Message;
import Chat.Shared.ClientInterface;
import Chat.Shared.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServerImpl implements ServerInterface
{
  private List<ClientInterface> connectedClients;

  public ServerImpl() throws RemoteException
  {
    connectedClients = new ArrayList<>();
    UnicastRemoteObject.exportObject(this, 0);
  }

  @Override public void sendMessage(Message messageContent)
      throws RemoteException
  {
    System.out.println("Received message: " + messageContent.getName() + ": "
        + messageContent.getMessage());

    for (ClientInterface client : connectedClients)
    {
     client.update(messageContent);
    }
  }

  @Override public void registreClient(ClientInterface client)
      throws RemoteException
  {
    connectedClients.add(client);
  }

}

