import java.io.*;
import java.util.*;

public class Main {

	static int w, h;
	static int[][] island;
	static boolean[][] visited;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		w = 0;
		h = 0;

		do {
			st = new StringTokenizer(br.readLine());

			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0)
				break;

			island = new int[h][w];
			visited = new boolean[h][w];

			for (int row = 0; row < h; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 0; col < w; col++) {
					island[row][col] = Integer.parseInt(st.nextToken());
				}
			}

			int cnt = 0;

			for (int row = 0; row < h; row++) {
				for (int col = 0; col < w; col++) {
					if (island[row][col] == 1 && !visited[row][col]) {
						dfs(row, col);
						cnt++;
					}
				}
			}

			sb.append(cnt).append("\n");

		} while (w > 0 && h > 0);

//		while(w>0 && h>0) {
//			st = new StringTokenizer(br.readLine());
//		}

		System.out.print(sb);

	}

	private static void dfs(int r, int c) {

		visited[r][c] = true;

		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc) && !visited[nr][nc] && island[nr][nc] == 1) {
				dfs(nr, nc);
			}
		}

	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < h && c >= 0 && c < w);
	}

}
