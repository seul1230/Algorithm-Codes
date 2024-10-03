package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
	메모리    101540 kb
	실행시간   432 ms
 */

public class Solution_1251_D4_하나로_조예슬 {
	static int N;
	static ArrayList<Edge> edges[];
	static double E, answer;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static class Edge implements Comparable<Edge> {
		int next;
		double dist;

		public Edge(int next, double dist) {
			this.next = next;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++){
			sb.append("#"+t+" ");
			N = Integer.parseInt(br.readLine());
			int[] X = new int[N], Y = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) X[j] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) Y[j] = Integer.parseInt(st.nextToken());

			E = Double.parseDouble(br.readLine());
			answer = Integer.MAX_VALUE;
			edges = new ArrayList[N];
			for (int i=0;i<N;i++) edges[i] = new ArrayList<>();

			for (int i=0;i<N-1;i++){
				for (int j=i+1;j<N;j++){
					double tmp = getDistance(X[i], Y[i], X[j], Y[j]);
					edges[i].add(new Edge(j, tmp));
					edges[j].add(new Edge(i, tmp));
				}
			}
			connectIsland();
		}
		System.out.print(sb);
	}
	private static double getDistance(int x1, int y1, int x2, int y2){
		return Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);
	}
	private static void connectIsland() {
		double costSum = 0;
		boolean[] visited = new boolean[N];

		PriorityQueue<Edge> queue = new PriorityQueue<>();
		for (Edge edge: edges[0]) queue.add(edge);
		visited[0] = true;
		int cnt = 1;
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if (visited[edge.next]) continue;
			visited[edge.next] = true;
			cnt ++;
			costSum += E * edge.dist;
			if (cnt == N) {
				sb.append(Math.round(costSum)+"\n");
				return;
			}
			for (Edge e: edges[edge.next]) {
				if (visited[e.next]) continue;
				queue.add(e);
			}
		}
	}
}

//public class Solution_1251_D4_하나로_조예슬 {
//	static int N;
//	static ArrayList<Edge> edges[];
//	static double E, answer;
//	static StringTokenizer st;
//	static StringBuilder sb = new StringBuilder();
//	static class Edge implements Comparable<Edge> {
//		int next;
//		double dist;
//
//		public Edge(int next, double dist) {
//			this.next = next;
//			this.dist = dist;
//		}
//
//		@Override
//		public int compareTo(Edge o) {
//			return Double.compare(this.dist, o.dist);
//		}
//	}
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int T = Integer.parseInt(br.readLine());
//		for (int t=1; t<T+1; t++){
//			sb.append("#"+t+" ");
//			N = Integer.parseInt(br.readLine());
//			int[] X = new int[N], Y = new int[N];
//
//			st = new StringTokenizer(br.readLine());
//			for (int j=0;j<N;j++) X[j] = Integer.parseInt(st.nextToken());
//
//			st = new StringTokenizer(br.readLine());
//			for (int j=0;j<N;j++) Y[j] = Integer.parseInt(st.nextToken());
//
//			E = Double.parseDouble(br.readLine());
//			answer = Integer.MAX_VALUE;
//			edges = new ArrayList[N];
//			for (int i=0;i<N;i++) edges[i] = new ArrayList<>();
//
//			for (int i=0;i<N-1;i++){
//				for (int j=i+1;j<N;j++){
//					double tmp = getDistance(X[i], Y[i], X[j], Y[j]);
//					edges[i].add(new Edge(j, tmp));
//					edges[j].add(new Edge(i, tmp));
//				}
//			}
//			connectIsland();
//		}
//		System.out.print(sb);
//	}
//	private static double getDistance(int x1, int y1, int x2, int y2){
//		return Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2);
//	}
//	private static void connectIsland() {
//		double costSum = 0;
//		boolean[] visited = new boolean[N];
//
//		PriorityQueue<Edge> queue = new PriorityQueue<>();
//		for (Edge edge: edges[0]) queue.add(edge);
//		visited[0] = true;
//		int cnt = 1;
//		while (!queue.isEmpty()) {
//			Edge edge = queue.poll();
//			if (visited[edge.next]) continue;
//			visited[edge.next] = true;
//			cnt ++;
//			costSum += E * edge.dist;
//			if (cnt == N) {
//				sb.append(Math.round(costSum)+"\n");
//				return;
//			}
//			for (Edge e: edges[edge.next]) {
//				if (visited[e.next]) continue;
//				queue.add(e);
//			}
//		}
//	}
//}
