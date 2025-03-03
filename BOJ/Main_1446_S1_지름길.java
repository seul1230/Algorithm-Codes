package BOJ;

import java.io.*;
import java.util.*;

public class Main_1446_S1_지름길 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		List<List<int[]>> graph = new ArrayList<>();
		for (int i = 0; i <= D; i++) {
			graph.add(new ArrayList<>()); // 노드의 개수만큼 생성
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if (v <= D) { // 노드범위 이상일 경우 제외
				graph.get(u).add(new int[]{v, w}); //
			}
		}

		int[] dist = new int[D + 1]; // 가중치를 기록할 배열
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;

		// 비용 기준으로 오름차순 정렬
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		pq.add(new int[]{0, 0}); // {현재 위치, 현재 비용}

		while (!pq.isEmpty()) {
			int[] current = pq.poll();
			int currentPos = current[0];
			int currentCost = current[1];

			// 이미 더 적은 비용으로 도달한 경로가 존재하는 경우
			if (currentCost > dist[currentPos]) continue;

			// 다음 지점으로 이동
			if (currentPos + 1 <= D && currentCost + 1 < dist[currentPos + 1]) {
				dist[currentPos + 1] = currentCost + 1;
				pq.add(new int[]{currentPos + 1, currentCost + 1});
			}

			// 지름길로 이동
			for (int[] edge : graph.get(currentPos)) {
				int nextPos = edge[0];
				int nextCost = currentCost + edge[1];
				if (nextCost < dist[nextPos]) {
					dist[nextPos] = nextCost;
					pq.add(new int[]{nextPos, nextCost});
				}
			}
		}
		System.out.println(dist[D]);
	}
}
