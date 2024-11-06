package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	메모리    14880kb
	실행시간  284ms
*/

public class Main_17070_G5_파이프_옮기기1 {

	static int N, answer, board[][];
	static int[] dx = {0,1,1};
	static int[] dy = {1,0,1};
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		answer = 0;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,1,0);
		System.out.println(answer);
	}
	private static void dfs(int x, int y, int dir) {
		// dir: 현재 파이프 방향
		// -> 0: 가로, 1: 세로, 2: 대각선
		if (x==N-1 && y==N-1) {
			answer ++;
			return;
		}
		int nx, ny, cnt = 0;
		for (int i=0;i<3;i++) {
			// 대각선 방향으로 못 가는 경우
			if (i==2 && cnt>0) continue;
			nx = x+dx[i]; ny = y+dy[i];
			if (nx<0||nx>=N||ny<0|ny>=N) continue;
			if (board[nx][ny]==1) {
				cnt++;
				continue;
			}
			// 가로, 세로 방향일 때 처리
			if (dir<2 && i<2 && i!=dir) continue;
			dfs(nx, ny, i);
		}
	}
}