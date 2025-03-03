package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_11066_G3_파일_합치기 {
	static int K, files[];
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			K = Integer.parseInt(br.readLine());
			files = Arrays.stream(br.readLine().split(" "))
			  				.mapToInt(Integer::parseInt).toArray();

			sb.append(makeFinalFile(1, K)+"\n");
		}
		System.out.print(sb);
	}

	private static int makeFinalFile(int from, int to) {
		int dp[][] = new int[K+1][K+1];
		int sum[] = new int[K+1];			// 누적합

		// 초기화
		for (int i=1; i<K+1; i++) sum[i] = sum[i-1] + files[i-1];
		// 원소 2개 합칠 때
		for (int i=1; i<K; i++) dp[i][i+1] = files[i] + files[i-1];

		// DP (3개 이상 합칠 때부터)
		for (int k = 2; k < K; k++){
			for (int i = 1; i <= K - k; i++){
				int j = i + k;
				dp[i][j] = INF;
				for (int p = i; p < j; p++)
					dp[i][j] = Math.min(dp[i][j], dp[i][p] + dp[p + 1][j] + sum[j] - sum[i - 1]);
			}
		}

//		for (int i=1; i<K+1; i++) {
//			for (int j=i; j<K+1; j++) {
//				dp[i][j] = INF;
//				for (int k=i; k<j; k++) {
//					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1]);
//				}
//			}
//		}

		for (int i=1; i<K+1; i++)
			System.out.println(Arrays.toString(dp[i]));
		System.out.println();

		return dp[from][to];
	}
}
/*

2
4
40 30 30 50
15
1 21 3 4 5 35 5 4 3 5 98 21 14 17 32

 */