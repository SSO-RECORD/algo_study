import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] adjMatrix;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/SWEA5643_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		// T만큼 반복
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().trim()); // 정점(학생)의 수
			int M = Integer.parseInt(br.readLine().trim()); // 간선의 수

			adjMatrix = new int[N + 1][N + 1];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 키가 더 작은 학생
				int b = Integer.parseInt(st.nextToken()); // 키가 더 큰 학생
				adjMatrix[a][b] = 1;
			}

			int result = 0;
			for (int student = 1; student <= N; student++) {
				if (gtBFS(student) + ltBFS(student) == N - 1) {
					result++;
				}
			}
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.print(sb);
	}

	// 나보다 키가 더 작은 학생의 수
	private static int ltBFS(int start) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1]; // 방문체크를 위한 배열
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[i][cur] != 0) {
					queue.add(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 나보다 키가 더 큰 학생의 수
	public static int gtBFS(int start) {
		int cnt = 0;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[cur][i] != 0) {
					queue.add(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		return cnt;
	}
}
