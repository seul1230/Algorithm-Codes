package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_5656_벽돌_깨기 {
	static int N, W, H, answer;
	static int[][] map, org;
	static int[] numbers; // 구슬 떨어뜨릴 열 저장

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			answer = Integer.MAX_VALUE;
			map = new int[H][W];
			org = new int[H][W];
			numbers = new int[N];

			for (int i=0;i<H;i++){
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<W;j++){
					org[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			permutation(0);
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}

	private static void permutation(int depth){
		if (depth == N) {
			for (int i=0;i<H;i++){
				System.arraycopy(org[i], 0, map[i], 0, W);
			}
			drop(numbers);
			answer = Math.min(answer, countBlock());
			return;
		}
		for (int i=0;i<W;i++) {
			numbers[depth] = i;
			permutation(depth+1);
		}
	}

	private static void drop(int[] nums){
		for (int i=0;i<N;i++){
			for (int j=0;j<H;j++){
				if (map[j][nums[i]]!=0) {
					search(j, nums[i]);
					adjustBlocks();
					break;
				}
			}
		}
	}

	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	private static void search(int x, int y){
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x,y,map[x][y]});
		map[x][y] = 0;

		while (!queue.isEmpty()){
			int[] now = queue.poll();
			int power = now[2];
			for (int i=1;i<power;i++){
				for (int j=0;j<4;j++){
					int nx = now[0] + dx[j]*i;
					int ny = now[1] + dy[j]*i;
					if (nx<0 || nx>=H || ny<0 || ny>=W)  continue;
					if (map[nx][ny]!=0) {
						queue.add(new int[] {nx,ny,map[nx][ny]});
						map[nx][ny] = 0;
					}
				}
			}
		}
	}

	private static void adjustBlocks(){
		Stack<Integer> stack = new Stack<>();
		for (int j=0;j<W;j++){
			for (int i=0;i<H;i++){
				if (map[i][j]!=0) stack.add(map[i][j]);
			}
			for (int i=H-1;i>=0;i--){
				if (!stack.isEmpty()) map[i][j] = stack.pop();
				else map[i][j] = 0;
			}
		}
	}

	private static int countBlock(){
		int cnt = 0;
		for (int i=0;i<H;i++){
			for (int j=0;j<W;j++){
				if (map[i][j]!=0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}