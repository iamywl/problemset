import java.io.*;
import java.util.*;

public class Solution {
    static final int MAX_NODES = 20005;
    static int[] val = new int[MAX_NODES];
    static int[] nxt = new int[MAX_NODES];
    static int[] prv = new int[MAX_NODES];
    static int nodeCnt;
    static int head, tail;

    static void init() {
        head = 1;
        tail = 2;
        nxt[head] = tail;
        prv[head] = 0;
        prv[tail] = head;
        nxt[tail] = 0;
        nodeCnt = 2;
    }

    static int newNode(int v) {
        int idx = ++nodeCnt;
        val[idx] = v;
        return idx;
    }

    static void addFront(int v) {
        int idx = newNode(v);
        int after = nxt[head];
        nxt[head] = idx;
        prv[idx] = head;
        nxt[idx] = after;
        prv[after] = idx;
    }

    static void addBack(int v) {
        int idx = newNode(v);
        int before = prv[tail];
        nxt[before] = idx;
        prv[idx] = before;
        nxt[idx] = tail;
        prv[tail] = idx;
    }

    static void delVal(int v) {
        int cur = nxt[head];
        while (cur != tail) {
            if (val[cur] == v) {
                int b = prv[cur];
                int a = nxt[cur];
                nxt[b] = a;
                prv[a] = b;
                break;
            }
            cur = nxt[cur];
        }
    }

    static int queryK(int k) {
        int cur = nxt[head];
        int count = 0;
        while (cur != tail) {
            count++;
            if (count == k) return val[cur];
            cur = nxt[cur];
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            init();
            int m = Integer.parseInt(br.readLine().trim());
            StringBuilder out = new StringBuilder();
            boolean first = true;

            for (int i = 0; i < m; i++) {
                String cmdLine = br.readLine().trim();
                StringTokenizer st = new StringTokenizer(cmdLine);
                String cmd = st.nextToken();
                int param = Integer.parseInt(st.nextToken());

                if (cmd.equals("ADD_FRONT")) {
                    addFront(param);
                } else if (cmd.equals("ADD_BACK")) {
                    addBack(param);
                } else if (cmd.equals("DEL_VAL")) {
                    delVal(param);
                } else if (cmd.equals("QUERY_K")) {
                    int ans = queryK(param);
                    if (!first) out.append(" ");
                    out.append(ans);
                    first = false;
                }
            }

            sb.append("#").append(tc).append(" ").append(out).append("\n");
        }
        System.out.print(sb);
    }
}
