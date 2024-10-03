package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_14002_G4_가장_긴_증가하는_부분_수열_4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		List<Integer> list = new ArrayList<>();
		int[] idx = new int[N];

		for (int i=0; i<N; i++) {
			int pos = Collections.binarySearch(list, A[i]);
			if (pos < 0)
				pos = - (pos + 1);
			idx[i] = pos;
			if (pos == list.size()) list.add(A[i]);
			else list.set(pos, A[i]);
		}
		System.out.println(list.size());
		int now = list.size();
		int[] ans = new int[now];

		for (int i=N-1, x=list.size()-1; i>=0; i--) {
			if (now == idx[i]+1) {
				ans[x--] = A[i];
				now --;
			}
		}
		for (int a: ans) sb.append(a +" ");
		System.out.println(sb);
	}
}