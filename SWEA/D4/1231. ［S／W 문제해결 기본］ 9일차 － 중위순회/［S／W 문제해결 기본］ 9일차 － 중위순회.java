import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[] tree;
	static char[] alpaArr;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA1231_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		// 10만큼 반복
		for (int t = 1; t <= 10; t++) {
			N = Integer.parseInt(br.readLine().trim()); // 노드의 수
			int left = 0;
			int right = 0;
			tree = new int[N * 2 + 2];
			alpaArr = new char[N + 1];
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				int parent = Integer.parseInt(st.nextToken()); // 부모 노드
				char alpa = st.nextToken().charAt(0);

				String leftToken = st.hasMoreTokens() ? st.nextToken() : null; // 왼쪽 자식 존재 확인
				String rightToken = st.hasMoreTokens() ? st.nextToken() : null; // 오른쪽 자식 존재 확인

				if (leftToken != null) {
					left = Integer.parseInt(leftToken); // 왼쪽 자식
				} else {
					left = 0;
				}
				if (rightToken != null) {
					right = Integer.parseInt(rightToken); // 오른쪽 자식
				} else {
					right = 0;
				}

				tree[n] = parent;
				tree[n * 2] = left;
				tree[n * 2 + 1] = right;

				alpaArr[n] = alpa;

			}
			sb.append("#").append(t).append(" ");
			recursion(1);
			sb.append("\n");
		}
		System.out.print(sb);
	}

	private static void recursion(int node) {
		// 기저조건
		if (node > N) { // 노드 번호가 노드 개수보다 커지면
			return;
		}

		// 유도파트
		recursion(node * 2);
		sb.append(alpaArr[node]);
		recursion(node * 2 + 1);

	}

}
