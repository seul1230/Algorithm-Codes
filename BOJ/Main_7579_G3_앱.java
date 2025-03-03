package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7579_G3_앱 {
	static final long INF = Long.MAX_VALUE;
	static int N, M, costs[];
	static long answer, memories[], dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = INF; dp = new long[N][10001];
		memories = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		costs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int i=0; i<N; i++) {
			for (int j=0; j<10001; j++) {
				if (i==0) {
					if (costs[i] <= j) dp[0][j] = memories[i];
				} else{
					if (costs[i]>j) dp[i][j] = dp[i-1][j];
					else dp[i][j] = Math.max(dp[i-1][j-costs[i]] + memories[i], dp[i-1][j]);
				}
				if (dp[i][j] >= M) answer = Math.min(answer, j);
			}
		}
		System.out.println(answer);
	}
}
