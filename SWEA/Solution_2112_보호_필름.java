package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_2112_보호_필름_조예슬 {
	static int D, W, K, answer;
	static int[][] film, org;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1;t<T+1;t++){
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			film = new int[D][W];
			org = new int[D][W];
			isSelected = new boolean[D];
			answer = Integer.MAX_VALUE;
			for (int i=0;i<D;i++){
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<W;j++){
					org[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if (check(org)) answer = 0;
			else {
				getSubset(0);
			}
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}

	private static void getSubset(int depth){
		if (depth == D){
			for (int i=0;i<D;i++){
				System.arraycopy(org[i], 0, film[i], 0, W);
			}
			insertChemical(0,0);
			return;
		}
		isSelected[depth] = true;
		getSubset(depth+1);
		isSelected[depth] = false;
		getSubset(depth+1);
	}

	private static void insertChemical(int depth, int insertCnt){
		if (insertCnt >= answer) return;
		if (depth == D){
			if (check(film))
				answer = Math.min(answer, insertCnt);
			return;
		}
		// 약품 투여
		if (isSelected[depth]){
			Arrays.fill(film[depth], 0);
			insertChemical(depth+1, insertCnt+1);
			Arrays.fill(film[depth], 1);
			insertChemical(depth+1, insertCnt+1);
		} else {
			insertChemical(depth+1, insertCnt);
		}
	}

	private static boolean check(int[][] org){
		for (int j=0;j<W;j++){
			int cnt = 0;
			int now = 0;
			boolean possible = false;
			for (int i=0;i<D;i++){
				if (org[i][j]!=now){
					now = org[i][j];
					cnt = 0;
				}
				cnt ++;
				if (cnt == K){
					possible = true;
					break;
				}
			}
			if (!possible) return false;
		}
		return true;
	}
}
