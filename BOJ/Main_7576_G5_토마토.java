package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_7576_G5_토마토 {
	static int N, M, answer;
	static int[][] board;
	static List<Tomato> tomatos = new ArrayList<>();
	static boolean visited[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		answer = -1;

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];

		boolean possible = false;
		boolean AllRiped = true;

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j]==1) {
					possible = true;
					tomatos.add(new Tomato(i, j, 0));
					visited[i][j] = true;
				} else if (board[i][j] == 0 && AllRiped)
					AllRiped = false;
				else if (board[i][j] == -1)
					visited[i][j] = true;
			}
		}

		if (possible) {
			answer = bfs();
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (!visited[i][j]) {
						possible = false;
					}
				}
				if (!possible) {
					answer = -1;
					break;
				}
			}
		} else if (AllRiped) {
			System.out.println("--");
			answer = 0;
		}
		System.out.println(answer);
	}

	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};

	private static int bfs() {
		Queue<Tomato> q = new LinkedList<>(tomatos);
		int x, y, nx, ny, date=0;
		while (!q.isEmpty()) {
			Tomato now = q.poll();
			x = now.x; y = now.y; date = now.date;
			for (int i=0; i<4; i++) {
				nx = x+dx[i]; ny = y+dy[i];
				if (nx<0||nx>=N||ny<0||ny>=M) continue;
				if (visited[nx][ny]||board[nx][ny]!=0) continue;
				board[nx][ny] = 1;
				visited[nx][ny] = true;
				q.add(new Tomato(nx, ny, date+1));
			}
		}
		return date;
	}

	static class Tomato {
		int x, y;
		int date;

		public Tomato(int x, int y, int date) {
			this.x = x;
			this.y = y;
			this.date = date;
		}
	}
}
