package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_D3_햄버거_다이어트 {
	static int N, L, answer;
	static int[] tastes, calories;
	static int[] isSelected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<T+1;t++){
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			isSelected = new int[N];
			tastes = new int[N];
			calories = new int[N];
			for (int n=0;n<N;n++) {
				st = new StringTokenizer(br.readLine());
				tastes[n] = Integer.parseInt(st.nextToken());
				calories[n] = Integer.parseInt(st.nextToken());
			}
			answer = 0;
			findBestCombi(0,0,0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	public static void findBestCombi(int depth, int totalTaste, int totalCal){
		if (totalCal > L) return;
		if (depth == N) {
			answer = Math.max(answer, totalTaste);
			return;
		}
		findBestCombi(depth+1, totalTaste+tastes[depth], totalCal+calories[depth]);
		findBestCombi(depth+1, totalTaste, totalCal);
	}
}
