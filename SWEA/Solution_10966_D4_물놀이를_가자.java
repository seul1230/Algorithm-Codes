package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_10966_D4_물놀이를_가자 {
	static int N, M, answer;
	static int[][] cnt;
	static char[][] map;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static Queue<Pos> q = new LinkedList<>();
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<T+1;t++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			cnt = new int[N][M];
			answer = 0;
			for (int i=0;i<N;i++) {
				Arrays.fill(cnt[i], Integer.MAX_VALUE);
			}

			visited = new boolean[N][M];
			for (int i=0;i<N;i++){
				map[i] = br.readLine().toCharArray();
				for (int j=0;j<M;j++){
					if (map[i][j]=='W') {
						q.add(new Pos(i, j, 0));
						visited[i][j] = true;
					}
				}
			}
			getDistance();
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.println(sb);
	}

	private static void getDistance() {
		int x, y, nx, ny, dist;
		while (!q.isEmpty()){
			Pos now = q.poll();
			x = now.x; y = now.y; dist = now.dist;
			answer += dist;
			for (int i=0;i<4;i++){
				nx = x+dx[i]; ny = y+dy[i];
				if (nx<0||nx>=N||ny<0||ny>=M||visited[nx][ny]) continue;
				if (map[nx][ny]=='W') continue;
				q.add(new Pos(nx, ny, dist+1));
				visited[nx][ny] = true;
			}
		}
	}
	static class Pos {
		int x, y, dist;
		public Pos(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
