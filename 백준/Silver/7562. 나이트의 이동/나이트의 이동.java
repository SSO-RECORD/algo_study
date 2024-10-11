import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int l, targetX, targetY;
	static int[] dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		for (int tc = 1; tc <= T; tc++) {
			l = Integer.parseInt(br.readLine().trim()); // 체스판 한 변의 길이
			st = new StringTokenizer(br.readLine());
			int nowX = Integer.parseInt(st.nextToken());
			int nowY = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			targetX = Integer.parseInt(st.nextToken());
			targetY = Integer.parseInt(st.nextToken());

			visited = new boolean[l][l];

			int result = bfs(nowX, nowY, 0);
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static int bfs(int x, int y, int depth) {
		Queue<int[]> queue = new LinkedList<>();
		visited[x][y] = true;
		queue.add(new int[] { x, y, depth });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cx = cur[0];
			int cy = cur[1];
			int cd = cur[2];

			if (cx == targetX && cy == targetY) {
				return cd;
			}

			for (int d = 0; d < 8; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (isIn(nx, ny) && !visited[nx][ny]) {
					visited[nx][ny] = true;
					queue.add(new int[] { nx, ny, cd + 1 });
				}
			}
		}

		return 0;

	}

	private static boolean isIn(int x, int y) {
		if (x >= 0 && x < l && y >= 0 && y < l)
			return true;
		return false;
	}

}
