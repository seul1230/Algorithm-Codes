package BOJ;

import java.io.*;
import java.util.*;

public class Main_10844_S1_쉬운_계단수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int prev = 0; long cnt = 0;
		long[][] dp = new long[101][10];
		Arrays.fill(dp[1], 1);
		for (int i=2; i<=N; i++) {
			dp[i][0] = dp[i-1][1] % 1000000000;
			for (int j=1; j<9; j++) {
				dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
			}
			dp[i][9] = dp[i-1][8] % 1000000000;
		}
		for (int i=1; i<10; i++) cnt += dp[N][i];
		System.out.println(cnt % 1000000000);
	}
}
