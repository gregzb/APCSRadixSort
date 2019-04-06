import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private Node headRef = new Node(null);
    private Node tailRef = headRef;

    private int size = 0;

    public LinkedList() {

    }

    public int size() {
        return size;
    }

    public int lSize() {
        int count = 0;
        Node current = headRef;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public void clear() {
        headRef.setNext(null);
        size = 0;
    }

    public boolean addAsHead(T val) {
        add(0, val);
        return true;
    }

    public boolean addAsTail(T val) {
        add(size, val);
        return true;
    }

    public boolean addAsTail(T val, boolean yes) {
        //System.out.println("gresd");
        add(size, val, true);
        return true;
    }

    public boolean add(int index, T value) {
        Node previous = getNode(index - 1);
        previous.setNext(new Node(value, previous.getNext()));

        if (index == size) {
            tailRef = previous.getNext();
        }

        size++;
        return true;
    }

    public boolean add(int index, T value, boolean yes) {
        Node previous = getNode(index - 1, true);
        previous.setNext(new Node(value, previous.getNext()));

        if (index == size) {
            //System.out.println("burgle");
            tailRef = previous.getNext();
        }

        size++;
        return true;
    }

    private Node getNode(int index) {
        if (index == size - 1) return tailRef;
        Node current = headRef;
        for (int count = -1; count < index; count++, current = current.getNext()) ;
        return current;
    }

    private Node getNode(int index, boolean yes) {
        if (index == size - 1) {
            //System.out.println("yurt");
            return tailRef;
        }
        System.out.println("oof");
        Node current = headRef;
        for (int count = -1; count < index; count++, current = current.getNext()) ;
        return current;
    }

    public T get(int index) {
        return getNode(index).getData();
    }

    public T set(int index, T value) {
        Node temp = getNode(index);
        T tempObj = temp.getData();
        temp.setData(value);
        return tempObj;
    }

    public T remove(int index) {
        Node previous = getNode(index - 1);
        T tempObj = previous.getNext().getData();
        previous.setNext(previous.getNext().getNext());

        size--;

        return tempObj;
    }

    public T removeFront() {
        return remove(0);
    }

    public String toString() {
        String temp = "";

        Node current = headRef.getNext();
        while (current != null) {
            temp += current + " --> ";
            current = current.getNext();
        }

        temp += "[no next]";

        return temp;
    }

    public void extend(LinkedList<T> other) {
        tailRef.setNext(other.headRef.getNext());
        tailRef = other.tailRef;
        size += other.size;
        other.clear();
    }

    public void reverse() {
        if (size < 2) return;
        tailRef = headRef.getNext();
        Node current = headRef.getNext().getNext();
        while (current != null) {
            Node temp = current.getNext();
            current.setNext(current.prev);
            if (temp == null) {
                headRef.setNext(current);
                //System.out.println(headRef.getNext());
            }
            //System.out.println("PREV IS " + current.prev);
            current = temp;
        }
        //System.out.println("wee");
        ////System.out.println(toString());
        //System.out.println(headRef.getNext());
        //System.out.println(headRef.getNext().getNext());
        //System.out.println(headRef.getNext().getNext().getNext());
        //System.out.println(headRef.getNext().getNext().getNext().getNext());
        //System.out.println(headRef.getNext().getNext().getNext().getNext().getNext());
        //System.out.println(tailRef);
    }

    public Iterator iterator() {
        return new LinkedListIterator(this);
    }

    class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node next) {
            setData(data);
            setNext(next);
        }

        public Node setNext(Node newNext) {
            Node temp = next;
            next = newNext;
            if (newNext != null) {
                newNext.prev = this;
            } else {

            }
            return temp;
        }

        public Node getNext() {
            return next;
        }

        public T setData(T data) {
            T temp = data;
            this.data = data;
            return temp;
        }

        public T getData() {
            return data;
        }

        public String toString() {
            return "Node-" + hashCode() + ": " + getData() + " -> " + (next == null ? "null" : next.hashCode());
        }
    }

    class LinkedListIterator implements Iterator {

        Node current;

        public LinkedListIterator(LinkedList<T> list) {
            current = list.getNode(0);
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            T data = current.getData();
            current = current.getNext();
            return data;
        }


    }
}