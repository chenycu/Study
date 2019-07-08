package chapter_2_4;

import utils.BaseSort;

import java.util.Comparator;

public class HeapSort extends BaseSort {

    // 堆的优势在于根节点绝对不小于子节点（并不保证有序）。
    // 所以堆排序先要构造堆，然后再类似于优先队列删除最大值来使堆有序。
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        // 1、构造堆
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        // 2、堆有序
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    /**
     * 堆元素下沉操作
     *
     * @param a 数组
     * @param k 数组索引
     */
    private void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    @Override
    protected boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    @Override
    protected void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(a);
        heapSort.showArray(a);
    }

}
