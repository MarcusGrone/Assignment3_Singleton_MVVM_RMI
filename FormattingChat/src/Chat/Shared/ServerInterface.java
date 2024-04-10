package Chat.Shared;

import Chat.ClientSide.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote
{
  void sendMessage(Message messageContent) throws RemoteException;
  void registreClient(ClientInterface client) throws RemoteException;

}
