package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
	메모리       18,636 kb
	실행시간      119 ms

 */

public class Solution_5658_모의_보물상자_비밀번호_조예슬 {
	static int N, K;
	static List<Character> nums;
	static TreeSet<Integer> set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String tmp = br.readLine();
			set  = new TreeSet<>();
			nums = new ArrayList<>();
			for (int i=0; i<N; i++) {
				nums.add(tmp.charAt(i));
			}
			for (int i=0; i<=N/4; i++){
				createNum();
				Collections.rotate(nums, 1);
			}
			List<Integer> decNums  = new ArrayList<>(set);
			Collections.sort(decNums, Collections.reverseOrder());
			sb.append("#"+t+" "+decNums.get(K-1)+"\n");
		}
		System.out.print(sb);
	}

	private static void createNum() {
		int num = 0;
		for (int i=0; i<N; i+=N/4) {
			num = 0;
			for (int j=0; j<N/4; j++){
				num *= 16;
				num += hexToDec(nums.get(i+j));
			}
			set.add(num);
		}
	}

	private static int hexToDec(char str) {
		if ('0'<=str && str<='9') return str-'0';
		return 10 + str - 'A';
	}
}
