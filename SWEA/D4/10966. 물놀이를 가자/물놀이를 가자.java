import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, M;
	static char[][] map;
	static int[][] distance;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/SWEA10966_sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine().trim()); // 테스트케이스 수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 행의 수
			M = Integer.parseInt(st.nextToken()); // 열의 수

			map = new char[N][M];
			distance = new int[N][M];

			Queue<int[]> water = new LinkedList<>();

			for (int row = 0; row < N; row++) {
				String str = br.readLine();
				for (int col = 0; col < M; col++) {
					map[row][col] = str.charAt(col);

					if (map[row][col] == 'W') {
						water.add(new int[] { row, col });
						distance[row][col] = 0;
					} else {
						distance[row][col] = Integer.MAX_VALUE;
					}
				}
			}

			bfs(water);
			int result = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < M; col++) {
					result += distance[row][col];
				}
			}
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(Queue<int[]> water) {

		while (!water.isEmpty()) {
			int[] cur = water.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (isIn(nr, nc) && map[nr][nc] == 'L') {
					if (distance[nr][nc] > distance[cr][cc] + 1) {
						distance[nr][nc] = distance[cr][cc] + 1;
						water.add(new int[] { nr, nc });
					}
				}
			}
		}

	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}

}
