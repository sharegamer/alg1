import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;
    private int num;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 2;
        queue = (Item[]) new Object[size];
        num = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return num == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return num;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (num == size)
            resize(2 * size);
        queue[num] = item;
        num++;

    }

    private void resize(int n) {
        size = n;
        Item[] newqueue = (Item[]) new Object[n];
        for (int i = 0; i < num; i++)
            newqueue[i] = queue[i];
        queue = newqueue;
    }

    // remove and return a random item
    public Item dequeue() {
        Item remove = null;
        if (num == 0)
            throw new NoSuchElementException();
        if (num == 1) {
            remove = queue[0];
            queue[0] = null;
            num--;
        } else {
            int i = StdRandom.uniformInt(num);
            remove = queue[i];
            queue[i] = queue[num - 1];
            queue[num - 1] = null;
            num--;
            if (4 * (num) < size) {
                resize(size / 2);
            }
        }

        return remove;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (num == 0)
            throw new NoSuchElementException();
        return queue[StdRandom.uniformInt(num)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        int pointer;
        Item[] copy;

        RandomIterator() {
            pointer = 0;
            copy = (Item[]) new Object[num];
            for (int i = 0; i < num; i++) {
                copy[i] = queue[i];
            }
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return pointer < num;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            pointer++;
            return copy[pointer - 1];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        for (int i = 0; i < 18; i++) {
            rq.enqueue("A" + i);
        }
        System.out.println("first iterator");
        for (String s : rq) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("second iterator ");
        for (String s : rq) {
            System.out.print(s + " ");
        }
        System.out.println();
        for (int i = 0; i < 18; i++) {
            System.out.print("deque ");
            System.out.print(rq.dequeue());
            System.out.println(". remain " + rq.size() + " elements. now capacity ");
        }

    }

}
