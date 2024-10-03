package SWEA;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_6109_D4_추억의_2048게임_조예슬 {
	static int N, tiles[][], tmpTiles[][], finalTiles[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t=1;t<T+1;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			String direction = st.nextToken();
			tiles = new int[N][N];
			tmpTiles = new int[N][N];
			finalTiles = new int[N][N];
			for (int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<N;j++) {
					tiles[i][j] = tmpTiles[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			rotate(direction);
			moveDown();
			rotateReverse(direction);

			sb.append("#"+t+"\n");
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					sb.append(finalTiles[i][j]+" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	private static void moveDown() {
		Queue<Integer> nums = new LinkedList<>();
		for (int j=0;j<N;j++) {
			int idx = N-1;
			for (int i=N-1;i>=0;i--) {
				if (tmpTiles[i][j] != 0) {
					nums.offer(tmpTiles[i][j]);
					tmpTiles[i][j] = 0;
				}
			}
			//push
			while(!nums.isEmpty()){
				int tmp = nums.poll();
				if(!nums.isEmpty() && nums.peek()==tmp) {
					tmpTiles[idx--][j] = nums.poll()*2;
				}
				else tmpTiles[idx--][j] = tmp;
			}
			nums.clear();
		}
	}

	private static void rotate(String dir) {
		if (dir.equals("up")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmpTiles[i][j] = tiles[N-1-i][N-1-j];
				}
			}

		} else if (dir.equals("left")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmpTiles[i][j] = tiles[j][N-1-i];
				}
			}
		} else if (dir.equals("right")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tmpTiles[i][j] = tiles[N-1-j][i];
				}
			}
		}
	}

	private static void rotateReverse(String dir) {
		if (dir.equals("up")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					finalTiles[i][j] = tmpTiles[N-1-i][N-1-j];
				}
			}

		} else if (dir.equals("left")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					finalTiles[i][j] = tmpTiles[N-1-j][i];
				}
			}
		} else if (dir.equals("right")) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					finalTiles[i][j] = tmpTiles[j][N-1-i];
				}
			}
		} else{
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					finalTiles[i][j] = tmpTiles[i][j];
				}
			}
		}
	}

	private static void swap(int x1, int y1, int x2, int y2) {
		int tmp = tiles[x1][y1];
		tiles[x1][y1] = tiles[x2][y2];
		tiles[x2][y2] = tmp;
	}

}