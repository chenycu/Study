package chapter_2_4;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    /**
     * 创建一个优先队列
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * 创建一个优先队列并设置其容量
     * @param initCapacity 优先队列的初始容量
     */
    public MaxPQ(int initCapacity) {
        // 数组是协变的，数组能追踪元素的实际类型，这个类型是在数组创建时建立的。
        // 如果new key[10]，因类型擦除的缘故，数组无法确定元素的实际类型
        pq = (Key[]) new Comparable[initCapacity + 1];
        n = 0;
    }


    /**
     * 向优先队列中插入一个元素
     * @param a 插入的元素
     */
    public void insert(Key a) {
        pq[++n] = a;
    }

    /**
     * 返回最大元素
     * @return 最大元素的索引
     */
    public int max() {
        int maxIndex = 1;
        for (int i = 1; i < size()+1; i++) {
            if (less(maxIndex, i)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * 删除最大元素
     * @return 被删除的元素
     */
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        int maxIndex = max();
        Key max = pq[maxIndex];
        exch(maxIndex, n--);
        pq[n+1] = null; // 垃圾回收
        return max;
    }

    /**
     * 返回队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回优先队列中的元素个数
     * @return
     */
    public int size() {
        return n;
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public static void main(String[] arg) {
        MaxPQ<String> maxPQ = new MaxPQ<>(10);
        maxPQ.insert("a");
        maxPQ.insert("b");
        maxPQ.insert("c");
        System.out.println(maxPQ.delMax());
        maxPQ.insert("d");
        maxPQ.insert("e");
    }

}
