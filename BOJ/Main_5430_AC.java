package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_5430_AC {
	static char orders[];
	static int n;
	static List<Integer> numbers;
	//	static Integer[] numbers;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			orders = br.readLine().toCharArray();
			n = Integer.parseInt(br.readLine());
			if (n<=0) numbers = new LinkedList<>();
			else {
				String tmp = br.readLine();
				numbers = Arrays.asList(Arrays.stream(tmp.substring(1, tmp.length() - 1).split(","))
				  .map(Integer::parseInt)
				  .toArray(Integer[]::new));
			}
//			numbers = Arrays.stream(tmp.substring(1, tmp.length()-1).split(","))
//						  .map(Integer::parseInt)
//						  .toArray(Integer[]::new);
			System.out.println(doFunctions());
		}
	}

	private static String doFunctions() {
		String str = "[";
		for (char c: orders) {
			if (c == 'R') {
				Collections.reverse(numbers);
			} else if (c == 'D') {
//				if (numbers.length == 0) return "error";
				Queue<Integer> q = new LinkedList<>(numbers);
				q.poll();
			}
		}
//		while (!q.isEmpty()) {
//			str += q.poll();
//			if (q.length > 1) str += ",";
//		}
		return str + "]";
	}
}
