package Chat.ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.function.Consumer;

public class ClientConnection implements Runnable
{
  private ObjectOutputStream outToServer;
  private ObjectInputStream inFromServer;
  private Consumer<Message> onMessageRecieved;

  public ClientConnection(Socket socket, Consumer<Message> onMessageRecieved)
      throws IOException
  {
    outToServer = new ObjectOutputStream(socket.getOutputStream());
    inFromServer = new ObjectInputStream(socket.getInputStream());
    this.onMessageRecieved = onMessageRecieved;
  }

  @Override public void run()
  {
    try
    {
      while (true)
      {
        Message message = (Message) inFromServer.readObject();
        System.out.println(message.getName() + ": " + message.getMessage());
        if (onMessageRecieved != null)
        {
          onMessageRecieved.accept(message);
        }
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      throw new RuntimeException(e);
    }
  }

  public void send(Message message) throws IOException
  {
    outToServer.writeObject(message);
    System.out.println("ClientConnection: " + message);
  }
}

