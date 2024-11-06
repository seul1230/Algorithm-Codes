package BOJ;

import java.io.*;
import java.util.*;

public class Main_14852_G4_타일_채우기_3_ {
	public static void main(String[] args) throws Exception	 {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] dp = new long[1000001];
		long[] sum = new long[1000001];
		dp[0] = 1; dp[1] = 2; dp[2] = 7;
		sum[0] = 1; sum[1] = 3; sum[2] = 10;
		for (int i=3; i<=N; i++) {
			dp[i] = (2 * dp[i-1] + 3 * dp[i-2] + 2 * sum[i-3]) % 1000000007;
			sum[i] = (sum[i-1] + dp[i]) % 1000000007;
		}
		System.out.println(dp[N]);
	}
}
