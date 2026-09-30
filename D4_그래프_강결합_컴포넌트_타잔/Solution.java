import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) return;
        int T = Integer.parseInt(line.trim());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = null;
            while (st == null || !st.hasMoreTokens()) {
                line = br.readLine();
                if (line == null) break;
                st = new StringTokenizer(line);
            }
            if (st == null) break;

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            List<Integer>[] adj = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                adj[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                while (!st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
            }

            int[] discovery = new int[V + 1];
            int[] low = new int[V + 1];
            boolean[] inStack = new boolean[V + 1];
            int[] sccStack = new int[V + 1];
            int sccTop = 0;
            int timer = 0;

            List<Integer> minVertices = new ArrayList<>();

            int[] callNode = new int[V + 1];
            int[] callEdgeIdx = new int[V + 1];
            int callTop = 0;

            for (int i = 1; i <= V; i++) {
                if (discovery[i] != 0) continue;

                callNode[0] = i;
                callEdgeIdx[0] = 0;
                callTop = 1;

                discovery[i] = low[i] = ++timer;
                sccStack[sccTop++] = i;
                inStack[i] = true;

                while (callTop > 0) {
                    int u = callNode[callTop - 1];
                    int edgeIdx = callEdgeIdx[callTop - 1];

                    if (edgeIdx < adj[u].size()) {
                        int v = adj[u].get(edgeIdx);
                        callEdgeIdx[callTop - 1] = edgeIdx + 1;

                        if (discovery[v] == 0) {
                            discovery[v] = low[v] = ++timer;
                            sccStack[sccTop++] = v;
                            inStack[v] = true;

                            callNode[callTop] = v;
                            callEdgeIdx[callTop] = 0;
                            callTop++;
                        } else if (inStack[v]) {
                            low[u] = Math.min(low[u], discovery[v]);
                        }
                    } else {
                        callTop--;
                        if (callTop > 0) {
                            int p = callNode[callTop - 1];
                            low[p] = Math.min(low[p], low[u]);
                        }

                        if (low[u] == discovery[u]) {
                            int minNode = Integer.MAX_VALUE;
                            while (true) {
                                int top = sccStack[--sccTop];
                                inStack[top] = false;
                                if (top < minNode) {
                                    minNode = top;
                                }
                                if (top == u) break;
                            }
                            minVertices.add(minNode);
                        }
                    }
                }
            }

            Collections.sort(minVertices);

            sb.append("#").append(tc).append(" ").append(minVertices.size());
            for (int minNode : minVertices) {
                sb.append(" ").append(minNode);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
