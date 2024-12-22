import java.io.*;
import java.util.*;

public class Main {

	static int M, N;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 행의 수
		N = Integer.parseInt(st.nextToken()); // 열의 수

		arr = new int[M][N];
		visited = new boolean[M][N];

		for (int row = 0; row < M; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 0; col < N; col++) {
				arr[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;

		for (int row = 0; row < M; row++) {
			for (int col = 0; col < N; col++) {
				if (arr[row][col] == 1 && !visited[row][col]) {
					dfs(row, col);
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	private static void dfs(int r, int c) {

		visited[r][c] = true;

		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isIn(nr, nc) && !visited[nr][nc] && arr[nr][nc] != 0) {
				dfs(nr, nc);
			}
		}

	}

	private static boolean isIn(int r, int c) {
		return (r >= 0 && r < M && c >= 0 && c < N);
	}
}
