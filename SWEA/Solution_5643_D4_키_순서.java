package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5643_D4_키_순서_조예슬 {
	static int N, M, cnt, answer, students[][];//, rstudents[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			students = new int[N+1][N+1];
			answer  = 0;
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				students[a][b] = 1;
			}
			for (int i=1; i<N+1; i++) {
				students[i][0] = -1;
			}
			for (int i=1;i<N+1;i++) {
				if (students[i][0]!=-1) continue;
				checkConnection(i);
			}

			for (int i=1;i<=N;i++) {
				for (int j=1; j<=N; j++){
					students[0][j] += students[i][j];
				}
			}
			for (int k=1; k<=N; k++){
				if (students[k][0] + students[0][k] == N-1) answer ++;
			}
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.println(sb);
	}
	// dfs
	private static void checkConnection(int cur) {
		for (int i=1; i<N+1; i++) {
			if (students[cur][i] == 0) continue;

			// 탐색되지 않은 학생이므로 탐색
			if (students[i][0] == -1) {
				checkConnection(i);
			}

			// 나보다 키가 큰 학생이 탐색을 완료한 상태
			// i보다 키가 큰 학생이 있다면 그 학생들의 정보를 cur에 반영 (간접 관계를 직접 관계로 경로 압축!)
			if (students[i][0] > 0) {
				for (int j=1; j<N+1; j++) {
					if (students[i][j] != 0) students[cur][j] = 1;
				}
			}
		}

		students[cur][0] = 0;
		for (int k=1; k<N+1; k++) {
			students[cur][0] += students[cur][k];
		}
	}

	private static void checkConnection(int cur, int[][] matrix, boolean[] visited) {
		visited[cur] = true;
		for (int i=1; i<N+1; i++) {
			if (visited[i] || matrix[cur][i]==0) continue;
			cnt ++; checkConnection(i, matrix, visited);
		}
	}

	private static void checkConnection1(int cur, boolean[] visited) {
		visited[cur] = true;
		for (int i=1; i<N+1; i++) {
			if (students[cur][i]==0 || visited[i]) continue;
			cnt ++; checkConnection1(i, visited);
		}
	}
	private static void checkConnection2(int cur, boolean[] visited) {
		visited[cur] = true;
		for (int i=1; i<N+1; i++) {
			if (students[i][cur]==0 || visited[i]) continue;
			cnt ++; checkConnection2(i, visited);
		}
	}
	public static int gtBfs(int start) {//자신보다 큰 학생 따라 탐색
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];

		q.offer(start);
		visited[start] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && students[cur][i] != 0) {
					count++;
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		return count;
	}

	public static int ltBfs(int start) {//자신보다 작은 학생 따라 탐색
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];

		q.offer(start);
		visited[start] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && students[i][cur] != 0) {
					count++;
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		return count;
	}
}
