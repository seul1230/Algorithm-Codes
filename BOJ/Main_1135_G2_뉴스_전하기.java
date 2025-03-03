package BOJ;

import java.io.*;
import java.util.*;

public class Main_1135_G2_뉴스_전하기 {

	static List<Integer>[] list;
	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		list = new ArrayList[N];
		int root=0;
		dp = new int[N];
		for(int i=0; i<N; i++) {
			list[i] = new ArrayList<>();
			int a = Integer.parseInt(st.nextToken());
			if(a==-1)  root=i;
			else list[a].add(i);
		}
		int ans = solve(root);
		System.out.println(ans);
	}

	static int solve(int idx) {
		// list[idx]: idx번 사원 아래 부하직원들
		for (int next : list[idx]) {
			dp[next] = 1 + solve(next);
		}
		// 탐색이 오래 걸리는 트리부터 탐색해야 함 <- 탐색이 오래 걸리는 곳부터 전화 돌려야 최적해
		// 깊이 내림차순: 해당 부모까지 오는 데 시간이 더 오래 걸림
		Collections.sort(list[idx], (a, b) -> (dp[b] - dp[a]));
		int res =0;
		for(int i=0; i<list[idx].size(); i++) {
			int num = list[idx].get(i);
			dp[num] += i;
			res = Math.max(res, dp[num]);
		}
		return res;
	}
}
