import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int W, H;
	static int[][] grid;
	static int[] dr1 = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dc1 = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dr2 = { -1, 1, 0, 0 };
	static int[] dc2 = { 0, 0, -1, 1 };
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine().trim());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 열의 수
		H = Integer.parseInt(st.nextToken()); // 행의 수

		grid = new int[H][W];
		visited = new boolean[K + 1][H][W];

		for (int row = 0; row < H; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < W; col++) {
				grid[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int result = bfs(0, 0, 0, K);
		System.out.println(result);
	}

	private static int bfs(int x, int y, int cnt, int k) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y, cnt, k });
		visited[k][x][y] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int ccnt = cur[2];
			int ck = cur[3];

			if (cr == H - 1 && cc == W - 1) {
				return ccnt;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr2[d];
				int nc = cc + dc2[d];
				int ncnt = ccnt + 1;
				int nk = ck;

				if (isIn(nr, nc) && grid[nr][nc] == 0 && !visited[nk][nr][nc]) {
					queue.add(new int[] { nr, nc, ncnt, nk });
					visited[nk][nr][nc] = true;
				}
			}

			if (ck > 0) {

				for (int d = 0; d < 8; d++) {
					int nr = cr + dr1[d];
					int nc = cc + dc1[d];
					int ncnt = ccnt + 1;
					int nk = ck - 1;

					if (isIn(nr, nc) && grid[nr][nc] == 0 && !visited[nk][nr][nc]) {
						queue.add(new int[] { nr, nc, ncnt, nk });
						visited[nk][nr][nc] = true;
					}
				}
			}
		}

		return -1;

	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < H && c >= 0 && c < W);
	}

}
