import java.io.*;
import java.util.*;

public class Main {

	static int R, C;
	static int[][] arr;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		C = Integer.parseInt(st.nextToken()); // 행의 수
		R = Integer.parseInt(st.nextToken()); // 열의 수

		arr = new int[R][C];
		Queue<int[]> queue = new LinkedList<>();

		int unRipeTomatoes = 0;
		for (int row = 0; row < R; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < C; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());

				if (arr[row][col] == 1) {
					queue.add(new int[] { row, col, 0 });
				}

				if (arr[row][col] == 0) {
					unRipeTomatoes++;
				}
			}
		}

		if (unRipeTomatoes == 0) {
			System.out.println(0);
			return;
		}

		int result = bfs(queue, unRipeTomatoes);
		System.out.println(result);

	}

	private static int bfs(Queue<int[]> queue, int unRipeTomatoes) {

		int minDay = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int cd = cur[2];

			minDay = cd;

			for (int d = 0; d < 4; d++) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];

				if (isIn(nr, nc) && arr[nr][nc] == 0) {
					queue.add(new int[] { nr, nc, cd + 1 });
					arr[nr][nc] = 1;
					unRipeTomatoes--;
				}
			}
		}
		return (unRipeTomatoes == 0) ? minDay : -1;
	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < R && c >= 0 && c < C);
	}

}