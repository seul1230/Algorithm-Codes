package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
	메모리     62944kb
	실행시간    432ms
*/

public class Main_12100_2048_Easy_G2 {
	static int N, answer, dirList[];
	static int[][] board, tmp, org;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 0;
		org = new int[N][N];
		board = new int[N][N];
		tmp = new int[N][N];
		StringTokenizer st;
		for (int i=0;i<N;i++){
			org[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		dirList = new int[5];
		getPerm(0);
		System.out.println(answer);
	}

	// 중복순열 d
	private static void getPerm(int depth) {
		if (depth == 5) {
			playGame();
			return;
		}
		for (int i=0;i<4;i++) {
			dirList[depth] = i;
			getPerm(depth+1);
		}
	}

	// 좌우상하
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	private static void playGame() {
		for (int i=0;i<N;i++){
			board[i] = Arrays.copyOfRange(org[i], 0, N);
		}
//		System.out.println("=======");
		for (int dir: dirList) {
			Rotate(dir);
			moveDown();
			reRotate(dir);
		}
//		for (int j=0; j<N; j++){
//			for (int i=N-1; i>=0; i--) {
//				if (board[i][j]==0) break;
//				answer = Math.max(answer, board[i][j]);
//			}
//		}
	}

	// 회전
	private static void Rotate(int dir) {
		if (dir == 3) {
			for (int i=0;i<N;i++){
				for (int j=0;j<N;j++){
					tmp[i][j] = board[N-1-i][N-1-j];
				}
			}
		} else if (dir == 0) {
			for (int i=0;i<N;i++) {
				for (int j=0; j<N; j++) {
					tmp[i][j] = board[j][N-1-i];
				}
			}
		} else if (dir == 1) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					tmp[i][j] = board[N-1-j][i];
				}
			}
		} else {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					tmp[i][j] = board[i][j];
				}
			}
		}
	}
	private static void reRotate(int dir) {
		if (dir == 3) {
			for (int i=0;i<N;i++){
				for (int j=0;j<N;j++){
					board[i][j] = tmp[N-1-i][N-1-j];
				}
			}
		} else if (dir == 0) {
			for (int i=0;i<N;i++) {
				for (int j=0; j<N; j++) {
					board[i][j] = tmp[N-1-j][i];
				}
			}
		} else if (dir == 1) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					board[i][j] = tmp[j][N-1-i];
				}
			}
		} else {
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++){
					board[i][j] = tmp[i][j];
				}
			}
		}
	}

	// 같은 게 하나도 없으면 멈추기
	// 밀기
	private static void moveDown() {
		Queue<Integer> q = new LinkedList<>();
		for (int j=0;j<N;j++) {
			for (int i=N-1;i>=0;i--) {
				if (tmp[i][j]>0) {
					q.add(tmp[i][j]);
					tmp[i][j] = 0;
				}
			}

			int idx = N-1;
			while (!q.isEmpty()) {
				int top = q.poll();
				if (!q.isEmpty() && q.peek()==top) tmp[idx][j] = q.poll() * 2;
				else tmp[idx][j] = top;
				answer = Math.max(answer, tmp[idx][j]);
				idx--;
			}

			q.clear();
		}
	}

	static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
