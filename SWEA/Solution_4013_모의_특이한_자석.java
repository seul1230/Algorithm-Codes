package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_4013_모의_특이한_자석 {
	static int answer, K, checked;
	static int magnets[][], red[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1; t<T+1; t++) {
			answer = 0;
			magnets = new int[4][8];
			red = new int[4];
			K = Integer.parseInt(br.readLine());
			for (int i=0; i<4; i++) {
				magnets[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			for (int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int no = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				rotate(no-1, dir);
			}
			getScore();
			sb.append("#"+t+" "+answer+"\n");
		}
		System.out.print(sb);
	}

	private static void rotate(int no, int dir) {
		checked = 1<<no;
		move(no, dir, 1);		// move left
		move(no, dir, -1);		// move right
		// rotate all at once
		for (int i=0; i<4; i++) {
			if ((checked & 1<<i)==0) continue;
			if ((no-i)%2==0) red[i] = (red[i]-dir+8)%8;
			else red[i] = (red[i]+dir+8)%8;
		}
	}

	// recursion
	private static void move(int no, int dir, int moveDir) {
		int nextno = no+moveDir;
		if (nextno < 0 || nextno > 3) return;
		// addToCheck: offset to check magnet same or not
		// if clockwise -> addToCheck = 2
		int addToCheck = 2*moveDir;
		int myMagnet = magnets[no][(red[no]+addToCheck+8)%8];
		int nextMagnet = magnets[nextno][(red[nextno]-addToCheck+8)%8];
		// if same -> stop
		if (myMagnet == nextMagnet) return;
		// if different -> move to next
		checked |= 1<<nextno;
		move(nextno, -dir, moveDir);
	}

	private static void getScore() {
		for (int i=0; i<4; i++) {
			if (magnets[i][red[i]]==0) continue;
			answer += (int) Math.pow(2, i);
		}
	}
}

/*
1
2
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1
1 1
3 -1

1
2
1 0 1 0 0 1 0 1
0 0 1 0 1 1 1 1
0 0 1 1 0 0 0 1
0 1 0 1 1 0 0 0
2 -1
1 1

 */