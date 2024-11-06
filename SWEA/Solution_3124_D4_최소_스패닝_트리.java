package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3124_D4_최소_스패닝_트리 {
	static int V, E;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int t=1;t<T+1;t++){
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			Edge[] edges = new Edge[E];
			for (int i=0;i<E;i++){
				st = new StringTokenizer(br.readLine());
				edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(edges);
			makeSet();
			int cnt = 0; long cost = 0;
			for (Edge edge: edges) {
				if (union(edge.start, edge.end)) {
					cost += edge.weight;
					if (++cnt == V-1) break;
				}
			}
			sb.append(cost + "\n");
		}
		System.out.println(sb);
	}
	static class Edge implements Comparable<Edge> {
		int start, end, weight;
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static void makeSet() {
		parents = new int[V+1];
		for (int i=1;i<V+1;i++){
			parents[i] = -1;
		}
	}

	private static int findSet(int a) {
		if (parents[a]<0) return a;
		return parents[a] = findSet(parents[a]);
	}

	private static boolean union(int a, int b){
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}