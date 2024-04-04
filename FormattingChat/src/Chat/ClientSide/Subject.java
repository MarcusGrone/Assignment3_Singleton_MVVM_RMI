package Chat.ClientSide;

import java.beans.PropertyChangeListener;

public interface Subject
{
  public void addPropertyChangeListener(String name, PropertyChangeListener listener);
}
