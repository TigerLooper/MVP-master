package com.my.mvp.utils;

/**
 * Created by lp on 2016/3/25.
 */
public class Autils {
    // 快速排序：
    // 原理,通过一趟扫描将要排序的数据分割成独立的两部分,其中一部分的所有数据都比另外一部分的所有数据都要小,
    // 然后再按此方法对这两部分数据分别进行快速排序,整个排序过程可以递归进行,以此达到整个数据变成有序序列
    int[] x = {7, 2, 2, 6, 1, 4, 15, 9, 100};

    static int partition(int[] unsorted, int low, int high) {
        int pivot = unsorted[low];
        while (low < high) {
            while (low < high && unsorted[high] > pivot) high--;
            unsorted[low] = unsorted[high];
            while (low < high && unsorted[low] <= pivot) low++;
            unsorted[high] = unsorted[low];
        }
        unsorted[low] = pivot;
        return low;
    }

    public static void quick_sort(int[] unsorted, int low, int high) {
        int loc;
        if (low < high) {
            loc = partition(unsorted, low, high);
            quick_sort(unsorted, low, loc - 1);
            quick_sort(unsorted, loc + 1, high);
        }
    }

    //插入排序就是每一步都将一个待排数据按其大小插入到已经排序的数据中的适当位置，直到全部插入完毕。
    static void insertion_sort(int[] unsorted) {

        for (int i = 1; i < unsorted.length; i++) {
            if (unsorted[i - 1] > unsorted[i]) {
                int temp = unsorted[i];
                int j = i;
                while (j > 0 && unsorted[j - 1] > temp) {
                    unsorted[j] = unsorted[j - 1];
                    j--;
                }
                unsorted[j] = temp;
            }
        }
    }
    //原理是临近的数字两两进行比较,按照从小到大或者从大到小的顺序进行交换,
    //这样一趟过去后,最大或最小的数字被交换到了最后一位,
    //然后再从头开始进行两两比较交换,直到倒数第二位时结束

    static void bubble_sort(int[] unsorted) {
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = i; j < unsorted.length; j++) {
                if (unsorted[i] > unsorted[j]) {
                    int temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
    }
    // 顾名思意,就是直接从待排序数组里选择一个最小(或最大)的数字,每次都拿一个最小数字出来,
    // 顺序放入新数组,直到全部拿完
    // 再简单点,对着一群数组说,你们谁最小出列,站到最后边
    // 然后继续对剩余的无序数组说,你们谁最小出列,站到最后边
    // 再继续刚才的操作,一直到最后一个,继续站到最后边,现在数组有序了,从小到大

    static void selection_sort(int[] unsorted) {
        for (int i = 0; i < unsorted.length; i++) {
            int min = unsorted[i], min_index = i;
            for (int j = i; j < unsorted.length; j++) {
                if (unsorted[j] < min) {
                    min = unsorted[j];
                    min_index = j;
                }
            }
            if (min_index != i) {
                int temp = unsorted[i];
                unsorted[i] = unsorted[min_index];
                unsorted[min_index] = temp;
            }
        }
    }
}
