import java.io.*;
import java.util.*;

public class Solution {
    static int[][] board;
    static boolean[][] rowCheck, colCheck, boxCheck;
    static List<int[]> blanks;
    static boolean solved;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        int T = Integer.parseInt(line.trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            board = new int[9][9];
            rowCheck = new boolean[9][10];
            colCheck = new boolean[9][10];
            boxCheck = new boolean[9][10];
            blanks = new ArrayList<>();

            for (int r = 0; r < 9; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 9; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                    if (board[r][c] != 0) {
                        int num = board[r][c];
                        rowCheck[r][num] = true;
                        colCheck[c][num] = true;
                        boxCheck[(r / 3) * 3 + (c / 3)][num] = true;
                    } else {
                        blanks.add(new int[]{r, c});
                    }
                }
            }

            solved = false;
            dfs(0);

            sb.append("#").append(tc).append("\n");
            for (int r = 0; r < 9; r++) {
                for (int c = 0; c < 9; c++) {
                    sb.append(board[r][c]).append(c == 8 ? "" : " ");
                }
                sb.append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void dfs(int idx) {
        if (idx == blanks.size()) {
            solved = true;
            return;
        }

        int[] pos = blanks.get(idx);
        int r = pos[0];
        int c = pos[1];
        int b = (r / 3) * 3 + (c / 3);

        for (int num = 1; num <= 9; num++) {
            if (!rowCheck[r][num] && !colCheck[c][num] && !boxCheck[b][num]) {
                board[r][c] = num;
                rowCheck[r][num] = true;
                colCheck[c][num] = true;
                boxCheck[b][num] = true;

                dfs(idx + 1);
                if (solved) return;

                board[r][c] = 0;
                rowCheck[r][num] = false;
                colCheck[c][num] = false;
                boxCheck[b][num] = false;
            }
        }
    }
}
