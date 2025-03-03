package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2352_G2_반도체_설계 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ports[] = new int[N];
		for (int i=0; i<N; i++) ports[i] = Integer.parseInt(st.nextToken());

		List<Integer> dp = new ArrayList<>();
		for (int i=0; i<N; i++) {
			int pos = Collections.binarySearch(dp, ports[i]);
			if (pos < 0) pos = -(pos + 1);
			if (pos == dp.size()) dp.add(ports[i]);
			else dp.set(pos, ports[i]);
		}
		System.out.println(dp.size());
	}
}
