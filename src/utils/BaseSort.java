package utils;

public abstract class BaseSort {
    /**
     * 排序
     *
     * @param a 需要排序的数组
     */
    public abstract void sort(Comparable[] a);

    /**
     * 比较操作
     *
     * @param a 数组j
     * @param i 第一个元素索引
     * @param j 第二个元素索引
     * @return 如果v < w 返回true 否则返回false
     */
    protected boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    /**
     * 元素交换
     *
     * @param a 数组
     * @param i 第一个元素索引
     * @param j 第二个元素索引
     */
    protected void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 判断数组是否有序
     *
     * @param a 数组
     * @return true为有序，false为无序
     */
    public boolean isSort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a, i, i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     * @param a 数组
     */
    public void showArray(Comparable[] a) {
        for (Comparable c : a) {
            System.out.printf("%s, ", c);
        }
    }

}
