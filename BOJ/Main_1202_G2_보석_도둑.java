package BOJ;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1202_G2_보석_도둑 {
	static int N, K;
	static class Jewerly implements Comparable<Jewerly> {
		int m, v;
		public Jewerly(int m, int v) {
			this.m = m;
			this.v = v;
		}
		@Override
		public int compareTo(Jewerly o) {
			return (this.m==o.m)? o.v-this.v : this.m-o.m;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		long answer = 0;

		Jewerly[] jl = new Jewerly[N];
		int[] bags = new int[K];
		for (int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			jl[i] = new Jewerly(M, V);
		}
		Arrays.sort(jl);

		for (int i=0;i<K;i++){
			bags[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);

		// value priority queue
		PriorityQueue<Integer> valueQ = new PriorityQueue<>(Collections.reverseOrder());
		for (int i=0, idx=0;i<K;i++) {
			// put one jewerly in one bag
			// as high-value as possible
			while (idx<N && jl[idx].m <= bags[i])
				valueQ.offer(jl[idx++].v);
			if (!valueQ.isEmpty())
				answer += valueQ.poll();
		}
		System.out.println(answer);
	}
}
