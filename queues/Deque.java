import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        Item info;
        Node next;
        Node pre;

        Node(Item info, Node next, Node pre) {
            this.info = info;
            this.next = next;
            this.pre = pre;
        }
    }

    private Node first;
    private Node last;
    private int num;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        num = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return num == 0;
    }

    // return the number of items on the deque
    public int size() {
        return num;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node n = new Node(item, null, null);
        if (num == 0) {
            last = n;
            first = n;
        } else {
            n.next = first;
            first.pre = n;
            first = n;
        }
        num++;

    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node n = new Node(item, null, null);
        if (num == 0) {
            last = n;
            first = n;
        } else {
            n.pre = last;
            last.next = n;
            last = n;
        }
        num++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item i = null;
        if (num == 0)
            throw new NoSuchElementException();
        else if (num == 1) {
            i = (Item) first.info;
            first = null;
            last = null;
        } else {
            i = (Item) first.info;
            first = first.next;
            first.pre = null;
        }
        num--;
        return i;
    }

    // remove and return the item from the back
    public Item removeLast() {
        Item i = null;
        if (num == 0)
            throw new NoSuchElementException();
        else if (num == 1) {
            i = (Item) last.info;
            first = null;
            last = null;
        } else {
            i = (Item) last.info;
            last = last.pre;
            last.next = null;
        }
        num--;
        return i;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Dequeiterator();
    }

    private class Dequeiterator implements Iterator<Item> {
        int remain;
        Node pointer;

        Dequeiterator() {
            remain = num;
            pointer = first;
        }

        public boolean hasNext() {
            return remain != 0;
        }

        public Item next() {
            if (remain == 0)
                throw new NoSuchElementException();
            Item i = (Item) pointer.info;
            pointer = pointer.next;
            remain--;
            return i;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> dq = new Deque<>();
        for (int i = 0; i < 5; i++) {
            dq.addFirst("A" + i);
        }
        for (int i = 0; i < 5; i++) {
            dq.addLast("B" + i);
        }
        for (String s : dq) {
            System.out.println(s);
        }
        System.out.println("dq has " + dq.size() + " elements in total");
        for (int i = 0; i < 10; i++) {
            System.out.println(dq.removeFirst());
            System.out.println(dq.removeLast());
            System.out.println(dq.size());
        }
    }
}
