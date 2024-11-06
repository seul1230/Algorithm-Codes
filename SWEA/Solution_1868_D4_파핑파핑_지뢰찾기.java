package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 /*
    메모리 40,980 kb
    실행시간 226ms
 */
public class Solution_1868_D4_파핑파핑_지뢰찾기 {

	static int N, answer;
	static char[][] board;
	static boolean[][] visited;
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t < T + 1; t++) {
			sb.append("#"+t+" ");
			N = Integer.parseInt(br.readLine());
			board = new char[N][N];
			answer = 0;
			for (int i=0;i<N;i++){
				board[i] = br.readLine().toCharArray();
			}

			// 근처 지뢰 세기
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					if (board[i][j]=='.') countBomb(i, j);
				}
			}
			// 0으로부터 연쇄 폭발 처리 ('0' 개수 = 최소 클릭 횟수)
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					if (board[i][j] == '0'){
						answer ++;
						bfs(i, j);
					}
				}
			}
			// 추가로 눌러줘야 하는 곳
			for (int i=0;i<N;i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j]!='*') answer ++;
				}
			}
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}

	static int dx[] = {-1,0,1};
	private static void countBomb(int x, int y){
		int cnt = 0;
		for (int i=0;i<3;i++){
			for (int j=0;j<3;j++){
				int nx = x+dx[i], ny = y+dx[j];
				if (nx<0||nx>=N||ny<0||ny>=N) continue;
				if (board[nx][ny]=='*') cnt ++;
			}
		}
		board[x][y] = (char) (cnt+'0');
	}

	// BFS: 연쇄폭발 표시
	private static void bfs(int x, int y){
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(x, y));
		while (!queue.isEmpty()){
			Pos now = queue.poll();
			x = now.x; y = now.y;
			for (int i=0;i<3;i++){
				for (int j=0;j<3;j++) {
					int nx = x + dx[i], ny = y + dx[j];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == '*') continue;
					if (board[nx][ny] == '0') {
						board[nx][ny] = '*';
						queue.offer(new Pos(nx, ny));
					} else board[nx][ny] = '*';
				}
			}
		}
	}
}
