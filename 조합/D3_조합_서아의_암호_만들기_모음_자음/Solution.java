import java.io.*;
import java.util.*;

public class Solution {
    static int l, c;
    static char[] chars, picked;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            chars = new char[c];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < c; i++) {
                chars[i] = st.nextToken().charAt(0);
            }
            Arrays.sort(chars);

            picked = new char[l];
            sb.append("#").append(tc).append("\n");
            dfs(0, 0);
        }
        System.out.print(sb);
    }

    private static void dfs(int depth, int start) {
        if (depth == l) {
            int vowel = 0, consonant = 0;
            for (int i = 0; i < l; i++) {
                char ch = picked[i];
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowel++;
                } else {
                    consonant++;
                }
            }
            if (vowel >= 1 && consonant >= 2) {
                sb.append(new String(picked)).append("\n");
            }
            return;
        }

        for (int i = start; i < c; i++) {
            picked[depth] = chars[i];
            dfs(depth + 1, i + 1);
        }
    }
}
