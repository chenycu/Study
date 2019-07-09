package chapter_2_4;

import utils.StdIn;

import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq; // 基于堆得完全二叉树
    private int n;

    /**
     * 创建一个优先队列
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * 创建一个优先队列并设置其容量
     *
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
     *
     * @param a 插入的元素
     */
    public void insert(Key a) {
        pq[++n] = a;
        swim(n);
    }

    /**
     * 返回最大元素
     *
     * @return 最大元素的索引
     */
    public Key max() {
        return pq[1];
    }

    /**
     * 删除最大元素
     *
     * @return 被删除的元素
     */
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key max = pq[1];
        exch(1, n--);
        pq[n + 1] = null; // 置空回收
        sink(1);
        return max;
    }

    /**
     * 返回队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * 返回优先队列中的元素个数
     *
     * @return
     */
    public int size() {
        return n;
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/
    public void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    @SuppressWarnings("Duplicates")
    public void sink(int k) {
        while (2 * k <= n) { // 注意：这里2k <= N 说明k的子节点可能有1个或者2个，而如果2k < N 则子节点为2个, 2k == N 则子节点为1个（N为从1开始）
            int j = k * 2;
            if (j < n && less(j, j + 1)) j++; // 如果k的子节点只有一个就直接下一步。
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        MaxPQ<String> maxPQ = new MaxPQ<>(10);
        while (StdIn.hasNextLine()) {
            maxPQ.insert(new String(StdIn.readLine()));
            if (maxPQ.size() > 8)
                System.out.println(maxPQ.delMax());
        }
    }

}
