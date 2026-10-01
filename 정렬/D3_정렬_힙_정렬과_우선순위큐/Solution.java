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

        long nextLong() throws Exception {
            String s = next();
            if (s == null) return -1L;
            return Long.parseLong(s);
        }
    }

    static class MaxHeap {
        int[] heap;
        int size;

        MaxHeap(int capacity) {
            heap = new int[capacity + 1];
            size = 0;
        }

        void ensureCapacity(int minCapacity) {
            if (minCapacity >= heap.length) {
                int newCap = Math.max(minCapacity + 10, heap.length * 2);
                int[] newHeap = new int[newCap];
                System.arraycopy(heap, 0, newHeap, 0, size + 1);
                heap = newHeap;
            }
        }

        void maxHeapify(int i, int n) {
            while (true) {
                int left = 2 * i;
                int right = 2 * i + 1;
                int largest = i;

                if (left <= n && heap[left] > heap[largest]) {
                    largest = left;
                }
                if (right <= n && heap[right] > heap[largest]) {
                    largest = right;
                }

                if (largest != i) {
                    int tmp = heap[i];
                    heap[i] = heap[largest];
                    heap[largest] = tmp;
                    i = largest;
                } else {
                    break;
                }
            }
        }

        void buildMaxHeap() {
            for (int i = size / 2; i >= 1; i--) {
                maxHeapify(i, size);
            }
        }

        void insert(int key) {
            ensureCapacity(size + 1);
            size++;
            heap[size] = key;
            int i = size;
            while (i > 1 && heap[i / 2] < heap[i]) {
                int tmp = heap[i];
                heap[i] = heap[i / 2];
                heap[i / 2] = tmp;
                i = i / 2;
            }
        }

        int extractMax() {
            if (size < 1) return 0;
            int maxVal = heap[1];
            heap[1] = heap[size];
            size--;
            if (size >= 1) {
                maxHeapify(1, size);
            }
            return maxVal;
        }

        void heapsort() {
            int n = size;
            for (int i = n; i >= 2; i--) {
                int tmp = heap[1];
                heap[1] = heap[i];
                heap[i] = tmp;
                maxHeapify(1, i - 1);
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
            int M = fs.nextInt();

            MaxHeap mh = new MaxHeap(N + M + 5);
            for (int i = 1; i <= N; i++) {
                mh.heap[i] = fs.nextInt();
            }
            mh.size = N;
            mh.buildMaxHeap();

            long sumExtracted = 0;
            for (int i = 0; i < M; i++) {
                int op = fs.nextInt();
                if (op == 1) {
                    int x = fs.nextInt();
                    mh.insert(x);
                } else {
                    sumExtracted += mh.extractMax();
                }
            }

            int k = mh.size;
            sb.append('#').append(tc).append(' ').append(sumExtracted).append(' ').append(k);
            if (k > 0) {
                mh.heapsort();
                sb.append(' ').append(mh.heap[1]).append(' ').append(mh.heap[k]);
            }
            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
