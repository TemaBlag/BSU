import java.awt.event.KeyEvent;

public interface KeySubject {
    void attach(KeyObserver observer);
    void detach(KeyObserver observer);
    void notifyObservers(KeyEvent ev);
}
