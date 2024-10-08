import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visited;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int F = Integer.parseInt(st.nextToken()); // 건물의 총 층수
		int S = Integer.parseInt(st.nextToken()); // 강호가 현재 있는 층수
		int G = Integer.parseInt(st.nextToken()); // 가려고 하는 곳 층수
		int U = Integer.parseInt(st.nextToken()); // 위로 u층
		int D = Integer.parseInt(st.nextToken()); // 아래로 d층

		visited = new boolean[F + 1];
		result = 0;

		bfs(F, S, G, U, D);

		if (result == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(result);
		}

	}

	private static void bfs(int total, int now, int target, int up, int down) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { now, 0 });
		visited[now] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int curFloor = cur[0];
			int curDepth = cur[1];

			if (curFloor == target) {
				result = curDepth;
				return;
			}

			int nextFloor = curFloor + up;
			if (nextFloor <= total && !visited[nextFloor]) {
				queue.add(new int[] { nextFloor, curDepth + 1 });
				visited[nextFloor] = true;
			}

			nextFloor = curFloor - down;
			if (nextFloor >= 1 && !visited[nextFloor]) {
				queue.add(new int[] { nextFloor, curDepth + 1 });
				visited[nextFloor] = true;
			}

		}
		// 목표층에 도달할 수 없는 경우
		result = -1;
	}

}
