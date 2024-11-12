import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int R, C;
	static char[][] map;
	static int[][] distance;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA10966_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			distance = new int[R][C];
			Queue<int[]> water = new LinkedList<>();

			for (int row = 0; row < R; row++) {
				String str = br.readLine();
				for (int col = 0; col < C; col++) {
					map[row][col] = str.charAt(col);

					if (map[row][col] == 'W') {
						water.add(new int[] { row, col, 0 });
					} else {
						distance[row][col] = Integer.MAX_VALUE;
					}
				}
			}

			bfs(water);

			int result = 0;
			for (int row = 0; row < R; row++) {
				for (int col = 0; col < C; col++) {
					result += distance[row][col];
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");

		}
		System.out.print(sb);

	}

	private static void bfs(Queue<int[]> water) {

		while (!water.isEmpty()) {

			int[] cur = water.poll();
			int cr = cur[0];
			int cc = cur[1];
			int ct = cur[2];

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				int nt = ct + 1;

				if (isIn(nr, nc) && map[nr][nc] != 'W' && distance[nr][nc] > distance[cr][cc] + 1) {
					distance[nr][nc] = distance[cr][cc] + 1;
					water.add(new int[] { nr, nc, nt });
				}
			}
		}

	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < R && c >= 0 && c < C);
	}

}
