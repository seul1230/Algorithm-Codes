package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11505_G1_구간_곱_구하기 {
	static int N, M, K;
	static long nums[];

	static class SegmentTree {
		long tree[];
		int treeSize;
		final long MOD = 1_000_000_007;

		public SegmentTree(int arrSize) {
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h + 1);
			tree = new long[treeSize];
		}

		public long init(long[] arr, int node, int start, int end) {
			if (start == end) return tree[node] = arr[start];
			int mid = (start + end) / 2;
			return tree[node] = (init(arr, node * 2, start, mid) *
			  init(arr, node * 2 + 1, mid + 1, end)) % MOD;
		}

		public long update(int node, int start, int end, int idx, long value) {
			if (idx < start || idx > end) return tree[node];
			if (start == end) return tree[node] = value;
			int mid = (start + end) / 2;
			return tree[node] = (update(node * 2, start, mid, idx, value) *
			  update(node * 2 + 1, mid + 1, end, idx, value)) % MOD;
		}

		public long getMultiple(int node, int start, int end, int left, int right) {
			if (right < start || left > end) return 1; // 범위를 벗어난 경우
			if (left <= start && end <= right) return tree[node]; // 완전히 포함된 경우
			int mid = (start + end) / 2;
			return (getMultiple(node * 2, start, mid, left, right) *
			  getMultiple(node * 2 + 1, mid + 1, end, left, right)) % MOD;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}

		SegmentTree stree = new SegmentTree(N);
		stree.init(nums, 1, 1, N);

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if (a == 1) {
				stree.update(1, 1, N, b, c);
			} else if (a == 2) {
				long result = stree.getMultiple(1, 1, N, b, (int) c);
				sb.append(result).append("\n");
			}
		}
		System.out.print(sb);
	}
}
