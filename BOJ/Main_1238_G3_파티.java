package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1238_G3_파티 {
	static int N, M, X, answer;
	static final int INF = Integer.MAX_VALUE;
	static List<Node> T[], reverseT[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;

		T = new ArrayList[N]; reverseT = new ArrayList[N];
		for (int i=0; i<N; i++) {
			T[i] = new ArrayList<>();
			reverseT[i] = new ArrayList<>();
		}

		int from, to, weight;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken())-1;
			to = Integer.parseInt(st.nextToken())-1;
			weight = Integer.parseInt(st.nextToken());
			T[from].add(new Node(to, weight));
			reverseT[to].add(new Node(from, weight));
		}
		answer = 0;
		int[] go = getMinTime(T);
		int[] come = getMinTime(reverseT);
		for (int i=0; i<N; i++) {
			answer = Math.max(answer, go[i]+come[i]);
		}
		System.out.println(answer);
	}

	private static int[] getMinTime(List<Node>[] roads) {
		int minTime[] = new int[N];
		Arrays.fill(minTime, INF);

		PriorityQueue<Node> q = new PriorityQueue<>();
		boolean[] visited = new boolean[N];

		minTime[X] = 0;
		q.add(new Node(X, minTime[X]));

		while (!q.isEmpty()) {
			Node now = q.poll();
			int v = now.v, w = now.w;
			if (visited[v]) continue;
			visited[v] = true;

			for (Node n: roads[v]) {
				if (minTime[n.v] <=w+n.w) continue;
				minTime[n.v] = w+n.w;
				q.add(new Node(n.v, minTime[n.v]));
			}
		}
		return minTime;
	}

	static class Node implements Comparable<Node> {
		int v, w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
