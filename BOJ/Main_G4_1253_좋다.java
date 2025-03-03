package BOJ;

import java.util.*;
import java.io.*;

public class Main_G4_1253_좋다 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Arrays.stream(br.readLine().split(" "))
		  .mapToInt(Integer::parseInt).toArray();
		Arrays.sort(nums);
		int answer = 0;
		for (int i=0; i<N; i++) {
			int start = 0, end = N-1;

			while (start<end) {
				int sum = nums[start] + nums[end];
				if (sum == nums[i]) {
					if (start == i) {
						start ++;
						continue;
					} else if (end == i) {
						end --;
						continue;
					}
					answer ++;
					break;
				} else if (sum < nums[i]) {
					start ++;
				} else {
					end --;
				}
			}
		}
		System.out.println(answer);
	}
}
