import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int r2, c2;
	static int[] dr = { -2, -2, 0, 0, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim()); // 체스판의 크기

		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());

		int result = bfs(r1, c1, 0);

		System.out.println(result);

	}

	private static int bfs(int r, int c, int cnt) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		queue.add(new int[] { r, c, cnt });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int ccnt = cur[2];

			if (cr == r2 && cc == c2) {
				return ccnt;
			}

			for (int d = 0; d < 6; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				int ncnt = ccnt + 1;

				if (isIn(nr, nc) && !visited[nr][nc]) {
					queue.add(new int[] { nr, nc, ncnt });
					visited[nr][nc] = true;
				}
			}
		}

		return -1;

	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}

}
