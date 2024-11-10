import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
//			System.out.println(sb);
			int N = Integer.parseInt(br.readLine());
			int[] tree = new int[N];
			int limit = 0;
			int sum = 0;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < tree.length; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				limit = Math.max(limit, tree[i]);
			}

			for (int i = 0; i < tree.length; i++) {
				tree[i] = limit - tree[i];
				sum += tree[i];
			}

			if (sum == 0) {
				sb.append(0).append("\n");
				continue;
			}

			Arrays.sort(tree);
			int day = 1;
			while (true) {
				for (int i = 0; i < tree.length; i++) {
                    if(tree[i] == 0) continue;
					if (tree[i] >= 3) {
						if (day % 2 == 1) {
							tree[i] -= 1;
						} else {
							tree[i] -= 2;
						}
						break;
					}
					if (day % 2 == 1 && tree[i] == 1) {
						tree[i] = 0;
						break;
					}
					if (day % 2 == 0 && tree[i] == 2) {
						tree[i] = 0;
						break;
					}
				} // for
				if (check(tree))
					break;
				day++;
			} // while
			sb.append(day).append("\n");
		} // for_tc
		System.out.println(sb);
	}// main

	private static boolean check(int[] tree) {
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] != 0)
				return false;
		}
		return true;
	}
}// class