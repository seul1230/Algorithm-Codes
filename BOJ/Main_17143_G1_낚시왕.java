package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17143_G1_낚시왕_조예슬 {

	static int R, C, M, answer, board[][], afterMove[][];
	static int r, c, s, d, z;
	static List<Shark> sharks;
	static boolean[] isDead;
	static class Shark {
		int r,c,v,dir,size;
		public Shark(int r, int c, int v, int dir, int size) {
			this.r = r;
			this.c = c;
			this.v = v;
			this.dir = dir;
			this.size = size;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new ArrayList<>();
		board = new int[R+1][C+1];
		afterMove = new int[R+1][C+1];
		isDead = new boolean[M];
		answer = 0;
		int idx=1;
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(r, c, s, d, z));
			afterMove[r][c] = idx++;
		}

		for (int i=1;i<C+1;i++) {
			fishing(i); 	// 낚시 시작!
			updateShark(); // update: 상어 이동 & 잡아먹기
		}
		System.out.println(answer);
	}

	// 낚시
	private static void fishing(int idx) {
		for (int j=1;j<R+1;j++) {
			if (afterMove[j][idx] != 0) {
				int tmp = afterMove[j][idx]-1;
				if (isDead[tmp]) continue;
				answer += sharks.get(tmp).size;
				isDead[tmp] = true;
				return;
			}
		}
	}

	// 상어 이동
	private static void updateShark() {
		// 상어 자리 초기화
		for (int i=1;i<R+1;i++) Arrays.fill(afterMove[i], 0);
		// 상어 이동 & 위치 기록
		for (int j=0;j<M;j++) {
			move(j);
			if (isDead[j]) continue;
			int x = sharks.get(j).r, y = sharks.get(j).c;
			int tmp = afterMove[x][y];
			if (tmp!=0) { // 상어가 이미 위치해 있다면 (중복) 잡아먹기
				if (sharks.get(tmp-1).size < sharks.get(j).size) {
					afterMove[x][y] = j+1;
					isDead[tmp-1]=true;
				} else {
					isDead[j] = true;
				}
				continue;
			}
			afterMove[sharks.get(j).r][sharks.get(j).c] = j+1;
		}
	}

	private static void move(int idx) {
		if (isDead[idx]) return;
		int s = sharks.get(idx).v;

		while (s > 0) {
			r = sharks.get(idx).r; c = sharks.get(idx).c;
			d = sharks.get(idx).dir;
			if (d==1) {
				if (r-s >= 1) {
					sharks.get(idx).r = r-s;
					return;
				}
				s -= (r-1);
				sharks.get(idx).dir = 2;
				sharks.get(idx).r = 1;
			} else if (d==2) {
				if (r+s <= R) {
					sharks.get(idx).r = r+s;
					return;
				}
				s -= (R - r);
				sharks.get(idx).dir = 1;
				sharks.get(idx).r = R;
			} else if (d==3) {
				if (c+s <= C) {
					sharks.get(idx).c = c+s;
					return;
				}
				s -= (C - c);
				sharks.get(idx).dir = 4;
				sharks.get(idx).c = C;
			} else {
				if (c-s>=1) {
					sharks.get(idx).c = c-s;
					return;
				}
				s -= (c-1);
				sharks.get(idx).dir = 3;
				sharks.get(idx).c = 1;
			}
		}
	}
}


