import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            int k = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[k + 1];
            int[] sum = new int[k + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + arr[i];
            }

            int[][] dp = new int[k + 1][k + 1];

            for (int len = 2; len <= k; len++) {
                for (int i = 1; i <= k - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int mid = i; mid < j; mid++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j] + (sum[j] - sum[i - 1]));
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(dp[1][k]).append("\n");
        }
        System.out.print(sb);
    }
}
