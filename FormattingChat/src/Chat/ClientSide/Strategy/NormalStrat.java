package Chat.ClientSide.Strategy;

public class NormalStrat implements ChatStrategy
{
  @Override public String formatMessage(String message)
  {
    return "Normal: " + message;
  }
}