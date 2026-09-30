import java.io.*;
import java.util.*;

public class Solution {
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};
	static final int SIZE = 16;
	
	static int sol (int[][]board, int Starty, int Startx) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[SIZE][SIZE];
		boolean hasKey =false;
		
		q.offer(new int[] {Starty, Startx});
		visited[Starty][Startx]=true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			int y = cur[0];
			int x = cur[1];
			
			for(int d = 0; d<4; d++) {
				int ty =y+dy[d];
				int tx =x+dx[d];
				if(ty<0 || tx<0 || ty>=SIZE||tx>=SIZE) continue;
				if(visited[ty][tx]) continue;
				
				if(board[ty][tx]==3 && hasKey) return 1;
				if(board[ty][tx] == 0) {
					visited[ty][tx] = true;
					q.offer(new int[]{ty,tx});
				}
				if(board[ty][tx] == 4) {
					hasKey =true;
					visited[ty][tx] = true;
					q.offer(new int[]{ty,tx});
				}
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder res = new StringBuilder();
		
		String line;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.isEmpty()) continue;
			int tc = Integer.parseInt(line);
			int[][] board = new int[SIZE][SIZE];
			int startY=0;
			int startX=0;
			for(int y = 0; y< SIZE; y++) {
				char[] inputs = br.readLine().trim().toCharArray();
				for(int x =0 ; x<SIZE; x++) {
					board[y][x] = inputs[x] - '0';
					if(board[y][x] ==2) {
						startY = y;
						startX = x;
					}
				}
			}
			res.append("#").append(tc).append(" ").append(sol(board, startY, startX)).append("\n");
		}
		System.out.print(res);
	}
}
