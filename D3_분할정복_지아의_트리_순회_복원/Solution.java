import java.io.*;
import java.util.*;

public class Solution {
    static int[] preorder, inorder;
    static Map<Integer, Integer> inMap;
    static StringBuilder outSb;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine().trim());
            preorder = new int[n];
            inorder = new int[n];
            inMap = new HashMap<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) preorder[i] = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
                inMap.put(inorder[i], i);
            }
            
            outSb = new StringBuilder();
            buildPostorder(0, n - 1, 0, n - 1);
            sb.append("#").append(tc).append(outSb.toString()).append("\n");
        }
        System.out.print(sb);
    }
    
    private static void buildPostorder(int preS, int preE, int inS, int inE) {
        if (preS > preE) return;
        int root = preorder[preS];
        int rootIdx = inMap.get(root);
        int leftSize = rootIdx - inS;
        
        buildPostorder(preS + 1, preS + leftSize, inS, rootIdx - 1);
        buildPostorder(preS + leftSize + 1, preE, rootIdx + 1, inE);
        outSb.append(" ").append(root);
    }
}
