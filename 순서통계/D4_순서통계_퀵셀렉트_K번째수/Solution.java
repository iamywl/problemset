import java.io.InputStream;
import java.io.IOException;
import java.util.Random;

public class Solution {
    // Ultra-fast custom byte reader
    static class FastReader {
        private final InputStream in;
        private final byte[] buffer = new byte[65536];
        private int head = 0;
        private int tail = 0;

        public FastReader(InputStream in) {
            this.in = in;
        }

        private byte readByte() throws IOException {
            if (head >= tail) {
                head = 0;
                tail = in.read(buffer, 0, buffer.length);
                if (tail <= 0) return -1;
            }
            return buffer[head++];
        }

        public int nextInt() throws IOException {
            byte c = readByte();
            while (c <= ' ' && c != -1) {
                c = readByte();
            }
            if (c == -1) return Integer.MIN_VALUE;

            boolean negative = false;
            if (c == '-') {
                negative = true;
                c = readByte();
            }

            int res = 0;
            while (c >= '0' && c <= '9') {
                res = res * 10 + (c - '0');
                c = readByte();
            }
            return negative ? -res : res;
        }
    }

    private static final Random rng = new Random(42);

    // Iterative 3-way Quickselect with Randomized Pivot
    private static int quickSelect(int[] A, int k) {
        int left = 0, right = A.length - 1;
        while (left <= right) {
            if (left == right) {
                return A[left];
            }

            // Randomized pivot to prevent worst-case O(N^2)
            int pivotIdx = left + rng.nextInt(right - left + 1);
            int pivot = A[pivotIdx];
            A[pivotIdx] = A[left];
            A[left] = pivot;

            // 3-way partitioning: [left..lt-1] < pivot, [lt..gt] == pivot, [gt+1..right] > pivot
            int lt = left;
            int gt = right;
            int i = left + 1;

            while (i <= gt) {
                if (A[i] < pivot) {
                    int tmp = A[lt];
                    A[lt] = A[i];
                    A[i] = tmp;
                    lt++;
                    i++;
                } else if (A[i] > pivot) {
                    int tmp = A[gt];
                    A[gt] = A[i];
                    A[i] = tmp;
                    gt--;
                } else {
                    i++;
                }
            }

            if (k < lt) {
                right = lt - 1;
            } else if (k > gt) {
                left = gt + 1;
            } else {
                return A[k];
            }
        }
        return A[left];
    }

    public static void main(String[] args) throws Exception {
        FastReader fr = new FastReader(System.in);
        int T = fr.nextInt();
        if (T == Integer.MIN_VALUE) return;

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int N = fr.nextInt();
            int Q = fr.nextInt();

            int[] queries = new int[Q];
            for (int i = 0; i < Q; i++) {
                queries[i] = fr.nextInt();
            }

            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = fr.nextInt();
            }

            sb.append('#').append(tc);
            for (int i = 0; i < Q; i++) {
                int k = queries[i] - 1; // 0-based
                int val = quickSelect(A, k);
                sb.append(' ').append(val);
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
