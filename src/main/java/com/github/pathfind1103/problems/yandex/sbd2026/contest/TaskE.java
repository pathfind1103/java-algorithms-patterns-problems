import java.io.*;
import java.util.*;

public class TaskE {
    static int n;
    static long[] h;

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader(System.in);
        String firstLine = fr.next();
        if (firstLine == null) return;

        n = Integer.parseInt(firstLine);
        h = new long[n];
        for (int i = 0; i < n; i++) {
            h[i] = fr.nextLong();
        }

        if (n == 1) {
            System.out.println(Math.abs(h[0] - 1));
            return;
        }

        int l = 1, r = n;
        while (r - l > 3) {
            int m1 = l + (r - l) / 3;
            int m2 = r - (r - l) / 3;
            if (getCost(m1) < getCost(m2)) {
                r = m2;
            } else {
                l = m1;
            }
        }

        long minOperations = Long.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            minOperations = Math.min(minOperations, getCost(i));
        }

        System.out.println(minOperations);
    }

    static long getCost(int i) {
        long[] b = new long[n];
        for (int j = 0; j < n; j++) {
            // b_j = h_j + |i - j| (индексы j в формуле 1-based)
            b[j] = h[j] + Math.abs(i - (j + 1));
        }

        // Находим медиану за O(n)
        long median = quickSelect(b, n / 2);

        // Ограничение H >= max(i, n - i + 1)
        long hMin = Math.max(i, n - i + 1);
        long H = Math.max(median, hMin);

        long cost = 0;
        for (long val : b) {
            cost += Math.abs(val - H);
        }
        return cost;
    }

    static long quickSelect(long[] arr, int k) {
        int left = 0, right = arr.length - 1;
        Random rand = new Random();
        while (left <= right) {
            int pivotIndex = left + rand.nextInt(right - left + 1);
            pivotIndex = partition(arr, left, right, pivotIndex);
            if (pivotIndex == k) return arr[k];
            else if (pivotIndex < k) left = pivotIndex + 1;
            else right = pivotIndex - 1;
        }
        return -1;
    }

    static int partition(long[] arr, int left, int right, int pivotIndex) {
        long pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, storeIndex, right);
        return storeIndex;
    }

    static void swap(long[] arr, int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    return null;
                }
            }
            return st.nextToken();
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
