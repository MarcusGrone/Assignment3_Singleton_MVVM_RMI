package Chat.ServerSide;

import Chat.ClientSide.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnection implements Runnable
{

  private ObjectOutputStream outTolient;
  private ObjectInputStream inFromClient;

  private ConnectionPool connectionPool;

  public ServerConnection(Socket connectionSocket,
      ConnectionPool connectionPool) throws IOException
  {
    outTolient = new ObjectOutputStream(connectionSocket.getOutputStream());
    inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
    this.connectionPool = connectionPool;
  }

  @Override public void run()
  {
    try
    {
      Message message = (Message) inFromClient.readObject();
      System.out.println("Message in ServerConnection:" + message);
      connectionPool.broadCast(message, this);
    }
    catch (IOException | ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
    while (true)
    {
      try
      {
        Message message = (Message) inFromClient.readObject();
        System.out.println("Message in ServerConnection:" + message);
        connectionPool.broadCast(message, this);
      }
      catch (IOException | ClassNotFoundException e)
      {
        throw new RuntimeException(e);
      }
    }
  }



public void send(Message message) throws IOException
{
  outTolient.writeObject(message);

}

}