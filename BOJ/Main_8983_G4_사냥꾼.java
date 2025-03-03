package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_8983_G4_사냥꾼 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		long L = Integer.parseInt(st.nextToken());

		int[] places = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(places);

		Pos animals[] = new Pos[N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			animals[i] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int answer = 0;
		// 동물의 위치로부터 사냥 가능한 사대의 위치를 뽑음
		// mid = 사대 위치
		for (int i=0; i<N; i++) {
			int x = animals[i].x, y = animals[i].y;
			int start = 0, end = M - 1;

			while (start<=end) {
				int mid = (start + end) / 2;
				long dist = Math.abs(places[mid] - x) + y;
				if (dist <= L) {
					answer ++; break;
				}
				// 사정거리 밖에 있으면 사대거리 위치 조정
				if (x < places[mid]) end = mid - 1;
				else start = mid + 1;
			}
		}
		System.out.print(answer);
	}

	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
