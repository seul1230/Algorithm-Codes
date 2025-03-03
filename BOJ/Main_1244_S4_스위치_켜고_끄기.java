package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1244_S4_스위치_켜고_끄기 {
	static int N, switches[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		switches = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) switches[i] = Integer.parseInt(st.nextToken());

		int t = Integer.parseInt(br.readLine());
		int gender, num;
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			operateSwitch(gender, num);
		}
		for (int j = 0; j < N; j++) {
			System.out.print(switches[j] + " ");
			if ((j + 1) % 20 == 0) System.out.println();
		}
	}

	private static void operateSwitch(int gender, int num) {
		if (gender == 1) {
			for (int i=num; i<=N; i+=num) switches[i-1] ^= 1;
			return;
		}
		switches[--num] ^= 1;
		for (int i=1; i<N; i++) {
			if (0>(num-i) || (num+i)>=N) break;
			if (switches[num+i]!=switches[num-i]) break;
			switches[num + i] ^= 1;
			switches[num - i] ^= 1;
		}
	}
}


/*
8
0 1 0 1 0 0 0 1
2
1 8
2 7
*/