package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236_아기_상어 {

	static int N, M, fish, tmp;
	static int[][] board;
	static Fish shark;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		fish = 0;
		board = new int[N][N];

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j]==9) {
					shark = new Fish(i, j, 0);
					board[i][j] = 0;
				} else if (board[i][j]>0) fish++;
			}
		}
		eatFish();
		System.out.println(shark.time);
	}


	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};


	private static void eatFish() {
		int time = 0, eat = 0, size = 2;
		while (true) {
			PriorityQueue<Fish> q = new PriorityQueue<>();
			boolean visited[][] = new boolean[N][N];
			boolean noMore = true;

			q.add(shark);
			visited[shark.x][shark.y] = true;

			int x, y, nx, ny;
			while (!q.isEmpty()) {
				Fish now = q.poll();
				x = now.x; y = now.y;
				time = now.time;

				if (0<board[x][y] && board[x][y]<size)  {
					eat ++; board[x][y] = 0;
					shark = new Fish(x, y, time);
					noMore = false;
					break;
				}

				for (int i=0; i<4; i++) {
					nx = x+dx[i]; ny = y+dy[i];
					if (nx<0||nx>=N||ny<0||ny>=N||visited[nx][ny]) continue;
					if (board[nx][ny]>size) continue;
					q.add(new Fish(nx, ny, time+1));
					visited[nx][ny] = true;
				}
			}
			if (noMore) return;
			if (eat == size) {
				size ++;
				eat = 0;
			}
		}
	}

	static class Fish implements Comparable<Fish> {
		int x, y;
		int time;

		public Fish(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Fish o) {
			return (this.time != o.time)? Integer.compare(this.time, o.time):((this.x == o.x)? Integer.compare(this.y, o.y):Integer.compare(this.x, o.x));
		}

		@Override
		public String toString() {
			return "Fish [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
	}
}