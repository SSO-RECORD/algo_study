import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA14510_Sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim()); // 나무의 개수
			int[] tree = new int[N];
			int maxHeight = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < tree.length; i++) {
				tree[i] = Integer.parseInt(st.nextToken());

				if (maxHeight < tree[i]) {
					maxHeight = tree[i];
				}
			}

			int sum = 0;
			for (int i = 0; i < tree.length; i++) {
				tree[i] = maxHeight - tree[i];
				sum += tree[i];
			}

			if (sum == 0) {
				sb.append("#").append(tc).append(" ").append(0).append("\n");
				continue;
			}

			Arrays.sort(tree);

			int day = 1;
			while (true) {
				for (int i = 0; i < tree.length; i++) {
					if (tree[i] == 0)
						continue;
					if (tree[i] >= 3) {
						if (day % 2 == 1) {
							tree[i] -= 1;
						} else {
							tree[i] -= 2;
						}
						break;
					}

					if (day % 2 == 1 && tree[i] == 1) {
						tree[i] -= 1;
						break;
					}

					if (day % 2 == 0 && tree[i] == 2) {
						tree[i] -= 2;
						break;
					}
				}

				if (isCheck(tree))
					break;
				day++;
			}
			sb.append("#").append(tc).append(" ").append(day).append("\n");
		}
		System.out.print(sb);
	}

	private static boolean isCheck(int[] tree) {
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] != 0)
				return false;
		}
		return true;
	}

}
