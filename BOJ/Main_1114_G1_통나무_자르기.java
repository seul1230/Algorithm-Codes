package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1114_G1_통나무_자르기 {
	static int L, K, C, maxPc, possible[], pieces[];
	static int maxLen, cutStart;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		possible = new int[K+2]; pieces = new int[K+1];
		possible[K+1] = L;
		for (int i=1; i<K+1; i++) possible[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(possible);
		for (int i=0; i<K+1; i++) pieces[i] = possible[i+1] - possible[i];

		maxPc = 0;
		for (int piece : pieces) {
			maxPc = Math.max(maxPc, piece); // 가장 긴 조각 길이
		}


		maxLen = L; cutStart = 0;
		int start = 0, end = L;
		while (start<=end) {
			// mid = 가장 긴 조각 길이
			int mid = (start + end) / 2;
			int[] now = calc(mid);

			if (now[0] <= C) {
				maxLen = mid; cutStart = now[1];
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(maxLen + " " + cutStart);
	}

	private static int[] calc(int mid) {
		if (maxPc > mid) return new int[]{C+1, 0};
		int logLen = 0, cnt = 0;
		int firstCut = L;
		for (int i=K; i>=0; i--) {
			logLen += pieces[i];
			// mid = 가장 긴 조각 길이 -> 넘어가는 경우 = 잘라야 함
			if (logLen > mid) {
				logLen = pieces[i];
				firstCut = possible[i+1];
				cnt ++;
			}
		}
		// 처음 자르는 위치를 최소로 해야 함
		// C번 이하로 잘랐을 때, 처음 자르는 위치는 pieces[0]
		// C번 최대로 잘랐을 때, 처음 자르는 위치는 logLen
		/*
			10 4 2
			1 4 5 10
			-> 조각의 최대 길이가 최소가 되려면 5, 자를 수 있는 위치는 (1,5)(4,5)
			=> 자르는 위치: (1,5)

			10 4 2
			5 6 7 10
			-> 조각의 최대 길이가 최소가 되려면 5, 자를 수 있는 위치는 (5,6)(5,7)(5,10)
			=> 자르는 위치: (5, ..)
		 */

		if (cnt < C) firstCut = possible[1];
		return new int[]{cnt, firstCut};
	}
}
/*
10 4 2
1 4 5 10
ans: 5 1

10 4 3
1 2 3 9
ans: 6 1

5 2 1
1 2
ans: 3 2

5 1 1
5
ans: 5 5

10 4 2
9 8 2 1
ans : 6 2

9 9 2
1 2 3 4 5 6 7 8 9
ans: 3 3

 */