import java.io.*;
import java.util.*;

public class TaskD {
    static int M;
    static long[] allMasks;
    static int ptr = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        StringTokenizer st = new StringTokenizer(line);
        int n = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        allMasks = new long[n * 64];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            if (s != null) {
                generate(s, 0, 0, 0);
            }
        }

        Arrays.sort(allMasks, 0, ptr);

        long totalAns = 0;
        int i = 0;
        while (i < ptr) {
            int j = i;
            while (j < ptr && allMasks[j] == allMasks[i]) {
                j++;
            }

            long k = j - i;
            if (k >= 2) {
                long pairs = k * (k - 1) / 2;
                int letters = (int) (allMasks[i] & 15);

                if (letters % 2 == 1) {
                    totalAns -= pairs;
                } else {
                    totalAns += pairs;
                }
            }
            i = j;
        }

        System.out.println(totalAns);
    }

    static void generate(String s, int idx, long currentVal, int letters) {
        if (idx == M) {
            allMasks[ptr++] = (currentVal << 4) | (letters & 15);
            return;
        }

        char c = s.charAt(idx);
        if (c == '?') {
            generate(s, idx + 1, currentVal * 27, letters);
        } else {
            generate(s, idx + 1, currentVal * 27 + (c - 'a' + 1), letters + 1);
            generate(s, idx + 1, currentVal * 27, letters);
        }
    }
}
