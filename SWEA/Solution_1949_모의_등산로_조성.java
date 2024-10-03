package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1949_모의_등산로_조성_조예슬 {
	static int N, K, max, answer;
	static int map[][];
	static boolean visited[][];
	static Queue<Pos> q = new LinkedList<>();

	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=1; t<T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			max = 0; answer = 0;
			for (int i=0;i<N;i++){
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for (int j=0;j<N;j++) {
					max = Math.max(max, map[i][j]);
				}
			}
			for (int i=0; i<N;i++){
				for (int j=0;j<N;j++){
					if (max == map[i][j]) {
						dfs(i, j, 1, false);
					}
				}
			}
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.print(sb);
	}

	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};

	private static void dfs(int x, int y, int depth, boolean dig) {
		answer = Math.max(answer, depth);
		visited[x][y] = true;
		int nx, ny;
		for (int i=0; i<4; i++){
			nx = x+dx[i]; ny = y+dy[i];
			if (nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny]) continue;
			if (map[nx][ny] < map[x][y]) {
				dfs(nx, ny, depth + 1, dig);
				continue;
			}
			if (dig) continue;
			for (int k=1; k<K+1; k++) {
				if (map[nx][ny] - k < map[x][y]) {
					map[nx][ny] -= k;
					dfs(nx, ny, depth + 1, true);
					map[nx][ny] += k;
					break;
				}
			}
		}
		visited[x][y] = false;
	}
}
/*
1
4 4
8 3 9 5
4 6 8 5
8 1 5 1
4 9 5 5

 */