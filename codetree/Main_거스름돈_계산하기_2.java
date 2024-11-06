import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_거스름돈_계산하기_2 {
	static int N, S;
	static int[] V, A;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		V = new int[N]; A = new int[N];
		Map<Integer, Integer> map = new HashMap<>();

		int dp[] = new int[S+1];

		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			V[i] = v; A[i] = a;
		}
		for (int i=0; i<S+1; i++) {
			dp[i] = 20000;
		}
		dp[0] = 0;

		for (int i=1; i<S+1; i++) {
			for (int j=0; j<N; j++) {
				int v = V[j], a = A[j];

				dp[i] = Math.min(dp[i], dp[i-i/v*v]+i/v);
			}
		}

		System.out.println(dp[S]>=20000? -1: dp[S]);

	}
}
