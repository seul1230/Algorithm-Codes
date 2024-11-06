package SWEA;

import java.io.*;
import java.util.*;

public class Solution_D4_7465_창용마을무리의개수 {
	static int N, M, parents[];
	static void make(){
		parents = new int[N+1];
		for (int i=1;i<=N;i++) parents[i] = i;
	}
	static int find(int a){
		if (parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa==pb) return false;
		parents[pb] = pa;
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t=1;t<T+1;t++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			make();
			int cnt = N;
			for(int i=0;i<M;i++){
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (union(a, b)) cnt --;
			}
			sb.append("#"+t+" "+cnt+"\n");
		}
		System.out.println(sb);
	}
}
