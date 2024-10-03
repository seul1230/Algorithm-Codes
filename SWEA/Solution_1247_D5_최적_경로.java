package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1247_D5_최적_경로 {
	static int N, answer;
	static int board[][];
	static int compX, compY, homeX, homeY;
	static Pos prev;
	static Pos[] visitList, homeList;
	static boolean[] isSelected;
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t < T + 1; t++) {
			sb.append("#"+t+" ");
			answer = Integer.MAX_VALUE;
			board = new int[100][100];
			N = Integer.parseInt(br.readLine());
			visitList = new Pos[N+2];
			homeList = new Pos[N+2];
			isSelected = new boolean[N+2];

			StringTokenizer st = new StringTokenizer(br.readLine());
			int compX = Integer.parseInt(st.nextToken());
			int compY = Integer.parseInt(st.nextToken());
			int homeX = Integer.parseInt(st.nextToken());
			int homeY = Integer.parseInt(st.nextToken());

			homeList[0] = new Pos(compX, compY);
			homeList[N+1] = new Pos(homeX, homeY);

			for (int i=1;i<N+1;i++){
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				homeList[i] = new Pos(x, y);
			}

			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}

	private static void permutation(int depth) {
		if (depth == N){
			prev = visitList[0];
			move(0, 0);
			return;
		}
		for (int i=0;i<N;i++){
			if (isSelected[i]) continue;
			isSelected[i] = true;
			visitList[depth] = homeList[i];
			permutation(depth+1);
		}
	}

	// DFS
	private static void move(int depth, int distSum) {
		if (answer < distSum) return;
		if (depth == N+2){
			answer = Math.min(answer, distSum);
			return;
		}
		Pos now = visitList[depth];
		move(depth+1, distSum+getDistance(now));
		prev = now;
	}

	private static int getDistance(Pos now){
		return Math.abs(prev.x-prev.y)+Math.abs(now.x-now.y);
	}
}
