import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_12865_평범한_배낭 {

	static int N, K, answer;
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int dp[] = new int[K+1];
		List<Item> list = new ArrayList<>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.add(new Item(w, v));
		}

		for (int i = 0; i < N; i++) {
			Item now = list.get(i);
			for (int k = K; k - now.w>= 0; k--) dp[k] = Math.max(dp[k], dp[k - now.w] + now.v);
		}
		System.out.println(dp[K]);
	}

	static class Item implements Comparable<Item> {
		int w, v;
		public Item(int w, int v) {
			this.w = w;
			this.v = v;
		}
		@Override
		public int compareTo(Item o) {
			return (this.v == o.v)? Integer.compare(this.w, o.w): Integer.compare(o.v,  this.v);
		}
	}
}
