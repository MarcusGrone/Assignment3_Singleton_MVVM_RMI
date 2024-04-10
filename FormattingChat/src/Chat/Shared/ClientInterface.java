package Chat.Shared;

import Chat.ClientSide.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote
{
  void sendMessage(Message messageContent) throws RemoteException;

  void update(Message message) throws RemoteException;
}
