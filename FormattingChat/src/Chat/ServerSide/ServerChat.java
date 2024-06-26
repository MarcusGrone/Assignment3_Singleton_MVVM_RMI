package Chat.ServerSide;

import Chat.Shared.ServerInterface;

import java.rmi.AlreadyBoundException;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServerChat
{
  public static void main(String[] args) throws RemoteException, AlreadyBoundException {
    ServerInterface server = new ServerImpl();
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("ChatServer", server);
    System.out.println("Server started");
  }
}
