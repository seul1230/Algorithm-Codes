package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2110_G4_공유기_설치 {
	static final int MAX = Integer.MAX_VALUE;
	static int N, C, x[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken()); // 공유기 C개

		x = new int[N]; // 집 좌표
		for (int i=0; i<N; i++) x[i] = Integer.parseInt(br.readLine());
		Arrays.sort(x);

		int answer = 0;
		int start = 0, end = x[N-1] - x[0];

		while (start<=end) {
			// mid = 가장 인접한 두 공유기 사이의 거리
			int mid = (start + end) / 2;

			int prev = 0; int cnt = 1; // 구간으로 cnt 세기 때문에 초기값=1 (집의 개수 = cnt + 1)
			for (int i=1; i<N; i++) {
				int dist = x[i] - x[prev];
				if (dist >= mid) {
					cnt ++; prev = i;
				}
			}
			if (cnt < C) {
				// mid 이상인 집의 개수 늘려야 함 -> mid 줄임
				end = mid - 1;
			} else {
				answer = Math.max(answer, mid);
				start = mid + 1;
			}
		}
		System.out.print(answer);
	}
}
