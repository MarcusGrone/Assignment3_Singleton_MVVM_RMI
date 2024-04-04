package Chat.ServerSide;

import Chat.ClientSide.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool
{
  private final List<ServerConnection> connections;

  public ConnectionPool()
  {
    connections = new ArrayList<>();
  }

  public void add(ServerConnection serverConnection)
  {
    connections.add(serverConnection);
  }

  public void broadCast(Message message, ServerConnection connetingClient)
      throws IOException
  {
    for (ServerConnection connection : connections) {
      connection.send(message);
    }

  }
}
