package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15683_G3_감시 {
	static final int INF = Integer.MAX_VALUE;
	static int N, M, answer, directions[];
	static char board[][], copyMap[][];
	static List<CCTV> cctvList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		cctvList = new ArrayList<>();
		answer = INF;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M;j ++) {
				board[i][j] = st.nextToken().charAt(0);
				if (board[i][j] != '0' && board[i][j] != '6' && board[i][j] != '5')
					cctvList.add(new CCTV(i, j, board[i][j]-'0'));
			}
		}

		// 5번은 회전할 필요 없음
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (board[i][j]=='5') cctv5(i, j);
			}
		}

		directions = new int[cctvList.size()];
		permutation(0);
		System.out.println(answer);
	}

	private static void permutation(int depth) {
		if (depth == cctvList.size()) {
			copyMap = new char[N][M];
			for (int i=0; i<N; i++) copyMap[i] = Arrays.copyOf(board[i], M);
			for (int i=0; i<cctvList.size(); i++) {
				setDirection(cctvList.get(i), directions[i]);
			}
			countBlind();
			return;
		}
		for (int i=0; i<4; i++) {
			if (cctvList.get(depth).no == 5) directions[depth] = 0;
			else directions[depth] = i;
			permutation(depth+1);
		}
	}

	private static void cctv5 (int x, int y) {
		for (int i=0; i<N-x; i++) {
			if (board[x+i][y] == '6') break;
			if (board[x+i][y] == '0') board[x+i][y] = '#';
		}
		for (int i=0; i<=x; i++) {
			if (board[x-i][y] == '6') break;
			if (board[x-i][y] == '0') board[x-i][y] = '#';
		}
		for (int i=0; i<M-y; i++) {
			if (board[x][y+i] == '6') break;
			if (board[x][y+i] == '0') board[x][y+i] = '#';
		}
		for (int i=0; i<=y; i++) {
			if (board[x][y-i] == '6') break;
			if (board[x][y-i] == '0') board[x][y-i] = '#';
		}
	}

	// 상우하좌(시계방향)
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};

	// BFS
	private static void watch(CCTV cctv, int dir) {
		Queue<CCTV> q = new LinkedList<>();
		q.add(cctv);

		while (!q.isEmpty()) {
			CCTV now = q.poll();
			int nx = now.x + dx[dir], ny = now.y + dy[dir];
			if (0>nx || nx>=N || 0>ny || ny>=M || copyMap[nx][ny] == '6') break;
			if (copyMap[nx][ny]=='0') {
				copyMap[nx][ny] = '#';
				q.add(new CCTV(nx, ny, cctv.no));
			} else {
				q.add(new CCTV(nx, ny, cctv.no));
			}
		}
	}

	// 2번은 2 방향만, 1,3,4는 4방향 다 고려
	private static void setDirection(CCTV cctv, int dir) {
		if (cctv.no == 2) {
			if (dir % 2 == 0) {
				watch(cctv, 0); watch(cctv, 2);
			} else {
				watch(cctv, 1); watch(cctv, 3);
			}
		} else if (cctv.no == 1) {
			watch(cctv, dir);
		} else if (cctv.no == 3) {
			watch(cctv, dir);
			watch(cctv, (dir+1)%4);
		} else {
			watch(cctv, dir);
			watch(cctv, (3+dir)%4);
			watch(cctv, (dir+1)%4);
		}
	}
	private static void countBlind() {
		int cnt = 0;
		for (int i=0; i<N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == '0') cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}

	static class CCTV {
		int x, y, no;
		public CCTV(int x, int y, int no) {
			this.x = x;
			this.y = y;
			this.no = no;
		}
	}
}

/*

5 5
0 0 0 0 0
6 2 0 0 4
0 0 0 4 0
5 0 0 5 0
6 0 0 0 0
correct = 3,


3 3
0 0 0
0 4 0
0 0 0

5 2
0 0
0 0
3 0
0 0
6 0

A: 5 W: 6



3 3
0 0 0
0 1 0
0 0 0
ans) 7

3 3
0 0 0
0 2 0
0 0 0
ans)6

3 3
0 0 0
0 3 0
0 0 0
ans)6

3 3
0 0 0
0 4 0
0 0 0
ans)5

3 3
0 0 0
0 5 0
0 0 0
ans)4
 */
