import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws Exception {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        int nextInt() throws Exception {
            String s = next();
            if (s == null) return -1;
            return Integer.parseInt(s);
        }
    }

    static long swapCount = 0;

    static int partition(int[] A, int p, int r) {
        int x = A[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (A[j] <= x) {
                i++;
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
                swapCount++;
            }
        }
        int tmp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = tmp;
        swapCount++;
        return i + 1;
    }

    static void quicksort(int[] A, int p, int r) {
        while (p < r) {
            int q = partition(A, p, r);
            // Tail-recursion elimination to prevent deep stack frames in worst-case
            if (q - p < r - q) {
                quicksort(A, p, q - 1);
                p = q + 1;
            } else {
                quicksort(A, q + 1, r);
                r = q - 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        String tStr = fs.next();
        if (tStr == null) return;
        int T = Integer.parseInt(tStr);

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = fs.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = fs.nextInt();
            }

            swapCount = 0;
            quicksort(A, 0, N - 1);

            int midIdx = (N - 1) / 2;
            sb.append('#').append(tc).append(' ')
              .append(swapCount).append(' ')
              .append(A[0]).append(' ')
              .append(A[midIdx]).append(' ')
              .append(A[N - 1]).append('\n');
        }

        System.out.print(sb.toString());
    }
}
