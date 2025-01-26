import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Keyboard implements KeySubject {
    private List<KeyObserver> observers = new ArrayList<>(); // create list observers on keyboard

    KeyAdapter adapter;  // create link to the object type KeyAdapter, but not creating the object
    Keyboard() {
        adapter = new KeyAdapter() { // create object anonymous class
            @Override
            public void keyPressed(KeyEvent e) {
                notifyObservers(e); // report observer about event
            }
        };
    }
    @Override
    public void attach(KeyObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(KeyObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(KeyEvent ev) {
        for (KeyObserver observer : observers) {
            observer.update(ev);
        }
    }
}
