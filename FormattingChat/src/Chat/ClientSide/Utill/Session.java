package Chat.ClientSide.Utill;

public class Session {
   private static Session instance;
  private String currentClient;
  private Session() {
  }

  public static synchronized Session getInstance() {
    if (instance == null) {
      instance = new Session();
    }
    return instance;
  }

  public String getCurrentClient() {
    return currentClient;
  }

  public void setCurrentClient(String currentClient) {
    this.currentClient = currentClient;
  }
}