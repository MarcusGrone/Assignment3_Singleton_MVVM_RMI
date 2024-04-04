package Chat.ClientSide;

import Chat.ClientSide.Strategy.ChatStrategy;

import java.io.Serializable;

public class Message implements Serializable
{
  private String name;
  private String message;

  private ChatStrategy chatStrategy;


  public Message(String name, String message)
  {
    this.name = name;
    this.message = message;
  }

  public Message formatMessage() {
    String formattedContent = name + ": " + message;
    if (chatStrategy != null) {
      formattedContent = chatStrategy.formatMessage(formattedContent);
    }
    return new Message("", formattedContent);
  }

  public void setChatStrategy(ChatStrategy chatStrategy)
  {
    this.chatStrategy = chatStrategy;
  }
  public ChatStrategy getChatStrategy() {
    return chatStrategy;
  }

  public String getName()
  {
    return name;
  }

  public String getMessage()
  {
    return message;
  }
}
