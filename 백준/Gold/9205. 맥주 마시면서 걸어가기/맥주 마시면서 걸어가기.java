import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static List<Point> store;
	static StringBuilder sb;

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수

		for (int tc = 1; tc <= T; tc++) {
			n = Integer.parseInt(br.readLine().trim()); // 편의점의 개수

			st = new StringTokenizer(br.readLine());
			int homeX = Integer.parseInt(st.nextToken());
			int homeY = Integer.parseInt(st.nextToken());

			store = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int storeX = Integer.parseInt(st.nextToken());
				int storeY = Integer.parseInt(st.nextToken());
				store.add(new Point(storeX, storeY));
			}

			st = new StringTokenizer(br.readLine());
			int festX = Integer.parseInt(st.nextToken());
			int festY = Integer.parseInt(st.nextToken());

			bfs(homeX, homeY, festX, festY);
		}

		System.out.print(sb);

	}

	private static void bfs(int homeX, int homeY, int festX, int festY) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { homeX, homeY });
		boolean[] visited = new boolean[n];

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cx = cur[0];
			int cy = cur[1];

			if (Math.abs(cx - festX) + Math.abs(cy - festY) <= 1000) {
				sb.append("happy").append("\n");
//				System.out.println("happy");
				return;
			}

			for (int i = 0; i < n; i++) {
				if (!visited[i]) {
					Point next = store.get(i);

					if (Math.abs(cx - next.x) + Math.abs(cy - next.y) <= 1000) {
						visited[i] = true;
						queue.add(new int[] { next.x, next.y });
					}
				}
			}
		}
		sb.append("sad").append("\n");
//		System.out.println("sad");
		return;
	}

}
