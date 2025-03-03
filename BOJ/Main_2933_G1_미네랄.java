package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2933_G1_미네랄 {
	static int C, R, N, heights[];
	static boolean visited[][];
	static char cave[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cave = new char[R][];
		for (int i=0; i<R; i++) cave[i] = br.readLine().trim().toCharArray();
		N = Integer.parseInt(br.readLine());
		heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int i=0; i<N; i++) {
			if (!throwBar(i, R-heights[i])) continue;
			visited = new boolean[R][C];
			loop:
			for (int j=0; j<R; j++) {
				for (int k=0; k<C; k++) {
					if (cave[j][k]=='x' && !visited[j][k]) {
						if (findCluster(j, k)) break loop;
					}
				}
			}
		}

		for (int i=0; i<R; i++) {
			for (int j=0; j<C; j++) System.out.print(cave[i][j]);
			System.out.println();
		}
	}

	private static boolean throwBar(int dir, int height) {
		if (dir%2==0) { // 왼쪽에서 오른쪽
			for (int i=0; i<C; i++) {
				if (cave[height][i]!='.') {
					cave[height][i] = '.';
					return true;
				}
			}
		} else { 		// 오른쪽에서 왼쪽
			for (int i=C-1; i>=0; i--) {
				if (cave[height][i]!='.') {
					cave[height][i] = '.';
					return true;
				}
			}
		}
		return false;
	}

	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	private static boolean findCluster(int x, int y) {
		Queue<Pos> q = new LinkedList<>();
		List<Pos> clustPos = new ArrayList<>();
		q.add(new Pos(x, y));
		visited[x][y] = true;

		int bottom = 0, nx, ny;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			bottom = Math.max(bottom, now.x);
			for (int i=0; i<4; i++) {
				nx = now.x+dx[i]; ny = now.y+dy[i];
				if (nx<0 || nx>=R || ny<0 || ny>=C) continue;
				if (!visited[nx][ny] && cave[nx][ny]=='x') {
					q.add(new Pos(nx, ny));
					visited[nx][ny] = true;
				}
			}
			clustPos.add(now);
		}

		// 공중에 떠 있는 클러스터 발견!
		if (bottom != R-1) {
			drop(clustPos);
			return true;
		}
		return false;
	}

	private static void drop(List<Pos> elements) {
		int dropHeight = 1;
		for (Pos pos : elements) cave[pos.x][pos.y] = '.';
		loop:
		while (true) {
			for (Pos pos: elements) {
				if (pos.x + dropHeight == R || cave[pos.x+dropHeight][pos.y]=='x') {
					dropHeight --;
					break loop;
				}
			}
			dropHeight ++;
		}
		for (Pos pos : elements) cave[pos.x+dropHeight][pos.y] = 'x';
	}

	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
