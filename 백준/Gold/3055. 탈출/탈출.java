import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] map;
	static int[][] water;
	static boolean[][] visited;
	static boolean[][] visitedWater;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 행의 수
		C = Integer.parseInt(st.nextToken()); // 열의 수

		map = new char[R][C];
		water = new int[R][C];
		visited = new boolean[R][C];
		visitedWater = new boolean[R][C];
		int startR = 0, startC = 0;
		int startWR = 0, startWC = 0;

		for (int row = 0; row < R; row++) {
			String str = br.readLine();
			for (int col = 0; col < C; col++) {
				map[row][col] = str.charAt(col);

				if (map[row][col] == 'S') {
					startR = row;
					startC = col;
				}
//				if(map[row][col] == '*') {
//					water[row][col] = 1;
//					startWR = row;
//					startWC = col;
//				}
//				if(map[row][col] == 'D') {
//					water[row][col] = -1;
//				}

			}
		}

		// bfs_water(startWR, startWC, 0);
		bfs_water(0);
		int result = bfs(startR, startC, 0);

		if (result == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(result);
		}
//		for(int row=0; row<R; row++) {
//			for(int col=0; col<C; col++) {
//				System.out.print(water[row][col]);
//			}
//			System.out.println();
//		}

	}

	private static void bfs_water(int time) {

		Queue<int[]> queue = new LinkedList<>();
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (map[row][col] == '*') {
					queue.add(new int[] { row, col, 0 });
					water[row][col] = 1;
				}
			}
		}
		// water[wr][wc] = 1;
		// visitedWater[wr][wc] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cwr = cur[0];
			int cwc = cur[1];
			int ct = cur[2];

			for (int d = 0; d < 4; d++) {
				int nwr = cwr + dr[d];
				int nwc = cwc + dc[d];
				int nct = ct + 1;

				if (isIn(nwr, nwc) && water[nwr][nwc] == 0 && map[nwr][nwc] != 'X' && map[nwr][nwc] != 'D') {
					queue.add(new int[] { nwr, nwc, nct });
					water[nwr][nwc] = nct;
					// visited[nwr][nwc] = true;
				}
			}
		}
	}

	private static int bfs(int r, int c, int time) {

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { r, c, time });
		visited[r][c] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0]; // 현재 행 위치
			int cc = cur[1]; // 현재 열 위치
			int ct = cur[2]; // 현재 시간초(몇초가 지났는지)

			if (map[cr][cc] == 'D') {
				return ct;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				int nt = ct + 1;

				if (isIn(nr, nc) && !visited[nr][nc] && (water[nr][nc] == 0 || nt < water[nr][nc])
						&& map[nr][nc] != 'X') {
					queue.add(new int[] { nr, nc, nt });
					visited[nr][nc] = true;
				}
			}
		}
		return -1;

	}

	private static boolean isIn(int r, int c) {
		if (r >= 0 && r < R && c >= 0 && c < C)
			return true;
		return false;
	}

}
