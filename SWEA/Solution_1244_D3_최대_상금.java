package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1244_D3_최대_상금 {
	static int N, max;
	static char[] ca;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1;t<T+1;t++){
			st=new StringTokenizer(br.readLine()," ");
			ca=st.nextToken().toCharArray();
			N=Integer.parseInt(st.nextToken());
			max=0;
			dfs(0,0);
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	// 중복조합
	private static void dfs(int cnt, int start) {
		if (cnt == N) {
			max=Math.max(max,Integer.parseInt(String.valueOf(ca)));
			return;
		}
		for (int i=start; i<ca.length; i++){
			for (int j=i+1; j<ca.length; j++){
				swap(i, j);
				dfs(cnt+1, i);
				swap(i, j);
			}
		}
	}

	private static void swap(int a, int b) {
		char tmp = ca[a];
		ca[a] = ca[b];
		ca[b] = tmp;
	}
}
