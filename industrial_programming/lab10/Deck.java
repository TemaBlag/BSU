import javax.swing.*;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Deck<T> {
        private ArrayList<T> abc;

        Deck(ArrayList<T> arList) {
            abc = arList;
        }

        public int size() {
            return abc.size();
        }

        public void push(T el) {
            abc.add(el);
        }

        public T front() throws EmptyStackException {
            if (abc.isEmpty()) {
                throw new EmptyStackException();
            }
            return abc.get(0);
        }

        public T back() throws EmptyStackException {
            if (abc.isEmpty()) {
                throw new EmptyStackException();
            }
            return abc.get(size() - 1);
        }

        public void pushFront(T el) {
            abc.add(0, el);
        }

        public void pushBack(T el) {
            abc.add(el);

        }

        public void pushBackAll(ArrayList<T> addList) {
            for(T val: addList){
                pushBack(val);
            }
        }

        public void pushFrontAll(ArrayList<T> addList) {
            for (int i = addList.size() - 1; i >= 0; i--) {
                pushFront(addList.get(i));
            }
        }

        public T popFront() throws EmptyStackException {
            if (abc.isEmpty()) {
                throw new EmptyStackException();
            }
            T currEl = abc.get(0);
            abc.remove(0);
            return currEl;
        }

        public T popBack() throws EmptyStackException {
            if (abc.isEmpty()) {
                throw new EmptyStackException();
            }
            T currEl = abc.get(size() - 1);
            abc.remove(size() - 1);
            return currEl;
        }
        class Iterator {
            private int point;

            Iterator() {
                point = 0;
            }

            public void next() throws EmptyStackException {
                if (!hasNext()) {
                    throw new EmptyStackException();
                }
                point += 1;
            }

            public boolean hasNext() {
                return point < size();
            }

            public T current() {
                return abc.get(point);
            }
        }

        class ReversedIterator {
            private int point;

            ReversedIterator() {
                point = 0;
            }

            public void next() throws EmptyStackException {
                if (!hasNext()) {
                    throw new EmptyStackException();
                }
                point -= 1;
            }

            public boolean hasNext() {
                return point >= 0;
            }

            public T current() {
                return abc.get(point);
            }
        }

        public Iterator iterator() {
            return new Iterator();
        }

        public ReversedIterator reversedIterator() {
            return new ReversedIterator();
        }
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = iterator();
            while (it.hasNext()) {
                stringBuilder.append(it.current()).append(" ");
                it.next();
            }
            if (!stringBuilder.isEmpty()) {
                stringBuilder.append(";");
            }
            return stringBuilder.toString();
        }

        public boolean isEmpty() {
            return abc.isEmpty();
        }

        public void clear() {
            abc = new ArrayList<>();
        }

        public boolean equals(Deck<T> anotherList) {
            if (size() != anotherList.size()) {
                return false;
            }
            Iterator it1 = this.iterator();
            Iterator it2 = anotherList.iterator();
            while (it1.hasNext()) {
                if (!it1.current().equals(it2.current())){
                    return false;
                }
                it1.next();
                it2.next();
            }
            return true;
        }

        public DefaultListModel<T> listParameter() {
            DefaultListModel<T> model = new DefaultListModel<>();
            Iterator it = this.iterator();
            while (it.hasNext()) {
                model.addElement(it.current());
                it.next();
            }
            return model;
        }
    }
