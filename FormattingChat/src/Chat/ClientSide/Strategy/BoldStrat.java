package Chat.ClientSide.Strategy;

public class BoldStrat implements ChatStrategy
{
  public String formatMessage(String message) {
    return "Bold: " + message;
  }
}