package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Solution_2115_벌꿀채취_조예슬 {
	static int N, M, C, answer, tmpMax;
	static int[][] hive;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1;t<T+1;t++){
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			answer = 0; // 수익의 합 (최대)
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			hive = new int[N][N];
			for (int i=0;i<N;i++){
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<N;j++){
					hive[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			getCombi();
			sb.append(answer+"\n");
		}
		System.out.println(sb);
	}

	private static void getCombi(){
		int maxSum = 0;
		for (int i=0;i<N;i++){
			int sum = 0, max1 = 0, max2 = 0;
			for (int j=0;j<N-M+1;j++){
				tmpMax = 0;
				getHoney(i,j,0,0,0);
				max1 = Math.max(max1,tmpMax);

				tmpMax = 0;
				// 해당 줄에서 2개 뽑는 경우
				for (int k=j+M;k<N-M+1;k++){
					getHoney(i,k,0,0,0);
				}

				// 2개 줄에서 1개씩 뽑는 경우
				for (int k=i+1;k<N;k++){
					for (int m=0;m<N-M+1;m++){
						getHoney(k,m,0,0,0);
					}
				}
				max2 = tmpMax;
			}
			answer = Math.max(answer, max1+max2);
		}
		return;
	}

	private static void getHoney(int x, int y, int cnt, int HoneySum, int profitSum){
		// subset
		if (HoneySum > C) return;
		if (cnt == M) {
			tmpMax = Math.max(tmpMax, profitSum);
			return;
		}
		getHoney(x, y+1, cnt+1, HoneySum + hive[x][y], profitSum + hive[x][y]*hive[x][y]);
		getHoney(x, y+1, cnt+1, HoneySum, profitSum);
	}
}
