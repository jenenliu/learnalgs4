import edu.princeton.cs.algs4.StdOut;

public class NaturalMerge {
   private static Comparable[] aux;

   private static boolean less(Comparable a, Comparable b)
   {
        return a.compareTo(b) <= 0;
   }

   public static void merge(Comparable[] a, int lo, int mid, int hi)
    {
        // Merge a[lo...mid] with a[mid+1...hi]
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) // Copy a[lo...hi] to aux[lo...hi]
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) // Merge back to a[lo...hi]
            if (i > mid)    a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else  a[k] = aux[i++];
    }

   public static void sort(Comparable[] a) {
       int N = a.length;
       aux = new Comparable[N];

       for (int sz = 1; sz < N; )      // 当合并的大小是数组大小时, 说明排序完成, 也就是最后一次合并
       {
           int lo = 0;
           int mid = 0;
           int hi = 0;
           int i = lo;
           while (i < N-1)            // 每次从头开始遍历数组, 找到一个排好序的子数组就停下, 然后合并相邻的排序子数组
           {
               while (i < N-1 && less(a[i], a[i+1]))    // 找到第一个排好序的子数组结束下标, 并赋值给mid, 标志数组的第一个合并数组的结尾
                   i++;
               mid = i;
               if (i < N - 1)                          // 如果下标还未到达数组结尾, 则下标加一找下一个有序数组(相邻的)
                   ++i;
               while (i < N-1 && less(a[i], a[i+1]))   // 找到第二个排好序的子数组的结束下标, 并赋值给hi, 标志第二个合并数组的结尾
                   i++;
               hi = i;
               if (i < N - 1)
                   ++i;
               merge(a, lo, mid, hi);             // 合并两个排好序的子数组
               sz = Math.max(sz, hi - lo + 1);   // 记录合并过的数组大小最大值, 用于判断是否排好序
               lo = hi + 1;                     // 每次合并后继续下一次寻找排序子数组
           }
       }
   }

   private static void printComparables(Comparable[] a)
    {
        for (Comparable anA : a) StdOut.print(anA + ", ");
        StdOut.println();
    }

    public static void main(String[] args)
    {
        Comparable[] a = {-1, -2,-9,-2, -2, 10, 2, 8, 4, 1, 9, 10, 80, 3, 100};
        sort(a);
        printComparables(a);
    }
}
