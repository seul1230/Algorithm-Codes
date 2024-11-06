package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1861_D4_정사각형_방 {
	static int N, rooms[][];
	static int idx, answer;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<T+1;t++) {
			sb.append("#"+t+" ");
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			idx = -1; answer = 0;
			for (int i=0;i<N;i++) {
				rooms[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			visited = new boolean[N][N];
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					check(i, j, rooms[i][j], 1);
				}
			}
			sb.append(idx+" "+answer+"\n");
		}
		System.out.println(sb);
	}

	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	private static void check(int x, int y, int start, int cnt) {
		if (rooms[x][y]==N*N) return;

		int nx = x, ny = y;
		for (int i=0;i<4;i++) {
			nx = x+dx[i]; ny = y+dy[i];
			if (nx<0||nx>=N||ny<0||ny>=N||rooms[nx][ny]!=rooms[x][y]+1) continue;

			if (answer < cnt+1) {
				answer = cnt+1;
				idx = start;
			} else if (cnt+1 == answer){
				idx = Math.min(start, idx);
			}
			check(nx, ny, start, cnt+1);
		}
	}
}