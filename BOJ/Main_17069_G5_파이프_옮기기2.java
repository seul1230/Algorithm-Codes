package BOJ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
	메모리    14448 kb
	실행시간  116 ms
*/

public class Main_17069_G5_파이프_옮기기2_조예슬 {

	static int N, board[][];
	static long answer, dp[][][];
	static int[] dx = {0,1,1};
	static int[] dy = {1,0,1};
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N+1][N+1];
		dp = new long[N+1][N+1][3];
		answer = 0;
		for (int i=1;i<N+1;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=1;j<N+1;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp[1][2][0] = 1;
		for (int i=1; i<N+1; i++) {
			for (int j=3; j<N+1; j++){
				if (board[i][j]==1) continue;
				dp[i][j][0] = dp[i][j-1][0]+dp[i][j-1][2];
				dp[i][j][1] = dp[i-1][j][1]+dp[i-1][j][2];
				if (board[i-1][j]!=0 || board[i][j-1]!=0) continue;
				dp[i][j][2] = dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2];
			}
		}
		answer = dp[N][N][0]+dp[N][N][1]+dp[N][N][2];
		System.out.println(answer);
	}
}