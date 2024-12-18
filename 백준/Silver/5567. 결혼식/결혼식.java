import java.io.*;
import java.util.*;

public class Main {

	static List<Integer>[] relation;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine().trim()); // 동기의 수(상근이 포함)
		int m = Integer.parseInt(br.readLine().trim()); // 리스트의 길이

		relation = new ArrayList[n + 1];
		visited = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			relation[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());

			relation[p1].add(p2);
			relation[p2].add(p1);
		}

		int result = bfs(1, 0);

		System.out.println(result);

	}

	private static int bfs(int start, int depth) {
		int cnt = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;

		int currentDepth = 0; // 현재 탐색 깊이

		while (!queue.isEmpty() && currentDepth < 2) { // depth 2까지만 탐색
			int size = queue.size(); // 현재 깊이의 노드 개수

			for (int i = 0; i < size; i++) {
				int cur = queue.poll();

				for (int friend : relation[cur]) {
					if (!visited[friend]) {
						visited[friend] = true;
						queue.add(friend);
						cnt++; // 초대할 사람 카운트
					}
				}
			}

			currentDepth++; // 깊이 증가
		}

		return cnt;

	}

}
