package BOJ;

import java.io.*;
import java.util.*;

public class Main_2531_S1_회전초밥 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		// 초밥 개수를 저장하는 HashMap
		Map<Integer, Integer> dish = new HashMap<>();
		int maxCount = 0;

		// 초기 k개 설정
		for (int i = 0; i < k; i++) {
			dish.put(sushi[i], dish.getOrDefault(sushi[i], 0) + 1);
		}

		// 쿠폰 초밥 추가
		dish.put(c, dish.getOrDefault(c, 0) + 1);
		maxCount = dish.size();

		// 슬라이딩 윈도우 적용
		for (int i = 0; i < N; i++) {
			int removeSushi = sushi[i];
			int addSushi = sushi[(i + k) % N];

			// 제거
			if (dish.get(removeSushi) == 1) dish.remove(removeSushi);
			else dish.put(removeSushi, dish.get(removeSushi) - 1);

			// 추가
			dish.put(addSushi, dish.getOrDefault(addSushi, 0) + 1);

			// 최대값 갱신
			maxCount = Math.max(maxCount, dish.size());
		}

		System.out.println(maxCount);
	}
}
