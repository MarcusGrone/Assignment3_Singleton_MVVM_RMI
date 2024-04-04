package Chat.ClientSide.Strategy;

public class ItalicStrat implements ChatStrategy
{
  @Override public String formatMessage(String message)
  {
    return "italic: " + message;
  }

}
