package chapter_2_4;

import utils.StdIn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQTor<Key extends Comparable<Key>> implements Iterable<Key>{
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MaxPQTor(int initCapacity) {
        pq = (Key[]) new Comparable[initCapacity + 1];
        n = 0;
    }

    public MaxPQTor() {
        this(1);
    }

    public MaxPQTor(int initCapacity, Comparator<Key> comparator) {
        this(initCapacity);
        this.comparator = comparator;
    }

    public MaxPQTor(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public void insert(Key x) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
    }

    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        sink(1);
        if (n > 0 && n == (pq.length - 1) / 4) resize(pq.length / 2);
        return max;
    }

    private void resize(int capacity) {
        if (capacity < n)
            throw new IllegalArgumentException("Argument new capacity must greater then current capacity!");
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public boolean less(int i, int j) {
        if (comparator == null)
            return pq[i].compareTo(pq[j]) < 0;
        return comparator.compare(pq[i], pq[j]) < 0;
    }

    public void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }



    @SuppressWarnings("Duplicates")
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public void show() {
        for (Key k : this) {
            System.out.println(k.toString());
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MaxPQTor<Key> copy;

        public HeapIterator() {
            if (comparator == null) copy = new MaxPQTor<>(size());
            else copy = new MaxPQTor<>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        MaxPQTor<Transaction> maxPQTor = new MaxPQTor<>(2, Transaction.HOW_MUNCH_ORDER);
        while (StdIn.hasNextLine()) {
            String item = StdIn.readLine();
            if (item.equals("-")) {
                System.out.println(maxPQTor.delMax());
                continue;
            }
            if (item.equals("show")) {
                maxPQTor.show();
                continue;
            }
            String[] sts = item.split(" ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            try {
                Date dt = sdf.parse(sts[1]);
                double amount = Double.valueOf(sts[2]);
                Transaction transaction = new Transaction(sts[0], dt, amount);
                maxPQTor.insert(transaction);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
