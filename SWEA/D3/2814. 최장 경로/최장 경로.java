import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int result = 0;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA2814_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		// T만큼 반복
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점의 개수
			int M = Integer.parseInt(st.nextToken()); // 간선의 개수
			result = 0;
			adjMatrix = new int[N + 1][N + 1];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjMatrix[to][from] = adjMatrix[from][to] = 1;
			}

			for (int n = 1; n < N + 1; n++) {
				visited = new boolean[N + 1];
				visited[n] = true;
				dfs(n, 1);

			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(int n, int depth) {
		result = result < depth ? depth : result;

		for (int i = 1; i < N + 1; i++) {
			if (adjMatrix[n][i] == 0 || visited[i])
				continue;
			visited[i] = true;
			dfs(i, depth + 1);
			visited[i] = false;

		}

	}

}
