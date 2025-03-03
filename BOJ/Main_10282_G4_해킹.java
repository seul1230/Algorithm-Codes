package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_10282_G4_해킹 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int t=0; t<T; t++) {
			int computer = 0, answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			ArrayList<Node> graph[] = new ArrayList[n+1];
			for (int i=0; i<n+1; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				graph[b].add(new Node(a, s));
			}

			boolean[] visited = new boolean[n+1];
			int[] time = new int[n+1];

			for (int i=0; i<n+1; i++) {
				time[i] = Integer.MAX_VALUE;
			}

			time[c] = 0;
			for (int i=0; i<n;i++) {
				int tmpTime = Integer.MAX_VALUE;
				int nodeIdx = 0;
				for (int j=1; j<n+1; j++) {
					if (!visited[j] && time[j] < tmpTime) {
						nodeIdx = j;
						tmpTime = time[j];
					}
				}

				visited[nodeIdx] = true;

				for (int j=0; j<graph[nodeIdx].size(); j++) {
					Node adj = graph[nodeIdx].get(j);
					if (time[adj.no] > time[nodeIdx] + adj.time) {
						time[adj.no] = time[nodeIdx] + adj.time;
					}
				}
			}

			for (int i=1; i<n+1; i++) {
				if (time[i]!= Integer.MAX_VALUE) {
					computer ++;
					answer = Math.max(answer, time[i]);
				}
			}
			sb.append(computer + " " + answer + "\n");
		}
		System.out.print(sb);
	}

	static class Node {
		int no, time;
		public Node(int no, int time) {
			this.no = no;
			this.time = time;
		}
	}

}
